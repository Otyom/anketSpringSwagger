package otyom.anketSpring.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllAnswerStudentResponseDto {
      private String answer;
    private Date date;
    private Long surveyId;

}
