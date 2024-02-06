package otyom.anketSpring.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQuestionsBySurveyIdRequestDto {
    private String token;
    private Long surveyId;
}
