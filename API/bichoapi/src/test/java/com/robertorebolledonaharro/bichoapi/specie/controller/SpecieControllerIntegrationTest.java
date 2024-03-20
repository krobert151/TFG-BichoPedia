package com.robertorebolledonaharro.bichoapi.specie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.exception.UnauthorizedException;
import com.robertorebolledonaharro.bichoapi.security.jwt.access.JwtProvider;
import com.robertorebolledonaharro.bichoapi.specie.dto.SpecieDTO;
import com.robertorebolledonaharro.bichoapi.specie.error.SpecieNotFoundException;
import com.robertorebolledonaharro.bichoapi.user.model.User;
import com.robertorebolledonaharro.bichoapi.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@Sql(value = "classpath:insert-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:delete-data.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SpecieControllerIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    String adminToken;
    String userToken;

    HttpHeaders header = new HttpHeaders();
    @BeforeEach
    @Sql("classpath:delete-data.sql")
    public void setup() {


        User user = userService.findByUsername("krobert151").get();

        // Crea una autenticación con los detalles del usuario cargados
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        // Establece la autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(auth);

        adminToken = jwtProvider.generateToken(user);

    }

    @Test
    void findAllSpeciesDTO_200(){

        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(adminToken);

        HttpEntity<SpecieDTO> objectRequest = new HttpEntity<>(header);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<SpecieDTO[]> categoryDtoResponseEntity = restTemplate.exchange("http://localhost:"+port+"/species/allspecies", HttpMethod.GET,objectRequest, SpecieDTO[].class);

        Assertions.assertEquals(HttpStatus.OK,categoryDtoResponseEntity.getStatusCode());
        Assertions.assertEquals(categoryDtoResponseEntity.getBody()[0].scientificName(), "Pleurodelest walts");


    }

    @Test
    void findAllByCriteriaSpeciesDTO_200(){

        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(adminToken);

        HttpEntity<SpecieDTO> objectRequest = new HttpEntity<>(header);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<SpecieDTO[]> categoryDtoResponseEntity = restTemplate.exchange("http://localhost:"+port+"/species/allspecies?search=scientificName:*le* and ( type:Amphibian or type:Bird )", HttpMethod.GET,objectRequest, SpecieDTO[].class);

        Assertions.assertEquals(HttpStatus.OK,categoryDtoResponseEntity.getStatusCode());
        Assertions.assertEquals(categoryDtoResponseEntity.getBody()[0].scientificName(), "Pleurodelest walts");
        Assertions.assertEquals(categoryDtoResponseEntity.getBody()[1].scientificName(), "American Eagle");



    }

    @Test
    void findAllByCriteriaSpeciesDTO_404(){

        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(adminToken);

        HttpEntity<SpecieDTO> objectRequest = new HttpEntity<>(header);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<SpecieNotFoundException> categoryDtoResponseEntity = restTemplate.exchange("http://localhost:"+port+"/species/allspecies?search=scientificName:*manuel*", HttpMethod.GET,objectRequest, SpecieNotFoundException.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,categoryDtoResponseEntity.getStatusCode());

    }


    @Test
    void findAllSpeciesDTO_404(){

        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(adminToken);

        HttpEntity<SpecieDTO> objectRequest = new HttpEntity<>(header);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<SpecieNotFoundException> categoryDtoResponseEntity = restTemplate.exchange("http://localhost:"+port+"/species/allspecies?c=10&p=2", HttpMethod.GET,objectRequest, SpecieNotFoundException.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND,categoryDtoResponseEntity.getStatusCode());

    }

    @Test
    void findAllSpeciesDTO_401(){

        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SpecieDTO> objectRequest = new HttpEntity<>(header);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<UnauthorizedException> categoryDtoResponseEntity = restTemplate.exchange("http://localhost:"+port+"/species/allspecies?c=10&p=2", HttpMethod.GET,objectRequest, UnauthorizedException.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED,categoryDtoResponseEntity.getStatusCode());

    }

}
