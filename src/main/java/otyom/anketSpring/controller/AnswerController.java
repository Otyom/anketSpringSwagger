package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.dto.request.SaveAnswerRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.service.AnswerService;

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
}
