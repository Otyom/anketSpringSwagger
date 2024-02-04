package otyom.anketSpring.entity.QuestionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Secenek {
    @NotNull
    private String A;
    @NotNull
    private String B;
    @NotNull
    private String C;
    @NotNull
    private String D;
    @NotNull
    private String E;

}
