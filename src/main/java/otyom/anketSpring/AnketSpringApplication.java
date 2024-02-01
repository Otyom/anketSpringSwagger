package otyom.anketSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import otyom.anketSpring.controller.AdminController;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.User;
import otyom.anketSpring.entity.enums.Gender;
import otyom.anketSpring.entity.enums.RoleEnum;
import otyom.anketSpring.repository.IAdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class AnketSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnketSpringApplication.class, args);
	}


}
