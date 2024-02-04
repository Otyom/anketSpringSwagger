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
   public ResponseEntity<List<GetAllAdminResponse>> getAdmin(@RequestParam String token){
    return ResponseEntity.ok(adminService.getAll(token));
   }
   @PostMapping("/getAdminById")
    public ResponseEntity<GetAdminByIdResponseDto>getAdminById(@RequestBody GetAdminByIdRequestDto dto){
       return  ResponseEntity.ok(adminService.getAdminfindById(dto));
   }
   @PostMapping("/login")
   private ResponseEntity<LoginAdminResponseDto>login(@RequestBody LoginAdminRequestDto dto){
       return ResponseEntity.ok(adminService.login(dto));

   }

















    //Runtime hata fırlatma ekrana örnek
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String>runtimeException(){
       return new ResponseEntity<>("hata aldık", HttpStatus.BAD_GATEWAY);
    }



}
