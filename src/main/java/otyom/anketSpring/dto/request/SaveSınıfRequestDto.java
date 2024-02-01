package otyom.anketSpring.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Columns;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveSınıfRequestDto {
    private String token;
    private String name;

    

}
