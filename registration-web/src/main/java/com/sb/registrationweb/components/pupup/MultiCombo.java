package com.sb.registrationweb.components.pupup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sb.registrationweb.suppliers.SimpleDataSupplier;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MultiCombo<T> extends CustomField<Set<T>> {

	private List<ComboBox<T>> comboList = new ArrayList<ComboBox<T>>();
	private Panel panel = new Panel();
    private VerticalLayout panelContent = new VerticalLayout();
    private SimpleDataSupplier<List<T>> itemSupplier;
    private List<T> comboItems;
    
    public MultiCombo(String caption) {
    	super();
		setCaption(caption);
	}
    
	public MultiCombo() {
		super();
	}

	@Override
    protected Component initContent() {
        panel.setContent(panelContent);
        addNewItem(null);
		return panel;
	}
	
	public void setItemSupplier(SimpleDataSupplier<List<T>> itemSupplier) {
		this.itemSupplier = itemSupplier;
		comboItems = itemSupplier.getData();
		comboList.forEach(c -> c.setItems(comboItems));
	}

	private void addNewItem(T selected){
		ComboBox<T> combo = createItem(selected);
		comboList.add(combo);
		panelContent.addComponent(combo);
	}

	private ComboBox<T> createItem(T selected){
		ComboBox<T> combo = new ComboBox<T>();
		combo.setItems(comboItems);
		combo.setSelectedItem(selected);
		combo.addValueChangeListener(this::selectItemAction);
		combo.setSizeFull();
		return combo;
	}
	
	private void selectItemAction(ValueChangeEvent<T> e){
		Component component = e.getComponent();
		if(isOnTopOfTheList(component)){
			addNewItem(null);
		}
		if(e.getValue() == null){
			removeItem(component);
		}
	}
	
	private boolean isOnTopOfTheList(Component component){
		int indexOf = comboList.indexOf(component);
		return comboList.size() == indexOf +1;
	}
	
	private void removeItem(Component component){
		comboList.remove(component);
		panelContent.removeComponent(component);
	}

	@Override
	public Set<T> getValue() {
		return comboList.stream()
				.filter(s -> s.getValue() != null)
				.map(c -> c.getValue())
				.collect(Collectors.toSet());
	}

	@Override
	protected void doSetValue(Set<T> value) {
		comboList = new ArrayList<ComboBox<T>>();
		panelContent.removeAllComponents();
		value.forEach(this::addNewItem);
		
	}
	
}
