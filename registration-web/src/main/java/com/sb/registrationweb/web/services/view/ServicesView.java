package com.sb.registrationweb.web.services.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = "services")
public class ServicesView extends VerticalLayout implements View {

    @PostConstruct
    public void init(){

    }
}
