/**
 * 
 */
package br.com.util;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.com.util.ModeloEtl.Person;
/**
 * @author hp
 *
 */
public class Ouvidor {

	/**
	 * 
	 */
	public Ouvidor() {
		model = new ModeloEtl();
        observer = new OuvidorEtl(model);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Ouvidor ouvidor = new Ouvidor();
						        // we change the last name of the person, observer will get notified
						        for (Person person : model.getPersons()) {
						            person.setLastName(person.getLastName() + "1");
						        }
						        // we change the name of the person, observer will get notified
						        for (Person person : model.getPersons()) {
						            person.setFirstName(person.getFirstName() + "1");
						        }
						        
								ouvidor.model.jfrm.setVisible(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	private static ModeloEtl model;
	private static OuvidorEtl observer;

}


