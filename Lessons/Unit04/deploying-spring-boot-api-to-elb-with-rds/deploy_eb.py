import boto3
from datetime import datetime
import os
import time

# Configuration
APPLICATION_NAME = "student-management-app"
ENVIRONMENT_NAME = "student-env-final"
SOLUTION_STACK = "64bit Amazon Linux 2023 v4.6.5 running Corretto 21"
REGION = "us-east-1"
S3_BUCKET = "elasticbeanstalk-us-east-1-571336506993"
JAR_PATH = "target/demo-0.0.1-SNAPSHOT.jar"

# Database configuration
RDS_HOSTNAME = "student-app-db.citosm2cot6e.us-east-1.rds.amazonaws.com"
RDS_PASSWORD = "newpassword123"

def main():
    # Initialize AWS clients
    eb_client = boto3.client('elasticbeanstalk', region_name=REGION)
    s3_client = boto3.client('s3', region_name=REGION)

    try:
        # Step 1: Upload JAR to S3
        print("Uploading JAR to S3...")
        jar_key = upload_jar_to_s3(s3_client)

        # Step 2: Create application if it doesn't exist
        print("Creating/checking application...")
        ensure_application_exists(eb_client)

        # Step 3: Create application version
        print("Creating application version...")
        version_label = create_application_version(eb_client, jar_key)

        # Step 4: Create environment
        print("Creating environment...")
        environment_id = create_environment(eb_client, version_label)

        # Step 5: Wait for environment to be ready
        print("Waiting for environment to be ready...")
        wait_for_environment(eb_client, environment_id)

        print(f"Deployment successful! Environment URL: http://{ENVIRONMENT_NAME}.eba-[random].{REGION}.elasticbeanstalk.com")

    except Exception as e:
        print(f"Deployment failed: {str(e)}")
        raise

def upload_jar_to_s3(s3_client):
    """Upload JAR file to S3 bucket"""
    if not os.path.exists(JAR_PATH):
        raise FileNotFoundError(f"JAR file not found: {JAR_PATH}")

    timestamp = datetime.now().strftime("%Y%m%d-%H%M%S")
    jar_key = f"applications/{APPLICATION_NAME}/demo-{timestamp}.jar"

    s3_client.upload_file(JAR_PATH, S3_BUCKET, jar_key)
    print(f"JAR uploaded to S3: s3://{S3_BUCKET}/{jar_key}")

    return jar_key

def ensure_application_exists(eb_client):
    """Create application if it doesn't exist"""
    try:
        response = eb_client.describe_applications(ApplicationNames=[APPLICATION_NAME])
        if response['Applications']:
            print(f"Application {APPLICATION_NAME} already exists")
            return
    except eb_client.exceptions.ClientError as e:
        if 'does not exist' in str(e).lower():
            print(f"Application {APPLICATION_NAME} does not exist, creating...")
        else:
            print(f"Error checking application: {e}")

    # Create the application
    try:
        eb_client.create_application(
            ApplicationName=APPLICATION_NAME,
            Description="Student Management System deployed via Python SDK"
        )
        print(f"Created application: {APPLICATION_NAME}")

        # Wait a moment for the application to be fully created
        time.sleep(5)

    except eb_client.exceptions.ClientError as e:
        if 'already exists' in str(e).lower():
            print(f"Application {APPLICATION_NAME} already exists (created by another process)")
        else:
            raise

def create_application_version(eb_client, jar_key):
    """Create new application version"""
    version_label = f"v{datetime.now().strftime('%Y%m%d-%H%M%S')}"

    eb_client.create_application_version(
        ApplicationName=APPLICATION_NAME,
        VersionLabel=version_label,
        Description="Deployed via Python AWS SDK",
        SourceBundle={
            'S3Bucket': S3_BUCKET,
            'S3Key': jar_key
        }
    )

    print(f"Created application version: {version_label}")
    return version_label

def create_environment(eb_client, version_label):
    """Create Elastic Beanstalk environment"""

    # Environment configuration
    option_settings = [
        # Application environment variables
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'SERVER_PORT',
            'Value': '5000'
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'SPRING_PROFILES_ACTIVE',
            'Value': 'prod'
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'RDS_HOSTNAME',
            'Value': RDS_HOSTNAME
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'RDS_PORT',
            'Value': '5432'
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'RDS_DB_NAME',
            'Value': 'studentdb'
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'RDS_USERNAME',
            'Value': 'postgres'
        },
        {
            'Namespace': 'aws:elasticbeanstalk:application:environment',
            'OptionName': 'RDS_PASSWORD',
            'Value': RDS_PASSWORD
        },

        # Instance configuration
        {
            'Namespace': 'aws:autoscaling:launchconfiguration',
            'OptionName': 'InstanceType',
            'Value': 't3.micro'
        },
        {
            'Namespace': 'aws:autoscaling:launchconfiguration',
            'OptionName': 'IamInstanceProfile',
            'Value': 'aws-elasticbeanstalk-ec2-role'
        },

        # Single instance environment (no load balancer)
        {
            'Namespace': 'aws:elasticbeanstalk:environment',
            'OptionName': 'EnvironmentType',
            'Value': 'SingleInstance'
        },

        # Auto Scaling configuration
        {
            'Namespace': 'aws:autoscaling:asg',
            'OptionName': 'MinSize',
            'Value': '1'
        },
        {
            'Namespace': 'aws:autoscaling:asg',
            'OptionName': 'MaxSize',
            'Value': '1'
        }
    ]

    response = eb_client.create_environment(
        ApplicationName=APPLICATION_NAME,
        EnvironmentName=ENVIRONMENT_NAME,
        SolutionStackName=SOLUTION_STACK,
        VersionLabel=version_label,
        OptionSettings=option_settings
    )

    environment_id = response['EnvironmentId']
    print(f"Environment creation initiated: {environment_id}")

    return environment_id

