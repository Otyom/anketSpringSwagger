package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otyom.anketSpring.dto.request.LoginOgrenciRequestDto;
import otyom.anketSpring.dto.request.LoginOgretmenRequestDto;
import otyom.anketSpring.dto.response.LoginOgrenciResponseDto;
import otyom.anketSpring.dto.response.LoginOgretmenResponseDto;
import otyom.anketSpring.service.OgrenciService;
import otyom.anketSpring.service.OgretmenService;

@RestController
@RequestMapping("/öğretmen")
public class OgretmenController {
    @Autowired
    private OgretmenService ogretmenService;

    @Autowired
    private OgrenciService ogrenciService;

@PostMapping("/ogretmenLogin")
private ResponseEntity<LoginOgretmenResponseDto> ogretmenLogin(@RequestBody LoginOgretmenRequestDto dto){
    return ResponseEntity.ok(ogretmenService.ogretmenLogin(dto));
}





}
