package StudentGradeManagementSystem;

public class Student {

    private String name;
    private String registrationNumber;
    private int studentId;
    private double mathScore;
    private double englishScore;
    private double scienceScore;
    private double socialStudiesScore;

    public Student(String registrationNumber, String name, double mathScore, double englishScore,
                   double scienceScore, double socialStudiesScore) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.mathScore = validateScore(mathScore, "Math");
        this.englishScore = validateScore(englishScore, "English");
        this.scienceScore = validateScore(scienceScore, "Science");
        this.socialStudiesScore = validateScore(socialStudiesScore, "Social Studies");
    }

    private double validateScore(double score, String subjectName) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    subjectName + " score must be between 0 and 100, got: " + score);
        }
        return score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = validateScore(mathScore, "Math");
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(double englishScore) {
        this.englishScore = validateScore(englishScore, "English");
    }

    public double getScienceScore() {
        return scienceScore;
    }

    public void setScienceScore(double scienceScore) {
        this.scienceScore = validateScore(scienceScore, "Science");
    }

    public double getSocialStudiesScore() {
        return socialStudiesScore;
    }

    public void setSocialStudiesScore(double socialStudiesScore) {
        this.socialStudiesScore = validateScore(socialStudiesScore, "Social Studies");
    }

    public double getAverageScore() {
        return (mathScore + englishScore + scienceScore + socialStudiesScore) / 4.0;
    }

    public String getLetterGrade() {
        double avg = getAverageScore();
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %-3s | RegNo: %-10s | Name: %-15s | Math: %.1f | English: %.1f | Science: %.1f | SST: %.1f | Average: %.2f | Grade: %s",
                studentId, registrationNumber, name, mathScore, englishScore, scienceScore, socialStudiesScore,
                getAverageScore(), getLetterGrade());
    }
}