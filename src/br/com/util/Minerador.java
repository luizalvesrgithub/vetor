package br.com.util;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextArea;

public class Minerador {

	private JFrame frmMineradormigraoDe;
	private JTextField textField;
	private JTextField tfClasse;
	private JTextField tfNomeBancoDados;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfClasseJDBCdestino;
	private JTextField tfBdDestino;
	private JTextField tfUsuarioBdDestino;
	private JTextField tfSenhaBdDestino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minerador window = new Minerador();
					window.frmMineradormigraoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Minerador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMineradormigraoDe = new JFrame();
		frmMineradormigraoDe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\mineradorp.jpg"));
		frmMineradormigraoDe.setTitle("Minerador \u2014Migra\u00E7\u00E3o de Dados");
		frmMineradormigraoDe.setBounds(100, 100, 913, 694);
		frmMineradormigraoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmMineradormigraoDe.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		panel.add(toolBar, gbc_toolBar);
		
		JMenuBar menuBar = new JMenuBar();
		toolBar.add(menuBar);
		
		JMenuItem ntmAbrirArquivo = new JMenuItem("Abrir Arquivo");
		menuBar.add(ntmAbrirArquivo);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		menuBar.add(mntmSalvar);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		menuBar.add(mntmFechar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMineradormigraoDe.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Migra\u00E7\u00E3o", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_12 = new JLabel("Dados a Migrar/Origem");
		lblNewLabel_12.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblNewLabel_12.setForeground(new Color(139, 0, 0));
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.gridwidth = 2;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 0;
		gbc_lblNewLabel_12.gridy = 0;
		panel_3.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JLabel lblNewLabel_11 = new JLabel("M\u00F3dulo:");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 1;
		panel_3.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JComboBox cbModulos = new JComboBox();
		GridBagConstraints gbc_cbModulos = new GridBagConstraints();
		gbc_cbModulos.insets = new Insets(0, 0, 5, 5);
		gbc_cbModulos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbModulos.gridx = 1;
		gbc_cbModulos.gridy = 1;
		panel_3.add(cbModulos, gbc_cbModulos);
		
		JLabel lblNewLabel_15 = new JLabel("M\u00F3dulo:");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 2;
		gbc_lblNewLabel_15.gridy = 1;
		panel_3.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		JComboBox cbModuloDestino = new JComboBox();
		GridBagConstraints gbc_cbModuloDestino = new GridBagConstraints();
		gbc_cbModuloDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbModuloDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbModuloDestino.gridx = 3;
		gbc_cbModuloDestino.gridy = 1;
		panel_3.add(cbModuloDestino, gbc_cbModuloDestino);
		
		JLabel lblTabelas = new JLabel("Tabelas:");
		lblTabelas.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTabelas = new GridBagConstraints();
		gbc_lblTabelas.anchor = GridBagConstraints.EAST;
		gbc_lblTabelas.insets = new Insets(0, 0, 5, 5);
		gbc_lblTabelas.gridx = 0;
		gbc_lblTabelas.gridy = 2;
		panel_3.add(lblTabelas, gbc_lblTabelas);
		
		JComboBox cbTabelasModuloOrigem = new JComboBox();
		GridBagConstraints gbc_cbTabelasModuloOrigem = new GridBagConstraints();
		gbc_cbTabelasModuloOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_cbTabelasModuloOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTabelasModuloOrigem.gridx = 1;
		gbc_cbTabelasModuloOrigem.gridy = 2;
		panel_3.add(cbTabelasModuloOrigem, gbc_cbTabelasModuloOrigem);
		
		JLabel lblNewLabel_16 = new JLabel("Tabelas:");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_16.gridx = 2;
		gbc_lblNewLabel_16.gridy = 2;
		panel_3.add(lblNewLabel_16, gbc_lblNewLabel_16);
		
		JComboBox cbTabelasDestino = new JComboBox();
		GridBagConstraints gbc_cbTabelasDestino = new GridBagConstraints();
		gbc_cbTabelasDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbTabelasDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTabelasDestino.gridx = 3;
		gbc_cbTabelasDestino.gridy = 2;
		panel_3.add(cbTabelasDestino, gbc_cbTabelasDestino);
		
		JLabel lblNewLabel_13 = new JLabel("Propriedades Tabela");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridwidth = 2;
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 3;
		panel_3.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JLabel lblNewLabel_17 = new JLabel("Propriedades Tabela Destino");
		lblNewLabel_17.setForeground(new Color(160, 82, 45));
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.gridwidth = 2;
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_17.gridx = 2;
		gbc_lblNewLabel_17.gridy = 3;
		panel_3.add(lblNewLabel_17, gbc_lblNewLabel_17);
		/*
		Vector months = new Vector();
        JList list = new JList(months);

        months.addElement("January");
        months.addElement("December");
        
        c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 3; // third row
        pane.add(new JScrollPane(list), c);
		*/
        Vector<Estrutura> vpropstableorigem = new Vector();
		JList<Estrutura> lstPropriedadesTabOrigem = new JList(vpropstableorigem);
		GridBagConstraints gbc_lstPropriedadesTabOrigem = new GridBagConstraints();
		gbc_lstPropriedadesTabOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_lstPropriedadesTabOrigem.gridwidth = 2;
		gbc_lstPropriedadesTabOrigem.fill = GridBagConstraints.BOTH;
		gbc_lstPropriedadesTabOrigem.gridx = 0;
		gbc_lstPropriedadesTabOrigem.gridy = 4;
		panel_3.add(lstPropriedadesTabOrigem, gbc_lstPropriedadesTabOrigem);
		
		JLabel lblNewLabel_14 = new JLabel("Dados a Gravar/Destino");
		lblNewLabel_14.setForeground(new Color(107, 142, 35));
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_14.gridwidth = 2;
		gbc_lblNewLabel_14.gridx = 2;
		gbc_lblNewLabel_14.gridy = 0;
		panel_3.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		Vector<Estrutura> vpropstabledestino = new Vector();
		JList<Estrutura> lstPropriedadesTabelaDestino = new JList(vpropstabledestino);
		GridBagConstraints gbc_lstPropriedadesTabelaDestino = new GridBagConstraints();
		gbc_lstPropriedadesTabelaDestino.gridwidth = 2;
		gbc_lstPropriedadesTabelaDestino.insets = new Insets(0, 0, 5, 0);
		gbc_lstPropriedadesTabelaDestino.fill = GridBagConstraints.BOTH;
		gbc_lstPropriedadesTabelaDestino.gridx = 2;
		gbc_lstPropriedadesTabelaDestino.gridy = 4;
		panel_3.add(lstPropriedadesTabelaDestino, gbc_lstPropriedadesTabelaDestino);
		
		JButton btnSelecionarParPropriedade = new JButton("Selecionar Par de Para");
		btnSelecionarParPropriedade.setToolTipText("Pode ocorrer de mais de uma propriedade da tabela de origem se destine a um \u00FAnico campo da tabela de destino. Neste caso ser\u00E1 constru\u00EDda a concatena\u00E7\u00E3o dos campos no comando de sele\u00E7\u00E3o da tabela de origem. Exemplo: Select a, (ddd+op+numfone) as fone from table_alvo.");
		GridBagConstraints gbc_btnSelecionarParPropriedade = new GridBagConstraints();
		gbc_btnSelecionarParPropriedade.gridwidth = 2;
		gbc_btnSelecionarParPropriedade.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelecionarParPropriedade.gridx = 1;
		gbc_btnSelecionarParPropriedade.gridy = 5;
		panel_3.add(btnSelecionarParPropriedade, gbc_btnSelecionarParPropriedade);
		
		Vector<Parestrutura> vparesdepara = new Vector();
		JList<Parestrutura> lstParesDePara = new JList(vparesdepara);
		GridBagConstraints gbc_lstParesDePara = new GridBagConstraints();
		gbc_lstParesDePara.gridwidth = 4;
		gbc_lstParesDePara.insets = new Insets(0, 0, 5, 0);
		gbc_lstParesDePara.fill = GridBagConstraints.BOTH;
		gbc_lstParesDePara.gridx = 0;
		gbc_lstParesDePara.gridy = 6;
		panel_3.add(lstParesDePara, gbc_lstParesDePara);
		
		JButton btnSimularScript = new JButton("Simular Script");
		btnSimularScript.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_btnSimularScript = new GridBagConstraints();
		gbc_btnSimularScript.anchor = GridBagConstraints.NORTH;
		gbc_btnSimularScript.insets = new Insets(0, 0, 5, 5);
		gbc_btnSimularScript.gridx = 0;
		gbc_btnSimularScript.gridy = 7;
		panel_3.add(btnSimularScript, gbc_btnSimularScript);
		
		JLabel lblNewLabel_18 = new JLabel("Script de Migra\u00E7\u00E3o");
		lblNewLabel_18.setForeground(new Color(0, 0, 255));
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_18.setToolTipText("O Script de migra\u00E7\u00E3o \u00E9 o texto gerado para execu\u00E7\u00E3o em banco de dados. Dever\u00E1 estar apto a ser copiado para um software qualquer ou simplemente ser executado no aplicativo. O Script \u00E9 quebrado a cada mil registros para gerar pontos de commit e assim melhorar a performance e n\u00E3o impactar nas atividades do ambiente global.");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.gridwidth = 4;
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_18.gridx = 0;
		gbc_lblNewLabel_18.gridy = 8;
		panel_3.add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		JTextArea taScriptMigracao = new JTextArea();
		taScriptMigracao.setText("INSERT INTO tabela_destino (Vetor(campos)) Values (SELECT vetor(campos) from tabela de origem.");
		GridBagConstraints gbc_taScriptMigracao = new GridBagConstraints();
		gbc_taScriptMigracao.gridwidth = 4;
		gbc_taScriptMigracao.fill = GridBagConstraints.BOTH;
		gbc_taScriptMigracao.gridx = 0;
		gbc_taScriptMigracao.gridy = 9;
		panel_3.add(taScriptMigracao, gbc_taScriptMigracao);
		
		JButton btnMegrarDados = new JButton("Gravar Dados Migra\u00E7\u00E3o");
		btnMegrarDados.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_btnMegrarDados = new GridBagConstraints();
		gbc_btnMegrarDados.anchor = GridBagConstraints.NORTH;
		gbc_btnMegrarDados.gridwidth = 2;
		gbc_btnMegrarDados.insets = new Insets(0, 0, 5, 5);
		gbc_btnMegrarDados.gridx = 1;
		gbc_btnMegrarDados.gridy = 7;
		panel_3.add(btnMegrarDados, gbc_btnMegrarDados);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Consultas", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Relacionamentos/Sem\u00E2ntica", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Montador Alg\u00E9brico", null, panel_6, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		frmMineradormigraoDe.getContentPane().add(scrollPane_1, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		frmMineradormigraoDe.getContentPane().add(panel_1, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Pesquisar");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Seguir");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		frmMineradormigraoDe.getContentPane().add(panel_2, BorderLayout.WEST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Conex\u00E3o Banco Origem:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox cbBancoDados = new JComboBox();
		GridBagConstraints gbc_cbBancoDados = new GridBagConstraints();
		gbc_cbBancoDados.gridwidth = 2;
		gbc_cbBancoDados.insets = new Insets(0, 0, 5, 0);
		gbc_cbBancoDados.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbBancoDados.gridx = 1;
		gbc_cbBancoDados.gridy = 0;
		panel_2.add(cbBancoDados, gbc_cbBancoDados);
		
