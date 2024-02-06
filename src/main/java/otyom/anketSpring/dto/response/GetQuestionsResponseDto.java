package otyom.anketSpring.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.entity.enums.QuestionType;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQuestionsResponseDto {
    private Long Questionid;
    private String question;
    private QuestionType questionType;

}
