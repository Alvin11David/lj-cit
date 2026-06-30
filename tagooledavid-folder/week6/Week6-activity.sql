-- Exercise 1: Create Courses table
CREATE TABLE course(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    code  VARCHAR(10) UNIQUE NOT NULL ,
    credit_units INT
);

INSERT INTO course(title,code,credit_units)
VALUES
('Computer Science','CSC 22067',5),
('Software Engineering','SAS 5609',3);


-- Exercise 2: Student
-- Create table
CREATE TABLE student(
    regno VARCHAR(10) PRIMARY KEY NOT NULL,
    name VARCHAR(40) NOT NULL,
    gpa NUMERIC(4,2) NOT NULL,
    age INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

SELECT name,regno FROM student;

SELECT name,regno,gpa,is_active,age FROM student WHERE gpa >= 3.5 ORDER BY gpa DESC;


SELECT name,regno,gpa,is_active,age FROM student WHERE age > 21 AND is_active = TRUE;

SELECT name,regno,gpa,is_active,age FROM student WHERE name LIKE 'A%';