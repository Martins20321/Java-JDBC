package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Principal {
	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;// Preparar uma consulta
		ResultSet rst = null;// Resultado da consulta("Select")

		try {
			con = DB.getConnection();

			st = con.createStatement();

			rst = st.executeQuery("select * from department"); // Resultado da Consulta

			while (rst.next()) {
				int id = rst.getInt("Id");
				String name = rst.getString("Name");
				System.out.println(id + ", " + name);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally { // Fechando as operacoes
			DB.closeResult(rst);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}