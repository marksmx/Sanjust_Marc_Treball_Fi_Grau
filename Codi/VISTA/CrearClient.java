package VISTA;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import DADES.SQLClients;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CrearClient {
	
	
	/** IMPORTACI� I DECLARACI� DELS CONTROLADORS DE CONSULTES SQL QUE S'UTILITZEN EN AQUESTA PANTALLA */
	
	SQLClients sqlCl = new SQLClients();
	
	
	/** DECLARACI� GLOBAL D'ALGUNES VARIABLES */
	
	private boolean fet=false;

	
	/** DECLARACI� DEL JFRAME, DE BOTONS I DE CAMPS DE TEXT */

	JFrame frame;
	JButton button = new JButton("AFEGIR CLIENT");
	JButton button_1 = new JButton("CANCEL�LAR OPERACI�");
	

	/** FUNCI� PER A CRIDAR A LA FUNCI� QUE COMPOSA ELS ELEMENTS DE LA PANTALLA I A LES FUNCIONS DE CONSTRUCCI� DE LA TAULA */

	public CrearClient() throws SQLException {
		
		initialize();
		
	}
	
	
	/** FUNCI� ON ES CONSTRUEIXEN TOTS ELS ELEMENTS DE LA PANTALLA I S'APLIQUEN LES CONSULTES SQL, ENTRE ALTRES FUNCIONS */

	private void initialize() throws SQLException {

		
		/** Aqu� es declaren les caracter�stiques que tindr� la base de la pantalla (resoluci�, color, mida fixe) */
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 473, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		/** Inici del conjunt d'elements que composen la cap�alera */

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 688, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(81, 11, 387, 60);
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
		lblNewLabel.setIcon(new ImageIcon(CrearClient.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		/** Fi del conjunt d'elements que composen la cap�alera */

		
		JTextPane textPane = new JTextPane();
		textPane.setText("");
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("HelveticaNeue", Font.PLAIN, 24));
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(44, 135, 159, 35);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Empresa");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(71, 93, 101, 42);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("");
		textPane_2.setForeground(Color.BLACK);
		textPane_2.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_2.setFocusTraversalKeysEnabled(false);
		textPane_2.setFocusCycleRoot(false);
		textPane_2.setBackground(Color.WHITE);
		textPane_2.setBounds(268, 135, 159, 35);
		frame.getContentPane().add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Concepte");
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_3.setFocusable(false);
		textPane_3.setFocusTraversalKeysEnabled(false);
		textPane_3.setFocusCycleRoot(false);
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.BLACK);
		textPane_3.setBounds(293, 93, 103, 42);
		frame.getContentPane().add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("NIF");
		textPane_4.setForeground(Color.WHITE);
		textPane_4.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_4.setFocusable(false);
		textPane_4.setFocusTraversalKeysEnabled(false);
		textPane_4.setFocusCycleRoot(false);
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.BLACK);
		textPane_4.setBounds(105, 201, 47, 42);
		frame.getContentPane().add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("");
		textPane_5.setForeground(Color.BLACK);
		textPane_5.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_5.setFocusTraversalKeysEnabled(false);
		textPane_5.setFocusCycleRoot(false);
		textPane_5.setBackground(Color.WHITE);
		textPane_5.setBounds(44, 243, 159, 35);
		frame.getContentPane().add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setEditable(false);
		textPane_6.setText(Integer.toString(sqlCl.recompteClients()));
		textPane_6.setForeground(Color.BLACK);
		textPane_6.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_6.setFocusTraversalKeysEnabled(false);
		textPane_6.setFocusCycleRoot(false);
		textPane_6.setBackground(Color.WHITE);
		textPane_6.setBounds(268, 243, 159, 35);
		frame.getContentPane().add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("ID Client");
		textPane_7.setForeground(Color.WHITE);
		textPane_7.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_7.setFocusable(false);
		textPane_7.setFocusTraversalKeysEnabled(false);
		textPane_7.setFocusCycleRoot(false);
		textPane_7.setEditable(false);
		textPane_7.setBackground(Color.BLACK);
		textPane_7.setBounds(306, 201, 101, 42);
		frame.getContentPane().add(textPane_7);
		
		
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("");
		textPane_8.setForeground(Color.BLACK);
		textPane_8.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_8.setFocusTraversalKeysEnabled(false);
		textPane_8.setFocusCycleRoot(false);
		textPane_8.setBackground(Color.WHITE);
		textPane_8.setBounds(71, 344, 325, 35);
		frame.getContentPane().add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("Mail");
		textPane_9.setForeground(Color.WHITE);
		textPane_9.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_9.setFocusable(false);
		textPane_9.setFocusTraversalKeysEnabled(false);
		textPane_9.setFocusCycleRoot(false);
		textPane_9.setEditable(false);
		textPane_9.setBackground(Color.BLACK);
		textPane_9.setBounds(209, 299, 47, 42);
		frame.getContentPane().add(textPane_9);
		
		
		/** Inici conjunt de codi dels bot�ns */
		/** Inici del bot� "Cancel�lar Operaci�" */
		
		button_1.setForeground(Color.BLACK);
		button_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_1.setBackground(Color.BLACK);
				button_1.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_1.setBackground(Color.WHITE);
				button_1.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_1.setFocusPainted(false);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("HelveticaNeue", Font.BOLD, 10));
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el bot�, tornarem a la pantalla principal */
					
					Principal frm = new Principal();
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
			
			}
			
		});
		
		button_1.setBounds(246, 431, 150, 42);
		frame.getContentPane().add(button_1);
		
		/** Fi del bot� "Cancel�lar Operaci�" */

		
		/** Inici del bot� "Afegir Client" */
		
		button.setForeground(Color.BLACK);
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button.setBackground(Color.BLACK);
				button.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button.setBackground(Color.WHITE);
				button.setForeground(Color.BLACK);
				
			}
			
		});
		
		button.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button.setBackground(Color.WHITE);
		button.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** En clicar el bot�, comprovar� els camps i afegir� un nou client */
				
				if(textPane.getText().length()==0 || textPane_2.getText().length()==0 || textPane_5.getText().length()==0 || textPane_8.getText().length()==0) {
					
					JOptionPane.showMessageDialog(null, "Has deixat camps sense omplir!","ERROR",JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					if(textPane_5.getText().length()>9) {
						
						JOptionPane.showMessageDialog(null, "El NIF �s massa llarg","ERROR",JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						sqlCl.crearClient(textPane.getText(), textPane_2.getText(), textPane_5.getText(), textPane_6.getText(), textPane_8.getText());
						
						fet = true;
						
					}
					
				}
				
				if(fet == true) {
					
					try {
						
						Principal frm = new Principal();
						frm.frame.setVisible(true);
						frame.setVisible(false);
						
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
						
					}
					
				} 
				
				fet = false;
				
			}
			
		});
		
		button.setBounds(71, 431, 150, 42);
		frame.getContentPane().add(button);
	
		/** Fi del bot� "Afegir Client" */
		/** Fi conjunt de codi dels bot�ns */
		
	}

}
