package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Survey;

@Repository
public interface ISurveyRepository extends JpaRepository<Survey,Long> {
}
