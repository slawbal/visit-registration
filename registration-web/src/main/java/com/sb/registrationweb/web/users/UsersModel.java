package com.sb.registrationweb.web.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sb.registrationweb.entity.UserDTO;
import com.sb.registrationweb.rest.config.RestConfig;

@Service
public class UsersModel {

	private final RestTemplate restTemplate = new RestTemplate();
	
	public UserDTO getUserByLogin(String login){
		try{
			return restTemplate.getForObject(RestConfig.createURL("users/" + login + "/get"), UserDTO.class);
		}catch (HttpClientErrorException ex){
			System.out.println("User " + login + "does not exist");
		}
		return null;
	}
	
	public List<UserDTO> getAllUsers(){
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(RestConfig.createURL("users/find-all"), UserDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public void createUser(UserDTO user) {
		restTemplate.postForEntity(RestConfig.createURL("user/add"), user, UserDTO.class);
	}
}
