package otyom.anketSpring.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.response.GetAllQuestionByStudentResponseDto;
import otyom.anketSpring.dto.request.GetSutudentByIdRequestDto;
import otyom.anketSpring.dto.request.LoginStudentRequestDto;
import otyom.anketSpring.dto.request.SaveStudentRequestDto;
import otyom.anketSpring.dto.response.*;
import otyom.anketSpring.entity.*;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IStudentRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private  IStudentRepository repository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JsonTokenManager jsonTokenManager;
    @Autowired
    private ClasService clasService;





    //Öğrenci kaydet
    public BaseResponseDto studentSave(SaveStudentRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("token not found");
        }
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }

        // dto'dan clasId'yi aldım yoksa hata attım
        Long clasId = dto.getClasId();
        if (clasId == null) {
            throw new IllegalArgumentException("clasId cannot be null");
        }

        // varsada Clas'ı buldum ve clas yoksa not found fırlattım.
        Clas clas = clasService.findById(clasId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: "));



        Student student= Student.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .cinsiyet(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .tc(dto.getTc())
                .clas(clas)
                .password(dto.getPassword())
                .role(RoleEnum.STUDENT_ROLE)
                .build();

            repository.save(student);
            return BaseResponseDto.builder()
                    .message("ok")
                    .statusCode(200)
                    .httpStatus(HttpStatus.OK)
                    .build();

    }


    //Sınıftaki öğrencileri Getir.
    public List<GetClasStudentResponseDto> getAllStudentByClasId(String token,Long clasId){
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("token not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty() || teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin or teacher not found");
        }

        List<Student> classes=repository.findByclasId(clasId);
        List<GetClasStudentResponseDto> responseDtos=new ArrayList<>();
        for (Student student :classes){
            responseDtos.add(GetClasStudentResponseDto.builder()
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


    public GetStudentByIdResponseDto getSutudentById(GetSutudentByIdRequestDto dto){
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("token not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty() || teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin or teacher not found");
        }
        Optional<Student> student=repository.findById(dto.getId());
        if (student.isEmpty())throw new RuntimeException();
        return GetStudentByIdResponseDto.builder()
                .email(student.get().getEmail())
                .name(student.get().getName())
                .surname(student.get().getSurname())
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }





















    public Optional<Student> findById(Long id) {
        Optional<Student>student= repository.findById(id);
        return student;
    }
    public  List<Student> getAllStudent(){
        return repository.findAll();
    }
    public Student save(Student student) {
       return repository.save(student);
    }
}
