/**
 * 
 */
package br.com.util;

import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;

/**
 * @author hp
 *
 */
public class JSptConsultas extends JSplitPane {
	/**
	 * 
	 */
	public JSptConsultas() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder());
		setRightComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{698, 0};
		gbl_panel.rowHeights = new int[]{21, 462, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 5, 5));
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.insets = new Insets(0, 0, 5, 0);
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		panel.add(menuBar, gbc_menuBar);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		panel.add(splitPane, gbc_splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{523, 0};
		gbl_panel_1.rowHeights = new int[]{30, 267, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Base Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{59, 61, 0};
		gbl_panel_2.rowHeights = new int[]{23, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JRadioButton radioButton = new JRadioButton("Origem");
		buttonGroup.add(radioButton);
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButton.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton.gridx = 0;
		gbc_radioButton.gridy = 0;
		panel_2.add(radioButton, gbc_radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Destino");
		buttonGroup.add(radioButton_1);
		GridBagConstraints gbc_radioButton_1 = new GridBagConstraints();
		gbc_radioButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_radioButton_1.gridx = 0;
		gbc_radioButton_1.gridy = 1;
		panel_2.add(radioButton_1, gbc_radioButton_1);
		
		JPanel panel_3 = new JPanel();
		splitPane.setRightComponent(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{226, 147, 174, 0};
		gbl_panel_3.rowHeights = new int[]{75, 25, 315, 30, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_3.add(textArea, gbc_textArea);
		
		JButton button = new JButton("Executar");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		panel_3.add(button, gbc_button);
		
		JButton button_1 = new JButton("Salvar em Arquivo");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 1;
		panel_3.add(button_1, gbc_button_1);
		
		JTextArea textArea_1 = new JTextArea();
		//textArea_1.setDropMode(DropMode.INSERT_ROWS);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridwidth = 3;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 2;
		panel_3.add(textArea_1, gbc_textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridwidth = 3;
		gbc_textArea_2.gridx = 0;
		gbc_textArea_2.gridy = 3;
		panel_3.add(textArea_2, gbc_textArea_2);
		
		JPanel panel_4 = new JPanel();
		setLeftComponent(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{89, 0};
		gbl_panel_4.rowHeights = new int[]{14, 0, 0, 333, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblTiposDeConsulta = new JLabel("Tipos de Consulta:");
		GridBagConstraints gbc_lblTiposDeConsulta = new GridBagConstraints();
		gbc_lblTiposDeConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_lblTiposDeConsulta.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTiposDeConsulta.gridx = 0;
		gbc_lblTiposDeConsulta.gridy = 0;
		panel_4.add(lblTiposDeConsulta, gbc_lblTiposDeConsulta);
		
		JCheckBox chckbxMultipla = new JCheckBox("Multipla");
		GridBagConstraints gbc_chckbxMultipla = new GridBagConstraints();
		gbc_chckbxMultipla.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxMultipla.gridx = 0;
		gbc_chckbxMultipla.gridy = 1;
		panel_4.add(chckbxMultipla, gbc_chckbxMultipla);
		
		JCheckBox chckbxSingular = new JCheckBox("Singular");
		GridBagConstraints gbc_chckbxSingular = new GridBagConstraints();
		gbc_chckbxSingular.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSingular.gridx = 0;
		gbc_chckbxSingular.gridy = 2;
		panel_4.add(chckbxSingular, gbc_chckbxSingular);
		
		JCheckBox chckbxRegra = new JCheckBox("Regra");
		GridBagConstraints gbc_chckbxRegra = new GridBagConstraints();
		gbc_chckbxRegra.gridx = 0;
		gbc_chckbxRegra.gridy = 3;
		panel_4.add(chckbxRegra, gbc_chckbxRegra);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 */
	public JSptConsultas(int newOrientation) {
		super(newOrientation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public JSptConsultas(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptConsultas(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptConsultas(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	private final ButtonGroup buttonGroup = new ButtonGroup();
}
