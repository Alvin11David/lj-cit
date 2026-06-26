-- 9.2 Homework Solution to number 6
CREATE DATABASE students_db;


CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    regno VARCHAR(14) NOT NULL UNIQUE,
    name VARCHAR(30) NOT NULL,
    gender VARCHAR(6) NOT NULL
);

INSERT INTO students ( regno,name,gender  ) 
VALUES
('R1','Tagoole David','Male'),
('R2','Namubiru Ashirah','Female'),
('R3','Kalange Fahad','Male'),
('R4','Mutuku Hakim','Male');




CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    code VARCHAR(5) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL,
    credit_units NUMERIC(4,2)
);

INSERT INTO courses ( code,name,credit_units ) 
VALUES
('C1','Computer Science',4.6),
('C2','Software Engineering',4.80),
('C3','Networks',6.7),
('C4','Cyber Security',5.8);



CREATE TABLE enrollments(
    id SERIAL PRIMARY KEY,
    course_id BIGINT UNSIGNED REFERENCES courses(id) ON DELETE CASCADE,
    student_id BIGINT UNSIGNED REFERENCES students(id) ON DELETE CASCADE,
    UNIQUE(course_id,student_id)
);

INSERT INTO enrollments ( course_id,student_id  )
VALUES
(1,4),
(2,3),
(3,2),
(4,1);


SELECT students.name,courses.name 
FROM enrollments 
JOIN students ON students.id = enrollments.student_id
JOIN courses ON courses.id = enrollments.course_id;