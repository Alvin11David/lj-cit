CREATE DATABASE student_db;

\c student_db

CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    math_score DOUBLE PRECISION NOT NULL,
    english_score DOUBLE PRECISION NOT NULL,
    science_score DOUBLE PRECISION NOT NULL,
    social_studies_score DOUBLE PRECISION NOT NULL
);