package otyom.anketSpring.service;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import otyom.anketSpring.dto.request.GetAdminByIdRequestDto;
import otyom.anketSpring.dto.request.LoginAdminRequestDto;
import otyom.anketSpring.dto.request.LoginOgrenciRequestDto;
import otyom.anketSpring.dto.request.SaveOgrenciRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.dto.response.GetSınıfOgrenciResponseDto;
import otyom.anketSpring.dto.response.LoginAdminResponseDto;
import otyom.anketSpring.dto.response.LoginOgrenciResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Ogrenci;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IOgrenciRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OgrenciService {
    @Autowired
    private IOgrenciRepository repository;

    public final JsonTokenManager jsonTokenManager;

    public OgrenciService(JsonTokenManager jsonTokenManager) {
        this.jsonTokenManager = jsonTokenManager;
    }

    //Öğrenci kaydet
    public BaseResponseDto ogrenciSave(SaveOgrenciRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        /*if (id.isEmpty()){
            throw new RuntimeException();
        }*/


        Ogrenci ogrenci= (Ogrenci) Ogrenci.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .cinsiyet(dto.getCinsiyet())
                .phoneNumber(dto.getPhoneNumber())
                .sinifId(dto.getSınıfId())
                .tc(dto.getTc())
                .password(dto.getPassword())
                .role(RoleEnum.Ogrenci_Role)
                .build();
        if (ogrenci.getSinifId()==null || ogrenci.getSinifId()==0){
            throw new RuntimeException();
        }else {
            repository.save(ogrenci);
            return BaseResponseDto.builder()
                    .message("ok")
                    .statusCode(200)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }


    //Sınıftaki öğrencileri Getir.
    public List<GetSınıfOgrenciResponseDto> getAllOgrenciBySınıfId(long sınıfId){
        List<Ogrenci> ogrencis =repository.findBysinifId(sınıfId);

        List<GetSınıfOgrenciResponseDto> responseDtos=new ArrayList<>();
        for (Ogrenci ogrenci:ogrencis){
            responseDtos.add(GetSınıfOgrenciResponseDto.builder()
                            .sinifId(ogrenci.getSinifId())
                            .name(ogrenci.getName())
                            .surname(ogrenci.getSurname())
                            .cinsiyet(ogrenci.getCinsiyet())
                            .email(ogrenci.getEmail())
                            .message("ok")
                            .StatusCode(200)
                    .build());
        }
        return responseDtos;
    }



    //Öğrenci Login
    public LoginOgrenciResponseDto ogrenciLogin(LoginOgrenciRequestDto dto){
        if (!repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException();
        }
        Optional<Ogrenci> ogrenci=repository.findOptionalByEmail(dto.getEmail());

        if (!ogrenci.get().getPassword().equals(dto.getPassword())){
            throw new RuntimeException();
        }

        Optional<String>token= jsonTokenManager.createToken(ogrenci.get().getId());
        return LoginOgrenciResponseDto.builder()
                .message("ok")
                .statusCode(2001)
                .token(token.get())
                .build();
    }





    public  List<Ogrenci>getAllOgrenci(){
        return repository.findAll();
    }
}
