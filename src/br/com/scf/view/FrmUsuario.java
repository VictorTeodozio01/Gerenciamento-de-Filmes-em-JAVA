package br.com.scf.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.com.scf.model.Usuario;

public class FrmUsuario extends JFrame {

	private JPanel contentPane;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private JPasswordField txtRepetir;
	private JFormattedTextField txtCpf;
	private JComboBox cbNivel;
	private boolean edicao = false;

	private JButton btnNovo, btnSalvar, btnEditar, btnExcluir, btnCancelar,btnLocalizar;
	private JButton btnVoltar;

	private void limpar() {
		txtCodigo.setText("");
		txtCpf.setText("");
		txtNome.setText("");
		txtSenha.setText("");
		txtRepetir.setText("");
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
			btnLocalizar.setEnabled(true);
		} else if (acao == 1) {
			btnNovo.setEnabled(false);
			btnSalvar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnLocalizar.setEnabled(false);
		} else if (acao == 2) {
			btnNovo.setEnabled(true);
			btnSalvar.setEnabled(false);
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
			btnCancelar.setEnabled(false);
			btnLocalizar.setEnabled(true);
		}

	}
	private void estadoCampos(boolean ativo) {
		if (ativo == true) {
			txtNome.setEditable(true);
			txtNome.requestFocus();
			txtSenha.setEditable(true);
			txtRepetir.setEditable(true);
			txtCpf.setEditable(true);
			cbNivel.setEditable(true);
		} else {
			txtNome.setEditable(false);
			txtSenha.setEditable(false);
			txtRepetir.setEditable(false);
			txtCpf.setEditable(false);
			cbNivel.setEditable(false);
		}

	}

	public FrmUsuario(int id, String nome, String cpf, int nivel, String senha) {
		setTitle("Controle de Usu\u00E1rios");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
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
		panel.setBounds(0, 0, 644, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodigo.setBounds(10, 11, 62, 23);
		panel.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(Color.WHITE);
		txtCodigo.setBounds(10, 33, 70, 25);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNomeDeUsuario = new JLabel("Nome de Usuario");
		lblNomeDeUsuario.setForeground(Color.WHITE);
		lblNomeDeUsuario.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNomeDeUsuario.setBounds(10, 68, 179, 23);
		panel.add(lblNomeDeUsuario);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);
		txtNome.setBounds(10, 91, 401, 25);
		panel.add(txtNome);

		JLabel lblNivelDeAcesso = new JLabel("Nivel de Acesso");
		lblNivelDeAcesso.setForeground(Color.WHITE);
		lblNivelDeAcesso.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNivelDeAcesso.setBounds(244, 11, 179, 23);
		panel.add(lblNivelDeAcesso);

		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(new String[] { "Operador","Administrador" }));
		cbNivel.setBounds(244, 34, 179, 25);
		panel.add(cbNivel);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblCpf.setBounds(10, 127, 179, 23);
		panel.add(lblCpf);

		MaskFormatter m_cpf = null;

		try {
			m_cpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtCpf = new JFormattedTextField(m_cpf);
		txtCpf.setBounds(10, 150, 199, 25);
		panel.add(txtCpf);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblSenha.setBounds(10, 186, 179, 23);
		panel.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 208, 168, 25);
		panel.add(txtSenha);

		JLabel lblRepetirSenha = new JLabel("Repetir Senha");
		lblRepetirSenha.setForeground(Color.WHITE);
		lblRepetirSenha.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblRepetirSenha.setBounds(232, 186, 179, 23);
		panel.add(lblRepetirSenha);

		txtRepetir = new JPasswordField();
		txtRepetir.setBounds(231, 208, 179, 25);
		panel.add(txtRepetir);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(487, 11, 97, 33);
		panel.add(btnNovo);
		btnNovo.setBorder(null);
		btnNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(487, 64, 97, 33);
		panel.add(btnSalvar);
		btnSalvar.setBorder(null);
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(487, 123, 97, 33);
		panel.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edicao = true; // clicou no botao editar
				estadoBotoes(1);
				estadoCampos(true);
				txtNome.requestFocus();
				txtNome.selectAll();

			}
		});
		btnEditar.setBorder(null);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(487, 182, 97, 33);
		panel.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir?", "EXCLUIR",JOptionPane.YES_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					// acessar a camada model
					Usuario user = new Usuario();
					user.setId(Integer.parseInt(txtCodigo.getText()));
					user.excluir();
					limpar();
					estadoBotoes(0);
				}
			}
		});
		btnExcluir.setBorder(null);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(487, 226, 97, 33);
		panel.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null,"Deseja realmente cancelar?", "CANCELAR",JOptionPane.YES_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					limpar();
					estadoBotoes(0);
					estadoCampos(false);
				}
			}
		});
		btnCancelar.setBorder(null);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(487, 277, 97, 33);
		panel.add(btnLocalizar);
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); // destroir o objeto atual
				FrmPesqUsuario p = new FrmPesqUsuario();
				p.setVisible(true);
			}
		});
		btnLocalizar.setBorder(null);
		btnLocalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar = new JButton("VOLTAR AO PRINCIPAL");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //destroi o objeto atual
				FrmPrincipal p = new FrmPrincipal(nivel,nome,nivel);
				p.setVisible(true);
			}
		});
		btnVoltar.setBounds(10, 276, 168, 23);
		panel.add(btnVoltar);
									
		btnSalvar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (txtNome.getText().trim().equals("")) {
	
				txtNome.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Preencha o Nome!");
				txtNome.setBackground(Color.WHITE);
				txtNome.requestFocus();
	
			} else if (txtSenha.getText().trim().equals("")) {
	
				txtSenha.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Preencha a Senha!");
				txtSenha.setBackground(Color.WHITE);
				txtSenha.requestFocus();
	
			} else if (txtRepetir.getText().trim().equals("")) {
	
				txtRepetir.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Repita a Senha!");
				txtRepetir.setBackground(Color.WHITE);
				txtRepetir.requestFocus();
	
			} else {
				// todos os campos estao preenchidos
	
				// acessando a camada model
				Usuario user = new Usuario();
	
				user.setNome(txtNome.getText());
				user.setCpf(txtCpf.getText());
				if (txtSenha.getText().equals(txtRepetir.getText())) {
					user.setSenha(txtSenha.getText());
	
					if (cbNivel.getSelectedItem() == "Operador") {
						user.setNivel(0);
					} else {
						user.setNivel(1);
					}
	
					if (edicao == false) {
						// inserir novo
						user.salvar();
					} else {
						// atualizar
						user.setId(Integer.parseInt(txtCodigo.getText()));
						user.editar();
					}
	
					estadoCampos(false);
					estadoBotoes(0);
					limpar();
	
				} else {
					txtRepetir.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null,"Senhas nao conferem!");
					txtRepetir.setBackground(Color.WHITE);
					txtRepetir.setText("");
					txtRepetir.requestFocus();
				}
	
			  }
	
			}
		});
		btnNovo.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				estadoCampos(true);
				estadoBotoes(1);
				limpar();
				txtNome.requestFocus();
	
			}
		});
	
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("cadastro.png")));
		label.setBounds(0, 0, 650, 378);
		contentPane.add(label);
		
		estadoCampos(false);
		estadoBotoes(0);
		
		if (id > 0) {
		    estadoBotoes(2);
			txtCodigo.setText(id + "");
			txtNome.setText(nome);
			txtCpf.setText(cpf);
			txtSenha.setText(senha);
			
			if (nivel == 1) {
				cbNivel.setSelectedItem("Administrador");
			} else {
				cbNivel.setSelectedItem("Operador");
				}
			
			} else {
				System.out.println("Usuario vazio");
			}
		}
}
