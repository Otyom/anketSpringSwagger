package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveSurveyToQuestionRequestDto {
    private String Token;
    private Long surveyId;
    private Long clasId;
    private Long questionsId;

}