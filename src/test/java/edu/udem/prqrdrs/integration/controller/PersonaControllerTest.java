package edu.udem.prqrdrs.integration.controller;

import edu.udem.prqrdrs.dto.PersonaDto;
import edu.udem.prqrdrs.endtoend.controller.PersonaController;
import edu.udem.prqrdrs.integration.service.PersonaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonaController.class)
public class PersonaControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaService service;

    // write test cases here
    @Test
    public void givenPersonas_whenGetPersonas_thenReturnJsonArray()
            throws Exception {

        PersonaDto alex = PersonaDto.builder().login("alex").build();

        List<PersonaDto> allPersonas = Arrays.asList(alex);

        given(service.getPersonas()).willReturn(allPersonas);

        mvc.perform(get("/persona/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].login", is(alex.getLogin())));
    }

}
