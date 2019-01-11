package com.sb.registrationweb.suppliers;

import org.springframework.beans.factory.annotation.Autowired;

import com.sb.registrationweb.entity.UserDTO;
import com.sb.registrationweb.web.users.UsersModel;

public class UserDTOSupplier implements DataSupplier<UserDTO, String>{

	@Autowired
	private UsersModel model;
	
	@Override
	public UserDTO getData(String login) {
		return model.getUserByLogin(login);
	}

}