def wait_for_environment(eb_client, environment_id):
    """Wait for environment to reach Ready status"""
    print("Waiting for environment to be ready (this may take 10-15 minutes)...")

    while True:
        response = eb_client.describe_environments(EnvironmentIds=[environment_id])
        environment = response['Environments'][0]

        status = environment['Status']
        health = environment.get('Health', 'Unknown')

        print(f"Status: {status}, Health: {health}")

        if status == 'Ready':
            if health in ['Ok', 'Warning']:
                print("Environment is ready and healthy!")
                configure_security_groups()
                break
            elif health == 'Degraded' or health == 'Severe':
                print("Environment is ready but unhealthy. Configuring security groups...")
                configure_security_groups()
                break
        elif status in ['Terminated', 'Terminating']:
            raise Exception("Environment was terminated during creation")

        time.sleep(30)  # Wait 30 seconds before checking again

def find_rds_security_group():
    """Find the RDS database security group"""
    ec2_client = boto3.client('ec2', region_name=REGION)

    # Look for RDS instances to get their security groups
    rds_client = boto3.client('rds', region_name=REGION)

    try:
        # Find RDS instance starting with "student"
        response = rds_client.describe_db_instances()
        for db in response['DBInstances']:
            if db['DBInstanceIdentifier'].startswith('student'):
                vpc_security_groups = db['VpcSecurityGroups']
                if vpc_security_groups:
                    rds_sg = vpc_security_groups[0]['VpcSecurityGroupId']
                    print(f"Found RDS security group: {rds_sg}")
                    return rds_sg
    except Exception as e:
        print(f"Could not find RDS security group automatically: {e}")
        return None

def find_eb_security_group():
    """Find the Elastic Beanstalk security group"""
    ec2_client = boto3.client('ec2', region_name=REGION)

    try:
        response = ec2_client.describe_security_groups(
            Filters=[
                {
                    'Name': 'group-name',
                    'Values': ['awseb-*-AWSEBSecurityGroup-*']
                }
            ]
        )

        # Find the most recent one (likely belongs to our environment)
        if response['SecurityGroups']:
            eb_sg = response['SecurityGroups'][-1]['GroupId']
            print(f"Found EB security group: {eb_sg}")
            return eb_sg
    except Exception as e:
        print(f"Could not find EB security group automatically: {e}")
        return None

def configure_security_groups():
    """Configure security groups to allow EB to connect to RDS"""
    print("Configuring security groups for database access...")

    rds_sg = find_rds_security_group()
    eb_sg = find_eb_security_group()

    if not rds_sg or not eb_sg:
        print("Could not automatically configure security groups.")
        print("Please run these commands manually:")
        print("1. Find your RDS security group:")
        print("   aws rds describe-db-instances --query 'DBInstances[?starts_with(DBInstanceIdentifier, `student`)].VpcSecurityGroups[0].VpcSecurityGroupId' --output text")
        print("2. Find your EB security group:")
        print("   aws ec2 describe-security-groups --filters 'Name=group-name,Values=awseb-*' --query 'SecurityGroups[?contains(GroupName, `AWSEBSecurityGroup`)].GroupId' --output text")
        print("3. Allow EB to connect to RDS:")
        print("   aws ec2 authorize-security-group-ingress --group-id <RDS_SG> --protocol tcp --port 5432 --source-group <EB_SG>")
        return

    # Configure the security group rule
    ec2_client = boto3.client('ec2', region_name=REGION)

    try:
        ec2_client.authorize_security_group_ingress(
            GroupId=rds_sg,
            IpPermissions=[
                {
                    'IpProtocol': 'tcp',
                    'FromPort': 5432,
                    'ToPort': 5432,
                    'UserIdGroupPairs': [
                        {
                            'GroupId': eb_sg,
                            'Description': 'Allow EB access to RDS'
                        }
                    ]
                }
            ]
        )
        print(f"Successfully configured security group: EB ({eb_sg}) can now access RDS ({rds_sg})")
    except ec2_client.exceptions.ClientError as e:
        if 'already exists' in str(e):
            print("Security group rule already exists - database access is configured")
        else:
            print(f"Error configuring security group: {e}")
            print("You may need to configure this manually in the AWS console")

def get_environment_events(eb_client):
    """Get recent environment events for debugging"""
    response = eb_client.describe_events(
        ApplicationName=APPLICATION_NAME,
        EnvironmentName=ENVIRONMENT_NAME,
        MaxRecords=20
    )

    print("\nRecent events:")
    for event in response['Events']:
        print(f"{event['EventDate']}: {event['Message']}")

if __name__ == "__main__":
    main()