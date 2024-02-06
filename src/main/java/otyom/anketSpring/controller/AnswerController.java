package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.SaveAnswerRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetAllAdminResponseDto;
import otyom.anketSpring.dto.response.GetAllAnswerStudentResponseDto;
import otyom.anketSpring.entity.Answer;
import otyom.anketSpring.service.AnswerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/saveAnswer")
    public ResponseEntity<BaseResponseDto> surveySave(SaveAnswerRequestDto dto){
        return ResponseEntity.ok(answerService.saveAnswer(dto));
    }


    @GetMapping("/getAnswerByStudent")
    public ResponseEntity<List<GetAllAnswerStudentResponseDto>> getAdmin(@RequestParam String token,Long surveyId, Long studentId){
        return ResponseEntity.ok(answerService.getAllAnswerByStudentIdAndSurveyId(token,surveyId,studentId));
    }
}
