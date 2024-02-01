package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOgrenciBySınıfIdRequestDto {
    private String token;
    private Long id;
}
