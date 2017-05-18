package br.com.util;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.cliente.FretusModel;

public abstract class ProcedimentosApoio {

	/**
	 * Drivers e Urls para conexão a banco de dados.
	 */
	protected static void loadJdbcDriversUrls() {

		hmdriverurl.put("com.microsoft.sqlserver.jdbc.SQLServerDriver",
				new String("jdbc:sqlserver://tjsw378:1433;databaseName=asiw"));

		hmdriverurl.put("com.mysql.jdbc.Driver", new String("jdbc:mysql://localhost:3306/sakila")); // "jdbc:mysql://BDU16:3306/geafin"));

		hmdriverurl.put("com.microsoft.sqlserver.jdbc.SQLServerDriver",
				new String("jdbc:sqlserver://tjsw378:1433;databaseName=asiw"));

		hmdriverurl.put("org.postgresql.Driver", new String("jdbc:postgresql://localhost:5432/welementosdb"));

		hmdriverurl.put("com.mysq.jdbc.Driver2", new String("jdbc:mysql://localhost:3306/schtunica"));

		hmdriverurl.put("com.mysq.jdbc.Driver", new String("jdbc:mysql://localhost:3306/world"));

	}

	protected static void loadVars() {

		if (mapSqlSingleLineForDB.isEmpty()) {

			mapSqlSingleLineForDB.clear();

			mapSqlSingleLineForDB.put("MSSQLServer", new String("SELECT TOP 1 * FROM tabelaAlvo"));

			mapSqlSingleLineForDB.put("MySQL", new String("SELECT * FROM tabelaAlvo LIMIT 1"));

			mapSqlSingleLineForDB.put("PostgreSQL", new String("SELECT * FROM tabelaAlvo LIMIT 1"));

			mapSqlSingleLineForDB.put("Oracle", new String("SELECT * FROM tabelaAlvo WHERE ROWNUM = 1"));

			mapSqlSingleLineForDB.put("Sybase", new String("SET rowcount 10 SELECT * FROM tabelaAlvo"));

			mapSqlSingleLineForDB.put("DB2", new String("SELECT * FROM tabelaAlvo FETCH FIRST 1 ROW ONLY"));

			mapSqlSingleLineForDB.put("Generico", new String("SELECT * column FROM tabelaAlvo WHERE rowid < 2"));
		}

	}

