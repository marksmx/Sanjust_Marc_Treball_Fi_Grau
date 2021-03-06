package VISTA;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import DADES.SQLProductes;

public class CrearProducte {
	
	/** IMPORTACI� I DECLARACI� DELS CONTROLADORS DE CONSULTES SQL QUE S'UTILITZEN EN AQUESTA PANTALLA */
	
	SQLProductes sqlP = new SQLProductes();
	
	
	/** DECLARACI� GLOBAL D'ALGUNES VARIABLES */

	private String curText="";
	private double iva = 0;
	private double total = 0;
	private boolean producte=false;
	private boolean servei=false;
	private int idCom = sqlP.contarProductes() + sqlP.contarServeis() + 1;

	
	/** DECLARACI� DEL JFRAME, DE BOTONS I DE CAMPS DE TEXT */

	JFrame frame;
	JButton button = new JButton("CREAR PRODUCTE");
	JButton button_1 = new JButton("CREAR SERVEI");
	JButton button_2 = new JButton("TORNAR A INICI");
	JButton button_3 = new JButton("CANCEL�LAR OPERACI�");
	JButton button_4 = new JButton("CREAR");
	JButton button_5 = new JButton("CALCULAR PREU");

	
	/** FUNCI� PER A CRIDAR A LA FUNCI� QUE COMPOSA ELS ELEMENTS DE LA PANTALLA I A LES FUNCIONS DE CONSTRUCCI� DE LA TAULA */

	public CrearProducte() throws ClassNotFoundException, SQLException {
		
		initialize();
		
	}

	
	/** FUNCI� ON ES CONSTRUEIXEN TOTS ELS ELEMENTS DE LA PANTALLA I S'APLIQUEN LES CONSULTES SQL, ENTRE ALTRES FUNCIONS */

	private void initialize() throws ClassNotFoundException, SQLException {

		
		/** Aqu� es declaren les caracter�stiques que tindr� la base de la pantalla (resoluci�, color, mida fixe) */
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 819, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		/** Inici del conjunt d'elements que composen la cap�alera */
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 813, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(227, 11, 452, 60);
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
		lblNewLabel.setIcon(new ImageIcon(CrearProducte.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);

		/** Fi del conjunt d'elements que composen la cap�alera */
		
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setText("");
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(311, 135, 181, 35);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Element");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(349, 93, 103, 42);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEnabled(false);
		textPane_2.setText("");
		textPane_2.setForeground(Color.BLACK);
		textPane_2.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_2.setFocusTraversalKeysEnabled(false);
		textPane_2.setFocusCycleRoot(false);
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setBounds(555, 135, 190, 35);
		frame.getContentPane().add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Feq��ncia");
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_3.setFocusable(false);
		textPane_3.setFocusTraversalKeysEnabled(false);
		textPane_3.setFocusCycleRoot(false);
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.BLACK);
		textPane_3.setBounds(593, 93, 123, 42);
		frame.getContentPane().add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("Unitats");
		textPane_4.setForeground(Color.WHITE);
		textPane_4.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_4.setFocusable(false);
		textPane_4.setFocusTraversalKeysEnabled(false);
		textPane_4.setFocusCycleRoot(false);
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.BLACK);
		textPane_4.setBounds(366, 201, 84, 42);
		frame.getContentPane().add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEnabled(false);
		textPane_5.setText("");
		textPane_5.setForeground(Color.BLACK);
		textPane_5.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_5.setFocusTraversalKeysEnabled(false);
		textPane_5.setFocusCycleRoot(false);
		textPane_5.setBackground(Color.LIGHT_GRAY);
		textPane_5.setBounds(376, 243, 55, 35);
		frame.getContentPane().add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setEnabled(true);
		textPane_6.setEditable(false);
		textPane_6.setText(Integer.toString(idCom));
		textPane_6.setForeground(Color.BLACK);
		textPane_6.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_6.setFocusTraversalKeysEnabled(false);
		textPane_6.setFocusCycleRoot(false);
		textPane_6.setBackground(Color.WHITE);
		textPane_6.setBounds(630, 243, 66, 35);
		frame.getContentPane().add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("ID Element");
		textPane_7.setForeground(Color.WHITE);
		textPane_7.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_7.setFocusable(false);
		textPane_7.setFocusTraversalKeysEnabled(false);
		textPane_7.setFocusCycleRoot(false);
		textPane_7.setEditable(false);
		textPane_7.setBackground(Color.BLACK);
		textPane_7.setBounds(593, 201, 127, 42);
		frame.getContentPane().add(textPane_7);
	
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setEnabled(false);
		textPane_8.setText("");
		textPane_8.setForeground(Color.BLACK);
		textPane_8.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_8.setFocusTraversalKeysEnabled(false);
		textPane_8.setFocusCycleRoot(false);
		textPane_8.setBackground(Color.LIGHT_GRAY);
		textPane_8.setBounds(349, 352, 90, 35);
		frame.getContentPane().add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("Base");
		textPane_9.setForeground(Color.WHITE);
		textPane_9.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_9.setFocusable(false);
		textPane_9.setFocusTraversalKeysEnabled(false);
		textPane_9.setFocusCycleRoot(false);
		textPane_9.setEditable(false);
		textPane_9.setBackground(Color.BLACK);
		textPane_9.setBounds(364, 309, 66, 42);
		frame.getContentPane().add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("IVA");
		textPane_10.setForeground(Color.WHITE);
		textPane_10.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_10.setFocusable(false);
		textPane_10.setFocusTraversalKeysEnabled(false);
		textPane_10.setFocusCycleRoot(false);
		textPane_10.setEditable(false);
		textPane_10.setBackground(Color.BLACK);
		textPane_10.setBounds(503, 309, 47, 42);
		frame.getContentPane().add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setEnabled(false);
		textPane_11.setText("");
		textPane_11.setForeground(Color.BLACK);
		textPane_11.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_11.setFocusTraversalKeysEnabled(false);
		textPane_11.setFocusCycleRoot(false);
		textPane_11.setBackground(Color.LIGHT_GRAY);
		textPane_11.setBounds(480, 352, 90, 35);
		frame.getContentPane().add(textPane_11);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setEnabled(false);
		textPane_12.setText("");
		textPane_12.setForeground(Color.BLACK);
		textPane_12.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_12.setFocusTraversalKeysEnabled(false);
		textPane_12.setFocusCycleRoot(false);
		textPane_12.setBackground(Color.LIGHT_GRAY);
		textPane_12.setBounds(606, 352, 90, 35);
		frame.getContentPane().add(textPane_12);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText("Total");
		textPane_13.setForeground(Color.WHITE);
		textPane_13.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_13.setFocusable(false);
		textPane_13.setFocusTraversalKeysEnabled(false);
		textPane_13.setFocusCycleRoot(false);
		textPane_13.setEditable(false);
		textPane_13.setBackground(Color.BLACK);
		textPane_13.setBounds(621, 309, 55, 42);
		frame.getContentPane().add(textPane_13);

		
		/** Inici conjunt codi botons */
		/** Inici bot� "Tornar a Inici" */
		
