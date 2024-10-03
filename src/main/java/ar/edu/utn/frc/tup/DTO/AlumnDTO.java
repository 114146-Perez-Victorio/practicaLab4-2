package ar.edu.utn.frc.tup.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnDTO {
    private String legajo;
    private String nombre;
}
