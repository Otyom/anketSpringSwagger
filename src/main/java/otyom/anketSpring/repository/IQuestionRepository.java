package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Question;

import java.util.Optional;

@Repository
public interface IQuestionRepository extends JpaRepository<Question,Long> {
    Optional<Question> findByQuestion(String question);
}
