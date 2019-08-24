/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udem.prqrdrs.endtoend.controller;

import edu.udem.prqrdrs.dto.PersonaDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import edu.udem.prqrdrs.integration.service.PersonaService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("persona")
public class PersonaController {

    PersonaService personService = null;

    @Autowired
    PersonaController(PersonaService personService) {
        this.personService = personService;
    }

    @ApiOperation(
            value = "Retorna la lista de personas",
            response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Restorna la lista de personas en JSON"),})
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<PersonaDto> findAll() {
        return personService.getPersonas();
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    PersonaDto addPersona(@RequestBody PersonaDto personaDto) {
        return personService.addUser(personaDto);
    }

    @ApiOperation(
            value = "Retorna la persona con el id especificada",
            response = PersonaDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Restorna la una personas en JSON"),})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findById(@ApiParam(value = "ID of person to return", required = true) @PathVariable("id") String id) {
        PersonaDto per = null;
        if (isValidLong(id)) {
            per = personService.getUserById(Long.parseLong(id));
            if (per != null) {
                return new ResponseEntity<>(per, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro id", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(new PersonaDto(), HttpStatus.BAD_REQUEST);
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
