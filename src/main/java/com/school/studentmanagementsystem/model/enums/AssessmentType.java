package com.school.studentmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum AssessmentType {
    Exam("Exam"),
    Assignment("Assignment");

    private final String value;

    AssessmentType(String value) {
        this.value = value;
    }
}
