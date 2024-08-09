package com.school.studentmanagementsystem.exception;

public class StudentNotFoundException extends SMSAPIException{
    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "Student";
    private Object entityId = null;

    public StudentNotFoundException(Object entityId) {
        this.entityId = entityId;
    }

    @Override
    public String getMessage() {
        return String.format("%s with an id %s cannot be found or does not exist in record.", ENTITY_NAME, entityId.toString());
    }
}
