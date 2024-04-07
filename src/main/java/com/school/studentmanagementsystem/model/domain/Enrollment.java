package com.school.studentmanagementsystem.model.domain;

import com.school.studentmanagementsystem.model.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrollment_date", updatable = false, nullable = false)
    private Date enrollmentDate;

    @Column(name = "enrollment_status")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

}
