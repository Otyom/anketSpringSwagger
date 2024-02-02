package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import otyom.anketSpring.entity.enums.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaveStudentRequestDto {
    @NonNull
    private Long clasId;
    private String tc;
    private Gender gender;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String token;

}
