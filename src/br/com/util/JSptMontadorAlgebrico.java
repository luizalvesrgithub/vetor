/**
 * 
 */
package br.com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;

/**
 * @author hp
 *
 */
public class JSptMontadorAlgebrico extends JSplitPane {

	protected static JTextArea taSelectCommands = new JTextArea();
	/**
	 * 
	 */
	public JSptMontadorAlgebrico() {
		
		JPanel panel = new JPanel();
		setRightComponent(panel);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane);
		
		JPanel pscriptCenter = new JPanel();
		splitPane.setLeftComponent(pscriptCenter);
		pscriptCenter.setBackground(new Color(250, 235, 215));

		
		GridBagLayout gbl_pscriptCenter = new GridBagLayout();
		gbl_pscriptCenter.columnWidths = new int[] { 0, 559 };
		gbl_pscriptCenter.rowHeights = new int[] { 61, 0, 324, 0 };
		gbl_pscriptCenter.columnWeights = new double[] { 1.0, 1.0 };
		gbl_pscriptCenter.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pscriptCenter.setLayout(gbl_pscriptCenter);

		JButton btnExecutarScript = new JButton("Executar Script");
		GridBagConstraints gbc_btnExecutarScript = new GridBagConstraints();
		gbc_btnExecutarScript.insets = new Insets(0, 0, 5, 0);
		gbc_btnExecutarScript.gridx = 0;
		gbc_btnExecutarScript.gridy = 0;
		pscriptCenter.add(btnExecutarScript, gbc_btnExecutarScript);

		JScrollPane scpSelectCommands = new JScrollPane();
		GridBagConstraints gbc_scpSelectCommands = new GridBagConstraints();
		gbc_scpSelectCommands.insets = new Insets(0, 0, 5, 0);
		gbc_scpSelectCommands.fill = GridBagConstraints.BOTH;
		gbc_scpSelectCommands.gridx = 0;
		gbc_scpSelectCommands.gridy = 1;
		pscriptCenter.add(scpSelectCommands, gbc_scpSelectCommands);

		taSelectCommands = new JTextArea();
		scpSelectCommands.setColumnHeaderView(taSelectCommands);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		pscriptCenter.add(scrollPane, gbc_scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridheight = 2;
		gbc_editorPane.gridwidth = 2;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		pscriptCenter.add(editorPane, gbc_editorPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Uso futuro", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Uso Futuro 2", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		setLeftComponent(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{123, 0};
		gbl_panel_3.rowHeights = new int[]{14, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblGeraoDePossibilidades = new JLabel("Gera\u00E7\u00E3o de Possibilidades");
		GridBagConstraints gbc_lblGeraoDePossibilidades = new GridBagConstraints();
		gbc_lblGeraoDePossibilidades.insets = new Insets(0, 0, 5, 0);
		gbc_lblGeraoDePossibilidades.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblGeraoDePossibilidades.gridx = 0;
		gbc_lblGeraoDePossibilidades.gridy = 0;
		panel_3.add(lblGeraoDePossibilidades, gbc_lblGeraoDePossibilidades);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_3.add(list, gbc_list);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 */
	public JSptMontadorAlgebrico(int newOrientation) {
		super(newOrientation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public JSptMontadorAlgebrico(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptMontadorAlgebrico(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptMontadorAlgebrico(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
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
