package ar.edu.utn.frc.tup.services;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.DTO.NoteRecord;
import ar.edu.utn.frc.tup.DTO.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GestionService {

List<AlumnDTO> getAlumnoBD();
List<TeacherDTO> getTeacherBD();


List<AlumnDTO> registrarBD();
NoteRecord registrarNota(String legajo, String materia, int calificacion);


}
