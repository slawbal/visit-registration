package com.sb.registrationweb.web.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sb.registrationweb.entity.UserDTO;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class UsersPresenter {

	@Autowired
	private UsersModel model;
	
	private UsersView view;
	
	List<UserDTO> getAllUsers(){
		return model.getAllUsers();
	}

	public void setView(UsersView view) {
		this.view = view;
	}

	public void setData() {
		List<UserDTO> allUsers = model.getAllUsers();
		view.setUsers(allUsers);
	}

}
