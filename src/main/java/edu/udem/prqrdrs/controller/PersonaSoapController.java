package edu.udem.prqrdrs.controller;

import edu.udem.prqrdrs.dto.PersonaDto;
import edu.udem.prqrdrs.ws.client.PersonaSoapClient;
import edu.udem.prqrdrs.ws.consumer.Persona;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persona-client")
public class PersonaSoapController {

    PersonaSoapClient personaSoapClient = null;

    @Autowired
    PersonaSoapController(PersonaSoapClient personaSoapClient) {
        this.personaSoapClient = personaSoapClient;
    }


    @ApiOperation(
            value = "Retorna la persona con el id especificada via WS",
            response = PersonaDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Restorna la una personas en JSON"),})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findById(@ApiParam(value = "ID of person to return", required = true) @PathVariable("id") String id) {
        Persona per = null;
        PersonaDto perDto = null;
        if (isValidLong(id)) {
            per = personaSoapClient.getPersona(Long.parseLong(id)).getPersona();
            perDto = getDTO (per);
            if (per != null) {
                return new ResponseEntity<>(perDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro id", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(new PersonaDto(), HttpStatus.BAD_REQUEST);
    }


    private PersonaDto getDTO(Persona user) {
        ModelMapper modelMapper = new ModelMapper();
        PersonaDto dto = modelMapper.map(user, PersonaDto.class);
        return dto;
    }

    boolean isValidLong(String longValue) {
        try {
            Long.parseLong(longValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
