package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAdminByIdResponseDto {
    String name;
    String surname;
    String email;
    String message;
    int statusCode;
    String token;
}