		button_2.setForeground(Color.BLACK);
		button_2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_2.setBackground(Color.BLACK);
				button_2.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_2.setBackground(Color.WHITE);
				button_2.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					/** Al clicar el bot�, tornarem a la pantalla principal */
					
					Principal frm = new Principal();
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_2.setFocusPainted(false);
		button_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(50, 352, 146, 35);
		frame.getContentPane().add(button_2);
		
		/** Fi bot� "Tornar a Inici" */

		
		/** Inici bot� "Cancel�lar Operaci�" */

		button_3.setForeground(Color.BLACK);
		button_3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_3.setBackground(Color.BLACK);
				button_3.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_3.setBackground(Color.WHITE);
				button_3.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_3.setVisible(false);
		button_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** Al clicar el bot�, es reinicia la pantalla */
					
					CrearProducte frm = new CrearProducte();
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("HelveticaNeue", Font.BOLD, 10));
		button_3.setFocusPainted(false);
		button_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(50, 309, 146, 35);
		frame.getContentPane().add(button_3);
		
		/** Fi bot� "Cancel�lar Operaci�" */


		/** Inici bot� "Crear Producte" */

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
				
				/** Al clicar el bot�, es crear� un producte amb les caracter�stiques que li hem introdu�t */
				
				textPane.setBackground(Color.WHITE);
				textPane.setEnabled(true);
				textPane_5.setBackground(Color.WHITE);
				textPane_5.setEnabled(true);
				textPane_8.setBackground(Color.WHITE);
				textPane_8.setEnabled(true);
				textPane_11.setBackground(Color.WHITE);
				textPane_12.setBackground(Color.WHITE);
				button_3.setVisible(true);
				button_1.setVisible(false);
				button.setVisible(false);
				button_4.setVisible(true);
				button_5.setVisible(true);
				producte = true;
				servei = false;
				
			}
			
		});
		
		button.setBounds(50, 135, 146, 55);
		frame.getContentPane().add(button);
		
		/** Fi bot� "Crear Producte" */

		
		/** Inici bot� "Crear Servei" */

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
		button_1.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** Al clicar el bot�, es crear� un servei amb les caracter�stiques que li hem introdu�t */

				textPane.setBackground(Color.WHITE);
				textPane.setEnabled(true);
				textPane_2.setBackground(Color.WHITE);
				textPane_2.setEnabled(true);
				textPane_5.setBackground(Color.WHITE);
				textPane_5.setEnabled(true);
				textPane_8.setBackground(Color.WHITE);
				textPane_8.setEnabled(true);
				textPane_11.setBackground(Color.WHITE);
				textPane_12.setBackground(Color.WHITE);
				button_3.setVisible(true);
				button_1.setVisible(false);
				button.setVisible(false);
				button_4.setVisible(true);
				button_5.setVisible(true);
				producte = false;
				servei = true;
				
			}
			
		});
		
		button_1.setBounds(50, 223, 146, 55);
		frame.getContentPane().add(button_1);
		
		/** Fi bot� "Crear Servei" */

		
		/** Inici bot� "Crear" */

		button_4.setForeground(Color.BLACK);
		button_4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_4.setBackground(Color.BLACK);
				button_4.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_4.setBackground(Color.WHITE);
				button_4.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** Al clicar el bot�, es comprovar�n els camps i es crear� el producte o servei en q�esti� */
				
				if(producte==true && servei==false) {
					
					if(textPane.getText().length()==0 || textPane_5.getText().length()==0 || textPane_8.getText().length()==0 || textPane_11.getText().length()==0 || textPane_12.getText().length()==0 || textPane_6.getText().length()==0) {
						
						JOptionPane.showMessageDialog(null, "Has deixat camps sense omplir!","ERROR",JOptionPane.ERROR_MESSAGE);
						
					}else {
						
						try {
							
							Integer.parseInt(textPane_5.getText());
							sqlP.crearProducte(textPane.getText(), textPane_5.getText(), textPane_8.getText(), textPane_11.getText(), textPane_12.getText(),Integer.toString(idCom));
							JOptionPane.showMessageDialog(null, "S'ha afegit correctament","",JOptionPane.INFORMATION_MESSAGE);
							CrearProducte frm = new CrearProducte();
							frm.frame.setVisible(true);
							frame.setVisible(false);
							
						} catch (Exception e2) {
							
							JOptionPane.showMessageDialog(null, "Les unitats han de ser n�meros!","ERROR",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}
					
				}
				
				if(producte==false && servei==true) {
					
					if(textPane.getText().length()==0  || textPane_2.getText().length()==0 || textPane_5.getText().length()==0 || textPane_8.getText().length()==0 || textPane_11.getText().length()==0 || textPane_12.getText().length()==0 || textPane_6.getText().length()==0) {
						
						JOptionPane.showMessageDialog(null, "Has deixat camps sense omplir!","ERROR",JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						try {
							
							Integer.parseInt(textPane_5.getText());
							sqlP.crearServei(textPane.getText(), textPane_2.getText(), textPane_5.getText(), textPane_8.getText(), textPane_11.getText(), textPane_12.getText(), Integer.toString(idCom));
							JOptionPane.showMessageDialog(null, "S'ha afegit correctament","",JOptionPane.INFORMATION_MESSAGE);
							CrearProducte frm = new CrearProducte();
							frm.frame.setVisible(true);
							frame.setVisible(false);
							
						} catch (Exception e2) {
							
							JOptionPane.showMessageDialog(null, "Les unitats han de ser n�meros!","ERROR",JOptionPane.ERROR_MESSAGE);
							
						}
						
					}
					
				}
				
			}
			
		});
		
		button_4.setVisible(false);
		button_4.setForeground(Color.BLACK);
		button_4.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_4.setFocusPainted(false);
		button_4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(50, 188, 146, 55);
		frame.getContentPane().add(button_4);
		
		/** Fi bot� "Crear" */

		
		/** Inici bot� "Calcular Preu" */

		button_5.setForeground(Color.BLACK);
		button_5.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_5.setBackground(Color.BLACK);
				button_5.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_5.setBackground(Color.WHITE);
				button_5.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_5.setVisible(false);
		button_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** Al clicar el bot�, es calcular� el iva i el preu final a partir del preu base */
				
				if(textPane_8.getText().equals("")) {
					
				} else {
					
					try {
						
						Integer.parseInt(textPane_8.getText());
						iva = Integer.parseInt(textPane_8.getText()) * 0.21;
						textPane_11.setText(Double.toString(iva));
						total = Integer.parseInt(textPane_8.getText()) + iva;
						textPane_12.setText(Double.toString(total));
						
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "Has d'introdu�r un n�mero!","ERROR",JOptionPane.ERROR_MESSAGE);
						
					}
				
				}
				
			}
			
		});
		
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("HelveticaNeue", Font.BOLD, 10));
		button_5.setFocusPainted(false);
		button_5.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(224, 352, 115, 35);
		frame.getContentPane().add(button_5);
		
		/** Fi bot� "Calcular Preu" */
		/** Fi conjunt codi botons */
		
	}
	
}
