CREATE TABLE STUDENTS(
	id SERIAL PRIMARY KEY,
	name VARCHAR(20),
	registration_number CHAR(4) UNIQUE,
	sst INT DEFAULT 0,
	math INT DEFAULT 0,
	sci INT DEFAULT 0,
	eng INT DEFAULT 0
);

INSERT INTO STUDENTS (name, registration_number, sst, math, sci, eng) VALUES
('Alice Smith', 'R001', 85, 92, 88, 90),
('Bob Johnson', 'R002', 78, 80, 85, 75),
('Charlie Brown', 'R003', 95, 88, 91, 94),
('Diana Prince', 'R004', 82, 95, 89, 92);

