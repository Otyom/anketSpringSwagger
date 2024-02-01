package otyom.anketSpring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Columns;
import otyom.anketSpring.entity.Sinif;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SaveOgrenciRequestDto {
    @NonNull
    private Long sınıfId;
    private String tc;
    private Gender Cinsiyet;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String token;

}
