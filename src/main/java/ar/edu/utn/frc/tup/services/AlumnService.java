package ar.edu.utn.frc.tup.services;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import org.springframework.stereotype.Service;


public interface AlumnService {

AlumnDTO createAlumn(AlumnDTO alumn);

AlumnDTO updateByLegajo(String legajo,String newName);


}
