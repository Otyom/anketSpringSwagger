package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetStudentByIdResponseDto {
    private String name;
    private String surname;
    private String email;
    private String message;
    private int statusCode;
    private HttpStatus httpStatus;
}
