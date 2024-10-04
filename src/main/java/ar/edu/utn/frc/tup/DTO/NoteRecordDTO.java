package ar.edu.utn.frc.tup.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRecordDTO {
    private String legajo;
    private String materia;
    private int calificacion;
}
