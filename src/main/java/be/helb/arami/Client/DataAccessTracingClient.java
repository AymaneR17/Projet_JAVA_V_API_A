package be.helb.arami.Client;

import be.helb.arami.DTO.CareerDTO;
import be.helb.arami.DTO.FighterDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface DataAccessTracingClient {
    @RequestLine("GET /fighter/retirement")
    @Headers("Content-type: application/json")
    List<FighterDTO> getFighterRetiredById(@Param("id") Long id, @Param("isRetired") Boolean isRetired);
}
