package com.au.controllers;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.au.entities.Events;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EventControllerTest {
	@Autowired
	EventController eventController;
	@Autowired
	private TestRestTemplate restTemplate;
	@org.junit.Before
	   public void start() {
	       System.out.println("Before test cases");
	   }
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private EventRepository eventRepo;
//	String exampleCourseJson = "{\"eventId\":1,\"eventName\":\"Mehendi\",\"steps\":1,\"delFlag\":0}";
//
//	@Test
//	public void createUser() throws Exception {
//		Mockito.when(eventRepo.findOrderByCultureId(Mockito.anyInt()));
//		System.out.println("line0");
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/students/Student1/courses")
//				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
//				.contentType(MediaType.APPLICATION_JSON);
//		System.out.println("line1");
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println("line2");
//		MockHttpServletResponse response = result.getResponse();
//		System.out.println("line3");
//		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//		System.out.println("line4");
//		assertEquals("http://localhost:8080/getevents/1",
//				response.getHeader(HttpHeaders.LOCATION));
//		System.out.println("line5");
//	}
	
	@Test
	public void checkGetEvents() {
		ResponseEntity<Events> responseEntity =
	            restTemplate.postForEntity("/getevents", new Integer(1), Events.class);
	        Events events = responseEntity.getBody();
	        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	        assertEquals(0, events.getEventId());
		
	}
}
