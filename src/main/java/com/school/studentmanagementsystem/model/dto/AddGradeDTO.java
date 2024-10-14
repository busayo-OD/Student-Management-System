package com.school.studentmanagementsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddGradeDTO {
    private String studentId;
    private Long courseId;
    private String assessmentType;
    private Double score;
    private String remarks;

    public AddGradeDTO(String studentId, Long courseId, String assessmentType, Double score, String remarks) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.score = score;
        this.remarks = remarks;
    }
}
