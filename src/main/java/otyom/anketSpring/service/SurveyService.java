package otyom.anketSpring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.GetQuestionsBySurveyIdRequestDto;
import otyom.anketSpring.dto.request.GetSurveysIdByClasIdRequestDto;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetQuestionsResponseDto;
import otyom.anketSpring.dto.response.GetSurveysIdByClassIdResponseDto;
import otyom.anketSpring.entity.*;
import otyom.anketSpring.repository.ISurveyRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    @Autowired private ISurveyRepository repository;
    @Autowired private JsonTokenManager jsonTokenManager;
    @Autowired private TeacherService teacherService;
    @Autowired private QuestionService questionService;
    @Autowired
    private ClasService clasService;
    @Autowired private AdminService adminService;


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
        survey.getQuestion().add(question);
        repository.save(survey);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public Optional<Survey> findById(Long surveyId) {
        Optional<Survey> survey= repository.findById(surveyId);
        return survey;
    }



    public BaseResponseDto addSurveyToClas(Long surveyId, Long clasId,String token) {
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("id not found");
        }
        Optional<Teacher> teacher = teacherService.findById(id.get());
        if (teacher.isEmpty()) {
            throw new RuntimeException("teacher not found");
        }

        Survey survey = repository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found id"));
        Clas clas = clasService.findById(clasId)
                        .orElseThrow(() -> new EntityNotFoundException("Clas not found id"));
        if (survey.getClasses().contains(clas)) {
            return BaseResponseDto.builder()
                    .message("Bu anket bu sınıfa daha önce tanımlanmış")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .statusCode(400)
                    .build();
        }


        // Öğretmeni sınıfa ekle
        survey.getClasses().add(clas);
        repository.save(survey);
        return BaseResponseDto.builder()
                .message("ok")
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .build();
    }


    public List<String> getClasNamesBySurveyId(Long surveyId,String token) {
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Geçersiz token");
        }
        Optional<Admin> adminOptional = adminService.findById(id.get());
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());

        if (adminOptional.isEmpty() || teacherOptional.isEmpty()){
            throw new RuntimeException();
        }


        Survey survey = repository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Clas not found"));

        return survey.getClasses().stream()
                .map(Clas::getClasName)
                .collect(Collectors.toList());
    }


    public List<GetSurveysIdByClassIdResponseDto> getSurveysIdByClassId(GetSurveysIdByClasIdRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Geçersiz token");
        }
        Optional<Admin> adminOptional = adminService.findById(id.get());
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());

        if (adminOptional.isEmpty() || teacherOptional.isEmpty()){
            throw new RuntimeException();
        }


        List<Long> idByClassId = repository.findSurveyIdByClassId(dto.getClasId());
        List<GetSurveysIdByClassIdResponseDto> dtos = new ArrayList<>();


        for (Long dto1 : idByClassId) {
            dtos.add(GetSurveysIdByClassIdResponseDto.builder()
                    .id(dto1)
                    .message("ok")
                    .statusCode(200)
                    .httpStatus(HttpStatus.OK)
                    .build());
        }
        return dtos;

    }



    }







