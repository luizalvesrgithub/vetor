package com.util.br;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author t317542 - Ziele Capture-Daten für eine einzelne Tabelle.
 *
 */
public class ExtratorGeafin {

	static FileWriter arqasiwinfo;
	static FileWriter amostras;
	static PrintWriter gravarArq;
	static PrintWriter gravarArqAmostra;

	static String sqlCmdTodos = "SELECT * FROM ";
	static String sqlCmdCount = "SELECT count(*) FROM ";
	static String sqlCmdLimit = "SELECT * FROM ";
	static String sqlCmdLimitMssql = "SELECT TOP 1 * FROM ";
	
	static Vector<String> nomesTabelas = new Vector<String>();
	static Vector<String> textotex = new Vector<String>();
	static Vector<String> vgetandset = new Vector<String>();
	static Vector<String> vnamespace = new Vector<String>();

	static Vector<Estrutura> propriedadesTabelaOrigem = new Vector<Estrutura>();
	
	static Vector<Estrutura> propriedadesTabelaDestino = new Vector<Estrutura>();
	
	static int numeroRegistros = 0, tamanhoTabela = 0, volumeTabela = 0;

	public static void main(String[] args) throws Exception {

		String[][] nativestoobjecttypes = { { "char", "char" }, { "unsigned char", "unsigned char" },
				{ "signed char", "signed char" }, { "int", "int" }, { "unsigned int", "unsigned int" },
				{ "signed int", "signed int" }, { "short int", "short int" }, { "unsigned short", "unsigned short" },
				{ "signed short int", "signed short int" }, { "long int", "long int" },
				{ "signed long int", "signed long int" }, { "double", "double" }, { "long double", "long double" },
				{ "wchar_t", "wchar_t" } };
		// String[] ars = { "ALW", "AX", "BP", "CP", "CR", "CRW", "CTW",
		// "DIVERSO", "FIW", "FRW", "IM", "IMW", "JBPM",
		// "OP", "RPW", "RT", "SG", "SGW", "TEMP", "TT" };
		/* Pegando amostras */
		String[] ars = { "geafin" };

		for (int arsk = 0; arsk < ars.length; arsk++) {
			System.out.println("Arquivo em: " + "D:\\TJDFT\\SERSIA\\GEAFIN\\modulo_" + ars[arsk]
					+ "\\geafininfo_modulo_" + ars[arsk] + ".txt");
			System.out.println("Arquivo em: " + "D:\\TJDFT\\SERSIA\\GEAFIN\\modulo_" + ars[arsk]
					+ "\\amostragemDados_modulo_" + ars[arsk] + ".txt");

			try {
				arqasiwinfo = new FileWriter(
						"D:\\TJDFT\\SERSIA\\GEAFIN\\modulo_" + ars[arsk] + "\\geafininfo_modulo_" + ars[arsk] + ".txt",
						false);
				gravarArq = new PrintWriter(arqasiwinfo);

				amostras = new FileWriter("D:\\TJDFT\\SERSIA\\GEAFIN\\modulo_" + ars[arsk] + "\\amostragemDados_modulo_"
						+ ars[arsk] + ".txt", false);
				gravarArqAmostra = new PrintWriter(amostras);

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			gerarNamespaceline(ars[arsk]);

			Connection conGeafin = getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://BDU16:3306/geafin", "geafin",
					"geafin");

			Connection conAsiw = getConnection("com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://tjsw378:1433;databaseName=asiw", "sersia_asi", "111093eb0b");

			Statement stmt = conAsiw.createStatement();

			PreparedStatement prepStmt = conAsiw.prepareStatement(sqlCmdLimit);

			DatabaseMetaData mtdt = conAsiw.getMetaData();

			ResultSet rs = mtdt.getTables(conAsiw.getCatalog(), "%", "%", null);

			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();
			if (numCols > 0)
				System.out.println("Metadados — Número de Colunas : " + numCols);
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

			capturarDados(rs, numCols, stmt, prepStmt, gravarArq, gravarArqAmostra, ars[arsk]);

			printtotex();

			System.out.println("Finalizado com sucesso o módulo " + ars[arsk]);
			arqasiwinfo.close();
			amostras.close();
			conAsiw.close();
		}
	}

