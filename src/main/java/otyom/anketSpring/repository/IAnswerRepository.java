package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Answer;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findByStudentIdAndSurveyId(Long studentId, Long surveyId);
}
