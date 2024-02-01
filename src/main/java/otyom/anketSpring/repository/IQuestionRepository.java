package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Question;

@Repository
public interface IQuestionRepository extends JpaRepository<Question,Long> {
}
