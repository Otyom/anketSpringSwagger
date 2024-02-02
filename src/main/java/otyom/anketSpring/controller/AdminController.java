package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.*;
import otyom.anketSpring.dto.response.*;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.service.AdminService;
import otyom.anketSpring.service.StudentService;
import otyom.anketSpring.service.TeacherService;
import otyom.anketSpring.service.ClasService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClasService clasService;
    @Autowired
    private StudentService studentService;


    //Admin  İşlemleri
   @PostMapping("/saveAdmin")
   public void save(@RequestBody SaveAdminRequestDto dto){
    adminService.saveAdmin(dto);
   }

   @GetMapping("/getAll")
   public ResponseEntity<List<Admin>> getAdmin(){
    return ResponseEntity.ok(adminService.getAll());
   }
   @PostMapping("/getAdminById")
    public ResponseEntity<GetAdminByIdResponseDto>getAdminById(@RequestBody GetAdminByIdRequestDto dto){
       return  ResponseEntity.ok(adminService.findById(dto));
   }
   @PostMapping("/login")
   private ResponseEntity<LoginAdminResponseDto>login(@RequestBody LoginAdminRequestDto dto){
       return ResponseEntity.ok(adminService.login(dto));

   }





   //öğretmen işlemleri
   @PostMapping("/saveTeacher")
    public ResponseEntity<BaseResponseDto>teacherSave(SaveTeacherRequestDto dto){
       return ResponseEntity.ok(teacherService.saveTeacher(dto));
   }




   //Sınıf işlemleri
    @PostMapping("/saveClas")
    public ResponseEntity<BaseResponseDto>clasSave(SaveClasRequestDto dto){
        return ResponseEntity.ok(clasService.clasSave(dto));
    }


    //Öğrenci İşlemleri
    @PostMapping("/saveStudent")
    public ResponseEntity<BaseResponseDto>studentSave(@RequestBody SaveStudentRequestDto dto){
       return ResponseEntity.ok(studentService.studentSave(dto));
    }

    @GetMapping("/getSutudentByClasId")
    public ResponseEntity<List<GetClasStudentResponseDto>> getAllStudentByClasId(@RequestParam Long clasId){
        return ResponseEntity.ok(studentService.getAllStudentByClasId(clasId));
    }










    //Runtime hata fırlatma ekrana örnek
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String>runtimeException(){
       return new ResponseEntity<>("hata aldık", HttpStatus.BAD_GATEWAY);
    }



}
