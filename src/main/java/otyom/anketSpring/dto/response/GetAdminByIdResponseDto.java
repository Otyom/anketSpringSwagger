package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAdminByIdResponseDto {
    private String name;
    private String surname;
    private String email;
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;

}
