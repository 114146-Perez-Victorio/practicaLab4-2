package ar.edu.utn.frc.tup.services;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.DTO.EstadisticNotesDTO;
import ar.edu.utn.frc.tup.DTO.NoteRecordDTO;
import ar.edu.utn.frc.tup.DTO.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GestionService {

List<AlumnDTO> getAlumnoBD();
List<TeacherDTO> getTeacherBD();


List<AlumnDTO> registrarBD();
NoteRecordDTO registrarNota(String legajo, String materia, int calificacion);
List<EstadisticNotesDTO> estadisticReport(String materia);

}
