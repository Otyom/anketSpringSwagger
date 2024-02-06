package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Survey;

import java.util.List;

@Repository
public interface ISurveyRepository extends JpaRepository<Survey,Long> {


    @Query("SELECT s.id FROM Survey s JOIN s.classes c WHERE c.id = :classId")
    List<Long> findSurveyIdByClassId(Long classId);
}
