package com.school.studentmanagementsystem.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String address;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "contact_information")
    private String contactInformation;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "enrollment_status")
    private String enrollmentStatus;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false, nullable = false)
    private Date createdOn;

    public Student(String studentId, String firstname, String lastname) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Student() {
    }
}
