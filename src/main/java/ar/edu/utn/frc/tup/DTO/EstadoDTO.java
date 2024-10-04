package ar.edu.utn.frc.tup.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoDTO {
    private String libre;
    private String promocionado;
    private String regular;
}
