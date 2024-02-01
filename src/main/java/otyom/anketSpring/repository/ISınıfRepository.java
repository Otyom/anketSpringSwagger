package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Sinif;

@Repository
public interface ISınıfRepository extends JpaRepository<Sinif,Long> {
}
