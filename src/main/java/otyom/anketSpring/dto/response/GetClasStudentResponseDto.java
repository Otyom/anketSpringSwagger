package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import otyom.anketSpring.entity.enums.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetClasStudentResponseDto {
    private Long clasId;
    private String name;
    private String surname;
    private Gender gender;
    private String email;
    private String message;
    private int StatusCode;
}
