package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveAdminRequestDto {
   private String token;
   private Gender cinsiyet;
   private String name;
   private String surname;
   private String email;
   private String password;
   private String phoneNumber;
   private String tc;}