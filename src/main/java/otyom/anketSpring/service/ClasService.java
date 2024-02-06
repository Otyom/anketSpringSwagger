package otyom.anketSpring.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveClasRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Clas;
import otyom.anketSpring.entity.Survey;
import otyom.anketSpring.entity.Teacher;
import otyom.anketSpring.repository.IClasRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.List;
import java.util.Optional;

@Service
public class ClasService {

    @Autowired
    private IClasRepository repository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    //sadece admin erişebilir.
    public BaseResponseDto clasSave(SaveClasRequestDto dto) {
        Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Admin> adminOptional = adminService.findById(id.get());
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }

        Clas clas = Clas.builder()
                .clasName(dto.getName())
                .build();
        repository.save(clas);

        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }




    //sınıfa öğrenci ekle  Gereksiz zaten öğrenci eklerken sınıf zorunlu tutuluyor. Kalsın şimdilik.
   /* public BaseResponseDto addStudentToClass(Long studentId, Long classId,String token) {

        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Teacher> teacherOptional = teacherService.findById(id.get());
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("Admin veya teacher not found");
        }


        Student student = studentService.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id"));

        Clas clas = repository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id"));

        student.setClas(clas);
        studentService.save(student);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }
*/




    public Optional<Clas> findById(Long id) {
        return repository.findById(id);
    }


}
