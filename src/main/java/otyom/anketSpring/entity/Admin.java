package otyom.anketSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_admin")
public class Admin extends MyUser {
    int a;
}
