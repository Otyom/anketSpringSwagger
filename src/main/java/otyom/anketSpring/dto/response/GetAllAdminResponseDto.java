package otyom.anketSpring.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllAdminResponseDto {
    private String name;
    private String surname;
    private String email;
    private String message;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int statusCode;
    private HttpStatus httpStatus;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}