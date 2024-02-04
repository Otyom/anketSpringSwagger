package otyom.anketSpring.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import otyom.anketSpring.entity.Answer;
import otyom.anketSpring.entity.enums.QuestionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveQuestionRequestDto {
    private String token;
    private String question;
    private QuestionType questionType;



}