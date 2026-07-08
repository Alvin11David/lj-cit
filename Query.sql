CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         code VARCHAR(10) NOT NULL UNIQUE,
                         credit_units INTEGER
);

INSERT INTO courses (title, code, credit_units) VALUES ('Introduction to Computer Science', 'CS101', 4);
INSERT INTO courses (title, code, credit_units) VALUES ('Database Management Systems', 'DB202', 3);