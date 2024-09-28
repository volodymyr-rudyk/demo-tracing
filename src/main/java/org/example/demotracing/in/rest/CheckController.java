package org.example.demotracing.in.rest;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class CheckController {

    private final RestTemplate restTemplate;

    public CheckController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/make-request")
    Object make() {

        try {

            MtplResponse response = restTemplate.exchange(
                            RequestEntity.get("http://google.com/ewrwer")
                                    .accept(MediaType.ALL)
                                    .build(),
                            MtplResponse.class)
                    .getBody();

            return response;
        } catch (HttpClientErrorException clientErrorException) {
            System.err.println("Client error  exception -- " +  clientErrorException.getStatusCode() + " " + clientErrorException.getStatusText());
            throw new MtplClientException(clientErrorException);
        } catch (HttpServerErrorException serverErrorException) {
            throw new MtplServerException(serverErrorException);
        }


    }
}
