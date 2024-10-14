package com.school.studentmanagementsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditGradeDTO {
    private String assessmentType;
    private Double score;
    private String remarks;

    public EditGradeDTO(String assessmentType, Double score, String remarks) {
        this.assessmentType = assessmentType;
        this.score = score;
        this.remarks = remarks;
    }
}
