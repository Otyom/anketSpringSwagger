package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.LoginStudentRequestDto;
import otyom.anketSpring.dto.request.SaveStudentRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetClasStudentResponseDto;
import otyom.anketSpring.dto.response.LoginStudentResponseDto;
import otyom.anketSpring.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/studentLogin")
    private ResponseEntity<LoginStudentResponseDto>studentLogin(@RequestBody LoginStudentRequestDto dto){
        return ResponseEntity.ok(studentService.studentLogin(dto));
    }

    //Öğrenci İşlemleri
    @PostMapping("/saveStudent")
    public ResponseEntity<BaseResponseDto>studentSave(@RequestBody SaveStudentRequestDto dto){
        return ResponseEntity.ok(studentService.studentSave(dto));
    }

    @GetMapping("/getSutudentByClasId")
    public ResponseEntity<List<GetClasStudentResponseDto>> getAllStudentByClasId(@RequestParam String token, @RequestParam Long clasId){
        return ResponseEntity.ok(studentService.getAllStudentByClasId(token,clasId));
    }




}
