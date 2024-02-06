package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.GetQuestionsBySurveyIdRequestDto;
import otyom.anketSpring.dto.request.GetSurveysIdByClasIdRequestDto;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetQuestionsResponseDto;
import otyom.anketSpring.dto.response.GetSurveysIdByClassIdResponseDto;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.service.SurveyService;

import java.util.List;

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
    public ResponseEntity<BaseResponseDto> saveSurveyToQuestion(@RequestParam Long surveyId,Long questionId,String token)
    {
        return ResponseEntity.ok(surveyService.addQuestionToSurvey(surveyId,questionId,token));
    }

    @PostMapping("/addSurveyToClas")
    public ResponseEntity<BaseResponseDto>addSurveyToClas(@RequestParam Long surveyId,Long clasId,String token){
        return ResponseEntity.ok(surveyService.addSurveyToClas(surveyId,clasId,token));
    }


    @GetMapping("/{surveyId}/getClasesBySurveyId")
    public ResponseEntity<List<String>>getClasBySurveyId(@PathVariable Long surveyId,String token) {
        return ResponseEntity.ok(surveyService.getClasNamesBySurveyId(surveyId,token));
    }

    @GetMapping("/getSurveysIdByClassId")
    public ResponseEntity<List<GetSurveysIdByClassIdResponseDto>> getSurveysIdByClassId(GetSurveysIdByClasIdRequestDto dto){
        return ResponseEntity.ok(surveyService.getSurveysIdByClassId(dto));
    }



}
