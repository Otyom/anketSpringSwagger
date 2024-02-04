package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Answer;

import java.util.Optional;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {

}
