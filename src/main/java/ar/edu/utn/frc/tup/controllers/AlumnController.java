package ar.edu.utn.frc.tup.controllers;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.services.AlumnService;
import ar.edu.utn.frc.tup.services.GestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlumnController {

    @Autowired
    private AlumnService alumnService;
    @Autowired
    private GestionService gestionService;

    //Post crear alumno
    @PostMapping("/crearAlumno")
    public ResponseEntity<AlumnDTO> crearAlumno(@RequestBody AlumnDTO createAlumn){
        AlumnDTO alumnDTO = alumnService.createAlumn(createAlumn);
        return ResponseEntity.ok(alumnDTO);
    }

    //Editar alumno
    @PutMapping("/editar/alumno/{legajo}")
    public AlumnDTO updateAlum(@PathVariable String legajo,@RequestBody String newName){
        return alumnService.updateByLegajo(legajo,newName);
    }


    //Get alumnos
    @GetMapping("/alumns")
    public ResponseEntity<List<AlumnDTO>> getAllAlumns(){
        List<AlumnDTO> alumn = gestionService.getAlumnoBD();
        return ResponseEntity.ok(alumn);
    }

}
