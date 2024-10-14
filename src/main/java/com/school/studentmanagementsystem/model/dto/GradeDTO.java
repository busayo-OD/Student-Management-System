package com.school.studentmanagementsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeDTO {
    private String firstname;
    private String lastname;
    private String studentId;
    private String courseCode;
    private String courseName;
    private String assessmentType;
    private Double score;
    private String remarks;
}
