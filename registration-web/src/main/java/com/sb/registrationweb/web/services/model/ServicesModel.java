package com.sb.registrationweb.web.services.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sb.registrationweb.entity.ServiceDTO;
import com.sb.registrationweb.rest.config.RestConfig;

@Service
public class ServicesModel {

	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<com.sb.registrationweb.entity.ServiceDTO> getAllServices(){
		ResponseEntity<ServiceDTO[]> response = restTemplate.getForEntity(RestConfig.createURL("services/find-all"), ServiceDTO[].class);
		return Arrays.asList(response.getBody());
	}

}
