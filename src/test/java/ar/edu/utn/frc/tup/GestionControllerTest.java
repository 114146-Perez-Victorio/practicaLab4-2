package ar.edu.utn.frc.tup;

import ar.edu.utn.frc.tup.DTO.*;
import ar.edu.utn.frc.tup.client.RestClient;
import ar.edu.utn.frc.tup.controllers.GestionController;
import ar.edu.utn.frc.tup.entities.AlumnEntity;
import ar.edu.utn.frc.tup.entities.SubjectEntity;
import ar.edu.utn.frc.tup.models.Alumn;
import ar.edu.utn.frc.tup.models.Teacher;
import ar.edu.utn.frc.tup.repositories.AlumnRepository;
import ar.edu.utn.frc.tup.repositories.SubjectRepository;
import ar.edu.utn.frc.tup.repositories.TeacherRepository;
import ar.edu.utn.frc.tup.services.AlumnService;
import ar.edu.utn.frc.tup.services.GestionService;
import ar.edu.utn.frc.tup.services.impl.GestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GestionControllerTest {

    @InjectMocks
    private GestionController gestionController; // Clase que queremos probar

    @Mock
    private GestionService gestionService; // Servicio simulado

    @Mock
    private AlumnService alumnService; // Servicio simulado

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testRegistrarBD() {
        // Datos de prueba
        SubjectDTO subjectDTO = new SubjectDTO("Matematica", "Jose", 0, "Pendiente");
        List<SubjectDTO> mockMaterias = Arrays.asList(subjectDTO);
        AlumnDTO alumnDTO = new AlumnDTO("A001", "Fernando", mockMaterias);
        List<AlumnDTO> mockAlumnMateria = Arrays.asList(alumnDTO);

        // Comportamiento simulado
        when(gestionService.registrarBD()).thenReturn(mockAlumnMateria);

        // Llama al endpoint
        ResponseEntity<List<AlumnDTO>> response = gestionController.registrarBD();

        // Verifica el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAlumnMateria, response.getBody());
        verify(gestionService, times(1)).registrarBD(); // Verifica que el método fue llamado una vez
    }

    @Test
    void testRegistrarNota() {
        // Datos de prueba
        NoteRecordDTO noteRecordDTO = new NoteRecordDTO("A001", "Matematica", 8);
        NoteRecordDTO mockResponse = new NoteRecordDTO("A001", "Matematica", 8);

        // Comportamiento simulado
        when(gestionService.registrarNota(noteRecordDTO.getLegajo(), noteRecordDTO.getMateria(), noteRecordDTO.getCalificacion()))
                .thenReturn(mockResponse);

        // Llama al endpoint
        ResponseEntity<?> response = gestionController.RegistrarNota(noteRecordDTO);

        // Verifica el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(gestionService, times(1)).registrarNota(noteRecordDTO.getLegajo(), noteRecordDTO.getMateria(), noteRecordDTO.getCalificacion());
    }

    @Test
    void testInformeAcademico() {
        // Datos de prueba
        EstadisticNotesDTO estadisticNotesDTO = new EstadisticNotesDTO();
        List<EstadisticNotesDTO> mockEstadisticas = Arrays.asList(estadisticNotesDTO);

        // Comportamiento simulado
        when(gestionService.estadisticReport("Matematica")).thenReturn(mockEstadisticas);

        // Llama al endpoint
        ResponseEntity<List<EstadisticNotesDTO>> response = gestionController.informeAcedemico("Matematica");

        // Verifica el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEstadisticas, response.getBody());
        verify(gestionService, times(1)).estadisticReport("Matematica");
    }

    @Test
    void testGetAllTeachers() {
        // Datos de prueba
        TeacherDTO teacherDTO = new TeacherDTO("T001", "Jose","Literatura");
        List<TeacherDTO> mockTeachers = Arrays.asList(teacherDTO);

        // Comportamiento simulado
        when(gestionService.getTeacherBD()).thenReturn(mockTeachers);

        // Llama al endpoint
        ResponseEntity<List<TeacherDTO>> response = gestionController.getAllTeachers();

        // Verifica el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTeachers, response.getBody());
        verify(gestionService, times(1)).getTeacherBD();
    }

}
