package com.school.studentmanagementsystem.model.domain;

import com.school.studentmanagementsystem.model.enums.AssessmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "assessment_type")
    @Enumerated(EnumType.STRING)
    private AssessmentType assessmentType;

    private Double score;

    private String remarks;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "assessment_date", updatable = false, nullable = false)
    private Date assessmentDate;

    public Grade(Student student, Course course, AssessmentType assessmentType, Double score, String remarks) {
        this.student = student;
        this.course = course;
        this.assessmentType = assessmentType;
        this.score = score;
        this.remarks = remarks;
    }

    public Grade() {

    }


}
