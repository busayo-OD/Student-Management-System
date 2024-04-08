package com.school.studentmanagementsystem.model.enums;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    Present("Present"),
    Absent("Absent"),
    Tardy("Tardy");

    private final String value;

    AttendanceStatus(String value) {
        this.value = value;
    }
}
