package ar.edu.utn.frc.tup.controllers;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.DTO.NoteRecord;
import ar.edu.utn.frc.tup.DTO.TeacherDTO;
import ar.edu.utn.frc.tup.client.RestClient;
import ar.edu.utn.frc.tup.services.GestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class GestionController {

    @Autowired
    RestClient restClient;
    @Autowired
    GestionService gestionService;

@GetMapping("/alumns")
    public ResponseEntity<List<AlumnDTO>> getAllAlumns(){
    List<AlumnDTO> alumn = gestionService.getAlumnoBD();
    return ResponseEntity.ok(alumn);
}

@GetMapping("/teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
    List<TeacherDTO> teacher = gestionService.getTeacherBD();
    return ResponseEntity.ok(teacher);
}


//    El endpoint /gestion es esencial para establecer una base de datos estructurada y funcional en tu aplicación,
//    Una vez que implementes y pruebes este endpoint, podrás avanzar en el desarrollo de otras funcionalidades de tu sistema educativo
//    Básicamente estructura la base de datos el POST permitiendo así avanzar con otros endpoints

    @PostMapping("/gestion")
    public ResponseEntity<List<AlumnDTO>> registrarMateria(){
    List<AlumnDTO> alumnMateria = gestionService.registrarBD();
    return ResponseEntity.ok(alumnMateria);
}

    @PutMapping("/registrarNota")
    public ResponseEntity<?> RegistrarNota(@RequestBody NoteRecord noteRecord){
        NoteRecord response = gestionService.registrarNota(noteRecord.getLegajo(),noteRecord.getMateria(),noteRecord.getCalificacion());

        return ResponseEntity.ok(response);
    }

//FALTA LÓGICA DE PORCENTAJE, TESTING Y DOCKERIZAR

}
