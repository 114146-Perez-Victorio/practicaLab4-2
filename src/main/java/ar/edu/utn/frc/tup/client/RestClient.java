package ar.edu.utn.frc.tup.client;

import ar.edu.utn.frc.tup.entities.AlumnEntity;
import ar.edu.utn.frc.tup.models.Alumn;
import ar.edu.utn.frc.tup.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RestClient {

private final RestTemplate restTemplate;

@Value("${api.url}")
private String url;

//Levantado de manera local
//private String url = "http://localhost:8080";

public List<Alumn> getAllAlumns(){
    //Para manejar listas genericas usos exchange
    ResponseEntity<List<Alumn>> response = restTemplate.exchange(
            url + "/alumnos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Alumn>>() {}
    );
    return response.getBody();
}

public List<Teacher> getAllTeachers(){
    //Para manejar listas genericas usos exchange
    ResponseEntity<List<Teacher>> response = restTemplate.exchange(
            url + "/docentes",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Teacher>>() {}
    );
    return response.getBody();
}



}
