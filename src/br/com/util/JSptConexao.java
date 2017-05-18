package br.com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import br.com.cliente.FretusModel;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author t317542
 *
 */
public class JSptConexao extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JSptConexao() { // JFrame pjfrm) {

		initialize();

	}

	private void initialize() {

		JPanel jPconexoes = new JPanel();
		setRightComponent(jPconexoes);
		jspDiretorioConexoes = new JSplitPane();
		jspDiretorioConexoes.setOrientation(JSplitPane.VERTICAL_SPLIT);
		setLeftComponent(jspDiretorioConexoes);

		JTree jtreeConexaoOrigem = new JTree();
		jtreeConexaoOrigem.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				
			}
		});
		jtreeConexaoOrigem.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Banco de Destino") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Tabelas");
				node_1.add(new DefaultMutableTreeNode("blue"));
				node_1.add(new DefaultMutableTreeNode("violet"));
				node_1.add(new DefaultMutableTreeNode("red"));
				node_1.add(new DefaultMutableTreeNode("yellow"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Relacionamentos");
				node_1.add(new DefaultMutableTreeNode("basketball"));
				node_1.add(new DefaultMutableTreeNode("soccer"));
				node_1.add(new DefaultMutableTreeNode("football"));
				node_1.add(new DefaultMutableTreeNode("hockey"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Chaves");
				node_1.add(new DefaultMutableTreeNode("hot dogs"));
				node_1.add(new DefaultMutableTreeNode("pizza"));
				node_1.add(new DefaultMutableTreeNode("ravioli"));
				node_1.add(new DefaultMutableTreeNode("bananas"));
				add(node_1);
			}
		}));
		jtreeConexaoOrigem.setForeground(new Color(255, 222, 173));
		JScrollPane jspDireConexaoOrigem = new JScrollPane(jtreeConexaoOrigem);
		jspDiretorioConexoes.setLeftComponent(jspDireConexaoOrigem);

		JTree jtreeConexaoDestino = new JTree();
		jtreeConexaoDestino.setForeground(new Color(255, 218, 185));
		jtreeConexaoDestino.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Banco Origem") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Tabelas");
				node_1.add(new DefaultMutableTreeNode("blue"));
				node_1.add(new DefaultMutableTreeNode("violet"));
				node_1.add(new DefaultMutableTreeNode("red"));
				node_1.add(new DefaultMutableTreeNode("yellow"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Relacionamentos");
				node_1.add(new DefaultMutableTreeNode("basketball"));
				node_1.add(new DefaultMutableTreeNode("soccer"));
				node_1.add(new DefaultMutableTreeNode("football"));
				node_1.add(new DefaultMutableTreeNode("hockey"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Chaves");
				node_1.add(new DefaultMutableTreeNode("hot dogs"));
				node_1.add(new DefaultMutableTreeNode("pizza"));
				node_1.add(new DefaultMutableTreeNode("ravioli"));
				node_1.add(new DefaultMutableTreeNode("bananas"));
				add(node_1);
			}
		}));
		JScrollPane jspDireConexaoDestino = new JScrollPane(jtreeConexaoDestino);
		jspDiretorioConexoes.setRightComponent(jspDireConexaoDestino);

		GridBagLayout gbl_jPconexoes = new GridBagLayout();
		gbl_jPconexoes.columnWidths = new int[] { 693, 0 };
		gbl_jPconexoes.rowHeights = new int[] { 0, 0, 0 };
		gbl_jPconexoes.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jPconexoes.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		jPconexoes.setLayout(gbl_jPconexoes);

		JLabel lblBdOrigem = new JLabel("Banco de Origem");
		GridBagConstraints gbc_lblRenderizadorPdf = new GridBagConstraints();
		gbc_lblRenderizadorPdf.anchor = GridBagConstraints.WEST;
		gbc_lblRenderizadorPdf.insets = new Insets(0, 0, 5, 0);
		gbc_lblRenderizadorPdf.gridx = 0;
		gbc_lblRenderizadorPdf.gridy = 0;
		jPconexoes.add(lblBdOrigem, gbc_lblRenderizadorPdf);

		JPanel jPconJDBC = new JPanel();
		jPconJDBC.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_jPconJDBC = new GridBagConstraints();
		gbc_jPconJDBC.fill = GridBagConstraints.BOTH;
		gbc_jPconJDBC.gridx = 0;
		gbc_jPconJDBC.gridy = 1;
		jPconexoes.add(jPconJDBC, gbc_jPconJDBC);
		GridBagLayout gbl_jPconJDBC = new GridBagLayout();
		gbl_jPconJDBC.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_jPconJDBC.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_jPconJDBC.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jPconJDBC.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPconJDBC.setLayout(gbl_jPconJDBC);

		JLabel lblConBdOrigem = new JLabel("Conex\u00E3o Banco Origem:");
		lblBdOrigem.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		jPconJDBC.add(lblConBdOrigem, gbc_label_1);

		cbbBdOrigem = new JComboBox();
		cbbBdOrigem.setMaximumRowCount(10);
		cbbBdOrigem.setModel(new DefaultComboBoxModel(new String[] { "", "Generico", "DB2", "Cache", "MSSQLServer",
				"MySQL", "Oracle", "PostgreSQL", "Sybase" }));
		GridBagConstraints gbc_cbbBdOrigem = new GridBagConstraints();
		gbc_cbbBdOrigem.anchor = GridBagConstraints.WEST;
		gbc_cbbBdOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_cbbBdOrigem.gridx = 1;
		gbc_cbbBdOrigem.gridy = 0;
		jPconJDBC.add(cbbBdOrigem, gbc_cbbBdOrigem);

		JLabel lblDriver = new JLabel("Driver:");
		lblDriver.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		jPconJDBC.add(lblDriver, gbc_label_2);

		cbbDriverOrigem = new JComboBox();
		cbbDriverOrigem.setMaximumRowCount(10);
		cbbDriverOrigem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				ttfClasse.setText("");
				ttfNomeBancoOrigem.setText("");
				Object item = evt.getItem();
				if (evt.getStateChange() == ItemEvent.SELECTED) {
					ttfNomeBancoOrigem.setText(ProcedimentosApoio.hmdriverurl.get(item.toString()));
					System.out.println("Item selecionado: " + item.toString());
					ttfClasse.setText(item.toString());
				} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
					System.out.println("evento executado" + evt.getStateChange());
				}

			}
		});
		cbbDriverOrigem.setModel(new DefaultComboBoxModel(new String[] { "", "Gen\u00E9rico",
				"com.microsoft.sqlserver.jdbc.SQLServerDriver", "com.mysql.jdbc.Driver", "com.mysql.jdbc.Driver2",
				"ibm.db2.Driver", "oracle.jdbc.OracleDriver", "org.cache.Driver", "org.postgresql.Driver" }));
		GridBagConstraints gbc_cbbDriverOrigem = new GridBagConstraints();
		gbc_cbbDriverOrigem.anchor = GridBagConstraints.WEST;
		gbc_cbbDriverOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_cbbDriverOrigem.gridx = 1;
		gbc_cbbDriverOrigem.gridy = 1;
		jPconJDBC.add(cbbDriverOrigem, gbc_cbbDriverOrigem);

		JLabel lblClasse = new JLabel("Classe");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 2;
		jPconJDBC.add(lblClasse, gbc_label_3);

		ttfClasse = new JTextField();
		ttfClasse.setText("org.postgresql.Driver");
		ttfClasse.setColumns(10);
		GridBagConstraints gbc_ttfClasse = new GridBagConstraints();
		gbc_ttfClasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfClasse.insets = new Insets(0, 0, 5, 5);
		gbc_ttfClasse.gridx = 1;
		gbc_ttfClasse.gridy = 2;
		jPconJDBC.add(ttfClasse, gbc_ttfClasse);

		JLabel lblBancoDados = new JLabel("Banco de Dados:");
		GridBagConstraints gbclblbd = new GridBagConstraints();
		gbclblbd.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbclblbd.insets = new Insets(0, 0, 5, 5);
		gbclblbd.gridx = 0;
		gbclblbd.gridy = 3;
		jPconJDBC.add(lblBancoDados, gbclblbd);

		ttfNomeBancoOrigem = new JTextField();
		ttfNomeBancoOrigem.setText("jdbc:postgresql://localhost:5432/welementosdb");
		ttfNomeBancoOrigem.setColumns(10);
		GridBagConstraints gbc_ttfNomeBancoOrigem = new GridBagConstraints();
		gbc_ttfNomeBancoOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfNomeBancoOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_ttfNomeBancoOrigem.gridx = 1;
		gbc_ttfNomeBancoOrigem.gridy = 3;
		jPconJDBC.add(ttfNomeBancoOrigem, gbc_ttfNomeBancoOrigem);

		lblStatusOrigem = new JLabel("Status");
		lblStatusOrigem.setIcon(new ImageIcon(JSptConexao.class.getResource("/com/resc/br/Alert.png")));
		GridBagConstraints gbc_lblStatusOrigem = new GridBagConstraints();
		gbc_lblStatusOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatusOrigem.gridx = 2;
		gbc_lblStatusOrigem.gridy = 3;
		jPconJDBC.add(lblStatusOrigem, gbc_lblStatusOrigem);

		JLabel label_4 = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		jPconJDBC.add(label_4, gbc_label_4);

		ttfUsuarioOrigem = new JTextField();
		ttfUsuarioOrigem.setText("root");
		ttfUsuarioOrigem.setColumns(10);
		GridBagConstraints gbc_ttfUsuarioOrigem = new GridBagConstraints();
		gbc_ttfUsuarioOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfUsuarioOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_ttfUsuarioOrigem.gridx = 1;
		gbc_ttfUsuarioOrigem.gridy = 4;
		jPconJDBC.add(ttfUsuarioOrigem, gbc_ttfUsuarioOrigem);

		JLabel label_5 = new JLabel("Senha:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 5;
		jPconJDBC.add(label_5, gbc_label_5);

		// ttfSenhaBdOrigem = new JPasswordField(30);
		ttfSenhaBdOrigem = new JTextField(30);
		ttfSenhaBdOrigem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aev) {
				// ocultar campo didgitado resguardando o conteúdo original
				// (senha)
			}
		});
		ttfSenhaBdOrigem.setText("LARmysql07101957");
		ttfSenhaBdOrigem.setColumns(10);
		GridBagConstraints gbc_ttfSenhaBdOrigem = new GridBagConstraints();
		gbc_ttfSenhaBdOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfSenhaBdOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_ttfSenhaBdOrigem.gridx = 1;
		gbc_ttfSenhaBdOrigem.gridy = 5;
		jPconJDBC.add(ttfSenhaBdOrigem, gbc_ttfSenhaBdOrigem);

		JLabel lblBancoDeDestino = new JLabel("Banco de Destino");
		GridBagConstraints gbc_lblBancoDeDestino = new GridBagConstraints();
		gbc_lblBancoDeDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblBancoDeDestino.gridx = 1;
		gbc_lblBancoDeDestino.gridy = 6;
		jPconJDBC.add(lblBancoDeDestino, gbc_lblBancoDeDestino);

		JLabel label_7 = new JLabel("Conex\u00E3o Banco Destino:");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 7;
		jPconJDBC.add(label_7, gbc_label_7);

		cbbBancoDestino = new JComboBox();
		cbbBancoDestino.setModel(new DefaultComboBoxModel(new String[] { "", "Generico", "DB2", "Cache", "MSSQLServer",
				"Planilha", "MySQL", "Oracle", "PostgreSQL", "Sybase" }));
		GridBagConstraints gbc_cbbBancoDestino = new GridBagConstraints();
		gbc_cbbBancoDestino.anchor = GridBagConstraints.WEST;
		gbc_cbbBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_cbbBancoDestino.gridx = 1;
		gbc_cbbBancoDestino.gridy = 7;
		jPconJDBC.add(cbbBancoDestino, gbc_cbbBancoDestino);

		JLabel label_8 = new JLabel("Driver:");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.WEST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 8;
		jPconJDBC.add(label_8, gbc_label_8);

		cbbDriverBdDestino = new JComboBox<String>();
		cbbDriverBdDestino.setModel(new DefaultComboBoxModel(
				new String[] { "", "Gen\u00E9rico", "com.microsoft.sqlserver.jdbc.SQLServerDriver",
						"com.mysql.jdbc.Driver", "com.mysql.jdbc.Driver2", "com.mysql.jdbc.Driver3", "ibm.db2.Driver",
						"oracle.jdbc.OracleDriver", "org.cache.Driver", "org.postgresql.Driver" }));
		cbbDriverBdDestino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				ttfClasseBancoDestino.setText("");
				ttfNomeBancoDestino.setText("");
				// JComboBox cb = (JComboBox) evt.getSource();

				Object item = evt.getItem();

				if (evt.getStateChange() == ItemEvent.SELECTED) {
					ttfNomeBancoDestino.setText(ProcedimentosApoio.hmdriverurl.get(item.toString()));
					ttfClasseBancoDestino.setText(item.toString());

				} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
					System.out.println("evento executado" + evt.getStateChange());
				}

			}
		});
		GridBagConstraints gbc_cbbDriverBdDestino = new GridBagConstraints();
		gbc_cbbDriverBdDestino.anchor = GridBagConstraints.WEST;
		gbc_cbbDriverBdDestino.insets = new Insets(0, 0, 5, 5);
		gbc_cbbDriverBdDestino.gridx = 1;
		gbc_cbbDriverBdDestino.gridy = 8;
		jPconJDBC.add(cbbDriverBdDestino, gbc_cbbDriverBdDestino);

		JLabel label_9 = new JLabel("Classe:");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.WEST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 9;
		jPconJDBC.add(label_9, gbc_label_9);

		ttfClasseBancoDestino = new JTextField();
		ttfClasseBancoDestino.setText("org.postgresql.Driver");
		ttfClasseBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfClasseBancoDestino = new GridBagConstraints();
		gbc_ttfClasseBancoDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfClasseBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_ttfClasseBancoDestino.gridx = 1;
		gbc_ttfClasseBancoDestino.gridy = 9;
		jPconJDBC.add(ttfClasseBancoDestino, gbc_ttfClasseBancoDestino);

		JLabel label_10 = new JLabel("Banco de Dados:");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 10;
		jPconJDBC.add(label_10, gbc_label_10);

		ttfNomeBancoDestino = new JTextField();
		ttfNomeBancoDestino.setText("jdbc:postgresql://localhost:5432/elementosdb");
		ttfNomeBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfNomeBancoDestino = new GridBagConstraints();
		gbc_ttfNomeBancoDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfNomeBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_ttfNomeBancoDestino.gridx = 1;
		gbc_ttfNomeBancoDestino.gridy = 10;
		jPconJDBC.add(ttfNomeBancoDestino, gbc_ttfNomeBancoDestino);

		lblStatusDestino = new JLabel("Status");
		lblStatusDestino.setIcon(new ImageIcon(JSptConexao.class.getResource("/com/resc/br/Alert.png")));
		GridBagConstraints gbc_lblStatusDestino = new GridBagConstraints();
		gbc_lblStatusDestino.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatusDestino.gridx = 2;
		gbc_lblStatusDestino.gridy = 10;
		jPconJDBC.add(lblStatusDestino, gbc_lblStatusDestino);

		JLabel label_11 = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.WEST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 11;
		jPconJDBC.add(label_11, gbc_label_11);

		ttfUsuarioBancoDestino = new JTextField();
		ttfUsuarioBancoDestino.setText("postgres");
		ttfUsuarioBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfUsuarioBancoDestino = new GridBagConstraints();
		gbc_ttfUsuarioBancoDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfUsuarioBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_ttfUsuarioBancoDestino.gridx = 1;
		gbc_ttfUsuarioBancoDestino.gridy = 11;
		jPconJDBC.add(ttfUsuarioBancoDestino, gbc_ttfUsuarioBancoDestino);

		JLabel label_12 = new JLabel("Senha:");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.WEST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 0;
		gbc_label_12.gridy = 12;
		jPconJDBC.add(label_12, gbc_label_12);

		// ttfSenhaBancoDestino = new JPasswordField(30);
		ttfSenhaBancoDestino = new JTextField(30);
		ttfSenhaBancoDestino.setText("boeraremapiba");
		ttfSenhaBancoDestino.setColumns(10);
		GridBagConstraints gbc_ttfSenhaBancoDestino = new GridBagConstraints();
		gbc_ttfSenhaBancoDestino.insets = new Insets(0, 0, 5, 5);
		gbc_ttfSenhaBancoDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_ttfSenhaBancoDestino.gridx = 1;
		gbc_ttfSenhaBancoDestino.gridy = 12;
		jPconJDBC.add(ttfSenhaBancoDestino, gbc_ttfSenhaBancoDestino);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aevt) {
				boolean statuscon = false;
				int index = 1; // antes do botão conectar
				if (!cbbBdOrigem.getSelectedItem().toString().equals("")
						&& !cbbBancoDestino.getSelectedItem().toString().equals("")) {
					switch (index) {
					case 0:
						try {
							inciarDadosConexoes();
							break;
						} catch (Exception e) {
							System.out.println();
							JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Controle de Execução", 3);
						}
						break;
					case 1:
						try {
							if (!obterConexoes())
								JOptionPane.showMessageDialog(null,
										"Problemas com as informações diretivas para conexões. Reveja a guia Conexões.",
										"Controle de Execução", 3);
							else {
								statuscon = true;
							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e.getMessage().toString());
						}
						try {
							if (!cbTabelasDestino.getSelectedItem().toString().equals("")) {
								ProcedimentosApoio
										.analiseRelacionamentoTabela(cbTabelasDestino.getSelectedItem().toString());
							}
						} catch (Exception e) {
							//JOptionPane.showMessageDialog(null, "Deve existir tabela de Destino para seleção.","Controle de Execução", 3);
						}
						break;
					case 2:
						try {
							if (!cbTabelasDestino.getSelectedItem().toString().equals(""))
								analiseRelacionamentoTabela(cbTabelasDestino.getSelectedItem().toString());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Não há tabelas de destino para seleção.",
									"Controle de Execução", 3);
						}
						break;
					case 3:
						try {
							if (!cbTabelasDestino.getSelectedItem().toString().equals(""))
								analiseRelacionamentoTabela(cbTabelasDestino.getSelectedItem().toString());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Não há tabelas de destino para seleção.",
									"Controle de Execução", 3);
						}
						break;
					case 4:
						try {
							if (!cbTabelasDestino.getSelectedItem().toString().equals(""))
								analiseRelacionamentoTabela(cbTabelasDestino.getSelectedItem().toString());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Não há tabelas de destino para seleção.",
									"Controle de Execução", 3);
						}
						break;
					default:
						JOptionPane.showMessageDialog(null,
								"Processo de Conexão não realizado. Verificar diretivas de conexão.",
								"Controle de Execução", 3);
						break;

					}

					if (statuscon) {
						modificarIconStatusConexao();

						ProcedimentosApoio.conexaoOrigem = conexaoOrigem;
						ProcedimentosApoio.conexaoDestino = conexaoDestino;

						obterMetadados("Geral", "Origem", conexaoOrigem);
						ProcedimentosApoio.popularArvoreDBorigem(conexaoOrigem,
								cbbBdOrigem.getSelectedItem().toString());

						obterMetadados("Geral", "Destino", conexaoDestino);
						ProcedimentosApoio.popularArvoreDBdestino(conexaoDestino,
								cbbBancoDestino.getSelectedItem().toString());

						ProcedimentosApoio.treeConexaoOrigem.addTreeSelectionListener(new TreeSelectionListener() {
						    public void valueChanged(TreeSelectionEvent treeEvento) {
						        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						        		ProcedimentosApoio.treeConexaoOrigem.getLastSelectedPathComponent();
						        if (node == null) return;
					            Object nodeInfo = node.getUserObject();
					            String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
					            ProcedimentosApoio.jtableMestre = new JTable(ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoOrigem, 
										nomeNode));
					            
						    }
						});
						JScrollPane jspDireConexaoOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);
						jspDiretorioConexoes.setLeftComponent(jspDireConexaoOrigem);

						ProcedimentosApoio.treeConexaoDestino.addTreeSelectionListener(new TreeSelectionListener() {
						    public void valueChanged(TreeSelectionEvent treeEvento) {
						        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						        		ProcedimentosApoio.treeConexaoDestino.getLastSelectedPathComponent();
						        if (node == null) return;
					            Object nodeInfo = node.getUserObject();
					            String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
					            ProcedimentosApoio.jtableMestre = new JTable(ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoDestino, 
										nomeNode));
							    
						    }
						});

						try {
			    			
			    			ProcedimentosApoio.jtableMestre.setCellSelectionEnabled(true);
			    			ProcedimentosApoio.jtableMestre.setRowSelectionAllowed(true);
			    			ProcedimentosApoio.cellSelectionModel = ProcedimentosApoio.jtableMestre.getSelectionModel();
			    			ProcedimentosApoio.cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    			
			    			ProcedimentosApoio.cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			    				public void valueChanged(ListSelectionEvent event) {

			    					int[] selectedRow = ProcedimentosApoio.jtableMestre.getSelectedRows();
			    					int[] selectedColumns = ProcedimentosApoio.jtableMestre.getSelectedColumns();

			    					Vector<String> vtituloscolunas = new Vector<String>();
			    					Vector<String> vtiposdecolunas = new Vector<String>();
			    					Vector<Object> vconteudoRecord = new Vector<Object>();
			    					String nomeTabelaBDquepopulatable = TunicaGeral
			    							.getTituloTabMestreCorrente(TunicaGeral.tbdPmaster.getSelectedIndex());

			    					Iterator<DicionarioMetaDados> j = ProcedimentosApoio.vdmdOrigem.iterator();
			    					while (j.hasNext()) {
			    						DicionarioMetaDados dcmd = j.next();
			    						if (dcmd.getNome().equals(nomeTabelaBDquepopulatable)) {
			    							vtituloscolunas.addElement(dcmd.getNome());
			    							vtiposdecolunas.addElement(dcmd.getTipo());
			    						}
			    					}
			    					
			    					for (int i = 0; i < selectedRow.length; i++) {
			    						for (int k = 0; k < selectedColumns.length; k++) {
			    							vconteudoRecord.addElement(
			    									ProcedimentosApoio.jtableMestre.getValueAt(selectedRow[i], selectedColumns[k]));
			    						}
			    					}
			    					System.out.println("Selected: " + vconteudoRecord.toString());
			    					FretusModel.mostrarProjecaoRegistroMestre(vtituloscolunas, vtiposdecolunas, vconteudoRecord,
			    							nomeTabelaBDquepopulatable);

			    					System.out.println(ProcedimentosApoio.jtableMestre
			    							.getValueAt(ProcedimentosApoio.jtableMestre.getSelectedRow(), 0).toString());
			    				}
			    			});

			    		} catch (Exception e) {

			    		}
						JScrollPane jspDireConexaoDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
						jspDiretorioConexoes.setRightComponent(jspDireConexaoDestino);
					}
				}
			}

		});

		btnConectar.setIcon(new ImageIcon(Minerador.class.getResource("/com/resc/br/Database.png")));
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 1;
		gbc_btnConectar.gridy = 13;
		jPconJDBC.add(btnConectar, gbc_btnConectar);
		/*
		 * JLabel lblRenderizadorPdf = new JLabel("Renderizador PDF");
		 * lblRenderizadorPdf.setBackground(new Color(250, 235, 215));
		 * GridBagConstraints gbc_lblPdfRender = new GridBagConstraints();
		 * gbc_lblPdfRender.insets = new Insets(0, 0, 5, 5);
		 * gbc_lblPdfRender.gridx = 1; gbc_lblPdfRender.gridy = 14;
		 * jPconJDBC.add(lblRenderizadorPdf, gbc_lblRenderizadorPdf);
		 */

		JLabel lblGeraoModeloPdf = new JLabel("Gera\u00E7\u00E3o Modelo PDF");
		GridBagConstraints gbc_lblGeraoModeloPdf = new GridBagConstraints();
		gbc_lblGeraoModeloPdf.anchor = GridBagConstraints.WEST;
		gbc_lblGeraoModeloPdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeraoModeloPdf.gridx = 1;
		gbc_lblGeraoModeloPdf.gridy = 14;
		jPconJDBC.add(lblGeraoModeloPdf, gbc_lblGeraoModeloPdf);
		JLabel lblCaminhoDoAplicativo = new JLabel("Caminho do Aplicativo:");
		GridBagConstraints gbc_lblCaminhoDoAplicativo = new GridBagConstraints();
		gbc_lblCaminhoDoAplicativo.anchor = GridBagConstraints.WEST;
		gbc_lblCaminhoDoAplicativo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCaminhoDoAplicativo.gridx = 0;
		gbc_lblCaminhoDoAplicativo.gridy = 15;
		jPconJDBC.add(lblCaminhoDoAplicativo, gbc_lblCaminhoDoAplicativo);

		tfCaminhoAplicativo = new JTextField();
		GridBagConstraints gbc_tfCaminhoAplicativo = new GridBagConstraints();
		gbc_tfCaminhoAplicativo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCaminhoAplicativo.insets = new Insets(0, 0, 5, 5);
		gbc_tfCaminhoAplicativo.gridx = 1;
		gbc_tfCaminhoAplicativo.gridy = 15;
		jPconJDBC.add(tfCaminhoAplicativo, gbc_tfCaminhoAplicativo);
		tfCaminhoAplicativo.setColumns(10);

		JButton btnSelecionarExecutavel = new JButton("");
		btnSelecionarExecutavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Selecionar Arquivo");
				frm.getContentPane().add(fileChooser);
				fileChooser.setVisible(true);
				String abspathtofile = "";
				int result = fileChooser.showOpenDialog(frm);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					abspathtofile = selectedFile.getAbsolutePath();
				}
				tfCaminhoAplicativo.setText(abspathtofile);
			}
		});
		btnSelecionarExecutavel.setToolTipText(
				"Aplicativo renderizado. Na gera\u00E7\u00E3o de arquivos PDFs a partir do Latex, deve sere o caminho onde est\u00E1 o PDFLATEX.EXE.\r\n");
		btnSelecionarExecutavel.setIcon(new ImageIcon(Minerador.class.getResource("/com/resc/br/Clipboard.png")));
		GridBagConstraints gbc_btnSelecionarExecutavel = new GridBagConstraints();
		gbc_btnSelecionarExecutavel.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelecionarExecutavel.gridx = 2;
		gbc_btnSelecionarExecutavel.gridy = 15;
		jPconJDBC.add(btnSelecionarExecutavel, gbc_btnSelecionarExecutavel);

		JLabel lblArquivoAlvo = new JLabel("Arquivo Alvo:");
		GridBagConstraints gbc_lblArquivoAlvo = new GridBagConstraints();
		gbc_lblArquivoAlvo.anchor = GridBagConstraints.WEST;
		gbc_lblArquivoAlvo.insets = new Insets(0, 0, 0, 5);
		gbc_lblArquivoAlvo.gridx = 0;
		gbc_lblArquivoAlvo.gridy = 16;
		jPconJDBC.add(lblArquivoAlvo, gbc_lblArquivoAlvo);

		tfRenderFile = new JTextField();
		GridBagConstraints gbc_tfRenderFile = new GridBagConstraints();
		gbc_tfRenderFile.insets = new Insets(0, 0, 0, 5);
		gbc_tfRenderFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRenderFile.gridx = 1;
		gbc_tfRenderFile.gridy = 16;
		jPconJDBC.add(tfRenderFile, gbc_tfRenderFile);
		tfRenderFile.setColumns(10);

		JButton btnSelecionarArquivoAlvo = new JButton("");
		btnSelecionarArquivoAlvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Selecionar Arquivo");
				frm.getContentPane().add(fileChooser);
				fileChooser.setVisible(true);
				String abspathtofile = "";
				int result = fileChooser.showOpenDialog(frm);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					abspathtofile = selectedFile.getAbsolutePath();
				}
				tfRenderFile.setText(abspathtofile);
			}
		});
		btnSelecionarArquivoAlvo.setToolTipText(
				"Arquivo a ser submetido ao executor. Na gera\u00E7\u00E3o de arquivo .PDF o arquivo selecionado deve ser um .tex ou .apl.\r\n");
		btnSelecionarArquivoAlvo.setIcon(new ImageIcon(Minerador.class.getResource("/com/resc/br/Message.png")));
		GridBagConstraints gbc_btnSelecionarArquivoAlvo = new GridBagConstraints();
		gbc_btnSelecionarArquivoAlvo.gridx = 2;
		gbc_btnSelecionarArquivoAlvo.gridy = 16;
		jPconJDBC.add(btnSelecionarArquivoAlvo, gbc_btnSelecionarArquivoAlvo);

	}

	protected void obterMetadados(String modulo, String alvo, Connection conexao) {

		System.out.println("Número de chamadas: " +   ++ncalls);
		
		ProcedimentosApoio.nomesTabelas.removeAllElements();
		ProcedimentosApoio.nomesTabelas.clear();
		try {
			if (!conexaoOrigem.equals(null) && !conexaoDestino.equals(null)) {
				ProcedimentosApoio.capturarTabelasModulo(modulo, alvo, conexao);
				Iterator<String> i = ProcedimentosApoio.nomesTabelas.iterator();
				while (i.hasNext()) {
					if (alvo.equals("Origem") || alvo.equals("origem"))
						cbTabelasModuloOrigem.addItem((String) i.next());
					else
						cbTabelasDestino.addItem((String) i.next());
				}
				if (alvo.equals("Origem") || alvo.equals("origem"))
					ProcedimentosApoio.vnomesTabelasOrigem.addAll(ProcedimentosApoio.nomesTabelas);
				else
					ProcedimentosApoio.vnomesTabelasDestino.addAll(ProcedimentosApoio.nomesTabelas);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Conexão banco origem", 3);
		}

	}

	protected void modificarIconStatusConexao() {
		lblStatusDestino.setIcon(new ImageIcon(JSptConexao.class.getResource("/com/resc/br/Apply.png")));
		lblStatusOrigem.setIcon(new ImageIcon(JSptConexao.class.getResource("/com/resc/br/Apply.png")));

	}

	protected void analiseRelacionamentoTabela(String tabelaDestino) {

		Vector<String> vrelations = new Vector<String>();
		ProcedimentosApoio.vrelations = ConnectionFactory.executeGetCrossReference(conexaoDestino, null, null,
				tabelaDestino, null, null, null, vrelations);

		Iterator<String> i = vrelations.iterator();
		while (i.hasNext()) {
			System.out.println("Relacionamento da tabela " + tabelaDestino + " <-->" + i.next());
		}

		try {
			ResultSet rs = null;
			DatabaseMetaData meta = conexaoDestino.getMetaData();
			rs = meta.getExportedKeys(conexaoDestino.getCatalog(), null, "survey");
			while (rs.next()) {
				String fkTableName = rs.getString("FKTABLE_NAME");
				String fkColumnName = rs.getString("FKCOLUMN_NAME");
				int fkSequence = rs.getInt("KEY_SEQ");
				System.out.println("getExportedKeys(): fkTableName=" + fkTableName);
				System.out.println("getExportedKeys(): fkColumnName=" + fkColumnName);
				System.out.println("getExportedKeys(): fkSequence=" + fkSequence);
			}
		} catch (SQLException e) {

		}

	}

	protected boolean obterConexoes() {
		String conexaocomerro = "OK";

		try {
			conexaoDestino = ConnectionFactory.getConnection(ttfClasseBancoDestino.getText(),
					ttfNomeBancoDestino.getText(), ttfUsuarioBancoDestino.getText(), ttfSenhaBancoDestino.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Falha conexão Banco Destino", 3);
			conexaocomerro += "Falha no Destino";
		}

		try {
			conexaoAbstral = ConnectionFactory.getConnection("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306/sakila", "root", "LARmysql07101957");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Falha conexão Banco TunicaDB", 3);
			conexaocomerro += ", Falha Sakila";
		}

		try {
			ProcedimentosApoio.conexaoTunica = ConnectionFactory.getConnection("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306/tunica", "root", "LARmysql07101957");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Falha conexão Banco TunicaDB", 3);
			conexaocomerro += ", Falha TunicaDB";
		}
		try {
			conexaoOrigem = ConnectionFactory.getConnection(ttfClasse.getText(), ttfNomeBancoOrigem.getText(),
					ttfUsuarioOrigem.getText(), ttfSenhaBdOrigem.getText());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Falha conexão Bando Origem", 3);
			conexaocomerro += ", Falha no origem.";
		}

		System.out.println(conexaocomerro);
		return (conexaocomerro.equals("OK") ? true : false);

	}

	protected Connection obterConexaoDestino() throws Exception {
		
		return  ConnectionFactory.getConnection(ttfClasseBancoDestino.getText(),
				ttfNomeBancoDestino.getText(), ttfUsuarioBancoDestino.getText(), ttfSenhaBancoDestino.getText());
	}
	
	protected void inciarDadosConexoes() {

		cbbBdOrigem.setSelectedItem("");
		cbbBancoDestino.setSelectedItem("");

	}

	public static void setMetaDados() {

		try {

			JScrollPane jspDireConexaoOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);
			jspDiretorioConexoes.setLeftComponent(jspDireConexaoOrigem);

			JScrollPane jspDireConexaoDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
			jspDiretorioConexoes.setRightComponent(jspDireConexaoDestino);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String obterNomeCliente() {

		String cmdSql = "SELECT NOME FROM schtunica.cliente limit 1";

		try {
			PreparedStatement prest = ProcedimentosApoio.conexaoTunica.prepareStatement(cmdSql);
			ResultSet rs = prest.executeQuery();
			if (rs.next()) {
				return rs.getString("NOME");

			}

		} catch (Exception e) {
			
		}
		return "TRIBUNAL DE JUSTIÇA DO DISTRITO FEDERAL E DOS TERRITÓRIOS — TJDFT";
	}

	public static Connection conexaoOrigem = null;
	public static Connection conexaoDestino = null;
	public static Connection conexaoAbstral = null;

	private JFrame frm;

	protected static JSplitPane jspDiretorioConexoes;

	private JTextField textField;
	private JTextField ttfClasse;
	protected JTextField ttfNomeBancoOrigem;
	private JTextField ttfUsuarioOrigem;
	private JTextField ttfSenhaBdOrigem;
	private JTextField ttfClasseBancoDestino;
	protected JTextField ttfNomeBancoDestino;
	private JTextField ttfUsuarioBancoDestino;
	private JTextField ttfSenhaBancoDestino;

	protected static JComboBox cbbBancoDestino;
	protected static JComboBox cbbDriverBdDestino;
	protected static JComboBox cbModulos;
	protected static JComboBox cbModuloDestino;
	protected static JComboBox<String> cbTabelasModuloOrigem = new JComboBox();
	protected static JComboBox<String> cbTabelasDestino = new JComboBox<String>();

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	private JLabel lblStatusOrigem;
	private JLabel lblStatusDestino;
	protected static JComboBox cbbBdOrigem;
	private JComboBox cbbDriverOrigem;
	private JTextField tfCaminhoAplicativo;
	private JTextField tfRenderFile;
	private JTable table;

	private static int ncalls;
	private final Action action = new SwingAction();

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

		}
	}

	// private static JTabbedPane tbdMacrofuncoes = new
	// JTabbedPane(JTabbedPane.TOP);

}
