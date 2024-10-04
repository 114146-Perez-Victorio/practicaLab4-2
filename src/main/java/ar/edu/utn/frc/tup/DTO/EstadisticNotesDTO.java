package ar.edu.utn.frc.tup.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadisticNotesDTO {
    private String materia;
    private EstadoDTO estado;
    private String resultado;
}
