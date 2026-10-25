
./mvnw clean package -DskipTests

ls -la target/*.jar

Step 2: Create RDS Database

```text
aws rds create-db-instance \
    --db-instance-identifier student-app-db \
    --db-instance-class db.t4g.micro \
    --engine postgres \
    --master-username postgres \
    --master-user-password mypassword123 \
    --allocated-storage 20 \
    --db-name studentdb \
    --no-publicly-accessible \
    --no-multi-az
```

Step 3: Monitor Database Creation

```text
# Check status (will show "creating" initially, then "available" when ready)
aws rds describe-db-instances --db-instance-identifier student-db --query 'DBInstances[0].DBInstanceStatus' --output text
```

Step 3: Get Database Endpoint (once available)

```text
aws rds describe-db-instances --db-instance-identifier student-db --query 'DBInstances[0].Endpoint.Address' --output text
```

Step 4: Create Elastic Beanstalk Environment

While the database is creating, you can start creating your EB environment:

```text
eb create student-app-env
```

Step 5: Connect Everything

Once both the database shows "available" status and your EB environment is healthy:

```text
# Get the database endpoint first
DB_ENDPOINT=$(aws rds describe-db-instances --db-instance-identifier student-db --query 'DBInstances[0].Endpoint.Address' --output text)

echo $DB_ENDPOINT

# Set all environment variables
eb setenv SERVER_PORT=5000 \
         SPRING_PROFILES_ACTIVE=prod \
         RDS_HOSTNAME=$DB_ENDPOINT \
         RDS_PORT=5432 \
         RDS_DB_NAME=studentdb \
         RDS_USERNAME=postgres \
         RDS_PASSWORD=mypassword123
```

Start with the RDS creation command and let me know when the status changes to "available".

Check Environment Status

After setting the environment variables, check the status:

```text
eb status
eb health
```

Verify Variables Are Set

```text
eb printenv
```

You should see all the database connection variables listed.

Test the Application

Once the environment shows as "Ok" or "Ready":

```text
eb open
```

If There Are Issues

Check the logs to see if the app is connecting properly:

```text
eb logs
```

Look for Java application startup errors, database connection issues, or port configuration problems.

```text
eb health --refresh
```
