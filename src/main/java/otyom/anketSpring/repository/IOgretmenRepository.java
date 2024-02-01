package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Ogrenci;
import otyom.anketSpring.entity.Ogretmen;

import java.util.Optional;

@Repository
public interface IOgretmenRepository extends JpaRepository<Ogretmen,Long> {
    Boolean existsByEmail(String email);

    Optional<Ogretmen> findOptionalByEmail(String email);
}
