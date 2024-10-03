package ar.edu.utn.frc.tup.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="alumnos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String legajo;

    @Column
    private String nombre;

    @OneToMany(mappedBy = "alumnos", cascade = CascadeType.ALL)
    private List<SubjectEntity> materias;
}
