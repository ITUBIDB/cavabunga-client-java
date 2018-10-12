package tr.edu.itu.cavabunga.client.service;

import org.springframework.web.client.RestTemplate;
import lombok.Data;
import tr.edu.itu.cavabunga.lib.entity.Component;
import tr.edu.itu.cavabunga.lib.http.ComponentResponse;
import tr.edu.itu.cavabunga.lib.http.Response;

import java.util.List;

@Data
public class ComponentRestService {
	private RestTemplate restTemplate;

	public ComponentRestService(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}

	public void postComponent(Component component){
		this.restTemplate.postForEntity("/component", component, Response.class);
	}

	public Response putComponent(Component component){
		return this.restTemplate.patchForObject("/component/{id}", component, Response.class, component.getId());
	}

	public void deleteComponent(Long id) {
		this.restTemplate.delete("/component/{id}", id);
	}

	public List<Component> getComponent(Long id){
		return this.restTemplate.getForEntity("/component/{id}", ComponentResponse.class, id).getBody().getData();
	}

}
