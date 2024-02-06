package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "question_id")
    private Question question;


    private Date date;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;


    private Long surveyId;


}
