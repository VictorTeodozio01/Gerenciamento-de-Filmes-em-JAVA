package br.com.scf.view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.scf.model.Filme;
import br.com.scf.model.Usuario;

import javax.swing.border.EtchedBorder;

public class FrmPesqFilme extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable table;

	
	public FrmPesqFilme() {
		setTitle("Consulta de Filmes");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
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
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "    CONSULTA DE FILMES", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 43, 574, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Nome do Filme");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(10, 21, 179, 23);
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 144, 600, 164);
		contentPane.add(scrollPane);
		
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("cadastro.png")));
		label1.setBounds(0, 0, 600, 378);
		contentPane.add(label1);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBackground(new Color(255, 255, 255));
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				int linha = table.getSelectedRow();
				
				String cod = table.getValueAt(linha, 0).toString();
				int codigo = Integer.parseInt(cod);
				String nome = table.getValueAt(linha, 1).toString();
				
				
				FrmCadFilmes f = new FrmCadFilmes(codigo, nome);
				f.setVisible(true);
				dispose();
				
				
			}
		});
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				int linha = table.getSelectedRow();
				txtNome.setText(table.getValueAt(linha, 1).toString());
				btnSelecionar.setEnabled(true);
				
			}
		});
		table.setBorder(null);
		table.setBackground(Color.WHITE);

        Filme f = new Filme();
		f.listar(table); //estou carregando os dados
		
		scrollPane.setViewportView(table);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Filme f = new Filme();
				f.localizar(table, txtNome.getText());
				
			}
		});
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);
		txtNome.setBounds(10, 47, 244, 25);
		panel.add(txtNome);
		btnSelecionar.setEnabled(false);
		btnSelecionar.setOpaque(false);
		btnSelecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelecionar.setBorder(null);
		btnSelecionar.setBounds(264, 48, 96, 23);
		panel.add(btnSelecionar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(240, 240, 240));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose(); // destroir o objeto atual
				FrmCadFilmes p = new FrmCadFilmes (0, "");
				p.setVisible(true);

			}
		});
		btnCadastrar.setBounds(370, 48, 91, 23);
		panel.add(btnCadastrar);
		
		JButton btnNewButtonVoltar = new JButton("Voltar");
		btnNewButtonVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //destroi o objeto atual
				FrmPrincipal p = new FrmPrincipal(0,"",0);
				p.setVisible(true);
			}
		});
		btnNewButtonVoltar.setBounds(473, 49, 91, 21);
		panel.add(btnNewButtonVoltar);
		
		
		
		
	}
}
