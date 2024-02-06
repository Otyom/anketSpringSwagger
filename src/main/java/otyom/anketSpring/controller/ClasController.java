package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.SaveClasRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Clas;
import otyom.anketSpring.service.ClasService;

import java.util.List;

@RestController
@RequestMapping("/Class")
public class ClasController {
    @Autowired
    private ClasService clasService;

    //Sınıf işlemleri
    @PostMapping("/saveClas")
    public ResponseEntity<BaseResponseDto> clasSave(SaveClasRequestDto dto){
        return ResponseEntity.ok(clasService.clasSave(dto));
    }



}
