package StudentGradeManagementSystem;

class Student {

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

    public double getEnglishScore() {
        return englishScore;
    }

    public double getScienceScore() {
        return scienceScore;
    }

    public double getSocialStudiesScore() {
        return socialStudiesScore;
    }

    public double getAverageScore() {
        return (mathScore + englishScore + scienceScore + socialStudiesScore) / 4.0;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | RegNo: %-10s | Name: %-15s | Math: %.1f | English: %.1f | Science: %.1f | SST: %.1f | Average: %.2f",
                studentId, registrationNumber, name, mathScore, englishScore, scienceScore, socialStudiesScore, getAverageScore());
    }
}