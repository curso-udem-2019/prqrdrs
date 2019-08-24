package edu.udem.prqrdrs.integration.service;

import edu.udem.prqrdrs.dto.PersonaDto;

import java.util.List;

/**
 *
 * @author jpramirez
 */
public interface PersonaService {
    public PersonaDto addUser(PersonaDto user);
    public void deleteUser(PersonaDto user);
    public List<PersonaDto> getPersonas();
    public PersonaDto getUserByLogin(String login);
    public PersonaDto getUserById(Long id);

}
