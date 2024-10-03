package ar.edu.utn.frc.tup.repositories;

import ar.edu.utn.frc.tup.entities.AlumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnRepository extends JpaRepository<AlumnEntity,String> {
    Optional<AlumnEntity> findByLegajo(String legajo); // Asegúrate de que esto esté presente
}
