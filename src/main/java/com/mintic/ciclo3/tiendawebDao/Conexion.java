package com.mintic.ciclo3.tiendawebDao;

import java.sql.*;

public class Conexion {

	static String bd = "tiendaweb";
	static String user = "admin";
	static String password = "admin";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true";
	
	private Connection connection = null;
	
	//Constructor
	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error iniciando conexión :" + ex);
		}
	}
	
	public Connection GetConnection() {
		return this.connection;
	}
	
	public void CloseConnection() {
		if (this.connection != null) {		
			try {
				this.connection.close();
				this.connection = null;				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				System.out.println("Error iniciando conexión :" + ex);
			} 
		}
	}
	
}
