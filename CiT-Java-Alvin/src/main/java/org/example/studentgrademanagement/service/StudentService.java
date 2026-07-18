package org.example.studentgrademanagement.service;

import org.example.studentgrademanagement.model.Score;
import org.example.studentgrademanagement.model.Student;
import org.example.studentgrademanagement.model.StudentResponse;
import org.example.studentgrademanagement.model.Subject;
import org.example.studentgrademanagement.repository.ScoreRepository;
import org.example.studentgrademanagement.repository.StudentRepository;
import org.example.studentgrademanagement.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScoreRepository scoreRepository;

    private static final String[] DEFAULT_SUBJECTS = {"Math", "English", "Science", "Social Studies"};

    public StudentService(StudentRepository studentRepository,
                          SubjectRepository subjectRepository,
                          ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.scoreRepository = scoreRepository;
    }

    @jakarta.annotation.PostConstruct
    public void initSubjects() {
        for (String name : DEFAULT_SUBJECTS) {
            if (subjectRepository.findBySubjectName(name).isEmpty()) {
                subjectRepository.save(new Subject(name));
            }
        }
    }

    @Transactional
    public StudentResponse createStudent(String registrationNumber, String name,
                                          Map<String, Double> scores) {
        if (studentRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new IllegalArgumentException(
                    "A student with registration number " + registrationNumber + " already exists");
        }

        Student student = new Student(registrationNumber, name);
        student = studentRepository.save(student);

        List<Score> scoreEntities = new ArrayList<>();
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            Subject subject = subjectRepository.findBySubjectName(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Unknown subject: " + entry.getKey()));

            double value = entry.getValue();
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException(
                        entry.getKey() + " score must be between 0 and 100, got: " + value);
            }

            Score score = new Score(student, subject, value);
            scoreEntities.add(scoreRepository.save(score));
        }

        student.setScores(scoreEntities);
        return buildStudentResponse(student);
    }

    @Transactional
    public StudentResponse recordOrUpdateScores(String registrationNumber,
                                                  Map<String, Double> newScores) {
        Student student = studentRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No student found with registration number: " + registrationNumber));

        for (Map.Entry<String, Double> entry : newScores.entrySet()) {
            Subject subject = subjectRepository.findBySubjectName(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Unknown subject: " + entry.getKey()));

            double value = entry.getValue();
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException(
                        entry.getKey() + " score must be between 0 and 100, got: " + value);
            }

            Optional<Score> existing = scoreRepository.findByStudentStudentIdAndSubjectSubjectId(
                    student.getStudentId(), subject.getSubjectId());

            if (existing.isPresent()) {
                Score score = existing.get();
                score.setScore(value);
                scoreRepository.save(score);
            } else {
                scoreRepository.save(new Score(student, subject, value));
            }
        }

        return findByRegistrationNumber(registrationNumber);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(buildStudentResponse(student));
        }
        return responses;
    }

    @Transactional(readOnly = true)
    public StudentResponse findByRegistrationNumber(String registrationNumber) {
        Student student = studentRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No student found with registration number: " + registrationNumber));
        return buildStudentResponse(student);
    }

    private StudentResponse buildStudentResponse(Student student) {
        List<Score> scores = scoreRepository.findByStudentStudentId(student.getStudentId());

        Map<String, Double> scoreMap = new LinkedHashMap<>();
        double sum = 0;
        for (Score s : scores) {
            scoreMap.put(s.getSubject().getSubjectName(), s.getScore());
            sum += s.getScore();
        }

        double average = scores.isEmpty() ? 0.0 : sum / scores.size();
        String letterGrade = getLetterGrade(average);

        StudentResponse response = new StudentResponse();
        response.setStudentId(student.getStudentId());
        response.setRegistrationNumber(student.getRegistrationNumber());
        response.setName(student.getName());
        response.setScores(scoreMap);
        response.setAverage(average);
        response.setLetterGrade(letterGrade);
        return response;
    }

    private String getLetterGrade(double average) {
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        return "F";
    }
}
