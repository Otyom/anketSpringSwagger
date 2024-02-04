package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.request.SaveSurveyToQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.service.SurveyService;

@RestController
@RequestMapping("/survey")
@AllArgsConstructor
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping("/saveSurvey")
    public ResponseEntity<BaseResponseDto> surveySave(SaveSurveyRequestDto dto){
        return ResponseEntity.ok(surveyService.surveySave(dto));
    }

    @PostMapping("/saveSurveyToQuestion")
    public ResponseEntity<BaseResponseDto> saveSurveyToQuestion(@RequestParam Long surveyId,@RequestParam Long questionId,String token)
    {
        return ResponseEntity.ok(surveyService.addQuestionToSurvey(surveyId,questionId,token));
    }
}
