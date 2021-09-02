package br.com.alura.carteira.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private String url = "jdbc:mysql://localhost:3306/carteira";
	private String usuario = "docker";
	private String senha = "docker";
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(url, usuario, senha);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
			throw new RuntimeException(e);
		}
	}

}
