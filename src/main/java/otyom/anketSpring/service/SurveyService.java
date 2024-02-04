package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.request.SaveSurveyToQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Survey;
import otyom.anketSpring.entity.Survey_Question;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.repository.ISurveyRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Date;
import java.util.Optional;

@Service
public class SurveyService {
    @Autowired
    private ISurveyRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;
    @Autowired
    private AdminService adminService;
    @Autowired TeacherService teacherService;


    public BaseResponseDto surveySave(SaveSurveyRequestDto dto){
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty() || teacherOptional.isEmpty()) {
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


    public BaseResponseDto surveyToQuestionSave(SaveSurveyToQuestionRequestDto dto){
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty() || teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }
        Survey_Question surveyQuestion= Survey_Question.builder()
                .surveyId(dto.getSurveyId())
                .questionsId(dto.getQuestionsId())
                .clasId(dto.getClasId())
                .build();

        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


}
