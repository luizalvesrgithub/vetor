package br.com.util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class GuiasOpcoes extends JTabbedPane {

	
	public GuiasOpcoes() {
		super(JTabbedPane.TOP);
		initialize();
	}
	
	public GuiasOpcoes(int TOP) {
		
		super(TOP);
		initialize();
		
	}

	private void initialize() {
		
		JPanel pLayoutPagina = new JPanel();
		addTab("Configura\u00E7\u00E3o P\u00E1gina", new ImageIcon(GuiasOpcoes.class.getResource("/com/resc/br/layouticon.png")), pLayoutPagina, "Abrir op\u00E7\u00F5es de configura\u00E7\u00F5es de p\u00E1gina.");
		setDisabledIconAt(0, new ImageIcon(GuiasOpcoes.class.getResource("/com/resc/br/layouticon.png")));
		GridBagLayout gbl_pLayoutPagina = new GridBagLayout();
		gbl_pLayoutPagina.columnWidths = new int[]{0, 0};
		gbl_pLayoutPagina.rowHeights = new int[]{0, 0};
		gbl_pLayoutPagina.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pLayoutPagina.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pLayoutPagina.setLayout(gbl_pLayoutPagina);
		
		JButton bPdfPrinter = new JButton("Prt.");
		GridBagConstraints gbc_bPdfPrinter = new GridBagConstraints();
		gbc_bPdfPrinter.gridx = 0;
		gbc_bPdfPrinter.gridy = 0;
		pLayoutPagina.add(bPdfPrinter, gbc_bPdfPrinter);
		
		//
		JPanel pCentralOper = new JPanel();
		this.addTab("Central de Opera\u00E7\u00F5es",
				new ImageIcon("E:\\dev\\workspace\\metacaptor\\WebContent\\imagens\\PNG\\Equipment.png"), pCentralOper,
				null);
		GridBagLayout gbl_pCentralOper = new GridBagLayout();
		gbl_pCentralOper.columnWidths = new int[] { 0, 0, 0 };
		gbl_pCentralOper.rowHeights = new int[] { 0, 0, 0 };
		gbl_pCentralOper.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pCentralOper.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pCentralOper.setLayout(gbl_pCentralOper);

		JButton btnOp = new JButton("Op1");
		btnOp.setIcon(new ImageIcon("E:\\dev\\workspace\\metacaptor\\WebContent\\imagens\\pdficon.jpg"));
		GridBagConstraints gbc_btnOp = new GridBagConstraints();
		gbc_btnOp.insets = new Insets(0, 0, 5, 5);
		gbc_btnOp.gridx = 0;
		gbc_btnOp.gridy = 0;
		pCentralOper.add(btnOp, gbc_btnOp);

		JButton btnOp_1 = new JButton("Op2");
		GridBagConstraints gbc_btnOp_1 = new GridBagConstraints();
		gbc_btnOp_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnOp_1.gridx = 1;
		gbc_btnOp_1.gridy = 0;
		pCentralOper.add(btnOp_1, gbc_btnOp_1);

		JButton btnOp_2 = new JButton("Op3");
		GridBagConstraints gbc_btnOp_2 = new GridBagConstraints();
		gbc_btnOp_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnOp_2.gridx = 0;
		gbc_btnOp_2.gridy = 1;
		pCentralOper.add(btnOp_2, gbc_btnOp_2);

		JButton btnOp_3 = new JButton("Op4");
		GridBagConstraints gbc_btnOp_3 = new GridBagConstraints();
		gbc_btnOp_3.gridx = 1;
		gbc_btnOp_3.gridy = 1;
		pCentralOper.add(btnOp_3, gbc_btnOp_3);

		JPanel pSimulacoes = new JPanel();
		addTab("Simula\u00E7\u00F5es",
				new ImageIcon("E:\\dev\\workspace\\metacaptor\\WebContent\\imagens\\PNG\\Hourglass.png"), pSimulacoes,
				null);
		GridBagLayout gbl_pSimulacoes = new GridBagLayout();
		gbl_pSimulacoes.columnWidths = new int[] { 0, 0 };
		gbl_pSimulacoes.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pSimulacoes.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pSimulacoes.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pSimulacoes.setLayout(gbl_pSimulacoes);

		JButton btnSim = new JButton("Sim1");
		GridBagConstraints gbc_btnSim = new GridBagConstraints();
		gbc_btnSim.insets = new Insets(0, 0, 5, 0);
		gbc_btnSim.gridx = 0;
		gbc_btnSim.gridy = 0;
		pSimulacoes.add(btnSim, gbc_btnSim);

		JButton btnSim_1 = new JButton("Sim2");
		GridBagConstraints gbc_btnSim_1 = new GridBagConstraints();
		gbc_btnSim_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnSim_1.gridx = 0;
		gbc_btnSim_1.gridy = 1;
		pSimulacoes.add(btnSim_1, gbc_btnSim_1);

		JButton btnSim3 = new JButton("Sim3");
		btnSim3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnSim3 = new GridBagConstraints();
		gbc_btnSim3.gridx = 0;
		gbc_btnSim3.gridy = 2;
		pSimulacoes.add(btnSim3, gbc_btnSim3);

		JPanel pHjuda = new JPanel();
		this.addTab("Hist\u00F3rico e Ajuda",
				new ImageIcon("E:\\dev\\workspace\\metacaptor\\WebContent\\imagens\\PNG\\Help book 3d.png"), pHjuda,
				null);
		GridBagLayout gbl_pHjuda = new GridBagLayout();
		gbl_pHjuda.columnWidths = new int[] { 0, 0, 0 };
		gbl_pHjuda.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pHjuda.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pHjuda.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pHjuda.setLayout(gbl_pHjuda);

		JButton btnHa = new JButton("HA1");
		GridBagConstraints gbc_btnHa = new GridBagConstraints();
		gbc_btnHa.insets = new Insets(0, 0, 5, 5);
		gbc_btnHa.gridx = 0;
		gbc_btnHa.gridy = 0;
		pHjuda.add(btnHa, gbc_btnHa);

		JButton btnHa_3 = new JButton("HA4");
		GridBagConstraints gbc_btnHa_3 = new GridBagConstraints();
		gbc_btnHa_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnHa_3.gridx = 1;
		gbc_btnHa_3.gridy = 0;
		pHjuda.add(btnHa_3, gbc_btnHa_3);

		JButton btnHa_1 = new JButton("HA2");
		GridBagConstraints gbc_btnHa_1 = new GridBagConstraints();
		gbc_btnHa_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnHa_1.gridx = 0;
		gbc_btnHa_1.gridy = 1;
		pHjuda.add(btnHa_1, gbc_btnHa_1);

		JButton btnHa_4 = new JButton("HA5");
		GridBagConstraints gbc_btnHa_4 = new GridBagConstraints();
		gbc_btnHa_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnHa_4.gridx = 1;
		gbc_btnHa_4.gridy = 1;
		pHjuda.add(btnHa_4, gbc_btnHa_4);

		JButton btnHa_2 = new JButton("HA3");
		GridBagConstraints gbc_btnHa_2 = new GridBagConstraints();
		gbc_btnHa_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnHa_2.gridx = 0;
		gbc_btnHa_2.gridy = 2;
		pHjuda.add(btnHa_2, gbc_btnHa_2);

		JButton btnHa_5 = new JButton("HA6");
		GridBagConstraints gbc_btnHa_5 = new GridBagConstraints();
		gbc_btnHa_5.gridx = 1;
		gbc_btnHa_5.gridy = 2;
		pHjuda.add(btnHa_5, gbc_btnHa_5);
		
	}
}
