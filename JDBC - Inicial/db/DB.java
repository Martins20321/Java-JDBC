package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	private static ResultSet rs = null;
	private static Statement st = null;
	
	// metodo para carregar o properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {// Recebendo o arquivo
			Properties props = new Properties();
			props.load(fs);// load = ler o arquivo
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	// metodo para conectar com o banco de dados
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();// Pegou as propriedades do banco
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	// metodo para fechar conexao
	public static void closeConnection() {
		try {
		if(conn != null) { 
			conn.close();
		}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
		if(rs != null) {
			rs.close();
		}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	public static void closeStatement(Statement st) {
		try {
		if(st != null) {
			st.close();
		}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
