package br.com.scf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

public class FrmSplash extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSplash frame = new FrmSplash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void carregar(){
		
		new Thread(){
			
		 public void run(){   	
			 
			  for (int i = 1; i <= 100; i++) {
				
				  try {
					Thread.sleep(10);
					progressBar.setValue(i);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				  
			  }  
			  
			  //fechando a tela splash e chamando o objeto (tela de login)
			  dispose();
			  FrmLogin l = new FrmLogin();
			  l.setVisible(true);
			 
		 }
					
		}.start();	
	}
	public FrmSplash() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("icon.png")));
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLACK);
		progressBar.setBackground(Color.WHITE);
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 169, 400, 31);
		contentPane.add(progressBar);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("splash.jpg")));
		label.setBounds(0, 0, 400, 200);
		contentPane.add(label);
		carregar();
	}
}
