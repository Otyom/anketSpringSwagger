package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Answer;
import otyom.anketSpring.entity.Question;
import otyom.anketSpring.entity.Student;
import otyom.anketSpring.entity.Survey;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByclasId(Long sinifId);

    Boolean existsByEmail(String email);

    Optional<Student>findOptionalByEmail(String email);





}
