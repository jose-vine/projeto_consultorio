package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
	
	private static final String URL = "jdbc:mysql://localhost/lista05";
	private static final String USUARIO = "root";
	private static final String SENHA = "Reike987456321#";
	private static Connection conexao;
	
	private ConexaoSingleton() {
		
	}
	
	public static Connection obterConexao() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				System.out.println("Banco de dados conectado!");
			} catch (SQLException e) {
				System.out.println("Banco de dados não conectado!");
				e.printStackTrace();
			}
		}
		
		return conexao;
	}
	
}
