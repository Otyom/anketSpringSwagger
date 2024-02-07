package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.Exception.userexceptions.TokenNotFoundException;
import otyom.anketSpring.dto.request.SaveQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetAllQuestionByStudentResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.entity.QuestionType.Secenek;
import otyom.anketSpring.entity.QuestionType.Text;
import otyom.anketSpring.entity.QuestionType.YesNo;
import otyom.anketSpring.entity.Student;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.entity.enums.QuestionType;
import otyom.anketSpring.repository.IQuestionRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private IQuestionRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;



    public BaseResponseDto questionSave(SaveQuestionRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()){
            throw new TokenNotFoundException();
        }

        Optional<Admin> adminOptional = adminService.findById(id.get());
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());

        if (adminOptional.isEmpty() || teacherOptional.isEmpty()){
            throw new RuntimeException();
        }



        Optional<Question> question = repository.findByQuestion(dto.getQuestion());
        if (question.isPresent()) {
            return BaseResponseDto.builder()
                    .message("Aynı sorudan mevcut")
                    .statusCode(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        Question newQuestion = Question.builder()
                .date(new Date())
                .teacherId(teacherOptional.get().getId())
                .question(dto.getQuestion())
                .type(dto.getQuestionType())
                .build();
        newQuestion.setType(dto.getQuestionType());
        if (dto.getQuestionType()==QuestionType.YESNO){
            newQuestion.setAnswerType(YesNo.class.toString());
        } else if (dto.getQuestionType()==QuestionType.SEÇENEK) {
            newQuestion.setAnswerType(Secenek.class.toString());
        } else if (dto.getQuestionType()==QuestionType.TEXT) {
               newQuestion.setAnswerType(Text.class.toString());
        }
        repository.save(newQuestion);

        return BaseResponseDto.builder()
                .message("Question saved successfully")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    //admin ve öğrenci erişebilir.
    public List<GetAllQuestionByStudentResponseDto> getQuestionsByStudentIdAndSurveyId(String token, Long studentId, Long surveyId) {
        Optional<Long> id= jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new TokenNotFoundException();
        }

        Optional<Admin> adminOptional = adminService.findById(id.get());
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());

        if (adminOptional.isEmpty() || teacherOptional.isEmpty()){
            throw new RuntimeException();
        }

        Optional<Student>studentOptional=studentService.findById(studentId);

        if (!studentOptional.get().getId().equals(studentId)) {
            throw new RuntimeException("yetkiniz yok");
        }

        List<Question> questionsList =repository.findQuestionsByStudentAndSurvey(studentId, surveyId);

        //ve cevap listesine for ile kaydedilir.
        List<GetAllQuestionByStudentResponseDto> dtos = new ArrayList<>();
        for (Question question : questionsList) {
            dtos.add(GetAllQuestionByStudentResponseDto.builder()
                    .type(question.getType())
                    .date(question.getDate())
                    .teacherId(question.getTeacherId())
                    .question(question.getQuestion())
                    .build());
        }
        return dtos;
    }

    public Optional<Question> findById(Long questionId) {
        Optional<Question> question=repository.findById(questionId);
       return question;
    }
}
