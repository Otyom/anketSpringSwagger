package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.dto.request.LoginTeacherRequestDto;
import otyom.anketSpring.dto.response.LoginTeacherResponseDto;
import otyom.anketSpring.service.StudentService;
import otyom.anketSpring.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

@PostMapping("/ogretmenLogin")
private ResponseEntity<LoginTeacherResponseDto> teacherLogin(@RequestBody LoginTeacherRequestDto dto){
    return ResponseEntity.ok(teacherService.teacherLogin(dto));
}





}