package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Survey_Question;

@Repository
public interface ISurveyQuestionRepository extends JpaRepository<Survey_Question,Long> {


}
