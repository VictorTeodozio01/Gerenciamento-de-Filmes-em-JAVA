package br.com.scf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Usuario {

	private int id;
	private String nome, cpf;
	private int nivel;
	private String senha;
	private Date dataCad;

	// conectar ao banco de dados

	Connection conn = ConnectionFactory.conectar();

	// escrever e executar comandos sql
	PreparedStatement robo = null;

	// guardar/armazenar os dados que vem do banco
	ResultSet rs = null;

	
	public void carregar(JTable nameTable) {
		String selecionar = "SELECT * FROM usuario ORDER BY id DESC";
				

		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
        
		String[] nameColumns = {"Cod.","Nome do Usuário","CPF","Nivel","Senha","Data"};
		
		//add as colunas no modelo
		aModel.setColumnIdentifiers(nameColumns);
		
		try {
			robo = conn.prepareStatement(selecionar);
			rs = robo.executeQuery();
			
			//transformar o vetor(ResultSet) em metadados
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			
		
			
			while(rs.next()){
				
			   Object[] objeto = new Object[numColumns];	
				
			  for(int i=0;i<numColumns;i++){	
				  objeto[i] = rs.getObject(i+1);
			  }
				aModel.addRow(objeto);
			}
			
			nameTable.setModel(aModel);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void pesquisar(JTable nameTable, String nameUser) {
		String selecionar = "SELECT *FROM usuario WHERE nome LIKE '%"
				+ nameUser + "%';";

		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
        
		String[] nameColumns = {"Cod.","Nome do Usuário","CPF","Nivel","Senha","Data"};
		
		//add as colunas no modelo
		aModel.setColumnIdentifiers(nameColumns);
		
		try {
			robo = conn.prepareStatement(selecionar);
			rs = robo.executeQuery();
			
			//transformar o vetor(ResultSet) em metadados
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			
			aModel.setNumRows(0); //limpar as linhas
			
			while(rs.next()){
				
			   Object[] objeto = new Object[numColumns];	
				
			  for(int i=0;i<numColumns;i++){	
				  objeto[i] = rs.getObject(i+1);
			  }
				aModel.addRow(objeto);
			}
			
			nameTable.setModel(aModel);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void excluir() {
		String deletar = "DELETE FROM usuario WHERE id=" + getId() + ";";

		try {
			robo = conn.prepareStatement(deletar);
			robo.execute();
			JOptionPane
					.showMessageDialog(null, "Usuário Deletado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editar() {

		String editar = "UPDATE usuario SET nome='" + getNome() + "',cpf='"
				+ getCpf() + "',nivel=" + getNivel() + ",senha='" + getSenha()
				+ "' WHERE id=" + getId() + ";";

		try {
			robo = conn.prepareStatement(editar);
			robo.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Usuário Atualizado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* metodo para salvar o usuario */
	public void salvar() {

		String inserir = "INSERT INTO usuario (nome,cpf,nivel,senha) VALUES ('"
				+ getNome() + "','" + getCpf() + "'," + getNivel() + ",'"
				+ getSenha() + "');";

		try {
			robo = conn.prepareStatement(inserir);
			robo.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Usuario já cadastrado com este CPF!");
			e.printStackTrace();
		}

	}

	/* metodo para logar no sistema */
	public boolean logar() {
		boolean existe = false;

		String verificar = "SELECT * FROM usuario WHERE cpf='" + this.cpf
				+ "' AND senha='" + this.senha + "';";

		try {
			robo = conn.prepareStatement(verificar);
			rs = robo.executeQuery(); // executando a query

			if (rs.next()) {
				existe = true; // usuario existe

				this.nome = rs.getString("nome");
				this.nivel = rs.getInt("nivel");
				this.id = rs.getInt("id");

				JOptionPane.showMessageDialog(null, "Bem vindo(a), \n"
						+ getNome());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

}
