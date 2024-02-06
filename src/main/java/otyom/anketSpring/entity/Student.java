package otyom.anketSpring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student extends MyUser {

    @ManyToMany
    @JoinTable(
            name = "student_surveys",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "surveyId"))
    private Set<Survey> surveys = new HashSet<>();



    //bir öğrenci sadece bir sınıfa ait olabilir. Yani student tablosuna clasId eklendi.
    @ManyToOne
    @JoinColumn(name = "classId")
    private Clas clas;


    @OneToMany(mappedBy = "student")
    private List<Answer> answers;





}
