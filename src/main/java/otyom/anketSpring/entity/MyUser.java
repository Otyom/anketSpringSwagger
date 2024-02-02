package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class MyUser {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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
