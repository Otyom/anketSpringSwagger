package otyom.anketSpring.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "survey_question",
            joinColumns = {@JoinColumn(name = "questionId")},
            inverseJoinColumns = @JoinColumn(name = "surveyId")
    )
    private List<Survey> surveys;


    @ManyToMany(mappedBy = "surveys")
    private List<Clas> classes;

    private String title;
    private Date date;
    private String description;

}
