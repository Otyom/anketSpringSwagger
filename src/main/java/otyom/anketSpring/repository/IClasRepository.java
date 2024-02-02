package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Clas;

@Repository
public interface IClasRepository extends JpaRepository<Clas,Long> {
}
