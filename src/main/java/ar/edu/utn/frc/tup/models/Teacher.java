package ar.edu.utn.frc.tup.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    private String matricula;
    private String nombre;
    private String materia;
}
