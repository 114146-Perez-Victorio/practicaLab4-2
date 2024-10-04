package ar.edu.utn.frc.tup.DTO;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnDTO {

    private String legajo;
    private String nombre;
    private List<SubjectDTO> materias;
}
