package ar.edu.utn.frc.tup.services.impl;

import ar.edu.utn.frc.tup.DTO.*;
import ar.edu.utn.frc.tup.client.RestClient;
import ar.edu.utn.frc.tup.entities.AlumnEntity;
import ar.edu.utn.frc.tup.entities.SubjectEntity;
import ar.edu.utn.frc.tup.models.Alumn;
import ar.edu.utn.frc.tup.models.Teacher;
import ar.edu.utn.frc.tup.repositories.AlumnRepository;
import ar.edu.utn.frc.tup.repositories.SubjectRepository;
import ar.edu.utn.frc.tup.repositories.TeacherRepository;
import ar.edu.utn.frc.tup.services.GestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GestionServiceImpl implements GestionService {

    @Autowired
    private AlumnRepository alumnRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private RestClient restClient;


    @Override
    public List<AlumnDTO> getAlumnoBD() {
        // Paso 1: Obtener la lista de alumnos desde el RestClient
        List<Alumn> alumns = restClient.getAllAlumns();

        // Paso 2: Mapear los alumnos de la api externa a nuestro DTO
        List<AlumnDTO> alumnDTOS = alumns.stream().map(alumn -> AlumnDTO.builder()
                .legajo(alumn.getLegajo())
                .nombre(alumn.getNombre())
                .build()).collect(Collectors.toList());

        // Imprimir la lista de AlumnDTO para verificar el mapeo
        alumnDTOS.forEach(alumnDTO -> System.out.println(alumnDTO));

        return alumnDTOS;

    }

    @Override
    public List<TeacherDTO> getTeacherBD() {
        List<Teacher> teacher = restClient.getAllTeachers();
        List<TeacherDTO> teacherDTOS = teacher.stream().map(teacher1 -> TeacherDTO.builder()
                .matricula(teacher1.getMatricula())
                .nombre(teacher1.getNombre())
                .materia(teacher1.getMateria())
                .build()).collect(Collectors.toList());

        teacherDTOS.forEach(teacherDto -> System.out.println(teacherDto));

        return teacherDTOS;

    }

    @Override
    public List<AlumnDTO> registrarBD() {
        // 1. Registrar alumnos y profesores
        List<Alumn> alumnList = restClient.getAllAlumns();
        List<Teacher> teacherList = restClient.getAllTeachers();

        List<AlumnDTO> alumnDTOList = new ArrayList<>();

        // 2. Iterar sobre cada alumno y registrar materias
        for (Alumn alum : alumnList) {
            AlumnEntity alumnEntity = new AlumnEntity();
            alumnEntity.setNombre(alum.getNombre());
            alumnEntity.setLegajo(alum.getLegajo());

            List<SubjectEntity> subjectList = new ArrayList<>();

            // 3. Iterar sobre cada profesor para registrar materias
            for (Teacher teacher : teacherList) {
                SubjectEntity subjectEntity = new SubjectEntity();
                subjectEntity.setMateria(teacher.getMateria());
                subjectEntity.setAlumnos(alumnEntity);
                subjectEntity.setDocente(teacher.getNombre());
                subjectEntity.setCalificacion(0);
                subjectEntity.setEstado("Pendiente");
                subjectList.add(subjectEntity);
            }

            // 4. Asociar las materias al alumno y guardar en la base de datos
            alumnEntity.setMaterias(subjectList);
            alumnRepository.save(alumnEntity);

            // 5. Crear el DTO de respuesta para cada alumno
            List<SubjectDTO> subjectDTOList = subjectList.stream().map(subject -> {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setMateria(subject.getMateria());
                subjectDTO.setDocente(subject.getDocente());
                subjectDTO.setCalificacion(subject.getCalificacion());
                subjectDTO.setEstado(subject.getEstado());
                return subjectDTO;
            }).collect(Collectors.toList());

            // 6. Agregar el DTO del alumno a la lista
            AlumnDTO alumnDTO = new AlumnDTO(alum.getLegajo(), alum.getNombre(), subjectDTOList);
            alumnDTOList.add(alumnDTO);
        }

        // 7. Retornar la lista de alumnos con materias
        return alumnDTOList;
    }


    @Override
    public NoteRecordDTO registrarNota(String legajo, String materia, int calificacion) {
        Optional<AlumnEntity> optionalAlumnEntity = alumnRepository.findByLegajo(legajo);

        if (optionalAlumnEntity.isPresent()) {
            AlumnEntity alumno = optionalAlumnEntity.get();
            boolean materiaEncontrada = false; // Bandera para verificar si la materia fue encontrada

            for (SubjectEntity subject : alumno.getMaterias()) {
                if (subject.getMateria().equals(materia)) {
                    materiaEncontrada = true; // Marca que la materia fue encontrada
                    subject.setCalificacion(calificacion);

                    // Actualizar el estado según la calificación
                    if (calificacion >= 9) {
                        subject.setEstado("Promocionado");
                    } else if (calificacion >= 4) {
                        subject.setEstado("Regular");
                    } else {
                        subject.setEstado("Libre");
                    }

                    subjectRepository.save(subject); // Guarda la materia actualizada
                    return new NoteRecordDTO(legajo, materia, calificacion); // Retorna el registro de la nota
                }
            }

            // Si llegamos aquí, la materia no fue encontrada
            if (!materiaEncontrada) {
                throw new RuntimeException("Materia no encontrada para el alumno");
            }

        } else {
            throw new RuntimeException("Alumno no encontrado");
        }

        return null; // Opcional, ya que no debería llegar aquí
    }

    @Override
    public List<EstadisticNotesDTO> estadisticReport(String materia) {

        List<SubjectEntity> subjectList = subjectRepository.findByMateria(materia);

        Map<String, List<SubjectEntity>> subjectGrouped = subjectList.stream()
                .collect(Collectors.groupingBy(sub -> sub.getMateria().toLowerCase()));

        return subjectGrouped.entrySet().stream().map(entry ->{
            String subjectEntry = entry.getKey();
            List<SubjectEntity> subjectEntities = entry.getValue();

            long libres = subjectEntities.stream().filter(sub -> sub.getEstado().equals("Libre")).count();
            long regulares = subjectEntities.stream().filter(sub -> sub.getEstado().equals("Regular")).count();
            long promocionados = subjectEntities.stream().filter(sub -> sub.getEstado().equals("Promocionado")).count();
            long total = subjectEntities.size();


            String porcentajeLibre = String.format("%.2f%%", (libres * 100.0) / total);
            String porcentajeRegular = String.format("%.2f%%", (regulares * 100.0) / total);
            String porcentajePromocionado = String.format("%.2f%%", (promocionados * 100.0) / total);

            String resultado = (regulares + promocionados) > (0.6 * total) ? "Exitoso" : "Fracaso";

            EstadoDTO estado = new EstadoDTO(porcentajeLibre,porcentajeRegular,porcentajePromocionado);

            return new EstadisticNotesDTO(subjectEntry,estado,resultado);

        }).collect(Collectors.toList());


    }


}
