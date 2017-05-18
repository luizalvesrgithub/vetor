package br.com.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		System.out.println("Executando...");
		try {

			String url = "jdbc:postgresql://localhost:5432/welementosdb";
			String usuario = "postgres";
			String senha = "boeraremapiba";

			Class.forName("org.postgresql.Driver");

			Connection con;

			con = DriverManager.getConnection(url, usuario, senha);

			System.out.println("Conexão realizada com sucesso.");

			Statement stm = con.createStatement();
			
			
			stm.executeUpdate("INSERT INTO tipo_projeto VALUES (1,'00010','VIABILIDAD','NBR6118 NBR6120',0,5,1212324565)");
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/*id integer NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
idempresa integer NOT NULL,
codigo character(1) NOT NULL,
sigla character(1) NOT NULL,
nome character(1) NOT NULL,
endereco character(1) NOT NULL,
inscricaofederal character(1) NOT NULL,
inscricaoestadual character(1) NOT NULL,
inscricaomunicipal character(1) NOT NULL,
telefone character(1) NOT NULL,
fax character(1) NOT NULL,
codigoposta character(1) NOT NULL,
email character(1) NOT NULL,
paginahttp character(1) NOT NULL,
senha character(1) NOT NULL,
CONSTRAINT cliente_pkey PRIMARY KEY (id)*/
