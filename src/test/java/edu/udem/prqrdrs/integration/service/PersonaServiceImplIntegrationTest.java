package edu.udem.prqrdrs.integration.service;

import edu.udem.prqrdrs.dto.PersonaDto;
import edu.udem.prqrdrs.entities.PersonaEntity;
import edu.udem.prqrdrs.integration.dao.PersonaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PersonaServiceImplIntegrationTest {

    @TestConfiguration
    static class PersonaServiceImplIntegrationTestConfiguration {
        @Autowired
        private PersonaRepository personaRepository;

        @Bean
        public PersonaServiceImpl employeeService() {
            return new PersonaServiceImpl(personaRepository);
        }
    }

    @Autowired
    private PersonaService personaService;

    @MockBean
    private PersonaRepository personaRepository;


    @Before
    public void setUp() {
        PersonaEntity alex = PersonaEntity.builder().login("alexLogin").build();

        Mockito.when(personaRepository.findByLogin(alex.getLogin()))
                .thenReturn(alex);
    }

    @Test
    public void whenValidLogin_thenPersonaShouldBeFound() {
        String login = "alexLogin";
        PersonaDto found = personaService.getUserByLogin(login);

        assertThat(found.getLogin())
                .isEqualTo(login);
    }
}



