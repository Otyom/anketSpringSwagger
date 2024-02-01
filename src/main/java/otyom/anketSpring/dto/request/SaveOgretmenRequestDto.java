package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SaveOgretmenRequestDto {
    private String token;  //AdminToken
    private String tc;
    private Gender Cinsiyet;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;

}
