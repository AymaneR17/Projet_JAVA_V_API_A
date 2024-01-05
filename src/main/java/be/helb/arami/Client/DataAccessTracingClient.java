package be.helb.arami.Client;

import be.helb.arami.DTO.CareerDTO;
import feign.Headers;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;


public interface DataAccessTracingClient {

    @RequestLine("POST /career/create/{fighterId}")
    @Headers("Content-type: application/json")
    ResponseEntity<Void> createCareer(Long fighterId,CareerDTO careerDTO);

}
