package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import otyom.anketSpring.entity.enums.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SaveTeacherRequestDto {
    private String token;  //AdminToken
    private String tc;
    private Gender gender;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

}
