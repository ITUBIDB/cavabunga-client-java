package tr.edu.itu.cavabunga.client.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tr.edu.itu.cavabunga.lib.entity.component.Event;
import tr.edu.itu.cavabunga.lib.http.ComponentResponse;
import tr.edu.itu.cavabunga.lib.http.Response;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComponentRestServiceTest {

	@Spy
	@InjectMocks
	private ComponentRestService componentRestService;

	@Mock
	private RestTemplate mockRestTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void testPostComponent() {
		Event component = mock(Event.class);
		ResponseEntity responseEntity = mock(ResponseEntity.class);
		when(mockRestTemplate.postForEntity("/component", component, Response.class)).thenReturn(responseEntity);
		componentRestService.postComponent(component);
		Mockito.verify(mockRestTemplate, Mockito.times(1)).postForEntity("/component", component, Response.class);
	}

	@Test
	public void testPutComponent() {
		Event component = mock(Event.class);
		Response response = mock(Response.class);
		when(component.getId()).thenReturn((long)1);
		when(mockRestTemplate.patchForObject(
			"/component/{id}", component, Response.class, component.getId()))
			.thenReturn(response);
		componentRestService.putComponent(component);
		Mockito.verify(mockRestTemplate, Mockito.times(1))
			.patchForObject("/component/{id}", component, Response.class, (long)1);
	}

	@Test
	public void testDeleteComponent() {
		doNothing().when(mockRestTemplate).delete("/component/{id}",1);
		componentRestService.deleteComponent((long)1);
		Mockito.verify(mockRestTemplate, Mockito.times(1))
			.delete("/component/{id}",(long)1);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetComponent() {
		ResponseEntity responseEntity = mock(ResponseEntity.class);
		ComponentResponse componentResponse = mock(ComponentResponse.class);

		when(responseEntity.getBody()).thenReturn(componentResponse);
		when(componentResponse.getData()).thenReturn(mock(List.class));
		when(mockRestTemplate.getForEntity(
			"/component/{id}", ComponentResponse.class, (long)1))
			.thenReturn(responseEntity);
		componentRestService.getComponent((long)1);
		Mockito.verify(mockRestTemplate, Mockito.times(1))
			.getForEntity("/component/{id}", ComponentResponse.class, (long)1);
	}
}
