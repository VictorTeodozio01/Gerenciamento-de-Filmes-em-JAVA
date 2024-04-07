package br.com.scf.view;


import java.awt.Toolkit;
import java.util.Date;




import javax.swing.JFrame;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class FrmPrincipal extends JFrame {
	private JMenuItem mntmRealizarVenda;
	private final JButton btnUsuario = new JButton("USUARIOS");
    
	public FrmPrincipal(int nivel, String usuario,int id) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
		setTitle("Sistema de Cadastro de Filmes  " + usuario + " | " + new Date());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		//setExtendedState(MAXIMIZED_BOTH);
		getContentPane().setLayout(null);
		JPanel ContentPane = new JPanel();
		ContentPane.setBounds(0, -15, 592, 388);
		getContentPane().add(ContentPane);
		ContentPane.setLayout(null);
		JButton btnCadFilmes = new JButton("CADASTRAR FILMES");
		btnCadFilmes.setBounds(52, 280, 497, 70);
		ContentPane.add(btnCadFilmes);
		btnUsuario.setBounds(52, 45, 497, 70);
		ContentPane.add(btnUsuario);
		JButton btnLocalizarFilmes = new JButton("CONSULTAR FILMES");
		btnLocalizarFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FrmPesqFilme u = new FrmPesqFilme();
				u.setVisible(true);
			}
		});
		btnLocalizarFilmes.setBounds(52, 152, 497, 70);
		ContentPane.add(btnLocalizarFilmes);
		
		JLabel lblLabelPrincipal = new JLabel("");
		lblLabelPrincipal.setBounds(0, 0, 610, 400);
		ContentPane.add(lblLabelPrincipal);
		lblLabelPrincipal.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("cadastro.png")));
		
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FrmUsuario u = new FrmUsuario(0, "", "", 0, "");
				u.setVisible(true);
			}
		});
		btnCadFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FrmCadFilmes u = new FrmCadFilmes(0, "");
				u.setVisible(true);
			}
		});

	}
}
