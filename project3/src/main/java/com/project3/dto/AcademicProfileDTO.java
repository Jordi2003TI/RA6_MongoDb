package com.project3.dto;

public class AcademicProfileDTO {

    private String grade;
    private String course;
    private String observations;
    private String status;

    public AcademicProfileDTO() {}

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
