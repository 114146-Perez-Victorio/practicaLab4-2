package ar.edu.utn.frc.tup.repositories;

import ar.edu.utn.frc.tup.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.security.auth.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findByMateria(String materia); // Cambia el nombre a findByMateria
}
