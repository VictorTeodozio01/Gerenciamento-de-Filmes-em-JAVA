package br.com.scf.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.scf.model.Usuario;

public class FrmLogin extends JFrame {
	private JPanel contentPane;
	private JPasswordField txtSenha;
    private JFormattedTextField txtCpf;
	public FrmLogin() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
		setTitle("Sistema de Cadastro de Filmes - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.YELLOW);
		lblCpf.setFont(new Font("Arial", Font.BOLD, 20));
		lblCpf.setBounds(10, 69, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.YELLOW);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 20));
		lblSenha.setBounds(0, 155, 71, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setEchoChar('*');
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(62, 152, 222, 25);
		contentPane.add(txtSenha);
		
		/*Criar mascara para o CPF*/
		MaskFormatter mask_cpf = null;
		
		try {
			mask_cpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtCpf = new JFormattedTextField(mask_cpf);
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setBounds(62, 66, 222, 25);
		contentPane.add(txtCpf);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLogar.setBounds(40, 234, 222, 30);
		contentPane.add(btnLogar);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("login.jpg")));
		label.setBounds(0, 0, 300, 300);
		contentPane.add(label);
		
		//Acao do botao btnLogar
		getRootPane().setDefaultButton(btnLogar); //ativar o Enter
		btnLogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtSenha.getText().trim().equals("")) {
					
					txtSenha.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Preencha a Senha!");
					txtSenha.setBackground(Color.WHITE);
					txtSenha.setText("");
					txtSenha.requestFocus();
					
				} else {
                    //acessar a camada model 
					Usuario user = new Usuario();
					user.setCpf(txtCpf.getText());
					user.setSenha(txtSenha.getText());
					boolean status = user.logar();
					
					if (status==true) {
						//entrar na tela principal
						dispose(); //destroi o objeto atual
						FrmPrincipal p = new FrmPrincipal(user.getNivel(), user.getNome(),user.getId());
						p.setVisible(true);
						
					} else {
						txtSenha.setBackground(Color.RED);
						txtCpf.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null,"CPF/Senha Incorretos!");
						txtSenha.setBackground(Color.WHITE);
						txtCpf.setBackground(Color.WHITE);
						txtSenha.setText("");
						txtCpf.setText("");
						txtCpf.requestFocus(); //colocar o cursor dentro
					}
					
					
				}
				
			}
		});
		
	}
}
