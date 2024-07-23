package com.school.studentmanagementsystem.exception;

public class EmailAlreadyExistsException extends SMSAPIException{
    private static final long serialVersionUID = 1L;
    public static final String ENTITY_NAME = "User";

    @Override
    public String getMessage() {
        return String.format("%s already exists with this email in the record and is not available for use by another user.", ENTITY_NAME);
    }
}
