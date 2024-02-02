package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginStudentResponseDto {
    int statusCode;
    String message;
    String token;
}
