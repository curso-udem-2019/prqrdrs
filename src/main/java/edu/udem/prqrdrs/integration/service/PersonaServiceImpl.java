package edu.udem.prqrdrs.integration.service;

import edu.udem.prqrdrs.integration.dao.PersonaRepository;
import edu.udem.prqrdrs.dto.PersonaDto;
import edu.udem.prqrdrs.entities.PersonaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpramirez
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaDto addUser(PersonaDto user) {
        return this.getDTO(personaRepository.save(this.getEntity(user)));
    }

    @Override
    public void deleteUser(PersonaDto user) {
        personaRepository.delete(this.getEntity(user));
    }

    @Override
    public List<PersonaDto> getPersonas() {
        List<PersonaDto> list = new ArrayList<PersonaDto>();
        List<PersonaEntity> list2 = this.personaRepository.findAll();
        list2.stream().forEach(p -> list.add(this.getDTO(p)));
        
        return list;
    }

    @Override
    public PersonaDto getUserByLogin(String login) {
        return this.getDTO(personaRepository.findByLogin(login));
    }

    private PersonaDto getDTO(PersonaEntity user) {
        ModelMapper modelMapper = new ModelMapper();
        PersonaDto dto = modelMapper.map(user, PersonaDto.class);
        return dto;
    }

    private PersonaEntity getEntity(PersonaDto user) {
        ModelMapper modelMapper = new ModelMapper();
        PersonaEntity entity = modelMapper.map(user, PersonaEntity.class);
        return entity;
    }

    @Override
    public PersonaDto getUserById(Long id) {
           Optional<PersonaEntity> per = personaRepository.findById(id);
           if(per.isPresent()){
               return this.getDTO(per.get());
           }
           return null;
    }

}
