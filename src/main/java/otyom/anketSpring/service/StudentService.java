package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.LoginStudentRequestDto;
import otyom.anketSpring.dto.request.SaveStudentRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetClasStudentResponseDto;
import otyom.anketSpring.dto.response.LoginStudentResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Student;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IStudentRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository repository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;

    public final JsonTokenManager jsonTokenManager;
    public StudentService(JsonTokenManager jsonTokenManager) {
        this.jsonTokenManager = jsonTokenManager;
    }

    //Öğrenci kaydet
    public BaseResponseDto studentSave(SaveStudentRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }
        Student student= Student.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .cinsiyet(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .clasId(dto.getClasId())
                .tc(dto.getTc())
                .password(dto.getPassword())
                .role(RoleEnum.STUDENT_ROLE)
                .build();
        if (student.getClasId()==null || student.getClasId()==0){
            throw new RuntimeException();
        }else {
            repository.save(student);
            return BaseResponseDto.builder()
                    .message("ok")
                    .statusCode(200)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }


    //Sınıftaki öğrencileri Getir.
    public List<GetClasStudentResponseDto> getAllStudentByClasId(String token,Long clasId){
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty() || teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }

        List<Student> classes=repository.findByclasId(clasId);
        List<GetClasStudentResponseDto> responseDtos=new ArrayList<>();
        for (Student student :classes){
            responseDtos.add(GetClasStudentResponseDto.builder()
                            .clasId(student.getClasId())
                            .name(student.getName())
                            .surname(student.getSurname())
                            .gender(student.getCinsiyet())
                            .email(student.getEmail())
                            .message("ok")
                            .StatusCode(200)
                    .build());
        }
        return responseDtos;
    }


    //Öğrenci Login
    public LoginStudentResponseDto studentLogin(LoginStudentRequestDto dto){
        if (!repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException();
        }
        Optional<Student> student=repository.findOptionalByEmail(dto.getEmail());

        if (!student.get().getPassword().equals(dto.getPassword())){
            throw new RuntimeException();
        }

        Optional<String>token= jsonTokenManager.createToken(student.get().getId());
        return LoginStudentResponseDto.builder()
                .message("ok")
                .statusCode(2001)
                .token(token.get())
                .build();
    }


    public  List<Student> getAllStudent(){
        return repository.findAll();
    }

    public Optional<Student> findById(Long id) {
       Optional<Student>student= repository.findById(id);
       return student;
    }
}
