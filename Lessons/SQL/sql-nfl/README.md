# The NFL
This assignment will give you more practice with queries, and get you to deal with some more advanced parts of the sql language.

## Setup 
- In your terminal run the command **`psql -f seed.sql`**
- You should see something like this
```sh
 > $: psql -f seed.sql 
psql:seed.sql:1: NOTICE:  database "nfl" does not exist, skipping
DROP DATABASE
CREATE DATABASE
You are now connected to database "nfl" as user "thiagocavalcante".
psql:seed.sql:7: NOTICE:  table "players" does not exist, skipping
DROP TABLE
psql:seed.sql:8: NOTICE:  table "teams" does not exist, skipping
DROP TABLE
CREATE TABLE
CREATE TABLE
INSERT 0 32
INSERT 0 1532
```
- Now that your database was created and seeded go ahead and open **pgAdmin** and you will find a database named **nfl**

<hr>

### Exercise
Open vsCode and select the file [nfl.sql](nfl.sql). On this file is where you will submit your queries for each prompt. Few free to write your queries using the **pgAdmin**, but make sure to copy and paste the final queries into the nfl.sql file. :wink:
<br>

1.  List the names of all NFL teams
2.  List the stadium name and head coach of all NFC teams
3.  List the head coaches of the AFC South
4.  The total number of players in the NFL

&#x1F534; COMMIT 1<br>
"Commit: NFL - queried some NFL stuff"
<hr>

5.  The team names and head coaches of the NFC North and AFC East
6.  The 50 players with the highest salaries
7.  The average salary of all NFL players
8.  The names and positions of players with a salary above 10_000_000

&#x1F534; COMMIT 2<br>
"Commit: NFL - wow there's a lot of nfl data"
<hr>

## Hungry for more?

9.  The player with the highest salary in the NFL
10. The name and position of the first 100 players with the lowest salaries
11. The average salary for a DE in the nfl
12. The names of all the players on the Buffalo Bills
13. The total salary of all players on the New York Giants
14. The player with the lowest salary on the Green Bay Packers

&#x1F534; COMMIT 3<br>
"Commit: NFL - Poor Shaky Smithson"

