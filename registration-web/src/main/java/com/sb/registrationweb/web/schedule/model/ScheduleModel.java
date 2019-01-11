package com.sb.registrationweb.web.schedule.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.rest.config.RestConfig;

@Service
public class ScheduleModel {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<EventDTO> getAllEvents(){
		ResponseEntity<EventDTO[]> response = restTemplate.getForEntity(RestConfig.createURL("schedule/find-all"), EventDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public void createEvent(EventDTO event) {
		restTemplate.postForEntity(RestConfig.createURL("schedule/add"), event, EventDTO.class);
	}
}
