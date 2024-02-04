package otyom.anketSpring.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Question> questions = new HashSet<>();


    @ManyToMany(mappedBy = "surveys")
    private List<Clas> classes;

    private String title;
    private Date date;
    private String description;


}
