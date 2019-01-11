package com.sb.registrationweb;

import com.vaadin.ui.MenuBar;

public class NavigationMenu extends MenuBar{

	private ViewNavigator navigator;

	public MenuItem addItem(String caption, Class<?> view){
		return addItem(caption, (i) -> changeView(view));
	}
	
	public void setNavigator(ViewNavigator navigator) {
		this.navigator = navigator;
	}

	private void changeView(Class<?> view){
		navigator.navigateTo(view);
	}
}
