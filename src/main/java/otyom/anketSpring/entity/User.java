package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

import java.util.List;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="user_table")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long id;
        @Column(unique = true)
        private String tc;
        @Enumerated(EnumType.STRING)
        private Gender cinsiyet;
        private String name;
        private String surname;
        @Column(unique = true)
        private String email;
        private String password;
        @Column(unique = true)
        private String phoneNumber;
        @Enumerated(EnumType.STRING)
        private RoleEnum role;


}
