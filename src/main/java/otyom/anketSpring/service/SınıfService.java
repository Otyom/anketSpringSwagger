package otyom.anketSpring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.SaveSınıfRequestDto;
import otyom.anketSpring.dto.response.BaseResponseDto;
import otyom.anketSpring.entity.Sinif;
import otyom.anketSpring.repository.ISınıfRepository;

@Service
public class SınıfService {

    @Autowired
    private ISınıfRepository repository;

    public BaseResponseDto sınıfSave(SaveSınıfRequestDto dto) {
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
