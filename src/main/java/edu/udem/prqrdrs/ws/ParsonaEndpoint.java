package edu.udem.prqrdrs.ws;

import edu.udem.prqrdrs.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class ParsonaEndpoint {

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    PersonaService personService = null;

    @Autowired
    public ParsonaEndpoint(PersonaService personService) {
        this.personService = personService;
    }


//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//    @ResponsePayload
//    public GetCountryResponse getPersona(@RequestPayload GetPerson request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
//
//        return response;
//    }
}