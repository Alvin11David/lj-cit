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