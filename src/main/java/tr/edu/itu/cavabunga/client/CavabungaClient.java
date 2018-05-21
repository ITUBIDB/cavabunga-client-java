package tr.edu.itu.cavabunga.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.edu.itu.cavabunga.client.service.ComponentRestService;

@Data
@Service
public class CavabungaClient {

	private RestTemplate restTemplate;

	private ComponentRestService componentRestService;

    public CavabungaClient(String rootUri) {
    	restTemplate = new RestTemplateBuilder().rootUri(rootUri).build();
    	componentRestService = new ComponentRestService(restTemplate);
    }
}
