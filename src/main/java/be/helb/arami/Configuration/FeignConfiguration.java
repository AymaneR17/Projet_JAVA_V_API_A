package be.helb.arami.Configuration;

import be.helb.arami.Client.DataAccessTracingClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class FeignConfiguration {

    @Bean
    @Qualifier("dataAccessTracingClient")
    public DataAccessTracingClient dataAccessTracingClient(){
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(DataAccessTracingClient.class, "https://localhost:8181/");
    }
}
