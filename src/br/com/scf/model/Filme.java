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

public class Filme {

	private int id;
	private String nome_filme;
	private String Situacao;
	private Date dataCad;

	Connection conn = ConnectionFactory.conectar();
	PreparedStatement robo = null;
	ResultSet rs = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_filme() {
		return nome_filme;
	}

	public void setNome_filme(String nome_filme) {
		this.nome_filme = nome_filme;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}
	
	public String getSituacao() {
		return Situacao;
	}

	public void setSituacao(String Situacao) {
		Situacao = Situacao;
	}

	public void localizar(JTable nameTable, String nameFabric) {
		String[] nameColumns = { "Codigo", "Nome do Filme", "Data" };
		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
		aModel.setColumnIdentifiers(nameColumns);
		String select = "SELECT id,nome_filme,dat_cad FROM filme WHERE nome_fime LIKE '%"+getNome_filme()+ "%';";
		try {
			robo = conn.prepareStatement(select);
			rs = robo.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numColunas = rsmd.getColumnCount();

			aModel.setNumRows(0);
			
			while (rs.next()) {
				Object[] objeto = new Object[numColunas];

				for (int i = 0; i < numColunas; i++) {
					objeto[i] = rs.getObject(i + 1);
				}
				aModel.addRow(objeto);
			}
			nameTable.setModel(aModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listar(JTable nameTable) {

		String[] nameColumns = { "Codigo", "Nome Do Filme", "Data" };

		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();

		aModel.setColumnIdentifiers(nameColumns);

		String select = "SELECT id,nome_filme,dat_cad FROM filme ORDER BY id DESC;";

		try {
			robo = conn.prepareStatement(select);
			rs = robo.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numColunas = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objeto = new Object[numColunas];

				for (int i = 0; i < numColunas; i++) {

					objeto[i] = rs.getObject(i + 1);

				}
				aModel.addRow(objeto);

			}

			nameTable.setModel(aModel);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserir() {
		String inserir = "INSERT INTO filme (nome_filme) VALUES ('"
				+ getNome_filme() + "');";

		try {
			robo = conn.prepareStatement(inserir);
			robo.executeUpdate();
			JOptionPane
					.showMessageDialog(null, "Filme Salvo com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editar() {
		String editar = "UPDATE filme SET nome_filme='" + getNome_filme()
				+ "' WHERE id=" + getId() + ";";

		try {
			robo = conn.prepareStatement(editar);
			robo.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Filme Atualizado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir() {
		String deletar = "DELETE FROM filme WHERE id=" + getId() + ";";

		try {
			robo = conn.prepareStatement(deletar);
			robo.execute();
			JOptionPane.showMessageDialog(null,
					"Filme Excluido com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void situacao() {
		String editar = "UPDATE filme SET situa��o='" + getSituacao()
				+ "' WHERE id=" + getId() + ";";

		try {
			robo = conn.prepareStatement(editar);
			robo.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Filme Alugado ");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
