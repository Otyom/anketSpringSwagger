package otyom.anketSpring.dto.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAdminResponse {
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private RoleEnum role;
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
}
