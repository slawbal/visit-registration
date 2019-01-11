package com.sb.registrationweb.entity;

import java.time.Instant;
import java.util.Set;

import lombok.Data;

@Data
public class EventDTO {

	private String description;
	
	private Instant startDate;
	
	private UserDTO user;
	
	private Set<ServiceDTO> services;

}