package com.util.br;

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
import javax.swing.DefaultComboBoxModel;

public class Minerador {

	private JFrame frmMineradormigraoDe;
	private JTextField textField;
	private JTextField ttfClasse;
	private JTextField ttfNomeBancoOrigem;
	private JTextField ttfUsuarioOrigem;
	private JTextField ttfSenhaBdOrigem;
	private JTextField ttfClasseBancoDestino;
	private JTextField ttfNomeBancoDestino;
	private JTextField ttfUsuarioBancoDestino;
	private JTextField ttfSenhaBancoDestino;

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
		frmMineradormigraoDe.setBounds(100, 100, 812, 573);
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
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Conex\u00F5es", null, panel_7, "Informa\u00E7\u00F5es de banco de dados, drivers e classes de conex\u00E3o JDBC.  Exemplos: \r\norg.postgresql.Driver \u2014 POSTGRESQL\r\ncom.mysql.jdbc.Driver \u2014 MYSQL\r\noracle.jdbc.OracleDriver \u2014 ORACLE");
		tabbedPane.setDisabledIconAt(0, new ImageIcon("C:\\dev\\workspace\\vetor\\WebContent\\imagens\\bdicone.jpg"));
		tabbedPane.setForegroundAt(0, new Color(255, 140, 0));
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblBancoDeDados = new JLabel("Banco de Dados de Origem");
		lblBancoDeDados.setForeground(new Color(100, 149, 237));
		lblBancoDeDados.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblBancoDeDados = new GridBagConstraints();
		gbc_lblBancoDeDados.insets = new Insets(0, 0, 5, 0);
		gbc_lblBancoDeDados.gridx = 0;
		gbc_lblBancoDeDados.gridy = 0;
		panel_7.add(lblBancoDeDados, gbc_lblBancoDeDados);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 1;
		panel_7.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JLabel label = new JLabel("Conex\u00E3o Banco Origem:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_8.add(label, gbc_label);
		
		JComboBox cbbBdOrigem = new JComboBox();
		cbbBdOrigem.setModel(new DefaultComboBoxModel(new String[] {"DB2", "Cache", "MS Sqlserver ", "MySql", "Oracle", "PostgreSql", "", ""}));
		GridBagConstraints gbc_cbbBdOrigem = new GridBagConstraints();
		gbc_cbbBdOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbBdOrigem.gridwidth = 2;
		gbc_cbbBdOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_cbbBdOrigem.gridx = 1;
		gbc_cbbBdOrigem.gridy = 0;
		panel_8.add(cbbBdOrigem, gbc_cbbBdOrigem);
		
		JLabel label_1 = new JLabel("Driver:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel_8.add(label_1, gbc_label_1);
		
		JComboBox cbbDriverOrigem = new JComboBox();
		cbbDriverOrigem.setModel(new DefaultComboBoxModel(new String[] {"org.postgresql.Driver", "com.mysql.jdbc.Driver", "oracle.jdbc.OracleDriver"}));
		GridBagConstraints gbc_cbbDriverOrigem = new GridBagConstraints();
		gbc_cbbDriverOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbDriverOrigem.gridwidth = 2;
		gbc_cbbDriverOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_cbbDriverOrigem.gridx = 1;
		gbc_cbbDriverOrigem.gridy = 1;
		panel_8.add(cbbDriverOrigem, gbc_cbbDriverOrigem);
		
		JLabel label_2 = new JLabel("Classe");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel_8.add(label_2, gbc_label_2);
		
		ttfClasse = new JTextField();
		ttfClasse.setColumns(10);
		GridBagConstraints gbc_ttfClasse = new GridBagConstraints();
		gbc_ttfClasse.anchor = GridBagConstraints.WEST;
		gbc_ttfClasse.gridwidth = 2;
		gbc_ttfClasse.insets = new Insets(0, 0, 5, 0);
		gbc_ttfClasse.gridx = 1;
		gbc_ttfClasse.gridy = 2;
		panel_8.add(ttfClasse, gbc_ttfClasse);
		
		JLabel label_3 = new JLabel("Banco de Dados:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel_8.add(label_3, gbc_label_3);
		
		ttfNomeBancoOrigem = new JTextField();
		ttfNomeBancoOrigem.setColumns(10);
		GridBagConstraints gbc_ttfNomeBancoOrigem = new GridBagConstraints();
		gbc_ttfNomeBancoOrigem.anchor = GridBagConstraints.WEST;
		gbc_ttfNomeBancoOrigem.gridwidth = 2;
		gbc_ttfNomeBancoOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_ttfNomeBancoOrigem.gridx = 1;
		gbc_ttfNomeBancoOrigem.gridy = 3;
		panel_8.add(ttfNomeBancoOrigem, gbc_ttfNomeBancoOrigem);
		
		JLabel label_4 = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		panel_8.add(label_4, gbc_label_4);
		
		ttfUsuarioOrigem = new JTextField();
		ttfUsuarioOrigem.setColumns(10);
		GridBagConstraints gbc_ttfUsuarioOrigem = new GridBagConstraints();
		gbc_ttfUsuarioOrigem.anchor = GridBagConstraints.WEST;
		gbc_ttfUsuarioOrigem.gridwidth = 2;
		gbc_ttfUsuarioOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_ttfUsuarioOrigem.gridx = 1;
		gbc_ttfUsuarioOrigem.gridy = 4;
		panel_8.add(ttfUsuarioOrigem, gbc_ttfUsuarioOrigem);
		
		JLabel label_5 = new JLabel("Senha:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 5;
		panel_8.add(label_5, gbc_label_5);
		
		ttfSenhaBdOrigem = new JTextField();
		ttfSenhaBdOrigem.setColumns(10);
		GridBagConstraints gbc_ttfSenhaBdOrigem = new GridBagConstraints();
		gbc_ttfSenhaBdOrigem.anchor = GridBagConstraints.WEST;
		gbc_ttfSenhaBdOrigem.gridwidth = 2;
		gbc_ttfSenhaBdOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_ttfSenhaBdOrigem.gridx = 1;
		gbc_ttfSenhaBdOrigem.gridy = 5;
		panel_8.add(ttfSenhaBdOrigem, gbc_ttfSenhaBdOrigem);
		
		JLabel label_6 = new JLabel("Banco de Dados de Destino");
		label_6.setForeground(new Color(60, 179, 113));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.gridwidth = 2;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 6;
		panel_8.add(label_6, gbc_label_6);
		
		JLabel label_7 = new JLabel("Conex\u00E3o Banco Destino:");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 7;
		panel_8.add(label_7, gbc_label_7);
		
		JComboBox cbbBancoDestino = new JComboBox();
		cbbBancoDestino.setModel(new DefaultComboBoxModel(new String[] {"DB2", "Cache", "MS Sqlserver ", "MySql", "Oracle", "PostgreSql", ""}));
		GridBagConstraints gbc_cbbBancoDestino = new GridBagConstraints();
		gbc_cbbBancoDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbBancoDestino.gridwidth = 2;
		gbc_cbbBancoDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbbBancoDestino.gridx = 1;
		gbc_cbbBancoDestino.gridy = 7;
		panel_8.add(cbbBancoDestino, gbc_cbbBancoDestino);
		
		JLabel label_8 = new JLabel("Driver:");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.WEST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 8;
		panel_8.add(label_8, gbc_label_8);
		
		JComboBox cbbDriverBdDestino = new JComboBox();
		cbbDriverBdDestino.setModel(new DefaultComboBoxModel(new String[] {"org.postgresql.Driver", "com.mysql.jdbc.Driver", "oracle.jdbc.OracleDriver"}));
		GridBagConstraints gbc_cbbDriverBdDestino = new GridBagConstraints();
		gbc_cbbDriverBdDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbbDriverBdDestino.gridwidth = 2;
		gbc_cbbDriverBdDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cbbDriverBdDestino.gridx = 1;
		gbc_cbbDriverBdDestino.gridy = 8;
		panel_8.add(cbbDriverBdDestino, gbc_cbbDriverBdDestino);
		
		JLabel label_9 = new JLabel("Classe:");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.WEST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 9;
		panel_8.add(label_9, gbc_label_9);
		
		ttfClasseBancoDestino = new JTextField();
		ttfClasseBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfClasseBancoDestino = new GridBagConstraints();
		gbc_ttfClasseBancoDestino.anchor = GridBagConstraints.WEST;
		gbc_ttfClasseBancoDestino.gridwidth = 2;
		gbc_ttfClasseBancoDestino.insets = new Insets(0, 0, 5, 0);
		gbc_ttfClasseBancoDestino.gridx = 1;
		gbc_ttfClasseBancoDestino.gridy = 9;
		panel_8.add(ttfClasseBancoDestino, gbc_ttfClasseBancoDestino);
		
		JLabel label_10 = new JLabel("Banco de Dados:");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 10;
		panel_8.add(label_10, gbc_label_10);
		
		ttfNomeBancoDestino = new JTextField();
		ttfNomeBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfNomeBancoDestino = new GridBagConstraints();
		gbc_ttfNomeBancoDestino.anchor = GridBagConstraints.WEST;
		gbc_ttfNomeBancoDestino.gridwidth = 2;
		gbc_ttfNomeBancoDestino.insets = new Insets(0, 0, 5, 0);
		gbc_ttfNomeBancoDestino.gridx = 1;
		gbc_ttfNomeBancoDestino.gridy = 10;
		panel_8.add(ttfNomeBancoDestino, gbc_ttfNomeBancoDestino);
		
		JLabel label_11 = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.WEST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 11;
		panel_8.add(label_11, gbc_label_11);
		
		ttfUsuarioBancoDestino = new JTextField();
		ttfUsuarioBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfUsuarioBancoDestino = new GridBagConstraints();
		gbc_ttfUsuarioBancoDestino.anchor = GridBagConstraints.WEST;
		gbc_ttfUsuarioBancoDestino.gridwidth = 2;
		gbc_ttfUsuarioBancoDestino.insets = new Insets(0, 0, 5, 0);
		gbc_ttfUsuarioBancoDestino.gridx = 1;
		gbc_ttfUsuarioBancoDestino.gridy = 11;
		panel_8.add(ttfUsuarioBancoDestino, gbc_ttfUsuarioBancoDestino);
		
		JLabel label_12 = new JLabel("Senha:");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.WEST;
		gbc_label_12.insets = new Insets(0, 0, 0, 5);
		gbc_label_12.gridx = 0;
		gbc_label_12.gridy = 12;
		panel_8.add(label_12, gbc_label_12);
		
		ttfSenhaBancoDestino = new JTextField();
		ttfSenhaBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfSenhaBancoDestino = new GridBagConstraints();
		gbc_ttfSenhaBancoDestino.anchor = GridBagConstraints.WEST;
		gbc_ttfSenhaBancoDestino.gridwidth = 2;
		gbc_ttfSenhaBancoDestino.gridx = 1;
		gbc_ttfSenhaBancoDestino.gridy = 12;
		panel_8.add(ttfSenhaBancoDestino, gbc_ttfSenhaBancoDestino);
		
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
	}

}
