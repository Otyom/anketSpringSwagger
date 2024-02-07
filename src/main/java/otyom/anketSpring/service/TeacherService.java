package otyom.anketSpring.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.exception.userexceptions.TokenNotFoundException;
import otyom.anketSpring.dto.request.LoginTeacherRequestDto;
import otyom.anketSpring.dto.request.SaveTeacherRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.LoginTeacherResponseDto;
import otyom.anketSpring.entity.*;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.ITeacherRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private ITeacherRepository repository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ClasService clasService;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    //sadeceAdmin
    public BaseResponseDto saveTeacher(SaveTeacherRequestDto dto){
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()){
            throw new TokenNotFoundException();
        }
        Optional<Admin> admin=adminService.findById(id.get());
        if (admin.isEmpty())throw new RuntimeException();

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
        if (!repository.existsByEmail(dto.getEmail())){
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



    //sadece Admin
   @Transactional
    public BaseResponseDto addTeacherToClas(Long teacherId, Long clasId, String token) {

        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Admin> admin = adminService.findById(id.get());
        if (admin.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }

        Teacher teacher = repository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id"));
        Clas clas = clasService.findById(clasId)
                .orElseThrow(() -> new EntityNotFoundException("Clas not found with id"));
       if (teacher.getClasSes().contains(clas)) {
           return BaseResponseDto.builder()
                   .message("Bu öğretmen bu sınıfa daha önce tanımlanmış")
                   .httpStatus(HttpStatus.BAD_REQUEST)
                   .statusCode(400)
                   .build();
       }

        // Öğretmeni sınıfa ekle
        teacher.getClasSes().add(clas);
        repository.save(teacher);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

















    public Optional<Teacher> findById(Long id) {
        Optional<Teacher> teacher=repository.findById(id);
        return teacher;
    }



}
