package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.SaveQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.service.QuestionService;

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

}
