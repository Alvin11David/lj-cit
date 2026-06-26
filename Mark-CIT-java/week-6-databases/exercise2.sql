CREATE TABLE COURSES(
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	code VARCHAR(10) NOT NULL UNIQUE,
	credit_units INT
);

INSERT INTO COURSES(title,code,credit_units) VALUES
('Java Programming','CIT2001',5),
('Cloud Computing','CIT2002',4)

SELECT * FROM COURSES;

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    reg_number  VARCHAR(20)  UNIQUE NOT NULL,
    age INT,
    gpa NUMERIC(3,2) DEFAULT 0.0,
    is_active   BOOLEAN DEFAULT TRUE
);


INSERT INTO students (name, reg_number, age, gpa, is_active) VALUES
('Alice Smith', '001', 20, 3.85, TRUE),
('Bob Jones', '002', 22,3.65,TRUE),
('Charlie Brown', '003', 19, 2.90, FALSE),
('Diana Prince', '004', 21, 3.90, TRUE),
('Evan Wright', '005', 23, 3.45, TRUE),
('Fiona Gallagher', '006', 20, 3.10, FALSE);


SELECT name,reg_number FROM students;
