package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Admin;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {

    Boolean existsByEmail(String email);
    Optional<Admin>findOptionalByEmail(String email);



}
