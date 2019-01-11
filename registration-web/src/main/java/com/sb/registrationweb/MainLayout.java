package com.sb.registrationweb;

import javax.annotation.PostConstruct;

import com.vaadin.spring.annotation.ViewScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sb.registrationweb.web.schedule.view.ScheduleView;
import com.sb.registrationweb.web.users.UsersView;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.web.context.annotation.RequestScope;

@org.springframework.stereotype.Component
@Scope("request")
public class MainLayout extends VerticalLayout{

	private Panel content = new Panel();
	
	private NavigationMenu menu = new NavigationMenu();
	
	@PostConstruct
	public void init(){
        setSizeFull();

        
        menu.addItem("Kalendarz", ScheduleView.class);
        menu.addItem("Użytkownicy", UsersView.class);
        menu.addItem("Usługi", null, null);
        
        final CssLayout navigationBar = new CssLayout();
        addComponent(menu);
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.setSizeFull();
        addComponent(navigationBar);

        content.setSizeFull();
        navigationBar.addComponent(content);
        setExpandRatio(navigationBar, 1.0f);
	}
	
	public void setContent(Component component){
		content.setContent(component);
	}
	
	public void setNavigator(ViewNavigator navigator) {
		menu.setNavigator(navigator);
	}
}
