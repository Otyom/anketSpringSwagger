package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface IQuestionRepository extends JpaRepository<Question,Long> {
    Optional<Question> findByQuestion(String question);
    @Query("SELECT q FROM Question q JOIN q.answers a JOIN a.student s JOIN a.surveyId sur WHERE s.id = :studentId AND sur = :surveyId")
    List<Question> findQuestionsByStudentAndSurvey(@Param("studentId") Long studentId, @Param("surveyId") Long surveyId);
}
