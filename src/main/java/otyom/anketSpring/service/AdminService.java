package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.GetAdminByIdRequestDto;
import otyom.anketSpring.dto.request.SaveAdminRequestDto;
import otyom.anketSpring.dto.response.*;
import otyom.anketSpring.dto.request.LoginAdminRequestDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IAdminRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private IAdminRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;



    public void saveAdmin(SaveAdminRequestDto dto) {
  /*      Optional<Long> id = jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Admin> adminOptional = repository.findById(id.get());
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }*/
        Admin admin= Admin.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .cinsiyet(dto.getGender())
                .role(RoleEnum.ADMIN_ROLE)
                .tc(dto.getTc())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        repository.save(admin);
    }

    public List<GetAllAdminResponseDto> getAll(String token) {
        Optional<Long> id = jsonTokenManager.getIdByToken(token);
        if (id.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Optional<Admin> adminOptional = repository.findById(id.get());
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        Admin admin = adminOptional.get();


        List<Admin> adminList = repository.findAll();

        //ve cevap listesine  for ile kaydettim.
        List<GetAllAdminResponseDto> dtos = new ArrayList<>();
        for (Admin admin1 : adminList) {
            dtos.add(GetAllAdminResponseDto.builder()
                            .name(admin1.getName())
                            .gender(admin1.getCinsiyet())
                            .email(admin1.getEmail())
                            .surname(admin1.getSurname())
                            .httpStatus(HttpStatus.OK)
                            .message("ok")
                            .role(admin1.getRole())
                    .message("ok")
                    .statusCode(200)
                    .build());
        }
        return dtos;
    }

    public GetAdminByIdResponseDto getAdminfindById(GetAdminByIdRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id.isEmpty()){
            throw new RuntimeException();
        }
        Optional<Admin> admin=repository.findById(id.get());
        if (admin.isEmpty())throw new RuntimeException();

        return GetAdminByIdResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .email(admin.get().getEmail())
                .name(admin.get().getName())
                .surname(admin.get().getSurname())
                .email(admin.get().getEmail())
                .build();
    }

    public LoginAdminResponseDto login(LoginAdminRequestDto dto) {
        if (!repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException();
        }
        //kullanıcının e-maili varsa kullanıcıyı getir
        Optional<Admin> admin=repository.findOptionalByEmail(dto.getEmail());

        //email  şifresi eşit değise hata at
        if(!admin.get().getPassword().equals(dto.getPassword())){
            throw new RuntimeException();
        }

        //eşitse id ver token oluştur
        Optional<String> token=jsonTokenManager.createToken(admin.get().getId());
        return LoginAdminResponseDto.builder()
                .message("ok")
                .statusCode(2001)
                .token(token.get()) //burya ver
                .build();
    }

    public Optional<Admin> findById(Long id) {
        Optional<Admin> admin=repository.findById(id);
        return admin;
    }
}
