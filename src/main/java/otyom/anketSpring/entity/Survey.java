package otyom.anketSpring.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
            joinColumns = {@JoinColumn(name = "surveyId")},
            inverseJoinColumns = @JoinColumn(name = "questionId")
    )
    private List<Question> question;



    @ManyToMany
    @JoinTable(
            name = "survey_clas",
            joinColumns = {@JoinColumn(name = "surveyId")},
            inverseJoinColumns = @JoinColumn(name = "clasId"))
    private Set<Clas> classes=new HashSet<>();




    private String title;
    private Date date;
    private String description;


}
