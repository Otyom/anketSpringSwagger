package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Clas;

import java.util.List;

@Repository
public interface IClasRepository extends JpaRepository<Clas,Long> {

}