		JLabel lblNewLabel_2 = new JLabel("Driver:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JComboBox cbDriver = new JComboBox();
		GridBagConstraints gbc_cbDriver = new GridBagConstraints();
		gbc_cbDriver.gridwidth = 2;
		gbc_cbDriver.insets = new Insets(0, 0, 5, 0);
		gbc_cbDriver.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDriver.gridx = 1;
		gbc_cbDriver.gridy = 1;
		panel_2.add(cbDriver, gbc_cbDriver);
		
		JLabel lblNewLabel_3 = new JLabel("Classe");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		tfClasse = new JTextField();
		GridBagConstraints gbc_tfClasse = new GridBagConstraints();
		gbc_tfClasse.gridwidth = 2;
		gbc_tfClasse.insets = new Insets(0, 0, 5, 0);
		gbc_tfClasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfClasse.gridx = 1;
		gbc_tfClasse.gridy = 2;
		panel_2.add(tfClasse, gbc_tfClasse);
		tfClasse.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Banco de Dados:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		tfNomeBancoDados = new JTextField();
		GridBagConstraints gbc_tfNomeBancoDados = new GridBagConstraints();
		gbc_tfNomeBancoDados.gridwidth = 2;
		gbc_tfNomeBancoDados.insets = new Insets(0, 0, 5, 0);
		gbc_tfNomeBancoDados.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomeBancoDados.gridx = 1;
		gbc_tfNomeBancoDados.gridy = 3;
		panel_2.add(tfNomeBancoDados, gbc_tfNomeBancoDados);
		tfNomeBancoDados.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 4;
		panel_2.add(lblUsurio, gbc_lblUsurio);
		
		tfUsuario = new JTextField();
		GridBagConstraints gbc_tfUsuario = new GridBagConstraints();
		gbc_tfUsuario.gridwidth = 2;
		gbc_tfUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_tfUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUsuario.gridx = 1;
		gbc_tfUsuario.gridy = 4;
		panel_2.add(tfUsuario, gbc_tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Senha:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		tfSenha = new JTextField();
		GridBagConstraints gbc_tfSenha = new GridBagConstraints();
		gbc_tfSenha.gridwidth = 2;
		gbc_tfSenha.insets = new Insets(0, 0, 5, 0);
		gbc_tfSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSenha.gridx = 1;
		gbc_tfSenha.gridy = 5;
		panel_2.add(tfSenha, gbc_tfSenha);
		tfSenha.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Banco de Dados de Destino");
		lblNewLabel_6.setForeground(new Color(60, 179, 113));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		panel_2.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblConexoBancoDestino = new JLabel("Conex\u00E3o Banco Destino:");
		GridBagConstraints gbc_lblConexoBancoDestino = new GridBagConstraints();
		gbc_lblConexoBancoDestino.anchor = GridBagConstraints.EAST;
		gbc_lblConexoBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblConexoBancoDestino.gridx = 0;
		gbc_lblConexoBancoDestino.gridy = 7;
		panel_2.add(lblConexoBancoDestino, gbc_lblConexoBancoDestino);
		
		JComboBox cbBDdestino = new JComboBox();
		GridBagConstraints gbc_cbBDdestino = new GridBagConstraints();
		gbc_cbBDdestino.gridwidth = 2;
		gbc_cbBDdestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbBDdestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbBDdestino.gridx = 1;
		gbc_cbBDdestino.gridy = 7;
		panel_2.add(cbBDdestino, gbc_cbBDdestino);
		
		JLabel lblDriver = new JLabel("Driver:");
		GridBagConstraints gbc_lblDriver = new GridBagConstraints();
		gbc_lblDriver.anchor = GridBagConstraints.EAST;
		gbc_lblDriver.insets = new Insets(0, 0, 5, 5);
		gbc_lblDriver.gridx = 0;
		gbc_lblDriver.gridy = 8;
		panel_2.add(lblDriver, gbc_lblDriver);
		
		JComboBox cbDriverBdDestino = new JComboBox();
		GridBagConstraints gbc_cbDriverBdDestino = new GridBagConstraints();
		gbc_cbDriverBdDestino.gridwidth = 2;
		gbc_cbDriverBdDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbDriverBdDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDriverBdDestino.gridx = 1;
		gbc_cbDriverBdDestino.gridy = 8;
		panel_2.add(cbDriverBdDestino, gbc_cbDriverBdDestino);
		
		JLabel lblNewLabel_7 = new JLabel("Classe:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 9;
		panel_2.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		tfClasseJDBCdestino = new JTextField();
		GridBagConstraints gbc_tfClasseJDBCdestino = new GridBagConstraints();
		gbc_tfClasseJDBCdestino.gridwidth = 2;
		gbc_tfClasseJDBCdestino.insets = new Insets(0, 0, 5, 0);
		gbc_tfClasseJDBCdestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfClasseJDBCdestino.gridx = 1;
		gbc_tfClasseJDBCdestino.gridy = 9;
		panel_2.add(tfClasseJDBCdestino, gbc_tfClasseJDBCdestino);
		tfClasseJDBCdestino.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Banco de Dados:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 10;
		panel_2.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		tfBdDestino = new JTextField();
		GridBagConstraints gbc_tfBdDestino = new GridBagConstraints();
		gbc_tfBdDestino.gridwidth = 2;
		gbc_tfBdDestino.insets = new Insets(0, 0, 5, 0);
		gbc_tfBdDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfBdDestino.gridx = 1;
		gbc_tfBdDestino.gridy = 10;
		panel_2.add(tfBdDestino, gbc_tfBdDestino);
		tfBdDestino.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 11;
		panel_2.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		tfUsuarioBdDestino = new JTextField();
		GridBagConstraints gbc_tfUsuarioBdDestino = new GridBagConstraints();
		gbc_tfUsuarioBdDestino.gridwidth = 2;
		gbc_tfUsuarioBdDestino.insets = new Insets(0, 0, 5, 0);
		gbc_tfUsuarioBdDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUsuarioBdDestino.gridx = 1;
		gbc_tfUsuarioBdDestino.gridy = 11;
		panel_2.add(tfUsuarioBdDestino, gbc_tfUsuarioBdDestino);
		tfUsuarioBdDestino.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Senha:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 12;
		panel_2.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		tfSenhaBdDestino = new JTextField();
		GridBagConstraints gbc_tfSenhaBdDestino = new GridBagConstraints();
		gbc_tfSenhaBdDestino.gridwidth = 2;
		gbc_tfSenhaBdDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSenhaBdDestino.gridx = 1;
		gbc_tfSenhaBdDestino.gridy = 12;
		panel_2.add(tfSenhaBdDestino, gbc_tfSenhaBdDestino);
		tfSenhaBdDestino.setColumns(10);
	}

}
