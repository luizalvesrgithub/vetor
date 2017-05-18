package br.com.util;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import br.com.cliente.FretusModel;

public class JPanelMetaDados<E> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanelMetaDados(ConnectionFactory pcon) {

		setToolTipText(
				"Este componente dever\u00E1 ser adicionado \u00E0 equerda do painel Principal da Tunica e suas extens\u00F5es. Tem por finalidade direcionar as consultas mestre x detalhe e outras.\r\nO evento sele\u00E7\u00E3o de ocorr\u00EAncias nas listas ensejar\u00E1 a consulta a ser apresentada com seus desdobramentos nos pain\u00E9is \u00E0 direita.");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 82, 388, 0 };
		gridBagLayout.rowHeights = new int[] { 24, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 3;
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		add(tabbedPane, gbc_tabbedPane);

		JPanel ptabelas = new JPanel();
		tabbedPane.addTab("Tabelas", null, ptabelas, null);
		GridBagLayout gbl_ptabelas = new GridBagLayout();
		gbl_ptabelas.columnWidths = new int[] { 464, 0 };
		gbl_ptabelas.rowHeights = new int[] { 53, 0 };
		gbl_ptabelas.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_ptabelas.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		ptabelas.setLayout(gbl_ptabelas);

		jspDiretorioConexoes = new JSplitPane();
		jspDiretorioConexoes.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_jspDiretorioConexoesx = new GridBagConstraints();
		gbc_jspDiretorioConexoesx.fill = GridBagConstraints.BOTH;
		gbc_jspDiretorioConexoesx.gridx = 0;
		gbc_jspDiretorioConexoesx.gridy = 0;
		ptabelas.add(jspDiretorioConexoes, gbc_jspDiretorioConexoesx);

		try {

			ProcedimentosApoio.treeConexaoOrigem.getSelectionModel()
					.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			jspBdOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);

			ProcedimentosApoio.treeConexaoOrigem.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent treeEvento) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) ProcedimentosApoio.treeConexaoOrigem
							.getLastSelectedPathComponent();
					if (node == null)
						return;
					Object nodeInfo = node.getUserObject();
					String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
					ProcedimentosApoio.jtableMestre = new JTable(
							ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoOrigem, nomeNode));
					setListenerEventTable();
				}
			});

			jspDiretorioConexoes.setLeftComponent(jspBdOrigem);
			jspBdDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
			jspDiretorioConexoes.setRightComponent(jspBdDestino);

			ProcedimentosApoio.treeConexaoDestino.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent treeEvento) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) ProcedimentosApoio.treeConexaoDestino
							.getLastSelectedPathComponent();
					if (node == null)
						return;
					Object nodeInfo = node.getUserObject();
					String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
					ProcedimentosApoio.jtableMestre = new JTable(
							ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoDestino, nomeNode));
					setListenerEventTable();
				}
			});

		} catch (Exception e) {
			// TODO: handle exception
		}

		JPanel prelacionamentos = new JPanel();
		tabbedPane.addTab("Colunas", null, prelacionamentos, null);

		JList<? extends E> lstColunas = new JList();
		prelacionamentos.add(lstColunas);

		JPanel pchavesPrimarias = new JPanel();
		tabbedPane.addTab("Chaves Prim\u00E1rias", null, pchavesPrimarias, null);

		JList<? extends E> lstChavesPrimarias = new JList();
		pchavesPrimarias.add(lstChavesPrimarias);

		JPanel pchaveEstrangeira = new JPanel();
		tabbedPane.addTab("Chave Estrangeira", null, pchaveEstrangeira, null);

		JList<? extends E> lstChaveEstrangeira = new JList();
		pchaveEstrangeira.add(lstChaveEstrangeira);

		JLabel lblConexoBd = new JLabel("Conex\u00E3o BD:");
		GridBagConstraints gbc_lblConexoBd = new GridBagConstraints();
		gbc_lblConexoBd.anchor = GridBagConstraints.WEST;
		gbc_lblConexoBd.insets = new Insets(0, 0, 0, 5);
		gbc_lblConexoBd.gridx = 0;
		gbc_lblConexoBd.gridy = 0;
		add(lblConexoBd, gbc_lblConexoBd);

		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);

		try {
			con = pcon.getConnection("org.postgresql.Driver", "jdbc:postgresql://localhost/welementosdb", "postgres",
					"boeraremapiba");
			dbmetadata = con.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> obterTabelasDb() {
		return new ArrayList<T>();
	}

	public <T> List<T> obterMeataData(Class<T> classe)
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		List<T> list = new ArrayList<>();

		list.add(classe.cast(actuallyT));
		try {
			// Obter a lista de tabelas do banco
			ResultSet resultSet = dbmetadata.getTables(null, null, null, new String[] { "TABLE" });
			System.out.println("Printing TABLE_TYPE \"TABLE\" ");
			System.out.println("----------------------------------");
			while (resultSet.next()) {
				// Print
				System.out.println(resultSet.getString("TABLE_NAME"));
			}
			list.add(classe.getConstructor().newInstance()); // If default
																// constructor
		} finally {

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> obterTodasTabelasSistema(Class<T> classe) throws SQLException {

		List<T> list = new ArrayList<>();

		list.add(classe.cast(actuallyT));
		ResultSet resultSet = dbmetadata.getTables(null, null, null, new String[] { "SYSTEM TABLE" });
		System.out.println("Printing TABLE_TYPE \"SYSTEM TABLE\" ");
		System.out.println("----------------------------------");
		while (resultSet.next()) {
			list.add((T) resultSet.getString("TABLE_NAME"));
			System.out.println(resultSet.getString("TABLE_NAME"));
		}
		return list;
	}

	public <T> List<T> obterNomesColunas(Class<T> classe, String tableName) throws SQLException {

		List<T> list = new ArrayList<>();
		ResultSet columns = dbmetadata.getColumns(null, null, tableName, null);
		while (columns.next()) {
			String columnName = columns.getString("COLUMN_NAME");
			String datatype = columns.getString("DATA_TYPE");
			String columnsize = columns.getString("COLUMN_SIZE");
			String decimaldigits = columns.getString("DECIMAL_DIGITS");
			String isNullable = columns.getString("IS_NULLABLE");
			String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
			// Printing results
			System.out.println(columnName + "---" + datatype + "---" + columnsize + "---" + decimaldigits + "---"
					+ isNullable + "---" + is_autoIncrment);
		}
		return list;

	}

	public <T> List<T> obterChavesPrimarias(Class<T> classe, String tableName) throws SQLException {

		List<T> list = new ArrayList<>();
		ResultSet PK = dbmetadata.getPrimaryKeys(null, null, tableName);
		System.out.println("------------PRIMARY KEYS-------------");
		while (PK.next()) {
			System.out.println(PK.getString("COLUMN_NAME") + "===" + PK.getString("PK_NAME"));
		}
		return list;
	}

	public static <T> List<T> obterChavesPrimarias(String tableName) throws SQLException {

		String nomeTabela[] = tableName.split("\\(");
		
		List<T> list = new ArrayList<>();
		ResultSet PK = dbmetadata.getPrimaryKeys(null, null, nomeTabela[0]);
		System.out.println("------------PRIMARY KEYS-------------"); // só
																		// recupera
																		// as
																		// chaves
																		// primárias
																		// do
																		// metadados
		while (PK.next()) {
			System.out.println(PK.getString("COLUMN_NAME") + "===" + PK.getString("PK_NAME"));
			UtilToGenerics<String, String, String> ug = new UtilToGenerics<>(PK.getString("COLUMN_NAME"),
					PK.getString("PK_NAME"), PK.getString("TABLE_NAME"));
			list.add((T) ug);
		}
		// recuperar registros membros de uma chave pai...

		return list;
	}

	public <T> List<T> obterChavesEstrangeiras(Class<T> classe, String tableName) throws SQLException {

		List<T> list = new ArrayList<>();
		ResultSet FK = dbmetadata.getImportedKeys(null, null, tableName);
		System.out.println("------------FOREIGN KEYS-------------");
		while (FK.next()) {
			System.out.println(FK.getString("PKTABLE_NAME") + "---" + FK.getString("PKCOLUMN_NAME") + "==="
					+ FK.getString("FKTABLE_NAME") + "---" + FK.getString("FKCOLUMN_NAME"));
		}
		return list;

	}

	public static void setMetaDados() {

		try {

			jspBdOrigem = new JScrollPane(ProcedimentosApoio.treeConexaoOrigem);
			jspDiretorioConexoes.setLeftComponent(jspBdOrigem);

			jspBdDestino = new JScrollPane(ProcedimentosApoio.treeConexaoDestino);
			jspDiretorioConexoes.setRightComponent(jspBdDestino);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void setListenerEventTable() {
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
							.getTituloTabMestreCorrente(TunicaGeral.tbdPmaster.getSelectedIndex()); // ProcedimentosApoio.jtableMestre.getName();

					Iterator<DicionarioMetaDados> j = ProcedimentosApoio.vdmdOrigem.iterator();
					while (j.hasNext()) {
						DicionarioMetaDados dcmd = j.next();
						if (dcmd.getNome().equals("nometabelaselecionada")) {
							vtituloscolunas.addElement(dcmd.getNome());
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPanelMetaDados jpmd = new JPanelMetaDados(new ConnectionFactory());

					jpmd.jfrm.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	protected static JSplitPane jspDiretorioConexoes;
	protected static JScrollPane jspBdOrigem;
	protected static JScrollPane jspBdDestino;

	Connection con;
	ConnectionFactory conexao;
	protected static DatabaseMetaData dbmetadata;
	private Object actuallyT;

	private JFrame jfrm;
}
