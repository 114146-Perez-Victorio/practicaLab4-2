package ar.edu.utn.frc.tup.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO {
    private String docente;
    private String materia;
    private int calificacion;
    private String estado;


}
