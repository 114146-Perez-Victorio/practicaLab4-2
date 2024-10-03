package ar.edu.utn.frc.tup.repositories;

import ar.edu.utn.frc.tup.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
}
