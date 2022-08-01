package ratingStudents.entity;

public class Student {
    private int id;
    private String name;
    private String subjectName;
    private String assignmentCategory;
    private String datOfSubmission;
    private int points;

    public Student(int id,String name, String subjectName, String assignmentCategory, String datOfSubmission, int points) {
        this.id =id;
        this.name = name;
        this.subjectName = subjectName;
        this.assignmentCategory = assignmentCategory;
        this.datOfSubmission = datOfSubmission;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getAssignmentCategory() {
        return assignmentCategory;
    }

    public void setAssignmentCategory(String assignmentCategory) {
        this.assignmentCategory = assignmentCategory;
    }

    public String getDatOfSubmission() {
        return datOfSubmission;
    }

    public void setDatOfSubmission(String datOfSubmission) {
        this.datOfSubmission = datOfSubmission;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
