package com.school.studentmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum EnrollmentStatus {
    Enrolled("Enrolled"),
    Dropped("Dropped");

    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }
}
