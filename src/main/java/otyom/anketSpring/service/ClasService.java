package otyom.anketSpring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveClasRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Clas;
import otyom.anketSpring.repository.IClasRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@Service
public class ClasService {

    @Autowired
    private IClasRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    public BaseResponseDto clasSave(SaveClasRequestDto dto) {
      Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()){
            throw new RuntimeException();
        }

        Clas clas = Clas.builder()
                .clasName(dto.getName())
                .build();
        repository.save(clas);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .build();
    }






}
