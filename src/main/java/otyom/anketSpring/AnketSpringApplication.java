package otyom.anketSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import otyom.anketSpring.controller.AdminController;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IAdminRepository;
import otyom.anketSpring.util.JsonTokenManager;

import java.util.Optional;

@SpringBootApplication
public class AnketSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnketSpringApplication.class, args);



	}



}
