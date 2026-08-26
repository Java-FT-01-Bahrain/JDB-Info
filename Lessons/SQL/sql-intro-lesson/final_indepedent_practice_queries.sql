
-- In case students need to catchup from earlier in the lesson:

CREATE DATABASE generalassembly;

CREATE TABLE instructors (
instructor_id  SERIAL  PRIMARY KEY  NOT NULL,
name        TEXT                NOT NULL,
experience  INT                 NOT NULL,
website     VARCHAR(50)
  );

CREATE TABLE students (
student_id  SERIAL PRIMARY KEY  NOT NULL,
name        TEXT                NOT NULL,
age         INT                 NOT NULL,
address     VARCHAR(50)
 );
INSERT INTO students VALUES (DEFAULT, 'Jilly Cakes', 30, '123 Webdev Dr. Boston, MA');
INSERT INTO students VALUES (DEFAULT, 'Johnny Bananas', 25, '555 Five St, Fivetowns, NY');
INSERT INTO students VALUES (DEFAULT, 'Jackie Lackie', 101, '2 OldForThis Ct, Fivetowns, NY');
INSERT INTO students VALUES (DEFAULT, 'Slaggy McRaggy', 28);


-- Final Independent Practice
-- - Insert five more students:
--   - Nancy Gong is 40 and lives at 200 Horton Ave., Lynbrook, NY
INSERT INTO students VALUES (DEFAULT, 'Nancy Gong', 40, '200 Horton Ave., Lynbrook, NY');
--   - Laura Rossi is 70 and listed her address as "Unlisted"
INSERT INTO students VALUES (DEFAULT, 'Laura Rossi', 70, 'Unlisted');
--   - David Daniele is 28 and lives at 300 Dannington Ln., Washington, DC.
INSERT INTO students VALUES (DEFAULT, 'David Daniel', 28, '300 Dannington Ln., Washinton, DC.');
--   - Greg Fitzgerald is 25 and did not list an address
INSERT INTO students VALUES (DEFAULT, 'Greg Fitzgerald', 25);
--   - Randi Fitz is 28 and lives in Oceanside, NY
INSERT INTO students VALUES (DEFAULT, 'Randi Fitz', 28, 'Oceanside, NY');

--
-- - Randi wants her address to be corrected to 25 Broadway, New York, NY
UPDATE students SET address = '25 Broadway, New York, NY, New York, NY' WHERE address = 'Oceanside, NY';
-- - Get a list of student names and addresses who are older than 30 and order them by their address alphabetically
SELECT name, address FROM students WHERE  age > 30 ORDER BY address;
-- - Get a list of students whose first name begins with the letter "J"
SELECT * FROM students WHERE name LIKE 'J%';
-- - Get a list of student names who live in NY or MA
SELECT name FROM students WHERE address LIKE '%NY%' OR address LIKE '%MA%'
