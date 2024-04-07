package br.com.scf.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.scf.model.Usuario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;

public class FrmPesqUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable table;

	
	public FrmPesqUsuario() {
		setTitle("SISTEMA DE CADASTRO DE FILMES  - Pesquisa de Usuarios");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\System Develops\\Java\\2017.12.10 (Trabalho)\\imgs\\icon.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 378);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "    CONSULTA DE USUARIO     ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(0, 34, 584, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Nome de Usuario");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(10, 23, 129, 23);
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 125, 594, 181);
		contentPane.add(scrollPane);
		
		JButton btnSekecionar = new JButton("Selecionar");
		btnSekecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int linha = table.getSelectedRow();
				
				String cod = table.getValueAt(linha, 0).toString();
				int codigo = Integer.parseInt(cod);
				String nome = table.getValueAt(linha, 1).toString();
				String cpf = table.getValueAt(linha, 2).toString();
				String nivel = table.getValueAt(linha, 3).toString();
				int n = Integer.parseInt(nivel);
				String senha = table.getValueAt(linha, 4).toString();
				
	
				
				FrmUsuario u = new FrmUsuario(codigo,nome,cpf,n,senha);
				u.setVisible(true);
				dispose();
				
				
			}
		});
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				int linha = table.getSelectedRow();
				txtNome.setText(table.getValueAt(linha, 1).toString());
				btnSekecionar.setEnabled(true);
				
			}
		});
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		Usuario u = new Usuario();
		u.carregar(table); //estou carregando os dados
		
		scrollPane.setViewportView(table);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Usuario user = new Usuario();
				user.pesquisar(table, txtNome.getText());
				
			}
		});
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);
		txtNome.setBounds(10, 47, 332, 25);
		panel.add(txtNome);
		
		
		btnSekecionar.setEnabled(false);
		btnSekecionar.setOpaque(false);
		btnSekecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSekecionar.setBorder(null);
		btnSekecionar.setBounds(360, 48, 96, 23);
		panel.add(btnSekecionar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(483, 48, 91, 23);
		panel.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose(); // destroir o objeto atual
				FrmUsuario p = new FrmUsuario(0, "", "", 0, "");
				p.setVisible(true);

			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\System Develops\\Java\\2017.12.10 (Trabalho)\\imgs\\cadastro.png"));
		lblNewLabel.setBounds(0, 0, 600, 378);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
}
