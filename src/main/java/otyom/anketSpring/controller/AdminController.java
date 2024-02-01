package otyom.anketSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otyom.anketSpring.dto.request.*;
import otyom.anketSpring.dto.response.*;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IAdminRepository;
import otyom.anketSpring.service.AdminService;
import otyom.anketSpring.service.OgrenciService;
import otyom.anketSpring.service.OgretmenService;
import otyom.anketSpring.service.SınıfService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OgretmenService ogretmenService;
    @Autowired
    private SınıfService sınıfService;
    @Autowired
    private OgrenciService ogrenciService;


    //Admin  İşlemleri
   @PostMapping("/saveAdmin")
   public void save(@RequestBody SaveAdminRequestDto dto){
    adminService.save(dto);
   }

   @GetMapping("/getAll")
   public ResponseEntity<List<Admin>> getAdmin(){
    return ResponseEntity.ok(adminService.getAll());
   }
   @PostMapping("/getAdminById")
    public ResponseEntity<GetAdminByIdResponseDto>getAdminById(@RequestBody GetAdminByIdRequestDto dto){
       return  ResponseEntity.ok(adminService.findById(dto));
   }
   @PostMapping("/login")
   private ResponseEntity<LoginAdminResponseDto>login(@RequestBody LoginAdminRequestDto dto){
       return ResponseEntity.ok(adminService.login(dto));

   }





   //öğretmen işlemleri
   @PostMapping("/saveOgretmen")
    public ResponseEntity<BaseResponseDto>ogretmenSave(SaveOgretmenRequestDto dto){
       return ResponseEntity.ok(ogretmenService.saveOgretmen(dto));
   }




   //Sınıf işlemleri
    @PostMapping("/saveSınıf")
    public ResponseEntity<BaseResponseDto>sınıfSave(SaveSınıfRequestDto dto){
        return ResponseEntity.ok(sınıfService.sınıfSave(dto));
    }


    //Öğrenci İşlemleri
    @PostMapping("/saveOgrenci")
    public ResponseEntity<BaseResponseDto>ogrenciSave(@RequestBody SaveOgrenciRequestDto dto){
       return ResponseEntity.ok(ogrenciService.ogrenciSave(dto));
    }

    @GetMapping("/getOgrenciByIdSınıf")
    public ResponseEntity<List<GetSınıfOgrenciResponseDto>> getOgrenciBySınıfId(@RequestParam Long sınıfId){
        return ResponseEntity.ok(ogrenciService.getAllOgrenciBySınıfId(sınıfId));
    }










    //Runtime hata fırlatma ekrana örnek
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String>runtimeException(){
       return new ResponseEntity<>("hata aldık", HttpStatus.BAD_GATEWAY);
    }



}
