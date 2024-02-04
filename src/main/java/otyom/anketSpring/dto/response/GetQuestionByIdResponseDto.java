package otyom.anketSpring.dto.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQuestionByIdResponseDto {
    String question;
    String type;
    String message;
    int statusCode;
    HttpStatus httpStatus;
}
