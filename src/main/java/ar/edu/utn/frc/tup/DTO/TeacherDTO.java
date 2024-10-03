package ar.edu.utn.frc.tup.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {

    private String matricula;
    private String nombre;
    private String materia;
}
