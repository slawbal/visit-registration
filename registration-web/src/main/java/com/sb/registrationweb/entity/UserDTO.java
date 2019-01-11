package com.sb.registrationweb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String login;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private Boolean activated;
	
	private String password;

	
}
