package ar.edu.utn.frc.tup.controllers;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.DTO.EstadisticNotesDTO;
import ar.edu.utn.frc.tup.DTO.NoteRecordDTO;
import ar.edu.utn.frc.tup.DTO.TeacherDTO;
import ar.edu.utn.frc.tup.services.AlumnService;
import ar.edu.utn.frc.tup.services.GestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestion")
public class GestionController {
    @Autowired
    GestionService gestionService;
    @Autowired
    AlumnService alumnService;


    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

//    El endpoint /inicioBD es esencial para establecer una base de datos estructurada y funcional en tu aplicación,
//    Una vez que implementes y pruebes este endpoint, podrás avanzar en el desarrollo de otras funcionalidades de tu sistema educativo
//    Básicamente estructura la base de datos el POST permitiendo así avanzar con otros endpoints

    @PostMapping("/inicioBD")
    public ResponseEntity<List<AlumnDTO>> registrarBD(){
    List<AlumnDTO> alumnMateria = gestionService.registrarBD();
    return ResponseEntity.ok(alumnMateria);
}

    @PutMapping("/registrarNota")
    public ResponseEntity<?> RegistrarNota(@RequestBody NoteRecordDTO noteRecordDTO){
        NoteRecordDTO response = gestionService.registrarNota(noteRecordDTO.getLegajo(), noteRecordDTO.getMateria(), noteRecordDTO.getCalificacion());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/InformeAcademico")
    public ResponseEntity<List<EstadisticNotesDTO>> informeAcedemico(@RequestParam(value = "materia", required = false)String materia){
        List<EstadisticNotesDTO> estadistica = gestionService.estadisticReport(materia);
        return ResponseEntity.ok(estadistica);

    }


    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        List<TeacherDTO> teacher = gestionService.getTeacherBD();
        return ResponseEntity.ok(teacher);
    }


}
