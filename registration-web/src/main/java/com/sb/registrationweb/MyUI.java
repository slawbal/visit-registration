package com.sb.registrationweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.sb.registrationweb.web.schedule.view.ScheduleView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;


@Theme("mytheme")
@SpringUI
@SpringViewDisplay
@ServletComponentScan({"com.vaadin.wscdn"})
public class MyUI extends UI implements ViewDisplay{

	@Autowired
	private MainLayout content;
	
	@Override
	protected void init(VaadinRequest request) {
		content.setNavigator(new ViewNavigator(getNavigator()));
		setContent(content);
        getNavigator().addView("", ScheduleView.class);
	}

	@Override
	public void showView(View view) {
		content.setContent((Component) view);
	}
	
}
