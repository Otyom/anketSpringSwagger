package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByclasId(Long sinifId);

    Boolean existsByEmail(String email);

    Optional<Student>findOptionalByEmail(String email);
}
