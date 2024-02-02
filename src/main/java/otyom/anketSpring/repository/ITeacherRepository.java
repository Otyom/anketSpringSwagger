package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Teacher;

import java.util.Optional;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher,Long> {
    Boolean existsByEmail(String email);
    Optional<Teacher> findOptionalByEmail(String email);
}
