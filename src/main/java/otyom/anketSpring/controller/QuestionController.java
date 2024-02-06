package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.SaveQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetAllQuestionByStudentResponseDto;
import otyom.anketSpring.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    public QuestionService questionService;

    @PostMapping("/saveQuestion")
    public ResponseEntity<BaseResponseDto> questionSave(@RequestBody SaveQuestionRequestDto dto){
        return ResponseEntity.ok(questionService.questionSave(dto));
    }


    @GetMapping("/getQuestionByStudentAndSurvey")
    public ResponseEntity<List<GetAllQuestionByStudentResponseDto>> getQuestions(@RequestParam String token, Long surveyId, Long studentId){
        return ResponseEntity.ok(questionService.getQuestionsByStudentIdAndSurveyId(token,surveyId,studentId));
    }
}
