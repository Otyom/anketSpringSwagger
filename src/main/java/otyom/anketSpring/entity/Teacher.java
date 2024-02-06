package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "teacher")
public class Teacher extends MyUser {
  @ManyToMany
   @JoinTable(
           name = "teacher_clas",
           joinColumns = @JoinColumn(name = "teacherId"),
           inverseJoinColumns = @JoinColumn(name = "clasId"))
   private Set<Clas> clasSes = new HashSet<>();




}