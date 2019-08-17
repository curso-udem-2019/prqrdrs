package edu.udem.prqrdrs.ws.client;


import edu.udem.prqrdrs.ws.consumer.GetPersonaRequest;
import edu.udem.prqrdrs.ws.consumer.GetPersonaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class PersonaSoapClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(PersonaSoapClient.class);

    public GetPersonaResponse getPersona(long id) {

        GetPersonaRequest request = new GetPersonaRequest();
        request.setId(id);

        log.info("Requesting location for " + id);

        GetPersonaResponse response = (GetPersonaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/personas", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetPersonaRequest"));

        return response;
    }

}