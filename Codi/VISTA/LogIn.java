package VISTA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import java.io.File;
import java.io.IOException;

import DADES.*;

public class LogIn {

	
	/** IMPORTACI� I DECLARACI� DELS CONTROLADORS DE CONSULTES SQL QUE S'UTILITZEN EN AQUESTA PANTALLA */

	SQLUsuari sqlU = new SQLUsuari();

	
	/** DECLARACI� DEL JFRAME, DE BOTONS, DE CAMPS DE TEXT I ALTRES */
	
	public JFrame frame;
	JButton btnEntrar = new JButton("Entrar");

	private JTextField txtLogIn;
	JTextField textField;
	private JPasswordField passwordField;
	private JTextPane txtpnUsuari;
	private JTextPane textPane;
	static LogIn window;


	/** EN AQUEST MAIN SER� ON S'INICIAR� L'EXECUCI� DEL PROGRAMA */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					window = new LogIn();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
	}
	
	
	/** FUNCI� PER A CRIDAR A LA FUNCI� QUE COMPOSA ELS ELEMENTS DE LA PANTALLA I A LES FUNCIONS DE CONSTRUCCI� DE LA TAULA */
	
	public LogIn() throws SQLException {
		
		initialize();

		if(sqlU.exist() == false) {
			
			CrearUsuari frm = new CrearUsuari();
			frm.frame.setVisible(true);
			frame.setVisible(false);
			
		} else {
			
			frame.setVisible(true);
			
		}
		
	}

	
	/** FUNCI� ON ES CONSTRUEIXEN TOTS ELS ELEMENTS DE LA PANTALLA I S'APLIQUEN LES CONSULTES SQL, ENTRE ALTRES FUNCIONS */
	
	private void initialize() {
		
		
		/** Aqu� es declaren les caracter�stiques que tindr� la base de la pantalla (resoluci�, color, mida fixe) */
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 472, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		/** Inici del conjunt d'elements que composen la cap�alera */
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 466, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtLogIn = new JTextField();
		txtLogIn.setBounds(75, 11, 381, 60);
		txtLogIn.setForeground(Color.BLACK);
		panel.add(txtLogIn);
		txtLogIn.setBorder(null);
		txtLogIn.setAutoscrolls(false);
		txtLogIn.setFocusTraversalKeysEnabled(false);
		txtLogIn.setFocusable(false);
		txtLogIn.setBackground(Color.WHITE);
		txtLogIn.setSelectionColor(Color.GRAY);
		txtLogIn.setEditable(false);
		txtLogIn.setFont(new Font("HelveticaNeue", Font.PLAIN, 50));
		txtLogIn.setText("OnTime Agency");
		txtLogIn.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LogIn.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		/** Fi del conjunt d'elements que composen la cap�alera */
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setBounds(24, 131, 416, 107);
		frame.getContentPane().add(layeredPane);
		
		txtpnUsuari = new JTextPane();
		txtpnUsuari.setBackground(Color.BLACK);
		txtpnUsuari.setForeground(Color.WHITE);
		txtpnUsuari.setFocusable(false);
		txtpnUsuari.setFocusTraversalKeysEnabled(false);
		txtpnUsuari.setFocusCycleRoot(false);
		txtpnUsuari.setEditable(false);
		txtpnUsuari.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		txtpnUsuari.setText("DNI");
		txtpnUsuari.setBounds(47, 3, 50, 27);
		layeredPane.add(txtpnUsuari);
		
		textField = new JTextField();
		textField.setFont(new Font("HelveticaNeue", Font.PLAIN, 15));
		textField.setBounds(10, 41, 124, 35);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.WHITE);
		textPane.setText("Contrasenya");
		textPane.setFont(new Font("HelveticaNeue", Font.PLAIN, 21));
		textPane.setFocusable(false);
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setEditable(false);
		textPane.setBounds(279, 3, 137, 35);
		layeredPane.add(textPane);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("HelveticaNeue", Font.PLAIN, 15));
		passwordField.setBounds(279, 41, 127, 35);
		layeredPane.add(passwordField);
		
		
		/** Inici codi bot� "Entratr" */
		
		btnEntrar.setForeground(Color.BLACK);
		btnEntrar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnEntrar.setBackground(Color.BLACK);
				btnEntrar.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnEntrar.setBackground(Color.WHITE);
				btnEntrar.setForeground(Color.BLACK);
				
			}
			
		});
		
		/** Fi codi bot� "Entratr" */

		
		btnEntrar.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnEntrar.setFocusPainted(false);
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		btnEntrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(sqlU.iniciarSessi�(textField.getText(), passwordField.getText()) == true) {
						
						try {
							
							Principal frm = new Principal();
							frm.frame.setVisible(true);
							frame.setVisible(false);
							
						} catch (ClassNotFoundException e1) {
							
							e1.printStackTrace();
							
						}
						
					} else {
						
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnEntrar.setBounds(177, 237, 117, 53);
		frame.getContentPane().add(btnEntrar);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Identifica't");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(177, 93, 133, 27);
		frame.getContentPane().add(textPane_1);
		
	}
	
}
