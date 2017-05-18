/**
 * 
 */
package br.com.util;

import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;

/**
 * @author hp
 *
 */
public class JSptModelosGraficos extends JSplitPane {

	/**
	 * 
	 */
	public JSptModelosGraficos() {
		
		JSplitPane splitPane = new JSplitPane();
		setRightComponent(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton button = new JButton("Entidade");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		panel.add(button, gbc_button);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{256, 102, -34, 0};
		gbl_panel_1.rowHeights = new int[]{91, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 4.9E-324, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 4.9E-324, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1.gridheight = 3;
		gbc_splitPane_1.gridwidth = 3;
		gbc_splitPane_1.gridx = 0;
		gbc_splitPane_1.gridy = 0;
		panel_1.add(splitPane_1, gbc_splitPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane_1.setLeftComponent(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		splitPane_1.setRightComponent(tabbedPane_1);
		
		JPanel panel_2 = new JPanel();
		setLeftComponent(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{51, 0};
		gbl_panel_2.rowHeights = new int[]{57, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblInstrues = new JLabel("Instru\u00E7\u00F5es");
		GridBagConstraints gbc_lblInstrues = new GridBagConstraints();
		gbc_lblInstrues.insets = new Insets(0, 0, 5, 0);
		gbc_lblInstrues.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblInstrues.gridx = 0;
		gbc_lblInstrues.gridy = 0;
		panel_2.add(lblInstrues, gbc_lblInstrues);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_2.add(list, gbc_list);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 */
	public JSptModelosGraficos(int newOrientation) {
		super(newOrientation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public JSptModelosGraficos(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptModelosGraficos(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptModelosGraficos(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
