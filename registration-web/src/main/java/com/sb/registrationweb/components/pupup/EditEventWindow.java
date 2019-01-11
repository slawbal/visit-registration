package com.sb.registrationweb.components.pupup;

import java.util.List;

import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.entity.ServiceDTO;
import com.sb.registrationweb.suppliers.SimpleDataSupplier;

public class EditEventWindow extends AbstractEventWindow{

	public EditEventWindow(EventDTO event, SubmitAction submitAction, SimpleDataSupplier<List<ServiceDTO>> itemSupplier) {
		super(event, submitAction, itemSupplier);
		setCaption("Edytuj wizytę");
	}

}
