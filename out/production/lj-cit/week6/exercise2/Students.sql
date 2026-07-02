CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    reg_number VARCHAR(20) UNIQUE NOT NULL,
    age INT,
    gpa NUMERIC(3,2) DEFAULT 0.0,
    is_active BOOLEAN DEFAULT TRUE
);

SELECT name, reg_number FROM students;

SELECT * FROM students WHERE gpa >= 3.5 ORDER BY gpa DESC;

SELECT * FROM students WHERE is_active = TRUE AND  age > 21;

SELECT * FROM students WHERE name LIKE 'A%';