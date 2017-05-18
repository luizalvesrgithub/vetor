package br.com.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ConnectionFactory {

	public static Connection getConnection(String classForname, String url, String usuario, String senha) throws Exception {

		try {
			Class.forName(classForname);
			return DriverManager.getConnection(url, usuario, senha);
		} catch (Exception e) {
			
		}
		return null;

	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://BDU16:3306/geafin", "geafin", "geafin");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Vector<String> executeGetCrossReference(Connection con, 
			                                              String catalogo1, 
			                                              String schema1,
			                                              String tabela1, 
			                                              String catalogo2, 
			                                              String schema2, 
			                                              String tabela2, 
			                                              Vector<String> vrelations) {
		
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
	
	
	public class JDBCUtil {

	    String className, URL, user, password;
	    Connection connection;

	    public JDBCUtil(String className, String URL, String user, String password) {
	        this.className = className;
	        this.URL = URL;
	        this.user = user;
	        this.password = password;
	        this.connection = null;
	    }

	    public void getConnection() {

	        //Load the driver class
	        try {
	            Class.forName(className);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Unable to load the class. Terminating the program");
	            System.exit(-1);
	        }

	        //get the connection
	        try {
	            connection = DriverManager.getConnection(URL, user, password);
	        } catch (SQLException ex) {
	            System.out.println("Error getting connection: " + ex.getMessage());
	            System.exit(-1);
	        } catch (Exception ex) {
	            System.out.println("Error: " + ex.getMessage());
	            System.exit(-1);
	        }

	        if(connection != null)
	        {
	            System.out.println("Connected Successfully!");
	        }

	    }

	    public void executeQuery()
	    {
	        System.out.println("Enter your SQL query here: ");
	        Scanner scanner = new Scanner(System.in);
	        String query = scanner.nextLine();

	        ResultSet resultSet = null;
	        try
	        {
	            //executing query
	            Statement stmt = connection.createStatement();
	            resultSet = stmt.executeQuery(query);


	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnsNumber = metaData.getColumnCount();


	            //Printing the results
	            while(resultSet.next())
	            {

	                for(int i = 1; i <= columnsNumber; i++)
	                {
	                    System.out.printf("%-25s", (resultSet.getObject(i) != null)?resultSet.getObject(i).toString(): null );
	                }
	            }
	        }
	        catch (SQLException ex)
	        {
	            System.out.println("Exception while executing statement. Terminating program... " + ex.getMessage());
	        }
	        catch (Exception ex)
	        {
	            System.out.println("General exception while executing query. Terminating the program..." + ex.getMessage());
	        }

	    }

	    public void listAllTables()
	    {
	        DatabaseMetaData databaseMetaData= null;

	        //Fetching Database Metadata from connection
	        try {
	            databaseMetaData = connection.getMetaData();


	            //Print TABLE_TYPE "TABLE"
	            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
	            System.out.println("Printing TABLE_TYPE \"TABLE\" ");
	            System.out.println("----------------------------------");
	            while(resultSet.next())
	            {
	                //Print
	                System.out.println(resultSet.getString("TABLE_NAME"));
	            }

	            //Print TABLE_TYPE "SYSTEM TABLE"
	            resultSet = databaseMetaData.getTables(null, null, null, new String[]{"SYSTEM TABLE"});
	            System.out.println("Printing TABLE_TYPE \"SYSTEM TABLE\" ");
	            System.out.println("----------------------------------");
	            while(resultSet.next())
	            {
	                //Print
	                System.out.println(resultSet.getString("TABLE_NAME"));
	            }

	            //Print TABLE_TYPE "VIEW"
	            resultSet = databaseMetaData.getTables(null, null, null, new String[]{"VIEW"});
	            System.out.println("Printing TABLE_TYPE \"VIEW\" ");
	            System.out.println("----------------------------------");
	            while(resultSet.next())
	            {
	                //Print
	                System.out.println(resultSet.getString("TABLE_NAME"));
	            }


	        }
	        catch (SQLException ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }

	    }

	    public ArrayList<String> getTablesList()
	    {
	        DatabaseMetaData databaseMetaData= null;
	        ArrayList<String> tableList = new ArrayList<>();

	        //Fetching Database Metadata from connection
	        try {
	            databaseMetaData = connection.getMetaData();
	            ResultSet resultSet = databaseMetaData.getTables(null, null, null, null);
	            while (resultSet.next()) {
	                tableList.add(resultSet.getString("TABLE_NAME"));
	            }
	        }
	        catch (SQLException ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }

	        return tableList;
	    }

	    public void showTableMetaData(String tableName)
	    {
	        try {
	            DatabaseMetaData databaseMetaData = connection.getMetaData();

	            //get columns
	            ResultSet columns = databaseMetaData.getColumns(null,null, tableName, null);
	            while(columns.next())
	            {
	                String columnName = columns.getString("COLUMN_NAME");
	                String datatype = columns.getString("DATA_TYPE");
	                String columnsize = columns.getString("COLUMN_SIZE");
	                String decimaldigits = columns.getString("DECIMAL_DIGITS");
	                String isNullable = columns.getString("IS_NULLABLE");
	                String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");

	                //Printing results
	                System.out.println(columnName + "---" + datatype + "---" + columnsize + "---" + decimaldigits + "---" + isNullable + "---" + is_autoIncrment);

	            }

	            //GetPrimarykeys
	            ResultSet PK = databaseMetaData.getPrimaryKeys(null,null, tableName);
	            System.out.println("------------PRIMARY KEYS-------------");
	            while(PK.next())
	            {
	                System.out.println(PK.getString("COLUMN_NAME") + "===" + PK.getString("PK_NAME"));
	            }

	            //Get Foreign Keys
	            ResultSet FK = databaseMetaData.getImportedKeys(null, null, tableName);
	            System.out.println("------------FOREIGN KEYS-------------");
	            while(FK.next())
	            {
	                System.out.println(FK.getString("PKTABLE_NAME") + "---" + FK.getString("PKCOLUMN_NAME") + "===" + FK.getString("FKTABLE_NAME") + "---" + FK.getString("FKCOLUMN_NAME"));
	            }

	        }
	        catch (SQLException ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }
	        catch (Exception ex)
	        {
	            System.out.println("Error while fetching metadata. Terminating program.. " + ex.getMessage());
	            System.exit(-1);
	        }
	    }

	}

}
