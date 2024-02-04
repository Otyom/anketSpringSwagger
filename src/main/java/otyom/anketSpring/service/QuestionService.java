package otyom.anketSpring.service;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveQuestionRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.entity.QuestionType.Secenek;
import otyom.anketSpring.entity.QuestionType.Text;
import otyom.anketSpring.entity.QuestionType.YesNo;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.entity.enums.QuestionType;
import otyom.anketSpring.repository.IQuestionRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Date;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private IQuestionRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;
    @Autowired
    private TeacherService teacherService;



    public BaseResponseDto questionSave(SaveQuestionRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id==null)throw new RuntimeException();

        Optional<Teacher> teacher=teacherService.findById(id.get());
        if (teacher.isEmpty())throw new RuntimeException();

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
                .teacher(teacher.get())
                .question(dto.getQuestion())
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
                .message("Answer saved successfully")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }




    public boolean existsById(Long questionId) {
        repository.existsById(questionId);
        return true;
    }

    public Optional<Question> findById(Long questionId) {
        Optional<Question> question=repository.findById(questionId);
       return question;
    }
}
