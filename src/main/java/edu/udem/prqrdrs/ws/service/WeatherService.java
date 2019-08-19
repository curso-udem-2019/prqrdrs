package edu.udem.prqrdrs.ws.service;

import edu.udem.prqrdrs.ws.rest.WeatherRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final static String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final static String API_KEY = "YOUR KEY";

    public WeatherRoot getWeatherBy(String zip, String country) {
        RestTemplate restTemplate = new RestTemplate();
        String urlRestWS = String.format("%s?zip=%s,%s&APPID=%s",
                WEATHER_URL,
                zip,
                country,
                API_KEY);
        WeatherRoot weatherRoot = restTemplate.getForObject(urlRestWS, WeatherRoot.class);
        log.info(weatherRoot.toString());

        return weatherRoot;
    }


}
