package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveAnswerRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Answer;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.entity.Student;
import otyom.anketSpring.repository.IAnswerRepository;
import otyom.anketSpring.repository.IQuestionRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Date;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private IAnswerRepository answerRepository;

    @Autowired
    private IQuestionRepository questionRepository;
    @Autowired
    private StudentService studentService;

    @Autowired
    private JsonTokenManager jsonTokenManager;

    public BaseResponseDto saveAnswer(SaveAnswerRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Invalid student ıd");
        }
        Optional<Student> studentOptional = studentService.findById(id.get());
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        //dtodadi id'yi bul soruya ata.
        Optional<Question> question = questionRepository.findById(dto.getQuestionId());
        if (question.isEmpty()) {
            throw new RuntimeException("Question not found");
        }

        // Soru tipine göre Answer nesnesi oluştur
        Answer newAnswer = Answer.builder()
                .date(new Date())
                .question(question.get())
                .answer(dto.getAnswer())
                .build();
        answerRepository.save(newAnswer);

        return BaseResponseDto.builder()
                .message("Answer saved successfully")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }



}
