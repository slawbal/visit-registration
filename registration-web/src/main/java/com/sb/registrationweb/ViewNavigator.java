package com.sb.registrationweb;

import com.vaadin.navigator.Navigator;
import com.vaadin.spring.annotation.SpringView;

public class ViewNavigator {

	private Navigator navigator;
	
	public ViewNavigator(Navigator navigator){
		this.navigator = navigator;
	}
	
	public void navigateTo(Class<?> view){
		SpringView annotation = view.getAnnotation(SpringView.class);
		navigator.navigateTo(annotation.name());
	}
}
