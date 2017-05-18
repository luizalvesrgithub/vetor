package br.com.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class OuvidorEtl implements PropertyChangeListener, ActionListener {

	public OuvidorEtl(ModeloEtl model) {
		model.addChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Changed property: " + event.getPropertyName() + " [old -> " + event.getOldValue()
				+ "] | [new -> " + event.getNewValue() + "]");
	}

	@Override
	public void processAction(ActionEvent evento) throws AbortProcessingException {
		try {
			String componente = evento.getComponent().getClass().getName();
			if(componente.equals("??????"))
				System.out.println(componente);
		} catch (Exception e) {
			throw new AbortProcessingException();
		}
		
	}
	

}