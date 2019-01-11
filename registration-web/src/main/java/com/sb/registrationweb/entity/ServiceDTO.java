package com.sb.registrationweb.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ServiceDTO {
	
	private Long id;
	
	private String description;
	
	private Long durationInMinutes;

	@Override
	public String toString() {
		return description;
	}

}