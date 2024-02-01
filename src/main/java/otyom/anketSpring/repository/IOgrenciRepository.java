package otyom.anketSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otyom.anketSpring.entity.Admin;
import otyom.anketSpring.entity.Ogrenci;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOgrenciRepository extends JpaRepository<Ogrenci,Long> {

    List<Ogrenci> findBysinifId(Long sinifId);

    Boolean existsByEmail(String email);

    Optional<Ogrenci>findOptionalByEmail(String email);
}
