package ar.edu.utn.frc.tup.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="profesores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherEntity {
    @Id
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String materia;
}
