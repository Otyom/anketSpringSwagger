package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clas")
public class Clas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true,nullable = false)
    private String clasName;

    @ManyToMany
    @JoinTable(
            name = "clas_survey",
            joinColumns = @JoinColumn(name = "clas_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id")
    )
    private List<Survey> surveys;


}