package edu.udem.prqrdrs.integration.dao;

import edu.udem.prqrdrs.entities.PersonaEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PersonaRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PersonaRepository personaRepository;

    @Test
    public void whenFindByLogin_thenReturnPersona() {
        // given
        PersonaEntity alexEntity = PersonaEntity.builder().login("alexLogin").build();
        entityManager.persist(alexEntity);
        entityManager.flush();

        // when
        PersonaEntity found = personaRepository.findByLogin(alexEntity.getLogin());

        // then
        assertThat(found.getLogin()).isEqualTo(alexEntity.getLogin());
    }

    @Test
    public void whenFindByLoginJose_thenReturnPersona() {
        // when
        PersonaEntity found = personaRepository.findByLogin("jose");

        // then
        assertThat(found.getLogin()).isNotNull();
    }
}
