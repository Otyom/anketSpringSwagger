package otyom.anketSpring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.*;
import otyom.anketSpring.repository.ISurveyRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Date;
import java.util.Optional;

@Service
public class SurveyService {
    @Autowired private ISurveyRepository repository;
    @Autowired private JsonTokenManager jsonTokenManager;
    @Autowired private TeacherService teacherService;
    @Autowired private QuestionService questionService;


    public BaseResponseDto surveySave(SaveSurveyRequestDto dto){
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }
        Survey survey=Survey.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .date(new Date())
                .build();
        repository.save(survey);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public BaseResponseDto addQuestionToSurvey(Long surveyId, Long questionId,String token) {
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }
        //Survey nesnesini al yoksa hata at
        Survey survey = repository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + surveyId));

        //Question nesnesini al yoksa hata at
        Question question = questionService.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        // Survey nesnesine soruyu ekle
        survey.getQuestions().add(question);
        repository.save(survey);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


}
