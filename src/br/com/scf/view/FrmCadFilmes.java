package br.com.scf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.scf.model.Filme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import br.com.scf.model.Usuario;

public class FrmCadFilmes extends JFrame {
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtFilme;
	private boolean edicao = false;
	private JButton btnNovo, btnSalvar, btnEditar, btnExcluir, btnCancelar,btnConsultar;
	private JButton btnVoltar;
	private void resetar() {
		txtCodigo.setText("");
		txtFilme.setText("");
	}
	
	private void estadoBotoes(int acao) {
		// 0 inicial
		// 1 novo
		// 2 pesquisar
		if (acao == 0) {
			btnNovo.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnConsultar.setEnabled(true);
		} else if (acao == 1) {
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnNovo.setEnabled(false);
			btnSalvar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnConsultar.setEnabled(false);
		} else if (acao == 2) {
			btnNovo.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
			btnCancelar.setEnabled(false);
			btnConsultar.setEnabled(true);
		}
	
	}

	public FrmCadFilmes(int id, String descricao) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
		setTitle("Cadastro e Filme");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
	
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
																						
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panel.setBounds(0, 0, 594, 375);
		contentPane.add(panel);
		panel.setLayout(null);
	
		JLabel label = new JLabel("Codigo");
		label.setForeground(Color.WHITE);
		label.setBackground(new Color(255, 255, 0));
		label.setBounds(10, 61, 66, 14);
		panel.add(label);
		label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
																	
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBorder(null);
		txtCodigo.setBounds(71, 58, 96, 23);
		panel.add(txtCodigo);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBackground(Color.WHITE);
						
		JLabel lblNome_do_Filme = new JLabel("Nome do Filme");
		lblNome_do_Filme.setForeground(Color.WHITE);
		lblNome_do_Filme.setBounds(10, 176, 121, 23);
		panel.add(lblNome_do_Filme);
		lblNome_do_Filme.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
																																						
		txtFilme = new JTextField();
		txtFilme.setBounds(126, 177, 340, 23);
		panel.add(txtFilme);
		txtFilme.setEditable(false);
		txtFilme.setColumns(10);
		txtFilme.setBackground(Color.WHITE);
																																				
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(488, 61, 96, 23);
		panel.add(btnEditar);
		
		btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					edicao = true;
					txtFilme.setEditable(true);
					txtFilme.requestFocus();
					txtFilme.selectAll();
					estadoBotoes(1);
				}
			}
		);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(488, 177, 96, 23);
		panel.add(btnCancelar);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {																																	public void actionPerformed(ActionEvent arg0) {
	
		int r = JOptionPane.showConfirmDialog(null, "Deseja Cancelar?",
				"CANCELAR", JOptionPane.YES_OPTION);
		
				if (r == JOptionPane.YES_OPTION) {
		
					resetar();
					txtFilme.setEditable(false);
					estadoBotoes(0);
				}
		
			}
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(488, 116, 96, 23);
		panel.add(btnSalvar);
		
		btnSalvar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
			if (txtFilme.getText().trim().equals("")) {
				txtFilme.setBackground(Color.RED);
				JOptionPane
						.showMessageDialog(null, "Preencha o Nome do Filme");
				txtFilme.setBackground(Color.WHITE);
				txtFilme.requestFocus();
		
			} else {
		
				Filme f = new Filme();
				
				if (edicao==true) {
					f.setId(Integer.parseInt(txtCodigo.getText()));
					f.setNome_filme(txtFilme.getText());
					f.editar();
					edicao = false;
				} else {
					f.setNome_filme(txtFilme.getText());
					f.inserir();
				}
																
					estadoBotoes(0);
					resetar();
					txtFilme.setEditable(false);
					
				}
			}
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(488, 225, 96, 23);
		panel.add(btnExcluir);
		
		btnExcluir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
		int r = JOptionPane.showConfirmDialog(null,
				"Deseja realmente Excluir?", "EXCLUS�O",
						JOptionPane.YES_OPTION);
	
				if (r == JOptionPane.YES_OPTION) {
	
					Filme f = new Filme();
					f.setId(Integer.parseInt(txtCodigo.getText()));
	
					f.excluir();
	
					resetar();
					txtFilme.setEditable(false);
					estadoBotoes(0);
				}
	
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNovo = new JButton("Novo ");
		btnNovo.setBounds(488, 11, 96, 23);
		panel.add(btnNovo);										
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetar();
				txtFilme.setEditable(true);
				txtFilme.requestFocus();
				estadoBotoes(1);
		
			}
		});
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(488, 275, 96, 23);
		panel.add(btnConsultar);
		
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btnVoltar1 = new JButton("Voltar");
		btnVoltar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //destroi o objeto atual
				FrmPrincipal p = new FrmPrincipal(0,"",0);
				p.setVisible(true);
			}
		});
		btnVoltar1.setBounds(488, 324, 96, 21);
		panel.add(btnVoltar1);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FrmPesqFilme f = new FrmPesqFilme();
				f.setVisible(true);
				
		}
		});																											
		JLabel label1 = new JLabel("");
		label1.setBounds(0, 0, 600, 378);
		contentPane.add(label1);
		label1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("cadastro.png")));

		estadoBotoes(0);
		/* testar se tem codigo */
		if (id > 0) {
			txtCodigo.setText("" + id);
			txtFilme.setText(descricao);
			estadoBotoes(2);
		} else {
			System.out.println("Fabricante vazio");
			}
		}
	}
