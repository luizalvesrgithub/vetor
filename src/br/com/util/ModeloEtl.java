package br.com.util;

import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ModeloEtl {

	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";

	private List<Person> persons = new ArrayList<Person>();
	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();
	private List<ActionListener> actionlistener = new ArrayList<ActionListener>();

	private List<Object> guio = new ArrayList<Object>();

	public class Person {

		private String firstName;

		private String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {

			return firstName;
		}

		public void setFirstName(String firstName) {
			notifyListeners(this, FIRSTNAME, this.firstName, this.firstName = firstName);

		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			notifyListeners(this, LASTNAME, this.lastName, this.lastName = lastName);
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public ModeloEtl() {

		jfrm.getContentPane().setLayout(new BorderLayout(0, 0));
		jfrm.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\mineradorp.jpg"));
		jfrm.setTitle("Minerador \u2014Migra\u00E7\u00E3o de Dados");
		jfrm.setBounds(100, 100, 1112, 654);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JSplitPane splitPane = new JSplitPane();
		jfrm.getContentPane().add(splitPane);

		JPanel pdiretiva = new JPanel();
		splitPane.setLeftComponent(pdiretiva);
		GridBagLayout gbl_pdiretiva = new GridBagLayout();
		gbl_pdiretiva.columnWidths = new int[] { 55, 0 };
		gbl_pdiretiva.rowHeights = new int[] { 14, 0, 0, 0 };
		gbl_pdiretiva.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pdiretiva.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pdiretiva.setLayout(gbl_pdiretiva);

		JLabel lblLaboratrio = new JLabel("Laborat\u00F3rio");
		GridBagConstraints gbc_lblLaboratrio = new GridBagConstraints();
		gbc_lblLaboratrio.insets = new Insets(0, 0, 5, 0);
		gbc_lblLaboratrio.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblLaboratrio.gridx = 0;
		gbc_lblLaboratrio.gridy = 0;
		pdiretiva.add(lblLaboratrio, gbc_lblLaboratrio);

		JPanel jptiposLaje = new JPanel();
		jptiposLaje
				.setBorder(new TitledBorder(null, "Tipos de Laje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_jptiposLaje = new GridBagConstraints();
		gbc_jptiposLaje.fill = GridBagConstraints.BOTH;
		gbc_jptiposLaje.insets = new Insets(0, 0, 5, 0);
		gbc_jptiposLaje.gridx = 0;
		gbc_jptiposLaje.gridy = 1;
		pdiretiva.add(jptiposLaje, gbc_jptiposLaje);
		GridBagLayout gbl_jptiposLaje = new GridBagLayout();
		gbl_jptiposLaje.columnWidths = new int[] { 55, 0 };
		gbl_jptiposLaje.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_jptiposLaje.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jptiposLaje.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jptiposLaje.setLayout(gbl_jptiposLaje);

		JRadioButton rdbtnConcretoArmado = new JRadioButton("Concreto Armado");
		guio.add(rdbtnConcretoArmado);
		rdbtnConcretoArmado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});

		GridBagConstraints gbc_rdbtnConcretoArmado = new GridBagConstraints();
		gbc_rdbtnConcretoArmado.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnConcretoArmado.gridx = 0;
		gbc_rdbtnConcretoArmado.gridy = 0;
		jptiposLaje.add(rdbtnConcretoArmado, gbc_rdbtnConcretoArmado);
		buttonGroup.add(rdbtnConcretoArmado);

		JRadioButton rdbtnSteelDeck = new JRadioButton("Steel Deck");
		guio.add(rdbtnSteelDeck);
		rdbtnSteelDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				notifyActions(this, e);
			}
		});
		GridBagConstraints gbc_rdbtnSteelDeck = new GridBagConstraints();
		gbc_rdbtnSteelDeck.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSteelDeck.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnSteelDeck.gridx = 0;
		gbc_rdbtnSteelDeck.gridy = 1;
		jptiposLaje.add(rdbtnSteelDeck, gbc_rdbtnSteelDeck);
		buttonGroup.add(rdbtnSteelDeck);

		JRadioButton rdbtnMistoIsopor = new JRadioButton("Misto Isopor");
		guio.add(rdbtnMistoIsopor);
		rdbtnMistoIsopor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});
		GridBagConstraints gbc_rdbtnMistoIsopor = new GridBagConstraints();
		gbc_rdbtnMistoIsopor.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMistoIsopor.gridx = 0;
		gbc_rdbtnMistoIsopor.gridy = 2;
		jptiposLaje.add(rdbtnMistoIsopor, gbc_rdbtnMistoIsopor);
		buttonGroup.add(rdbtnMistoIsopor);

		JButton btnResetar = new JButton("Resetar");
		guio.add(btnResetar);
		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});
		GridBagConstraints gbc_btnResetar = new GridBagConstraints();
		gbc_btnResetar.gridx = 0;
		gbc_btnResetar.gridy = 2;
		pdiretiva.add(btnResetar, gbc_btnResetar);

		JPanel pespecifico = new JPanel();
		splitPane.setRightComponent(pespecifico);
		GridBagLayout gbl_pespecifico = new GridBagLayout();
		gbl_pespecifico.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pespecifico.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pespecifico.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pespecifico.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pespecifico.setLayout(gbl_pespecifico);

		JLabel lblParmetrosDeClculo = new JLabel("Par\u00E2metros de C\u00E1lculo");
		GridBagConstraints gbc_lblParmetrosDeClculo = new GridBagConstraints();
		gbc_lblParmetrosDeClculo.insets = new Insets(0, 0, 5, 5);
		gbc_lblParmetrosDeClculo.gridx = 0;
		gbc_lblParmetrosDeClculo.gridy = 0;
		pespecifico.add(lblParmetrosDeClculo, gbc_lblParmetrosDeClculo);

		JLabel lblrea = new JLabel("\u00C1rea:");
		GridBagConstraints gbc_lblrea = new GridBagConstraints();
		gbc_lblrea.anchor = GridBagConstraints.EAST;
		gbc_lblrea.insets = new Insets(0, 0, 5, 5);
		gbc_lblrea.gridx = 0;
		gbc_lblrea.gridy = 1;
		pespecifico.add(lblrea, gbc_lblrea);

		JTextField tfArea = new JTextField();
		GridBagConstraints gbc_tfArea = new GridBagConstraints();
		gbc_tfArea.insets = new Insets(0, 0, 5, 5);
		gbc_tfArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfArea.gridx = 1;
		gbc_tfArea.gridy = 1;
		pespecifico.add(tfArea, gbc_tfArea);
		tfArea.setColumns(10);

		JLabel lblAltura = new JLabel("Altura:");
		GridBagConstraints gbc_lblAltura = new GridBagConstraints();
		gbc_lblAltura.anchor = GridBagConstraints.EAST;
		gbc_lblAltura.insets = new Insets(0, 0, 5, 5);
		gbc_lblAltura.gridx = 0;
		gbc_lblAltura.gridy = 2;
		pespecifico.add(lblAltura, gbc_lblAltura);

		tfAltura = new JTextField();
		GridBagConstraints gbc_tfAltura = new GridBagConstraints();
		gbc_tfAltura.insets = new Insets(0, 0, 5, 5);
		gbc_tfAltura.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAltura.gridx = 1;
		gbc_tfAltura.gridy = 2;
		pespecifico.add(tfAltura, gbc_tfAltura);
		tfAltura.setColumns(10);

		JButton btnCalcular = new JButton("Calcular");
		guio.add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});

		GridBagConstraints gbc_btnCalcular = new GridBagConstraints();
		gbc_btnCalcular.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcular.gridx = 1;
		gbc_btnCalcular.gridy = 3;
		pespecifico.add(btnCalcular, gbc_btnCalcular);

		tResultadoCalculo = new JTable();
		GridBagConstraints gbc_tResultadoCalculo = new GridBagConstraints();
		gbc_tResultadoCalculo.insets = new Insets(0, 0, 5, 5);
		gbc_tResultadoCalculo.fill = GridBagConstraints.BOTH;
		gbc_tResultadoCalculo.gridx = 0;
		gbc_tResultadoCalculo.gridy = 4;
		pespecifico.add(tResultadoCalculo, gbc_tResultadoCalculo);

		tListaMateriais = new JTable();
		GridBagConstraints gbc_tListaMateriais = new GridBagConstraints();
		gbc_tListaMateriais.insets = new Insets(0, 0, 5, 5);
		gbc_tListaMateriais.fill = GridBagConstraints.BOTH;
		gbc_tListaMateriais.gridx = 1;
		gbc_tListaMateriais.gridy = 4;
		pespecifico.add(tListaMateriais, gbc_tListaMateriais);

		JButton btnImprCalculo = new JButton("Imprimir");
		guio.add(btnImprCalculo);
		btnImprCalculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});
		GridBagConstraints gbc_btnImprCalculo = new GridBagConstraints();
		gbc_btnImprCalculo.anchor = GridBagConstraints.NORTH;
		gbc_btnImprCalculo.insets = new Insets(0, 0, 0, 5);
		gbc_btnImprCalculo.gridx = 0;
		gbc_btnImprCalculo.gridy = 5;
		pespecifico.add(btnImprCalculo, gbc_btnImprCalculo);

		JButton btnImprMateriais = new JButton("Imprimir");
		guio.add(btnImprMateriais);
		btnImprMateriais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyActions(this, e);
			}
		});
		GridBagConstraints gbc_btnImprMateriais = new GridBagConstraints();
		gbc_btnImprMateriais.anchor = GridBagConstraints.SOUTH;
		gbc_btnImprMateriais.insets = new Insets(0, 0, 0, 5);
		gbc_btnImprMateriais.gridx = 1;
		gbc_btnImprMateriais.gridy = 5;
		pespecifico.add(btnImprMateriais, gbc_btnImprMateriais);
		// just for testing we hard-code the persons here:
		persons.add(new Person("Lars", "Vogel"));
		persons.add(new Person("Jim", "Knopf"));
		persons.add(new Person("Rodrigues", "Luiz Alves"));
	}

	public List<Object> getGuiActionApplyed() {
		return guio;
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

	public void addActionListener(ActionListener acao) {
		actionlistener.add(acao);
		for (Acoes act : Acoes.values()) {
			String oquefazer = acao.getClass().getName();
			execute(acao, oquefazer, act);
		}
	}

	private void notifyActions(ActionListener action, ActionEvent e) {
		addActionListener(action);
		for (Object gui : getGuiActionApplyed()) {
			String classeObjeto = gui.getClass().getName();
			String nomeAcao = action.getClass().getName();
			String nomeEvento = e.getActionCommand();
			System.out.println("Objeto: " + classeObjeto + " nome Ação: " + nomeAcao + " nome evento: " + nomeEvento);
		}

	}

	public boolean execute(ActionListener action, String oquefazer, Acoes act) {

		    if(act.equals(oquefazer)) {
		    	
		    }
			switch (act) {
				case calcular:
	
					break;
				case imprimir:
	
					break;
	
				case lajeconcreto:
	
					break;
	
				case lajesteeldck:
	
					break;
	
				case lajemista:
	
					break;
	
				case resetar:
	
					break;
	
				case gravarSituacao:
	
					break;
	
				case definirMelhorCaso:
	
					break;
	
				default:
					System.out.println("Tipo de ação não vinculada a processamento definido");
					break;
			}
		

		return true;
	}

	JFrame jfrm = new JFrame();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfAltura;
	private JTable tListaMateriais;
	private JTable tResultadoCalculo;

	private enum Acoes {
		calcularLaje, calcularViga, calcularPilar, imprimir, lajeconcreto, lajesteeldck, lajemista, resetar, gravarSituacao, definirMelhorCaso, calcular
	};
	
	private enum TiposElementos {pilarMadeira, PilarMetálica, lajeSteelDeck};

}