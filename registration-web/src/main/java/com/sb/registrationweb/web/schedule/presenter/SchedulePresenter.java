package com.sb.registrationweb.web.schedule.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.entity.ServiceDTO;
import com.sb.registrationweb.entity.UserDTO;
import com.sb.registrationweb.web.schedule.model.ScheduleModel;
import com.sb.registrationweb.web.schedule.view.ScheduleView;
import com.sb.registrationweb.web.services.model.ServicesModel;
import com.sb.registrationweb.web.users.UsersModel;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class SchedulePresenter {
	
	ScheduleView myView;
	
	@Autowired
	ScheduleModel scheduleModel;
	
	@Autowired
	ServicesModel servicesModel;

	@Autowired
	UsersModel userModel;
	
	public void setView(ScheduleView myView) {
		this.myView = myView;
		loadData();
	}
	
	public void loadData(){
		List<EventDTO> events = scheduleModel.getAllEvents();
		myView.setEventsForCalendar(events);
	}
	
	public void createEvent(EventDTO event){
		scheduleModel.createEvent(event);
	}
	
	public List<ServiceDTO> getAllServices(){
		return servicesModel.getAllServices();
	}
	
	public UserDTO getUser(String login){
		return userModel.getUserByLogin(login);
	}
}
