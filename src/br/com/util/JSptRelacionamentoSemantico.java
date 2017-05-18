/**
 * 
 */
package br.com.util;

import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * @author hp
 *
 */
public class JSptRelacionamentoSemantico extends JSplitPane {

	/**
	 * 
	 */
	public JSptRelacionamentoSemantico() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setRightComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		panel.add(splitPane, gbc_splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JRadioButton radioButton = new JRadioButton("Parcial");
		radioButton.setSelected(true);
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton.gridx = 0;
		gbc_radioButton.gridy = 0;
		panel_1.add(radioButton, gbc_radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Total");
		GridBagConstraints gbc_radioButton_1 = new GridBagConstraints();
		gbc_radioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_1.gridx = 0;
		gbc_radioButton_1.gridy = 1;
		panel_1.add(radioButton_1, gbc_radioButton_1);
		
		JButton button = new JButton("Gerar Pdf");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 0;
		gbc_button.gridy = 4;
		panel_1.add(button, gbc_button);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 163, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 4.9E-324, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_2.add(scrollPane, gbc_scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setToolTipText("Apresenta\u00E7\u00E3o de arquivos PDFs gerados para os relacionamentos de uma tabela singular. N\u00E3o se gera o MER por completo, mas uma vis\u00E3o particular dos relacionamentos de uma tabela espec\u00EDfica.");
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.inactiveCaption, null, null, null));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 5;
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		panel_2.add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel_3 = new JPanel();
		setLeftComponent(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{134, 0};
		gbl_panel_3.rowHeights = new int[]{14, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblConsultaPorRelacioamento = new JLabel("Consulta por Relacioamento");
		GridBagConstraints gbc_lblConsultaPorRelacioamento = new GridBagConstraints();
		gbc_lblConsultaPorRelacioamento.insets = new Insets(0, 0, 5, 0);
		gbc_lblConsultaPorRelacioamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblConsultaPorRelacioamento.gridx = 0;
		gbc_lblConsultaPorRelacioamento.gridy = 0;
		panel_3.add(lblConsultaPorRelacioamento, gbc_lblConsultaPorRelacioamento);
		
		JList listaRelacionamentos = new JList();
		listaRelacionamentos.setToolTipText("Listar todos os relacionamentos da base de dados e conforme seja selecionado o relacionamento listar a proje\u00E7\u00E3o de propriedades das tabela envolvidas.\r\n");
		GridBagConstraints gbc_listaRelacionamentos = new GridBagConstraints();
		gbc_listaRelacionamentos.fill = GridBagConstraints.BOTH;
		gbc_listaRelacionamentos.gridx = 0;
		gbc_listaRelacionamentos.gridy = 1;
		panel_3.add(listaRelacionamentos, gbc_listaRelacionamentos);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 */
	public JSptRelacionamentoSemantico(int newOrientation) {
		super(newOrientation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public JSptRelacionamentoSemantico(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptRelacionamentoSemantico(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public JSptRelacionamentoSemantico(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
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
