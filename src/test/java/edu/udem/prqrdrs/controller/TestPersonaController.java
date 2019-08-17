/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udem.prqrdrs.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.udem.prqrdrs.dto.PersonaDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestPersonaController {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    public void testCreatePerson() throws Exception {
        PersonaDto persona = new PersonaDto();
        persona.setApellido("TestApellido");
        persona.setNombre("TestNombre");
        persona.setEmail("TestEmail");
        persona.setLogin("TestLogin");
        String json = objectMapper.writeValueAsString(persona);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("/personas/"), HttpMethod.POST, entity, String.class);
        HttpStatus status = response.getStatusCode();
        Assert.assertEquals(status,HttpStatus.OK );
    }    
    
    
    @Test
    public void testRetrievePerson() throws Exception {
        
        PersonaDto persona = new PersonaDto();
        persona.setApellido("TestApellido");
        persona.setNombre("TestNombre");
        persona.setEmail("TestEmail");
        persona.setLogin("TestLogin");
        persona.setId(3L);
        
        String json = objectMapper.writeValueAsString(persona);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
          createURLWithPort("personas/3"), HttpMethod.GET, entity, String.class);
        
        
        String expected = objectMapper.writeValueAsString(persona);
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}