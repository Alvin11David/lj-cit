package StudentGradeManagementSystem;

class Student {

    private String name;
    private int studentId;
    private double mathScore;
    private double englishScore;
    private double scienceScore;
    private double socialStudiesScore;

    public Student(String name, double mathScore, double englishScore, double scienceScore, double socialStudiesScore) {
        this.name = name;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.scienceScore = scienceScore;
        this.socialStudiesScore = socialStudiesScore;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
        return String.format("ID: %s | Name: %-15s | Math: %.1f | English: %.1f | Science: %.1f | SST: %.1f | Average: %.2f",
                studentId, name, mathScore, englishScore, scienceScore, socialStudiesScore, getAverageScore());
    }
}