	protected static void analiseRelacionamentoTabela(String tabelaDestino) {

		ConnectionFactory.executeGetCrossReference(conexaoDestino, null, null, tabelaDestino, null, null, null,
				vrelations);

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

	protected static void popularArvoreDBorigem(Connection conn, String nomeBdOrigem) {

		trnodebdorigem = new DefaultMutableTreeNode(nomeBdOrigem);

		Iterator<String> i = nomesTabelasOrigem.iterator();
		while (i.hasNext()) {
			String nomeTabela = i.next();
			trnodeTabelasOrigem = new DefaultMutableTreeNode(nomeTabela);
			Vector<String> vcolunas = obterColunasTabela(conn, nomeTabela);
			Iterator<String> j = vcolunas.iterator();
			while (j.hasNext()) {
				String nomeColuna = j.next();
				trnodeCamposOrigem = new DefaultMutableTreeNode(nomeColuna);
				trnodeTabelasOrigem.add(trnodeCamposOrigem);

				DicionarioMetaDados dm = new DicionarioMetaDados();

				dm.setTipo("3");
				dm.setNome(nomeColuna);
				dm.setNomeTabela(nomeTabela);
				dm.setTabelapk(nomeTabela);
				dm.setNomeBd(nomeBdOrigem);
				dm.setMnemonico("");
				dm.setConceito("");
				dm.setPolonesa("F(X) = A ^ B / C"); 
				dm.setTitulocurto(retirarVogais(nomeColuna));
				dm.setTitulomedio(pesquisaFonetica(nomeColuna));
				dm.setTitulolongo(pesquisaFoneticaTexto(nomeColuna));
				dm.setNomefk("");
				dm.setNomepk("");
				dm.setTabelapk(nomeTabela);
				dm.setTabelafk(nomeTabela);

				vdmdOrigem.add(dm);
			}
			// trnodeTabelasOrigem.add(trnodeCamposOrigem);
			trnodebdorigem.add(trnodeTabelasOrigem);
		}
		
		treeConexaoOrigem = new JTree();

		treeConexaoOrigem.setModel(new DefaultTreeModel(trnodebdorigem));

		treeConexaoOrigem.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent treeEvento) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) ProcedimentosApoio.treeConexaoOrigem
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeInfo = node.getUserObject();
				String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
				ProcedimentosApoio.jtableMestre = new JTable(
						ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoOrigem, nomeNode));
				setListenerEventTable();//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<===============================
				
				ProcedimentosApoio.jtableMestre.setPreferredScrollableViewportSize(new Dimension(300, 200));
				ProcedimentosApoio.jtableMestre.setFillsViewportHeight(true);
				scpMaster = new JScrollPane(ProcedimentosApoio.jtableMestre);
				scpMaster.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "BD Origem",
						TitledBorder.CENTER, TitledBorder.TOP));
				FretusModel.atualizarTabsMestre(scpMaster, nomeNode);
			}
		});
	}

	private static String pesquisaFoneticaTexto(String nomeColuna) {
		String[] parts = nomeColuna.split("[\\W]");
        nomeColuna = "";
	    for(String i:parts){
	    	nomeColuna+=i;
	    }
		return nomeColuna;
	}

	private static String pesquisaFonetica(String nomeColuna) {
	    String[] parts = nomeColuna.split("[\\W]");
        nomeColuna = "";
	    for(String i:parts){
	    	nomeColuna+=i;
	    }
		return nomeColuna;
	}

	private static String retirarVogais(String nomeColuna) {
		
		return nomeColuna.replace("a, e, i, o, u", " ").trim();
		
	}

	protected static void popularArvoreDBdestino(Connection conn, String nomeBdDestino) {

		trnodebddestino = new DefaultMutableTreeNode(nomeBdDestino);

		Iterator<String> i = nomesTabelasDestino.iterator();
		while (i.hasNext()) {
			String nomeTabela = i.next();
			trnodeTabelasDestino = new DefaultMutableTreeNode(nomeTabela);
			Vector<String> vcolunas = obterColunasTabela(conn, nomeTabela);
			Iterator<String> j = vcolunas.iterator();
			while (j.hasNext()) {
				String nomeColuna = j.next();
				trnodeCamposDestino = new DefaultMutableTreeNode(nomeColuna);
				trnodeTabelasDestino.add(trnodeCamposDestino);
				DicionarioMetaDados dm = new DicionarioMetaDados();

				dm.setTipo("3");
				dm.setNome(nomeColuna);
				dm.setNomeTabela(nomeTabela);
				dm.setTabelafk(nomeTabela);
				dm.setNomeBd(nomeBdDestino);
				dm.setMnemonico("");
				dm.setConceito("");
				dm.setPolonesa("F(X) = A ^ B / C"); 
				dm.setTitulocurto(retirarVogais(nomeColuna));
				dm.setTitulomedio(pesquisaFonetica(nomeColuna));
				dm.setTitulolongo(pesquisaFoneticaTexto(nomeColuna));
				dm.setNomefk("");
				dm.setNomepk("");
				dm.setTabelapk(nomeTabela);
				dm.setTabelafk(nomeTabela);
				vdmdDestino.add(dm);
			}
			// trnodeTabelasOrigem.add(trnodeCamposOrigem);
			// trnodebdorigem.add(trnodeTabelasOrigem);
			// trnodeTabelasDestino = new DefaultMutableTreeNode(i.next());
			trnodebddestino.add(trnodeTabelasDestino);
		}

		
		treeConexaoDestino = new JTree();
		treeConexaoDestino.setModel(new DefaultTreeModel(trnodebddestino));
		treeConexaoDestino.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent treeEvento) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) ProcedimentosApoio.treeConexaoDestino
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeInfo = node.getUserObject();
				String nomeNode = treeEvento.getNewLeadSelectionPath().getLastPathComponent().toString();
				ProcedimentosApoio.jtableMestre = new JTable(
						ProcedimentosApoio.popularJTable(ProcedimentosApoio.conexaoDestino, nomeNode));
				setListenerEventTable();  //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<===============================
				ProcedimentosApoio.jtableMestre.setPreferredScrollableViewportSize(new Dimension(300, 200));
				ProcedimentosApoio.jtableMestre.setFillsViewportHeight(true);
				scpMaster = new JScrollPane(ProcedimentosApoio.jtableMestre);

				scpMaster.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "BD Destino",
						TitledBorder.CENTER, TitledBorder.TOP));

				FretusModel.atualizarTabsMestre(scpMaster, nomeNode);
				
			}
		});
	}

	//

	public static void iniciarVariaveis() {

		Iterator<String> tipos = Arrays.asList(arraytypes).iterator();
		while (tipos.hasNext())
			typesbtwplics.add(tipos.next());
	}

	/*
	 * Gerar vetor com os comandos INSERT para a tabela de destino a partir dos
	 * campos específicos da tabela de origem. AQUI COLOCAR OS PIPES NOS CAMPOS
	 * AGRUPADOS
	 */
	public static Vector<String> selectInsert(Connection destino, Connection origem, String nomeTabelaOrigem,
			String nomeTabelaDestino, Object[][] camposDePara, Boolean simular) {

		preparedInsert.removeAllElements();

		try {

			organizarCamposParaSelect(nomeTabelaDestino, nomeTabelaOrigem, fieldoftableTo, fieldoftable, camposDePara);

			Statement stmt = origem.createStatement();

			if (origemSelectTableCommand.containsKey(nomeTabelaOrigem)) {
				strcmd = origemSelectTableCommand.get(nomeTabelaOrigem);
			} else {
				strcmd = strSelect; // "SELECT * FROM " + nomeTabelaOrigem;
			}

			fieldoftable.clear();
			fieldoftable.removeAllElements();

			typesofcols.clear();
			typesofcols.removeAllElements();

			ResultSet rst = stmt.executeQuery(strcmd);

			rsmd = rst.getMetaData();
			int ncols = rsmd.getColumnCount();
			//// Para capturar metadados
			if (camposDePara.length == 0) {
				for (int j = 1; j < ncols + 1; j++) {
					fieldoftable.add(nomeTabelaOrigem + "." + rsmd.getColumnName(j));
					typesofcols.add(rsmd.getColumnTypeName(j));
					if (j == 1)
						System.out.println(rsmd.getColumnTypeName(j));
				}
			} else { // Array camposDepara está com valores
				for (int i = 0; i < camposDePara.length; i++) {
					fieldoftable.add(camposDePara[i][3].toString());
					typesofcols.add(camposDePara[i][4].toString());
				}
			}

			tiposdoscampostaborigem.removeAllElements();
			Iterator<String> it = typesofcols.iterator();
			while (it.hasNext()) {
				tiposdoscampostaborigem.addElement(it.next());
			}
			int controle = 0;

			vetorValues.clear();
			vetorValues.removeAllElements();

			while (rst.next()) { // Só para ler a primeira ocorrência da tabela.
									// Mudar para while para iteração completa.
				// O valores selecionados são somente aqueles que constam do
				// array camposDePara
				String valuesInsert = "(";
				for (int ix = 1; ix <= ncols; ix++) { //
					String nomeColuna = nomeTabelaOrigem + "." + rst.getMetaData().getColumnName(ix);
					if (fieldoftable.contains(nomeColuna)) {
						for (int jx = 0; jx < fieldoftable.size(); jx++) {
							String[] partes = fieldoftable.elementAt(jx).split("\\.");
							String nomeCampo = partes.length > 1 ? partes[1] : partes[0];
							if (fieldoftable.elementAt(jx).contains(nomeColuna) || nomeCampo.equals(nomeColuna)) {
								if (typesbtwplics.contains(typesofcols.elementAt(jx)))
									valuesInsert += (", '" + rst.getString(ix)) + "'";
								else
									valuesInsert += (", " + rst.getString(ix));
							}
						}
					} else { // Es wird resultierenden Inhaltsgruppierung Felder
								// aus.
						int ik = 0;
						String registro = rst.getString(ix);
						String[] campos = registro.split(",");
						String conteudo = "";
						for (String s : campos) {
							if (ik >= vtamanhocampoagrupados.size())
								System.out.println("Falha tratamento de campos agrupados.");
							else {
								Estrutura et = vtamanhocampoagrupados.elementAt(ik);
								if (typesbtwplics.contains(et.getTipoCampo()) && !s.endsWith("\"")
										&& !s.startsWith("\"")) {
									conteudo += "\"" + s + "\"|";
								} else {
									conteudo += s + "|";
								}
							}
							ik++;
						}
						registro = conteudo;
						registro = registro.replace("(", "");
						registro = registro.replace(")", "");
						registro = (registro.replace("\"", "")).trim();
						String[] parts = registro.split("\\|");
						String saida = "";
						for (String s : parts) {
							saida += "^" + s;
						}
						registro = (saida.replaceFirst("\\^", "")).trim();

						valuesInsert += ", '" + registro + "'";

					}
				}
				valuesInsert = valuesInsert.replaceFirst(",", "") + " )";
				valuesInsert = valuesInsert.replaceAll("'null'", "''");
				valuesInsert += "^";
				vetorValues.addElement(valuesInsert);
				System.out.println(valuesInsert);

				if (++controle == 1) { // para mostrar o primeiro registro
					for (int ix = 1; ix <= ncols; ix++) {
						System.out.println(
								rsmd.getColumnName(ix) + " Tipo: " + rst.getType() + " Conteúdo: " + rst.getString(ix));
					}
				}
			}
			System.out.println(nomeTabelaOrigem + " n.rec =  " + controle);
			// Já está na tabela
			// camposDepara???????????????????????????????????????????????
			obterColunasTabelaDestino(destino, nomeTabelaDestino); // não mais
																	// //
																	// necessário,
																	// // pois
																	// já // se
																	// tem os //
																	// dados no
																	// // array
																	// camposDePara
			// ---------------->tabela de destino , tabela de origem dos
			// dados, campos de destino e de origem
			// =======================================================================================================
			gerarInsertSelect(nomeTabelaDestino, nomeTabelaOrigem, fieldoftableTo, fieldoftable, camposDePara);
			// =======================================================================================================
			// executar os inserts preparados...
			if (!simular) {
				int controlador = 0;
				Iterator<String> i = preparedInsert.iterator();
				String cmdins = "";
				while (i.hasNext()) {
					if (++controlador == 1) {
						cmdins = i.next();
						System.out.println("Comando = " + cmdins);
						PreparedStatement preparedStmt = destino.prepareStatement(cmdins);
						preparedStmt.execute();
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Montador SQL", 3);
			e.printStackTrace();
		}
		return preparedInsert;

	}

	private static void organizarCamposParaSelect(String nomeTabelaDestino, String nomeTabelaOrigem,
			Vector<String> fieldoftableTo2, Vector<String> fieldoftable2, Object[][] camposDePara) {

		strSelect = "SELECT listaCampos FROM tabelaAlvo";
		vtamanhocampoagrupados.removeAllElements();
		String strConcatene = "(";
		String camposdestino = "";
		String camposorigem = "";
		int ncamposDePara = camposDePara.length;
		for (int ifinal = 0; ifinal < ncamposDePara; ifinal++) {
			if (ifinal < (camposDePara.length - 1)) {
				if (camposDePara[ifinal][0].toString().equals(camposDePara[ifinal + 1][0].toString())) {
					strConcatene += ", " + camposDePara[ifinal][3].toString();
					vtamanhocampoagrupados.add(new Estrutura(camposDePara[ifinal][3].toString(),
							camposDePara[ifinal][4].toString(), camposDePara[ifinal][5].toString()));
				} else {
					if (!strConcatene.equals("(")) {
						camposorigem += ", " + strConcatene.replaceFirst(",", "") + "AS camposAgrupados";
						strConcatene = "(";
					}
					camposorigem += ", " + camposDePara[ifinal][3].toString();
				}
			} else { // tratamento da última linha da tabela
				if (!strConcatene.equals("(")) {
					if (ifinal == (ncamposDePara - 1) && camposDePara[ncamposDePara - 1][0].toString()
							.equals(camposDePara[ncamposDePara - 2][0].toString())) {
						strConcatene += ", " + camposDePara[ifinal][3].toString();

						strConcatene = strConcatene.replaceFirst(",", "");

						camposorigem += ", " + strConcatene + ") AS camposAgrupados";
						strConcatene = "(";
					} else {
						strConcatene = strConcatene.replaceFirst(",", "");

						camposorigem += ", " + strConcatene + ") AS camposAgrupados";
						strConcatene = "(";
						camposorigem += ", " + camposDePara[ifinal][3].toString();
					}
				} else
					camposorigem += ", " + camposDePara[ifinal][3].toString();
			}
			if (!camposdestino.contains(camposDePara[ifinal][0].toString()))
				camposdestino += ", " + camposDePara[ifinal][0].toString();
		}
		camposorigem = camposorigem.replaceFirst(",", "");
		camposdestino = camposdestino.replaceFirst(",", "");

		strSelect = strSelect.replace("listaCampos", camposorigem);
		strSelect = strSelect.replace("tabelaAlvo", nomeTabelaOrigem);

	}

	public static Vector<String> gerarInsertSelect(String tabelaDestino, String tabelaOrigem,
			Vector<String> fieldoftableto, // campos da tabela destino
			Vector<String> fieldoftable, Object[][] camposDePara) { // campos da
																	// tabela de
																	// origem

		// Aqui: verifique se há campos de destino duplicado. Isto significa que
		// tem mais de um campo de origem para um único de destino então:
		// Campos da tabela de destino: considerar apenas os que foram
		// selecionados para receber dados.
		strSelect = "SELECT listaCampos FROM tabelaAlvo";

		String strConcatene = "(";
		String camposdestino = "";
		String camposorigem = "";
		int ncamposDePara = camposDePara.length;
		for (int ifinal = 0; ifinal < ncamposDePara; ifinal++) {
			if (ifinal < (camposDePara.length - 1)) {
				if (camposDePara[ifinal][0].toString().equals(camposDePara[ifinal + 1][0].toString())) {
					strConcatene += ", " + camposDePara[ifinal][3].toString();
				} else {
					if (!strConcatene.equals("(")) {
						camposorigem += ", " + strConcatene.replaceFirst(",", "") + "AS camposAgrupados";
						strConcatene = "(";
					}
					camposorigem += ", " + camposDePara[ifinal][3].toString();
				}
			} else { // tratamento da última linha da tabela
				if (!strConcatene.equals("(")) {
					if (ifinal == (ncamposDePara - 1) && camposDePara[ncamposDePara - 1][0].toString()
							.equals(camposDePara[ncamposDePara - 2][0].toString())) {
						strConcatene += ", " + camposDePara[ifinal][3].toString();

						strConcatene = strConcatene.replaceFirst(",", "");

						camposorigem += ", " + strConcatene + ") AS camposAgrupados";
						strConcatene = "(";
					} else {
						strConcatene = strConcatene.replaceFirst(",", "");

						camposorigem += ", " + strConcatene + ") AS camposAgrupados";
						strConcatene = "(";
						camposorigem += ", " + camposDePara[ifinal][3].toString();
					}
				} else
					camposorigem += ", " + camposDePara[ifinal][3].toString();
			}
			if (!camposdestino.contains(camposDePara[ifinal][0].toString()))
				camposdestino += ", " + camposDePara[ifinal][0].toString();
		}
		camposorigem = camposorigem.replaceFirst(",", "");
		camposdestino = camposdestino.replaceFirst(",", "");

		String strInsertValues = "";
		String strInsertSel = "";

		strSelect = strSelect.replace("listaCampos", camposorigem);
		strSelect = strSelect.replace("tabelaAlvo", tabelaOrigem);

		vinsertSelect.add(strSelect);

		if (destinoInsertTableCommand.containsKey(tabelaDestino)) {
			strInsertValues = strInsertSel = destinoInsertTableCommand.get(tabelaDestino);
		} else {
			strInsertSel = "INSERT INTO " + tabelaDestino + " (" + camposdestino + ") SELECT " + camposorigem + " FROM "
					+ tabelaOrigem;
			strInsertValues = "INSERT INTO " + tabelaDestino + " (" + camposdestino + ") VALUES ";
			strSelect = "SELECT " + camposorigem + " FROM " + tabelaOrigem;
		}

		vinsertSelect.add(strInsertSel);

		Iterator<String> k = vetorValues.iterator();
		while (k.hasNext()) {
			String sqlInsert = strInsertValues + k.next();
			System.out.println(sqlInsert);
			preparedInsert.add(sqlInsert);
		}

		return preparedInsert;

	}

	public static void obterColunasTabelaDestino(Connection conDestino, String nomeTabelaDestino) {
		try {
			Statement stmt = conDestino.createStatement();

			System.out.println("=====================================================");
			System.out.println(nomeTabelaDestino);
			System.out.println("=====================================================");
			String strCmdGeafin = "";

			strCmdGeafin = "SELECT * FROM " + nomeTabelaDestino;
			ResultSet rst = stmt.executeQuery("SELECT * FROM " + nomeTabelaDestino);
			fieldoftableTo.clear();
			fieldoftableTo.removeAllElements();

			// ResultSetMetaData rsmd = rstab.getMetaData()
			int nrecords = rst.getRow();

			ResultSetMetaData rsmd = rst.getMetaData();
			int numCols = rsmd.getColumnCount();
			if (numCols > 0)
				System.out.println(nomeTabelaDestino + " N.Rc: " + nrecords + " — Número de Colunas : " + numCols);

			for (int i = 1; i <= numCols; i++) {
				fieldoftableTo.add(rsmd.getColumnName(i));
				if (i > 1)
					System.out.print(", ");
				System.out.print(rsmd.getColumnLabel(i));
			}
			System.out.println("");

		} catch (Exception e) {
			System.out.println("Geafin Metadados: Erro controlado " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static Vector<String> obterColunasTabela(Connection conn, String nomeTabela) {
		try {
			Statement stmt = conn.createStatement();

			ResultSet rst = stmt.executeQuery("SELECT * FROM " + nomeTabela);
			vpropriedades.clear();
			vpropriedades.removeAllElements();

			ResultSetMetaData rsmd = rst.getMetaData();
			int numCols = rsmd.getColumnCount();

			for (int i = 1; i <= numCols; i++) {
				// System.out.println("Colunas--> " + rsmd.getColumnName(i));
				vpropriedades.add(rsmd.getColumnName(i));
			}
		} catch (Exception e) {
			System.out.println("Obtendo nomes de colunas--> " + e.getMessage());
			e.printStackTrace();
		}
		return vpropriedades;
	}

	public static void loadVectorTabelasGeafin() {

		String artablegeafin[] = { "abccurva", "abcprecurva", "acoplamento", "almoxarifado",
				"amortizacao_matperm_ativos", "amortizacao_matperm_baixados", "amortizacao_matperm_controle",
				"bancofornecedor", "bloco_patrimonio", "bloco_patrimonio_item", "bloqueio", "cbo", "cidadesuprimentos",
				"classificacaosiafi", "codigoestrutural", "coletor", "conserto", "contatorepresentanteregional",
				"contratosocial", "cotacao", "datafechamentosiafi", "depreciacao", "depreciacao_matperm_ativos",
				"depreciacao_matperm_baixados", "depreciacao_matperm_controle", "depreciacao_matperm_dump",
				"dirigentes", "documentosfornecedor", "editora", "empresascoligadas", "entidade",
				"entidadesfiscalizadoras", "entradaempenho", "entradaempenhoitens", "entradanota", "entradatermo",
				"esferaorcamentaria", "estado_inventario", "estadoconservacao", "estoquealmoxarifado",
				"estoquematpermsiafi", "estoquemovimentacao", "etiquetaspatrimoniocanceladas",
				"etiquetaspatrimoniolivres", "fasestipo", "fontesrecursos", "fornecedor", "fornecedorcontato",
				"fornecedorcontatotipoetiqueta", "fornecedorentidadefiscalizadora", "fornecedorprodutos",
				"fundamento_legal", "grupo", "grupo_depreciacao", "grupopadrao", "habilitacaocrc", "historico",
				"historico_lotacao", "historico_lotacao_nome", "historicoauxiliar", "historicoconservacao",
				"historicomaterialpermanente", "historicopadraocusteio", "horario_terceiro",
				"inconsistencia_inventario", "infchamadotecnico", "infchamadotecnicohistorico", "infcontrato",
				"infcontratoanexo", "infcontratofiscal", "infcontratofornecedor", "infcontratoocorrencia",
				"infra_agendamento_tarefa", "infra_log", "infra_log_acesso", "infra_parametro", "infra_sequencia",
				"infsaida", "infsubsecao", "inftiposituacao", "instrumento", "inventario", "inventario_geral",
				"inventario_itens_iniciais", "inventario_regularizacao_aus", "inventario_regularizacao_exc",
				"item_instrumento", "itementradatermo", "itementradatermoaux", "itenscontrato", "itensinvaux",
				"itensinventario", "itenstransferencia", "itenstransferenciaauxiliar", "lancamentosexecucao",
				"logrequisicaoretorno", "logtrocamaterial", "lotacao", "lotacao_antiga", "lotacaoplanocontascusteio",
				"manual", "marcamaterial", "material", "materialaux", "materialhistoricosiafi", "materialpermanente",
				"materialpermanenteauxiliar", "materialtransformacao", "mediaconsumoultimos12meses", "modalidade",
				"modelomaterial", "moeda", "motivooperacoes", "naturezadespesa", "nivelhabilitacao", "orcamento",
				"organograma", "orgaoexterno", "origem_instrumento", "palm_inventario", "palm_inventario_itens",
				"palm_inventario_lotacao", "palm_inventario_lotacao_index", "palm_inventario_naoidentif_alt",
				"palm_inventario_serie_index", "palm_parametrizacao", "parametros", "parametroscontaspublicas",
				"parametrosistema", "planocontascusteio", "planocontaspublicas", "prediolotacao", "processo",
				"processofornecparticipante", "processohistorico", "processotipo", "processotipomodalidade",
				"propostacomercial", "propostaobservacao", "rbfolder", "rbitem", "recebimento", "recebimentoitens",
				"recibo", "regime_execucao", "rel_instrumento_pessoa", "rel_instrumento_terceiro",
				"rel_inventario_geral_inventario", "rel_itensinventario_incons", "relcodestruturalestoque",
				"relextratomaterial", "relinventmp", "relinventmpdiv", "relmaterialpermanente", "representanteregional",
				"reqpesqprerusso2", "reqrusso2", "requisicao", "requisicaoitens", "requisicaoitensrecebimento",
				"requisicaopesquisapreco", "retiradaedital", "saca", "sacaaux", "sacalivros", "sacalivrositens",
				"saldocusteio", "seq_abcprecurva", "seq_bloco_patrimonio", "seq_bloco_patrimonio_item", "seq_bloqueio",
				"seq_historico_lotacao", "seq_historico_lotacao_nome", "seq_infra_log", "seq_infra_log_acesso",
				"seq_inventario", "seq_inventario_geral", "seq_inventario_regularizacao_aus",
				"seq_inventario_regularizacao_exc", "seq_itensinventario", "seq_manual", "seq_recebimento",
				"seq_recebimentoitens", "seq_rel_itensinventario_incons", "seq_solicitacao_recibo",
				"seq_solicitacao_termo", "seq_tipo_inventario", "sequencia", "siafidepara", "siafinovo",
				"sip_item_menu", "sip_perfil", "sip_recurso", "sip_rel_perfil_item_menu", "sip_rel_perfil_recurso",
				"situacao_inventario", "situacaomaterialpermanente", "socios", "solicitacao", "solicitacaoempenho",
				"solicitacaoitem", "solicitacaoitemsituacao", "solicitacaoitemsubstituto", "solicitacaomarcamodelo",
				"statusfornecedor", "subsistema", "subsistema_grupo", "suprimentodespesa", "suprimentofundos",
				"suprimentofundositens", "terceiro", "tipo_instrumento", "tipo_inventario", "tipocontrato",
				"tipodespesasuprimento", "tipoempenho", "tipoempresa", "tipoetiquetapatrimonio", "tipofasesprocesso",
				"tipolancamento", "tiponotafiscalcamposobrigatorios", "tipoprocesso", "tiporecurso", "tiporequisicao",
				"tiposdocumentos", "tombamentoalterado", "transferencia", "transferenciaauxiliar", "ufsuprimentos",
				"ultimamovimentacao", "unidadegestora", "unidadeorcamentaria", "unidadereferencia", "usuario",
				"usuarioagenciabanco", "usuarioplanocontas", "vara_conversao", "vara_conversao_portaria_99",
				"vara_conversao_siapro", "verificarmaterialfinanc", "vinculacao", "viewestoquealmoxarifado" };

		tabelasgeafin = new Vector<String>(Arrays.asList(artablegeafin));
	}
	// select (nr_ddd1 + '-' + nr_fone1 + '/' + nr_ddd2 + '-' + nr_fone2) as
	// telefone, (Nr_ddd_fax + '-' + nr_fax) as fax from cr_agente;

	private static void loadDestinoInsertTableCommand() {

		destinoInsertTableCommand.put("geafin.orgaoexterno", new String(
				"INSERT INTO orgaoexterno (idorgaoexterno, idcidade, nome, rua, bairro, cep, fone, fax, email) VALUES "));

		origemSelectTableCommand.put("ASIW.dbo.CR_AGENTE", new String(
				"select CD_AGENTE, cd_cidade, Nm_agente, Ed_agente, Nm_bairro, rtrim(replace(Nr_cep, '-', '')), (nr_ddd1 + '-' + nr_fone1 + '/' + nr_ddd2 + '-' + nr_fone2) as telefone, (Nr_ddd_fax + '-' + nr_fax) as fax, nm_email  from cr_agente"));

	};

	//
	public static void popularComboTabelasOrigem(String modulo) {

		// Leitura do metadados....
		ProcedimentosApoio.cbTabelasModuloOrigem.removeAllItems();
		nomesTabelas.removeAllElements();
		vnomesTabelasOrigem.removeAllElements();
		nomesTabelas.clear();
		try {
			if (!conexaoOrigem.equals(null)) {
				nomesTabelas.addAll(capturarTabelasModulo(modulo, "origem", conexaoOrigem));
				Iterator<String> i = nomesTabelas.iterator();
				while (i.hasNext()) {
					ProcedimentosApoio.cbTabelasModuloOrigem.addItem((String) i.next());
				}
				vnomesTabelasOrigem.addAll(nomesTabelas);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Popular Tabela Origem", 3);
		}

	}

	//
	public static void popularComboTabelasDestino(String pmodulo) {

		// Leitura do metadados....
		ProcedimentosApoio.cbTabelasDestino.removeAllItems();
		nomesTabelas.removeAllElements();
		vnomesTabelasDestino.removeAllElements();
		nomesTabelas.clear();
		try {

			if (!conexaoDestino.equals(null)) {
				if (pmodulo.equals("GLOBAL"))
					nomesTabelas.addAll(capturarTabelasModulo("geafin", "destino", conexaoDestino));
				else
					nomesTabelas.addAll(capturarTabelasModulo(pmodulo, "destino", conexaoDestino));

				Iterator<String> i = nomesTabelas.iterator();
				while (i.hasNext()) {
					ProcedimentosApoio.cbTabelasDestino.addItem(i.next());
				}
				vnomesTabelasDestino.addAll(nomesTabelas);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Conexão banco destino", 3);
		}
	}

	//
	public static Vector<String> capturarTabelasModulo(String pmodulo, String alvo, Connection conn) {

		String[] types = { "TABLE" };
		try {

			pmodulo = pmodulo.replaceAll("Módulo ", "");

			Statement stmt = conn.createStatement();

			PreparedStatement prepStmt = conn.prepareStatement(sqlCmdLimit);

			DatabaseMetaData mtdt = conn.getMetaData();

			ResultSet rsglobal = mtdt.getTables(conn.getCatalog(), "%", "%", null);
			ResultSetMetaData rsmdglobal = rsglobal.getMetaData(); // Oportunamente
																	// analisar
																	// este
																	// retorno
																	// com os
																	// parâmetros
																	// informados.

			ResultSet rs = mtdt.getTables(conn.getCatalog(), null, "%", types);
			ResultSetMetaData rsmd = rs.getMetaData();

			int numCols = rsmd.getColumnCount();

			if (numCols > 0)
				System.out.println("Metadados — Número de Colunas : " + numCols + " Módulo " + pmodulo);
			for (int i = 1; i <= numCols; i++) {
				if (i > 1)
					System.out.print(", ");
				System.out.print(rsmd.getColumnLabel(i));
			}

			//
			textotex.clear();
			textotex.removeAllElements();
			vgetandset.clear();
			vgetandset.removeAllElements();
			nomesTabelas.clear();
			nomesTabelas.removeAllElements();

			System.out.println("");
			System.out.println("See it to understand the difference between table catalog and table schema.");
			System.out.println("===========================================================================");
			ArrayList<String> arrayList = new ArrayList<String>();

			int cont = 0;
			String pmodtrim = pmodulo.trim();
			while (rs.next()) {
				cont++;
				nomesTabelas.add(rs.getString("TABLE_NAME"));
				// System.out.println(rs.getString("TABLE_NAME") + " número = "
				// + cont);
				int i = 1;
				while (i <= numCols) {
					arrayList.add(rs.getString(i++));
				}
			}
			System.out.println("Quantidade de tabelas " + cont);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Falha conexão Banco TunicaDB", 3);
			e.printStackTrace();
		}

		if (alvo.equals("origem") || alvo.equals("Origem"))
			nomesTabelasOrigem = nomesTabelas;
		else
			nomesTabelasDestino = nomesTabelas;

		return nomesTabelas;
	}

	public static Vector<Estrutura> obterColunasTiposTabela(String tabela, String alvo, Connection conn, String banco) {

		Vector<Estrutura> vestru = new Vector<Estrutura>();
		String cmdsqlorigemoudestino = mapSqlSingleLineForDB.get(banco).replace("tabelaAlvo", tabela);

		try {
			if (cmdsqlorigemoudestino.isEmpty()) {
				switch (banco) {
				case "Cache":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
					break;
				case "MySql":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
					break;
				case "Oracle":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
					break;
				case "Postgres":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				case "MSSql":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				case "DB2":
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				default:
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				}
				if (alvo.equals("destino")) {
					cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				} else {
					cmdsqlorigemoudestino = sqlCmdLimitMssql + tabela;
				}
			}
			System.out.println(cmdsqlorigemoudestino);
			/*
			 * ler um registro da tabela para extrair dados de tamanho
			 */

			Statement stmt = conn.createStatement();

			PreparedStatement prepStmt = conn.prepareStatement(sqlCmdLimit);

			DatabaseMetaData mtdt = conn.getMetaData();

			// Obter do metadados as informações de catalogo e schema
			if (alvo.equals("origem")) {
				ResultSet catalogoOrigem = mtdt.getCatalogs();
				while (catalogoOrigem.next()) {
					System.out.println("Catálogos   " + catalogoOrigem.getString("TABLE_CAT"));
					catOrigem = catalogoOrigem.getString(1);
				}
				catalogoOrigem.close();

				// table_cat, table_schem
				ResultSet schemaOrigem = mtdt.getSchemas();
				while (schemaOrigem.next()) {
					System.out.println(" Eschemas   " + schemaOrigem.getString("table_schem"));
					schOrigem = schemaOrigem.getString(1);
				}
				schemaOrigem.close();
			} else {
				ResultSet catalogoDestino = mtdt.getCatalogs();
				while (catalogoDestino.next()) {
					System.out.println("Catálogos   " + catalogoDestino.getString("TABLE_CAT"));
					catDestino = catalogoDestino.getString(1);
				}
				catalogoDestino.close();

				// table_cat, table_schem
				ResultSet schemaDestino = mtdt.getSchemas();
				while (schemaDestino.next()) {
					System.out.println(" Eschemas   " + schemaDestino.getString("table_schem"));
					schDestino = schemaDestino.getString(1);
				}
				schemaDestino.close();
			}

			ResultSet rstab = stmt.executeQuery(cmdsqlorigemoudestino);

			ResultSetMetaData rsmd = rstab.getMetaData(); // metadados da
															// tabela....
			if (alvo.equals("origem")) {
				catalogo = rsmd.getCatalogName(1);
				schema = rsmd.getSchemaName(2);
			} else {
				catalogo = rsmd.getCatalogName(1);
				schema = rsmd.getSchemaName(2);
			}

			int tamanhoEstrutura = 0;
			int numColumns = rsmd.getColumnCount();

			for (int j = 1; j < numColumns + 1; j++) {

				Estrutura estru = new Estrutura();
				estru.setNomeCampo(rsmd.getColumnName(j));
				estru.setTipoCampo(rsmd.getColumnTypeName(j));
				estru.setTamanho(Integer.toString(rsmd.getColumnDisplaySize(j)));
				vestru.add(estru);
			}

		} catch (Exception e) {

			infoBox("Erro não esperado: " + cmdsqlorigemoudestino + " " + e.getMessage(), "Acesso a Dados Falha");
			e.printStackTrace();

		}

		return vestru;

	}

	/*
	 * Retornar informações sobre a relação de chave primária e estrangeira Da
	 * tabela tabelaAnalise banco de dados .
	 **/
	public static Vector<String> executeGetCrossReference(Connection con, String catalogo1, String schema1,
			String tabela1, String catalogo2, String schema2, String tabela2) {

		vrelations.removeAllElements();
		vrelations.clear();
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getCrossReference(catalogo1, schema1, tabela1, null, schema2, tabela2);
			ResultSetMetaData rsmd = rs.getMetaData();

			// Display the result set data.
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					vrelations.addElement(rs.getString(i));
					System.out.println(rs.getString(i));
				}
			}
			rs.close();
		}

		catch (Exception e) {
			System.out.println("Conexão com banco não efetuada: " + catalogo1 + " " + schema1 + " " + tabela1 + " "
					+ schema2 + " " + tabela2);
			e.printStackTrace();
		}
		return vrelations;
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	//
	public static Vector<String> executeGetCrossReference(Connection con, String catalogo1, String schema1,
			String tabela1, String catalogo2, String schema2, String tabela2, Vector<String> vrelations) {

		vrelations.removeAllElements();
		vrelations.clear();
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getCrossReference(catalogo1, schema1, tabela1, null, schema2, tabela2);
			ResultSetMetaData rsmd = rs.getMetaData();
			// Display the result set data.
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= cols; i++) {
					vrelations.addElement(rs.getString(i));
					System.out.println(rs.getString(i));
				}
			}
			rs.close();
		}

		catch (Exception e) {
			System.out.println("Conexão com banco não efetuada: " + catalogo1 + " " + schema1 + " " + tabela1 + " "
					+ schema2 + " " + tabela2);
			e.printStackTrace();
		}
		return vrelations;
	}

	
	protected static void setListenerEventTable() {
		try {

			//ProcedimentosApoio.jtableMestre.setCellSelectionEnabled(true);
			ProcedimentosApoio.jtableMestre.setRowSelectionAllowed(true);
			ProcedimentosApoio.cellSelectionModel = ProcedimentosApoio.jtableMestre.getSelectionModel();
			ProcedimentosApoio.cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

			
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
						if (nomeTabelaBDquepopulatable.startsWith(dcmd.getNomeTabela())) {
							vtituloscolunas.addElement(dcmd.getNome());
						}

					}
					//ProcedimentosApoio.cellSelectionModel.setSelectionInterval(0, 0);   //aqui retorna
					
					Iterator<DicionarioMetaDados> k = ProcedimentosApoio.vdmdDestino.iterator();
					while (k.hasNext()) {
						DicionarioMetaDados dcmd = k.next();
						if (dcmd.getNome().startsWith(nomeTabelaBDquepopulatable)) {
							vtituloscolunas.addElement(dcmd.getNome());
						}

					}

					
					for (int i = 0; i < selectedRow.length; i++) {
						for (int m = 0; m < vtituloscolunas.size(); m++) { //  selectedColumns.length; k++) {
							vconteudoRecord.addElement(
									ProcedimentosApoio.jtableMestre.getValueAt(selectedRow[i], m)); //selectedColumns[k]));
						}
					}

					
					Iterator<Object> scont  =  vconteudoRecord.iterator();
					while(scont.hasNext()) {
						System.out.println("Conteudo coluna " + scont.next());
						
					}
					//==========================================================================================================
					FretusModel.mostrarProjecaoRegistroMestre(vtituloscolunas, vtiposdecolunas, vconteudoRecord,
							nomeTabelaBDquepopulatable);				
					
				}
			});

			ProcedimentosApoio.cellSelectionModel.addListSelectionListener(jtableMestre);
			ProcedimentosApoio.cellSelectionModel.setLeadSelectionIndex(0);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	//
	protected static DefaultTableModel popularJTable(Connection con, String ptabela) {

		Vector<Object> registros = new Vector<Object>();
		String titulos[] = {};
		Object[][] arrayRegistros = {};
		try {

			Statement stmt = con.createStatement();
			PreparedStatement prepStmt = con.prepareStatement(sqlCmdTodos + ptabela);
			ResultSet res = prepStmt.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			int ncolunas = rsmd.getColumnCount();
			titulos = new String[ncolunas];

			for (int h = 1; h <= ncolunas; h++) {
				titulos[h - 1] = rsmd.getColumnName(h);
			}

			while (res.next()) {
				Object[] rowData = new Object[ncolunas];
				for (int i = 0; i < rowData.length; ++i) {
					rowData[i] = res.getObject(i + 1);
				}
				registros.add(rowData);
			}
			int n = 0;
			arrayRegistros = new Object[registros.size()][];

			Iterator<Object> i = registros.iterator();
			while (i.hasNext()) {
				arrayRegistros[n++] = (Object[]) i.next();
			}

			// Quantos registros na tabela?

			prepStmt = con.prepareStatement(sqlCmdCount + ptabela);
			res = prepStmt.executeQuery();
			if (res.next()) {
				Nregistros = res.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return new DefaultTableModel(arrayRegistros, titulos);
	}

	//
	protected static int Nregistros = 0;
	protected static boolean primeiraCarga = true;

	static String sqlCmdTodos = "SELECT * FROM ";
	static String sqlCmdCount = "SELECT count(*) FROM ";
	static String sqlCmdLimit = "SELECT * FROM ";
	static String sqlCmdLimitMssql = "SELECT TOP 1 * FROM ";

	protected static Map<String, String> mapSqlSingleLineForDB = new HashMap<String, String>();

	static Vector<Estrutura> vpropstableorigem = new Vector();
	protected JList<Estrutura> lstPropriedadesTabOrigem = new JList(vpropstableorigem);
	protected Vector<Estrutura> vpropstabledestino = new Vector();
	protected JList<Estrutura> lstPropriedadesTabelaDestino = new JList(vpropstabledestino);
	static Vector<Parestrutura> vparesdepara = new Vector();
	protected JList<Parestrutura> lstParesDePara = new JList(vparesdepara);

	protected static JTextArea taScriptMigracao = new JTextArea();
	protected static Map<String, String> hmdriverurl = new HashMap<String, String>();
	public static Connection conexaoOrigem = null;
	public static Connection conexaoDestino = null;
	public static Connection conexaoAbstral = null;
	public static Vector<String> preparedInsert = new Vector<String>();
	static Vector<Estrutura> propriedadesTabelaOrigem = new Vector<Estrutura>();
	static Vector<Estrutura> propriedadesTabelaDestino = new Vector<Estrutura>();
	static Vector<String> fieldoftable = new Vector<String>();
	static Vector<String> fieldoftableTo = new Vector<String>();
	static Vector<String> nomesTabelas = new Vector<String>();
	static Vector<String> tabelasgeafin = new Vector<String>();
	static Vector<String> vinsertSelect = new Vector<String>();
	static Vector<String> vetorValues = new Vector<String>();
	static Vector<String> typesofcols = new Vector<String>();

	protected static Vector<String> vnomesTabelasOrigem = new Vector<String>();
	protected static Vector<String> vnomesTabelasDestino = new Vector<String>();
	protected static ArrayList<String> typesbtwplics = new ArrayList<String>();
	static Vector<String> vrelations = new Vector<String>();
	static Vector<String> vresultadoSqlCache = new Vector<String>();
	protected static String[] nomesColunas = { "Nome Propriedade", "Tipo Conteúdo", "Tamanho" };
	protected static String[] nomesColunasDePara = { "Nome Propriedade Para", "Tipo Conteúdo Para", "Tamanho Para",
			"Nome Propriedade De", "Tipo Conteúdo De", "Tamanho De" };
	protected Object[][] camposOrigem = null;
	protected Object[][] camposDestino = null;
	protected Object[][] inicialarray = null;
	protected Object[][] camposDePara = { { "Or1", "ot1", "olen1", "para1", "part1", "parlen1" },
			{ "Or2", "ot2", "olen2", "para1", "part1", "parlen1" },
			{ "Or3", "ot3", "olen3", "para1", "part1", "parlen1" } };

	protected static Vector<String> vpropriedades = new Vector<String>();

	protected static JTabbedPane tbdMacrofuncoes = new JTabbedPane(JTabbedPane.TOP);
	protected JPanel pconexao = new JPanel();
	protected JPanel pmigracao = null;
	protected JPanel prelsemantico = new JPanel();
	protected JSplitPane sptPanRelacionamentos = new JSplitPane();
	protected JPanel pRelacionamentos = new JPanel();
	protected JTabbedPane tbdPanePdfs = new JTabbedPane(JTabbedPane.BOTTOM);
	protected static JTabbedPane pTbdpRibbon = new GuiasOpcoes();
	protected JPanel palgebrico = new JPanel();
	protected JPanel pscript = new JPanel();
	protected static DefaultTableModel modelEstruturaOrigem = null;
	protected static DefaultTableModel modelEstruturaDestino = null;
	protected static DefaultTableModel modelEstruturaDePara = null;
	protected static JTable jtbLstPropriedadesTabOrigem = new JTable(modelEstruturaOrigem);
	protected static JTable jtbLstPropriedadesTabDestino = new JTable(modelEstruturaDestino);
	protected static JTable jtbLstPropriedadesTabDePara = new JTable(modelEstruturaDePara);
	protected static JScrollPane jpTabPropiedadesTaborigem = new JScrollPane(jtbLstPropriedadesTabOrigem);
	protected static JScrollPane jpTabPropiedadesTabDestino = new JScrollPane(jtbLstPropriedadesTabDestino);
	protected static JScrollPane jpTabPropiedadesTabDePara = new JScrollPane(jtbLstPropriedadesTabDePara);
	protected static JTree treeConexaoOrigem = null;
	protected static JTree treeConexaoDestino = null;
	protected static DefaultMutableTreeNode trnode = new DefaultMutableTreeNode("Meta Dados");
	// create the child nodes
	protected static DefaultMutableTreeNode trnodebdorigem = new DefaultMutableTreeNode("BD Origem");
	protected static DefaultMutableTreeNode trnodebddestino = new DefaultMutableTreeNode("BD Destino");
	protected static DefaultMutableTreeNode trnodeTabelasOrigem = null; // new
	// DefaultMutableTreeNode("BD
	// Origem");
	protected static DefaultMutableTreeNode trnodeTabelasDestino = null; // new
	// DefaultMutableTreeNode("BD
	// Destino");
	protected static DefaultMutableTreeNode trnodeCamposOrigem = null; // new
	// DefaultMutableTreeNode("B");
	protected static DefaultMutableTreeNode trnodeCamposDestino = null;
	protected static String sqlcachecomando = new String();
	protected static ResultSet rsCacheSql = null;
	protected static boolean brdbtnOrigem = false;
	protected static JRadioButton rdbtnOrigem = null;
	protected static JRadioButton rdbtnDestino = null;
	protected static JTextArea taSqlCacheComando = new JTextArea();
	protected static JTextArea taResultadoConsulta = new JTextArea();

	static Vector<String> nomesTabelasOrigem = new Vector<String>();
	static Vector<String> nomesTabelasDestino = new Vector<String>();

	static final String[] arraytypes = { "char", "varchar", "String", "byte", "CHAR", "VARCHAR", "STRING", "BYTE",
			"serial", "character", "bpchar" };

	public static Connection conn = null;
	public static Connection conngeafin = null;
	public static Connection connection = null;

	public static Connection conexaoTunica = null;
	
	private static Map<String, String> destinoInsertTableCommand = new HashMap<String, String>();
	private static Map<String, String> origemSelectTableCommand = new HashMap<String, String>();

	private static String strSelect = new String("SELECT listaCampos FROM tabelaAlvo");
	// Apenas para laboratório e trabalho por amostragem: estabelecer o par de
	// tabelas DE ===> PARA
	public static String[][] tabsasiwgeafin = { { "ASIW.dbo.CR_AGENTE", "geafin.orgaoexterno" },
			{ "ASIW.dbo.CR_CONTA", "geafin.codigoestrutural" } };

	static String strcmd = "";

	static ResultSetMetaData rsmd = null;

	public static Vector<String> tiposdoscampostaborigem = new Vector<String>();

	public static Vector<String> tiposdoscampostabdestino = new Vector<String>();

	protected static Vector<Estrutura> vtamanhocampoagrupados = new Vector<Estrutura>();

	protected JComboBox<String> cbbBancoDestino = new JComboBox<String>();

	protected JComboBox<String> cbbDriverBdDestino = new JComboBox<String>();

	protected static JComboBox cbModulos = new JComboBox();

	protected static JComboBox cbModuloDestino = new JComboBox();

	protected static JComboBox<String> cbTabelasModuloOrigem = new JComboBox();

	protected static JComboBox<String> cbTabelasDestino = new JComboBox();

	/*
	 * ----------------------------------------------------------------
	 * Latex-Format Text-Rederer PDF wird später implementiert werden.
	 * Kommentar- unter der Anweisung das Objekt zu erstellen. protected
	 * RenderTexpdf renderpdf = new RenderTexpdf();
	 * -------------------------------------------------------------------
	 */
	protected JTextField tfCaminhoAplicativo;
	protected JTextField tfRenderFile;
	protected JTable table;
	protected static JTable jtableMestre;
	protected static JTable jtableDetalhe;
	protected static JScrollPane scpMaster;
	protected static JScrollPane scpDetalhe;
	protected final Action action = new SwingAction();

	static Vector<String> textotex = new Vector<String>();
	static Vector<String> vgetandset = new Vector<String>();
	static Vector<String> vnamespace = new Vector<String>();

	static String catOrigem = new String();
	static String catDestino = new String();

	static String schOrigem = new String();
	static String schDestino = new String();

	static String catalogo = new String();
	static String schema = new String();

	private JComboBox cbbBdOrigem = new JComboBox();

	private JComboBox cbbDriverOrigem = new JComboBox();

	protected static Vector<DicionarioMetaDados> vdmdOrigem = new Vector<DicionarioMetaDados>();
	protected static Vector<DicionarioMetaDados> vdmdDestino = new Vector<DicionarioMetaDados>();

	public static GridBagConstraints gbc = new GridBagConstraints();
	public static TableColumn tcol = null;
	public static TableRow    trow = null;
	static ListSelectionModel cellSelectionModel = null;
	protected class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

		}
	}

}
