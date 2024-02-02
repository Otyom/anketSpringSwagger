package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.LoginTeacherRequestDto;
import otyom.anketSpring.dto.request.SaveTeacherRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.LoginTeacherResponseDto;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.ITeacherRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private ITeacherRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    public BaseResponseDto saveTeacher(SaveTeacherRequestDto dto){
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()){
            throw new RuntimeException();
        }

        Teacher ogretmen= Teacher.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .cinsiyet(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .tc(dto.getTc())
                .password(dto.getPassword())
                .role(RoleEnum.TEACHER_ROLE)
                .build();
        repository.save(ogretmen);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    //Oğretmen login
    public LoginTeacherResponseDto teacherLogin(LoginTeacherRequestDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException();           }

        Optional<Teacher> teacher=repository.findOptionalByEmail(dto.getEmail());
        if (!teacher.get().getPassword().equals(dto.getPassword())){
            throw  new RuntimeException();
    }
        Optional<String>token=jsonTokenManager.createToken(teacher.get().getId());
        return LoginTeacherResponseDto.builder()
                .token(token.get())
                .httpStatus(HttpStatus.OK)
                .message("ok")
                .build();


}




}
