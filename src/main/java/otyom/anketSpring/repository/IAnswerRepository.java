package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Answer;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {
}