package otyom.anketSpring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveSınıfRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Sinif;
import otyom.anketSpring.repository.ISınıfRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@Service
public class SınıfService {

    @Autowired
    private ISınıfRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    public BaseResponseDto sınıfSave(SaveSınıfRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        /*if (id.isEmpty()){
            throw new RuntimeException();
        }*/


        Sinif sınıf = Sinif.builder()
                .sinifName(dto.getName())
                .build();
        repository.save(sınıf);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .build();
    }






}
