package otyom.anketSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.GetAllOgrenciBySınıfIdRequestDto;
import otyom.anketSpring.dto.request.LoginAdminRequestDto;
import otyom.anketSpring.dto.request.LoginOgrenciRequestDto;
import otyom.anketSpring.dto.request.SaveOgrenciRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetSınıfOgrenciResponseDto;
import otyom.anketSpring.dto.response.LoginAdminResponseDto;
import otyom.anketSpring.dto.response.LoginOgrenciResponseDto;
import otyom.anketSpring.service.OgrenciService;

import java.util.List;

@RestController
@RequestMapping("/ogrenci")
@AllArgsConstructor
public class OgrenciController {

    @Autowired
    private OgrenciService ogrenciService;




    @PostMapping("/ogrenciLogin")
    private ResponseEntity<LoginOgrenciResponseDto>ogrenciLogin(@RequestBody LoginOgrenciRequestDto dto){
        return ResponseEntity.ok(ogrenciService.ogrenciLogin(dto));
    }

@PostMapping("/saveOgrenci")
 private ResponseEntity<BaseResponseDto>ogrenciSave(@RequestBody SaveOgrenciRequestDto dto){
        return ResponseEntity.ok(ogrenciService.ogrenciSave(dto));
}

}
