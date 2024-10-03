package ar.edu.utn.frc.tup.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="materias")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_materia")
    private AlumnEntity alumnos;

    @Column
    private String docente;

    @Column
    private String materia;

    @Column
    private int calificacion;

    @Column
    private String estado;
}
