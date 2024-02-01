package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.LoginOgrenciRequestDto;
import otyom.anketSpring.dto.request.LoginOgretmenRequestDto;
import otyom.anketSpring.dto.request.SaveOgretmenRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.LoginOgrenciResponseDto;
import otyom.anketSpring.dto.response.LoginOgretmenResponseDto;
import otyom.anketSpring.entity.Ogrenci;
import otyom.anketSpring.entity.Ogretmen;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IOgretmenRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@Service
public class OgretmenService {
    @Autowired
    private IOgretmenRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    public BaseResponseDto saveOgretmen(SaveOgretmenRequestDto dto){
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        /*if (id.isEmpty()){
            throw new RuntimeException();
        }*/

        Ogretmen ogretmen=(Ogretmen) Ogretmen.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .cinsiyet(dto.getCinsiyet())
                .phoneNumber(dto.getPhoneNumber())
                .tc(dto.getTc())
                .password(dto.getPassword())
                .role(RoleEnum.Ogretmen_Role)
                .build();
        repository.save(ogretmen);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    //Oğretmen login
    public LoginOgretmenResponseDto ogretmenLogin(LoginOgretmenRequestDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException();           }

        Optional<Ogretmen> ogretmen=repository.findOptionalByEmail(dto.getEmail());
        if (!ogretmen.get().getPassword().equals(dto.getPassword())){
            throw  new RuntimeException();
    }
        Optional<String>token=jsonTokenManager.createToken(ogretmen.get().getId());
        return LoginOgretmenResponseDto.builder()
                .token(token.get())
                .httpStatus(HttpStatus.OK)
                .message("ok")
                .build();

}




}
