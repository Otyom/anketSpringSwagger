package otyom.anketSpring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import otyom.anketSpring.entity.enums.QuestionType;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllQuestionByStudentResponseDto {
    private String question;
    private QuestionType type;
    private Date date;
    private Long teacherId;

}
