package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.Exception.userexceptions.AdminNotFoundException;
import otyom.anketSpring.Exception.userexceptions.TokenNotFoundException;
import otyom.anketSpring.dto.request.SaveAnswerRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetAllAnswerStudentResponseDto;
import otyom.anketSpring.entity.*;
import otyom.anketSpring.repository.IAnswerRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private IAnswerRepository repository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JsonTokenManager jsonTokenManager;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;

    //sadece öğrenci erişebilir.
    public BaseResponseDto saveAnswer(SaveAnswerRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new TokenNotFoundException();
        }

        Optional<Student> student = studentService.findById(id.get());
        if (student.isEmpty()) {
            throw new RuntimeException("öğrenci bulunamadı");
        }

        Clas studentClass = student.get().getClas();
        if (studentClass == null) {
            throw new RuntimeException("öğrenci bu clasa ait değil");
        }

        Optional<Question> question = questionService.findById(dto.getQuestionId());
        if (question.isEmpty()) {
            throw new RuntimeException("Soru bulunamadı");
        }

        // Soru tipine göre Answer nesnesi oluştur
        Answer newAnswer = Answer.builder()
                .date(new Date())
                .question(question.get())
                .answer(dto.getAnswer())
                .surveyId(dto.getSurveyid())
                .student(student.get())
                .build();
        repository.save(newAnswer);

        return BaseResponseDto.builder()
                .message("Answer saved successfully")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    //Admin ve öğretmen erişebilir.
    public List<GetAllAnswerStudentResponseDto> getAllAnswerByStudentIdAndSurveyId(String token, Long surveyId, Long studentId) {
        Optional<Long> adminId = jsonTokenManager.getIdByToken(token);
        if (adminId.isEmpty()) {
            throw new TokenNotFoundException();
        }

        Optional<Admin> adminOptional = adminService.findById(adminId.get());
        if (adminOptional.isEmpty()) {
            throw new AdminNotFoundException();
        }

        Optional<Teacher> teacherOptional = teacherService.findById(adminId.get());
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("admin not found");
        }


        Optional<Student>studentOptional=studentService.findById(studentId);

        // Eğer admin bir öğrenci ise hata at
        if (!studentOptional.get().getId().equals(studentId)) {
            throw new RuntimeException("Unauthorized access");
        }

        List<Answer> answerList = repository.findByStudentIdAndSurveyId(surveyId, studentId);

        //ve cevap listesine for ile kaydedilir.
        List<GetAllAnswerStudentResponseDto> dtos = new ArrayList<>();
        for (Answer answer : answerList) {
            dtos.add(GetAllAnswerStudentResponseDto.builder()
                    .answer(answer.getAnswer())
                    .surveyId(answer.getSurveyId())
                    .date(answer.getDate())
                    .build());
        }
        return dtos;
    }



}
