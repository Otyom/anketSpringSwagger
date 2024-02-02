package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveSurveyRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Survey;
import otyom.anketSpring.repository.ISurveyRepository;

@Service
public class SurveyService {
    @Autowired
    private ISurveyRepository repository;


    public BaseResponseDto surveySave(SaveSurveyRequestDto dto){

        Survey survey=Survey.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
        repository.save(survey);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
