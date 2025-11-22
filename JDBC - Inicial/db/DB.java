package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection con = null;
	
	
	public static Connection getConnection() { //gerar uma conexão com o banco de daods
		if(con == null) {
			try {
			Properties props = loadProperties(); //chamando o método
			String url = props.getProperty("dburl");//url dedinida no db.properties
			con = DriverManager.getConnection(url, props);//Obter conexão com o Banco de Dados
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			}
		return con;
	}
	
	public static void closeConnection() { //fechando a conexão
		if(con != null) {
			try {
			con.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	//Metotos auxiliares para fechar um objeto
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeResult(ResultSet st) {
		if(st != null) {
			try {
				st.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
