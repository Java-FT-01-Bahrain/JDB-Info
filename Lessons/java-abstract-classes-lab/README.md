# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Inheritance and Abstraction Lab

|                   Title           |  Type  | Duration |  Creator |
|:-------------------------------------------:|:------:|:--------:|:--------:|
| Inheritance and Abstraction | Lab |   1:30   | Suresh Melvin Sigera |

## Exercise

This lab will provide you with more practice creating abstract classes, subclasses, and interfaces.

You'll need to design a program that manages a school. The school has classrooms, and the classrooms have teachers and
students assigned to them. Each classroom contains students 

You decide what the actions and properties of each class will be.

#### Requirements

- At least one abstract class.
- At least one interface.
- At least three subclasses.
- Each subclass must be unique from the others.
- The student class should have the following properties: name, age, grade, Classrooms, city
- The Classroom class should have the following methods:
    1. findStudentsByage: should return all students under a certain age (passed in the parameter)
    2. findBestStudents: returns only the students with grades of 85 and above
    3. GetAverageGrade: will return the average grade of all students in the class
    4. sortedStudentsByAge: returns the students in a class sorted by age
    5. sortedStudentsByGrade: returns the students in a class sorted by grade
    6. getGrades: returns a hashmap of all the students based on their grades: A, B, C, D
    7. getAllStudents: returns all the students names in 1 string seperated by a comma
    8. studentsByCity: returns a hashMap of cities being the key and an ArrayList of students as the value
    9. findLowestPerforming: returns the 10 lowest performing students
    10. findHighestPerforming:returns the 10 heighest performing students
    11. onlyNames: return only the names of all the students in a classroom
    
**Bonus**:
- justGrades: returns a hashMap of only the students names and the grades
- queryStudents: will take 3 arguments and return that students that match the arguments provided
- In the classroom add the teacher as a field
- halfTeacherAge: this method should only return the students that have age that is half of the teachers age
- availableInPersonTutoring: returns all the students that are in the teachers city
- getOneStudentPerCity: returns only 1 student from every city. if 2 students have the same city return the one whos name comes first alphabetically
- groupStudentsByAgeRange: group all the students who are within an age range. 20-29,30-39,40-49,50-59
- longestName: find the student with the longest name
- shortestName: find the student with the shortest name
- studentsInitials: assume all students names are "firstName lastName". so all names are a word seperated by a word. return only the students intiails with their grades in a hashmap
- top20percent: returns the top 20% of students in our class
- topAnyPercent: returns all the students that are within a percentile that will be passed in the parameter

**Bonus**: Add extra classes, subclasses, or interfaces.

#### Deliverable

Java code for classes or interfaces that meet the requirements above.

