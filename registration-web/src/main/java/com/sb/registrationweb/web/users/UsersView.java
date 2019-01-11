package com.sb.registrationweb.web.users;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.sb.registrationweb.entity.UserDTO;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;

@SpringView(name = "users")
public class UsersView extends VerticalLayout implements View{

	@Autowired
	private UsersPresenter presenter;
	
	private Grid<UserDTO> userGrid = createGrid();
	
	private Button addNew = new Button("Nowy");
	
	@PostConstruct
	public void init(){
		presenter.setView(this);
		createGUI();
	}

	private void createGUI() {
		presenter.setData();
		addComponents(addNew, userGrid);
		userGrid.setSizeFull();
	}
	
	private Grid<UserDTO> createGrid(){
		Grid<UserDTO> userGrid = new Grid<UserDTO>();
		userGrid.addColumn(UserDTO::getFirstName).setCaption("Imie");
		userGrid.addColumn(UserDTO::getLastName).setCaption("Nazwisko");
		userGrid.addColumn(UserDTO::getLogin).setCaption("Identyfikator");
		userGrid.addColumn(UserDTO::getPhone).setCaption("Telefon");
		return userGrid;
	}

	public void setUsers(List<UserDTO> allUsers) {
		userGrid.setItems(allUsers);
	}
}
