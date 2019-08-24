package edu.udem.prqrdrs.ws;

import edu.udem.prqrdrs.dto.PersonaDto;
import edu.udem.prqrdrs.integration.service.PersonaService;
import edu.udem.prqrdrs.ws.soap.GetPersonaRequest;
import edu.udem.prqrdrs.ws.soap.GetPersonaResponse;
import edu.udem.prqrdrs.ws.soap.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ParsonaEndpoint {

    private static final String NAMESPACE_URI = "http://udem.edu/prqrdrs/ws/soap";

    PersonaService personService = null;

    @Autowired
    public ParsonaEndpoint(PersonaService personService) {
        this.personService = personService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonaRequest")
    @ResponsePayload
    public GetPersonaResponse getPersona(@RequestPayload GetPersonaRequest request) {
        GetPersonaResponse response = new GetPersonaResponse();
        Persona personaWS = getPersonaWS(personService.getUserById(request.getId()));
        response.setPersona(personaWS);
        return response;
    }

    private Persona getPersonaWS(PersonaDto personadto) {
        ModelMapper modelMapper = new ModelMapper();
        Persona personaWS = modelMapper.map(personadto, Persona.class);
        return personaWS;
    }

}