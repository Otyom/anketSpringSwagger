package otyom.anketSpring.dto.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQuestionByIdRequestDto {
    private String token;
    private Long id;
}
