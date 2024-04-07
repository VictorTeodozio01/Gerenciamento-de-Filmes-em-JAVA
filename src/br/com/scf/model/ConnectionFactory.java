package br.com.scf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	public static Connection conectar() {
		Connection conn = null;
		String banco = "sistema_de_cadastro_de_filmes";
		String caminho = "jdbc:mysql://localhost/" + banco;
		String usuario = "root";
		String senha = "123456";

		// carregar o driver do mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection(caminho, usuario, senha);
				System.out.println("Conectado ...");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Conexão Falhou!");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException erro) {
			JOptionPane.showMessageDialog(null, "Driver não carregado!");
			erro.printStackTrace();
		}

		return conn;
	}

}
