package otyom.anketSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otyom.anketSpring.dto.request.GetAdminByIdRequestDto;
import otyom.anketSpring.dto.request.SaveAdminRequestDto;
import otyom.anketSpring.dto.response.GetAdminByIdResponseDto;
import otyom.anketSpring.dto.request.LoginAdminRequestDto;
import otyom.anketSpring.dto.response.LoginAdminResponseDto;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IAdminRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private IAdminRepository repository;
    @Autowired
    private JsonTokenManager jsonTokenManager;

    public void save(SaveAdminRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        /*if (id.isEmpty()){
            throw new RuntimeException();
        }*/


        Admin admin= Admin.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .cinsiyet(dto.getCinsiyet())
                .role(RoleEnum.Admin_Role)
                .tc(dto.getTc())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        repository.save(admin);
    }

    public List<Admin> getAll() {
        return repository.findAll();
    }

    public GetAdminByIdResponseDto findById(GetAdminByIdRequestDto dto) {


        Optional<Admin> admin=repository.findById(dto.getId());
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



}
