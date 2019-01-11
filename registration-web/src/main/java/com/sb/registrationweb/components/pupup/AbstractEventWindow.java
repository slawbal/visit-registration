package com.sb.registrationweb.components.pupup;

import java.util.List;

import com.sb.registrationweb.converters.InstantToStringConverter;
import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.entity.ServiceDTO;
import com.sb.registrationweb.entity.UserDTO;
import com.sb.registrationweb.suppliers.DataSupplier;
import com.sb.registrationweb.suppliers.SimpleDataSupplier;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractEventWindow extends Window{
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private FormLayout form = new FormLayout();
	protected Binder<EventDTO> binder = new Binder<>(EventDTO.class);
	private TextField login = createTextField("Login");
	private TextField firstName = createTextField("Imię");
	private TextField lastName = createTextField("Nazwisko");
	protected DateTimeField startDate = createDateField("Data");
	private MultiCombo<ServiceDTO> combo = new MultiCombo<ServiceDTO>("Usługi");
	private Button save = new Button("Zapisz");
	private DataSupplier<UserDTO, String> userSupplier;
	private final EventDTO bean;
	
	public AbstractEventWindow(EventDTO bean, SubmitAction submitAction, SimpleDataSupplier<List<ServiceDTO>> itemSupplier){
		this.bean = bean;
		combo.setItemSupplier(itemSupplier);
		
		customizeWindowLayout();
		customizeSaveAction(submitAction);

		bindFields();
		binder.setBean(bean);
		customizeLoginFieldTextChangeAction();
	}
	
	private void bindFields(){
		binder.bind(login, "user.login");
		binder.bind(firstName, "user.firstName");
		binder.bind(lastName, "user.lastName"); 
		binder.forField(startDate)
			.withConverter(new InstantToStringConverter())	
			.bind("startDate");
		binder.bind(combo, "services");
	}
	
	private void customizeWindowLayout(){
		form.addComponents(login, firstName, lastName, startDate, combo,  save);
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.addComponent(form);
		setContent(mainLayout);
		center();
		setModal(true);
		setWidth(400, Unit.PIXELS);
		form.setSpacing(true);
	}

	private TextField createTextField(String caption){
		TextField textField = new TextField(caption);
		textField.setSizeFull();
		return textField;
	}
	
	private DateTimeField createDateField(String caption){
		DateTimeField dateField = new DateTimeField(caption);
		dateField.setSizeFull();
		return dateField;
	}
	
	private void customizeSaveAction(SubmitAction submitAction){
		save.addClickListener(e -> {
			submitAction.submit(bean);
			close();
		});
	}
	
	private void customizeLoginFieldTextChangeAction(){
		login.addValueChangeListener(v -> loadUserData(v));
	}
	
	private void loadUserData(ValueChangeEvent<String> v) {
		UserDTO data = userSupplier.getData(v.getValue());
		if(data != null){
			bean.setUser(data);
			binder.setBean(bean);
		}
	}
	
	public void setDataSupplier(DataSupplier<UserDTO, String> dataSupplier) {
		this.userSupplier = dataSupplier;
	}

	public interface SubmitAction{
		void submit(EventDTO event);
	}
}
