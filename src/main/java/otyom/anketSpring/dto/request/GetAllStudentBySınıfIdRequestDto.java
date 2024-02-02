package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStudentBySınıfIdRequestDto {
    private String token;
    private Long id;
}
