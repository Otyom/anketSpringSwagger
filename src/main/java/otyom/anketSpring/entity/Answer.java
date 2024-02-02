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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private Date date;
    private boolean isText;
    private boolean isNumeric;
    private boolean isYesNo;

    public Answer(String answer, Question question, Date date) {
        this.answer = answer;
        this.question = question;
        this.date = date;
        this.isText = false;
        this.isNumeric = false;
        this.isYesNo = false;
        if (question.getType().compareTo("text") == 0) {
            this.isText = true;
        } else if (question.getType().compareTo("numeric") == 0) {
            this.isNumeric = true;
        } else if (question.getType().compareTo("yes-no") == 0) {
            this.isYesNo = true;
        }

    }
}
