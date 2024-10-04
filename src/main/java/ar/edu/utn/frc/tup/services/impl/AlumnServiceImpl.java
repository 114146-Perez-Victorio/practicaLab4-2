package ar.edu.utn.frc.tup.services.impl;

import ar.edu.utn.frc.tup.DTO.AlumnDTO;
import ar.edu.utn.frc.tup.DTO.SubjectDTO;
import ar.edu.utn.frc.tup.entities.AlumnEntity;
import ar.edu.utn.frc.tup.entities.SubjectEntity;
import ar.edu.utn.frc.tup.repositories.AlumnRepository;
import ar.edu.utn.frc.tup.services.AlumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnServiceImpl implements AlumnService {

    @Autowired
    private AlumnRepository alumnRepository;

    //Para hacer POST
    @Override
    public AlumnDTO createAlumn(AlumnDTO alumn) {
        //Crear un alumno nuevo
        AlumnEntity alumn1 = new AlumnEntity();

        alumn1.setLegajo(alumn.getLegajo());
        alumn1.setNombre(alumn.getNombre());

        AlumnEntity alumnNew = alumnRepository.save(alumn1);

        return AlumnDTO.builder().legajo(alumnNew.getLegajo())
                .nombre(alumnNew.getNombre())
                .build();

    }

    @Override
    public AlumnDTO updateByLegajo(String legajo,String newName) {
        Optional<AlumnEntity> optionalAlumn = alumnRepository.findByLegajo(legajo);
        if (optionalAlumn.isPresent()) {
            AlumnEntity alumn = optionalAlumn.get();
            alumn.setNombre(newName);  // Actualiza solo el nombre
            alumnRepository.save(alumn); // Guarda el alumno actualizado

            // Devuelve el DTO actualizado
            return new AlumnDTO(alumn.getLegajo(), alumn.getNombre(), convertSubjectsToDTO(alumn.getMaterias()));
        } else {
            throw new RuntimeException("Alumno no encontrado");
        }


}

    private List<SubjectDTO> convertSubjectsToDTO(List<SubjectEntity> subjects) {
        return subjects.stream().map(subject -> {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setMateria(subject.getMateria());
            subjectDTO.setDocente(subject.getDocente());
            subjectDTO.setCalificacion(subject.getCalificacion());
            subjectDTO.setEstado(subject.getEstado());
            return subjectDTO;
        }).collect(Collectors.toList());
        }
}
