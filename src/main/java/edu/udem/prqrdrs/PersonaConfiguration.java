package edu.udem.prqrdrs;

import edu.udem.prqrdrs.ws.client.PersonaSoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PersonaConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("edu.udem.prqrdrs.ws.consumer");
        return marshaller;
    }

    @Bean
    public PersonaSoapClient countryClient(Jaxb2Marshaller marshaller) {
        PersonaSoapClient client = new PersonaSoapClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}