	private static void capturarDados(ResultSet rs, int numCols, Statement stmt, PreparedStatement prepStmt,
			PrintWriter gravarArq, PrintWriter gravarArqAmostra, String pmodulo) {
		try {

			System.out.println("");
			System.out.println("TABELAS E VOLUME DE DADOS");
			try {
				int cont = 0;
				while (rs.next()) {
					// if (rs.getString("TABLE_SCHEM").equals("geafin")) { // &&
					// rs.getString("TABLE_NAME").startsWith(pmodulo)){
					nomesTabelas.add(rs.getString("TABLE_NAME"));
					System.out.println(rs.getString("TABLE_NAME") + " número = " + ++cont);
					// }
				}
				//
				numeroRegistros = 0;
				Iterator<String> i = nomesTabelas.iterator();
				while (i.hasNext()) {
					String tabname = i.next();
					ResultSet rst = stmt.executeQuery(sqlCmdCount + tabname);

					if (rst.next()) {
						int numrecords = Integer.parseInt(rst.getString(1));
						if (numrecords > 0) {
							numeroRegistros = numrecords;
							// System.out.println(tabname + " num. Regs = " +
							// numrecords);
						}
					}
					/*
					 * ler um registro da tabela para extrair dados de tamanho
					 */
					ResultSet rstab = stmt.executeQuery(sqlCmdLimit + tabname + " LIMIT 1");
					ResultSetMetaData rsmd = rstab.getMetaData();
					int tamanhoEstrutura = 0;
					int numColumns = rsmd.getColumnCount();

					// System.out.println("class " + tabname + "{");
					vgetandset.clear();

					gravarArq.println("class  " + tabname + " {");
					for (int j = 1; j < numColumns + 1; j++) {
						String columnName = rsmd.getColumnName(j);
						String columnType = rsmd.getColumnTypeName(j);
						int tamanhoColuna = rsmd.getColumnDisplaySize(j);

						int precisao = rsmd.getPrecision(j); // se precisar,
																// mostrar.
						int escala = rsmd.getScale(j); // se precisar, mostrar
						String nomeclasse = rsmd.getColumnClassName(j); // uso
																		// futuro

						tamanhoEstrutura += tamanhoColuna;
						gravarArq.println("//Classe java: " + convertPrimitiveType(nomeclasse) + ", Precisao = "
								+ precisao + ", Escala = " + escala);
						gravarArq.println("  private " + convertPrimitiveType(columnType) + " " + columnName + "["
								+ tamanhoColuna + "];");
						vgetandset.add("public: void set" + columnName + "(const " + columnType + "& var) { "
								+ columnName + " = var; } " + columnType + " get" + columnName + "() {  return "
								+ columnName + "; }");
						// System.out.println(" "+ columnType +" " +columnName +
						// "[" + tamanhoColuna +"];");
					}
					Iterator<String> iv = vgetandset.iterator();
					while (iv.hasNext()) {
						gravarArq.println(iv.next());
					}
					gravarArq.println("}; /* Fim: " + tabname + " tamanho = " + tamanhoEstrutura + " bytes */");
					// System.out.println("}; /* Fim: "+ tabname + " tamanho = "
					// + tamanhoEstrutura + " bytes */");
					tamanhoTabela = tamanhoEstrutura;
					volumeTabela = tamanhoEstrutura * numeroRegistros;

					if (numeroRegistros > 0)
						textotex.add(tabname + " & " + tamanhoTabela + " & " + numeroRegistros + " & " + volumeTabela
								+ "\\\\ \\\\hline");
					// System.out.println(tabname + " & " + tamanhoTabela + " &
					// " + numeroRegistros + " & " + volumeTabela + "\\\\
					// \\\\hline");
					try {
						while (rstab.next()) {
							int k = 1;
							// System.out.print("Conteúdo: |");
							String sconteudo = "Conteúdo: |";
							while (k <= numColumns) {
								sconteudo += (rstab.getString(k++) + "|");
								// System.out.print(rstab.getString(k++)+"|");
							}
							gravarArqAmostra.println(tabname + " " + sconteudo);
							// System.out.println(tabname + " " + sconteudo);
						}
					} catch (SQLException e) {
						System.out.println("Erro controlado" + e.getMessage() + " Tabela " + tabname + " conteúdo = "
								+ rstab.toString().toString());
						e.printStackTrace();
					}
					// System.out.println("");
				}
			} catch (SQLException e) {
				System.out.println("Erro não esperado " + e.getMessage());
				e.printStackTrace();
			} finally {
				//
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		//
		gravarArq.println("} //Fim namespace_" + pmodulo);
	}

	public static void printtotex() {

		Iterator<String> i = textotex.iterator();
		while (i.hasNext()) {
			String linhatabtex = i.next();
			gravarArq.println(linhatabtex);
			// System.out.println(linhatabtex);
		}
	}

	public static void gerarNamespaceline(String pmodulo) {
		vnamespace.add("namespace  namespace" + pmodulo + "{");
		gravarArq.println("namespace  namespace" + pmodulo + "{");

	}

	public static String convertPrimitiveType(String className) {
		// por enquanto:
		return className;

	}

	public static Connection getConnection(String classForname, String url, String usuario, String senha)
			throws Exception {
		Class.forName(classForname);

		return DriverManager.getConnection(url, usuario, senha); // "sersia_asi",
																	// "111093eb0b");
	}

	public static Vector<String> capturarTabelasModulo(String pmodulo, String alvo) {

		Connection conn = null;
		try {

			pmodulo = pmodulo.replaceAll("Módulo ", "");
			if (alvo.equals("destino")) {

				conn = getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://BDU16:3306/geafin", "geafin", "geafin");
			} else {
				conn = getConnection("com.microsoft.sqlserver.jdbc.SQLServerDriver",
						"jdbc:sqlserver://tjsw378:1433;databaseName=asiw", "sersia_asi", "111093eb0b");
			}

			Statement stmt = conn.createStatement();

			PreparedStatement prepStmt = conn.prepareStatement(sqlCmdLimit);

			DatabaseMetaData mtdt = conn.getMetaData();

			ResultSet rs = mtdt.getTables(conn.getCatalog(), "%", "%", null);

			ResultSetMetaData rsmd = rs.getMetaData();

			int numCols = rsmd.getColumnCount();

			if (numCols > 0)
				System.out.println("Metadados — Número de Colunas : " + numCols);
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

			int cont = 0;
			while (rs.next()) {
				if (rs.getString("TABLE_NAME").startsWith(pmodulo)) {
					nomesTabelas.add(rs.getString("TABLE_NAME"));
					System.out.println(rs.getString("TABLE_NAME") + " número = " + ++cont);
				} 
				
				if(pmodulo.equals("geafin")){
					nomesTabelas.add(rs.getString("TABLE_NAME"));
					System.out.println(rs.getString("TABLE_NAME") + " número = " + ++cont);
				}
			}

		} catch (Exception e) {
			System.out.println("Erro não esperado " + e.getMessage());
			e.printStackTrace();
		}

		return nomesTabelas;
	}
	
	public static Vector<Estrutura> obterColunasTiposTabela(String tabela, String alvo) {
		
        Vector<Estrutura> vestru = new Vector<Estrutura>();
        String cmdsqlorigemoudestino = "";
		Connection conn = null;
		try {

			if (alvo.equals("destino")) {
				cmdsqlorigemoudestino = sqlCmdLimit + tabela + " LIMIT 1";
				conn = getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://BDU16:3306/geafin", "geafin", "geafin");
			} else {
				cmdsqlorigemoudestino = sqlCmdLimitMssql + tabela;
				conn = getConnection("com.microsoft.sqlserver.jdbc.SQLServerDriver",
						"jdbc:sqlserver://tjsw378:1433;databaseName=asiw", "sersia_asi", "111093eb0b");
			}

			/*
			 * ler um registro da tabela para extrair dados de tamanho
			 */
			
			Statement stmt = conn.createStatement();

			PreparedStatement prepStmt = conn.prepareStatement(sqlCmdLimit);

			DatabaseMetaData mtdt = conn.getMetaData();

			ResultSet rstab = stmt.executeQuery(cmdsqlorigemoudestino);
			
			ResultSetMetaData rsmd = rstab.getMetaData();
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
			System.out.println("Erro não esperado " + e.getMessage());
			e.printStackTrace();
		}
		
		return vestru;
		
	}
	

}
