package VISTA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DADES.SQLClients;
import DADES.SQLComandes;
import DADES.SQLProductes;
import MODEL.ClientCl;
import MODEL.ComandaCl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class Client {

	/** IMPORTACIÓ I DECLARACIÓ DELS CONTROLADORS DE CONSULTES SQL QUE S'UTILITZEN EN AQUESTA PANTALLA */
	
	SQLComandes sqlC = new SQLComandes();
	SQLClients sqlCl = new SQLClients();
	SQLProductes sqlP = new SQLProductes();
	
	
	/** DECLARACIÓ GLOBAL D'ALGUNES VARIABLES */

	LocalTime data2S  = java.time.LocalTime.now();
	private String idEmpresa;
	private JTable table;
	private JScrollPane scrollPane; 
	
	
	/** DECLARACIÓ DEL JFRAME, DE BOTONS I DE CAMPS DE TEXT */

	JFrame frame;
	JButton button = new JButton("INICIAR PROJECTE");
	JButton button2 = new JButton("FINALITZAR PROJECTE");
	JButton btnNewButton_1 = new JButton("ELIMINAR PROJECTE");
	JButton btnNewButton_2 = new JButton("ANULAR PROJECTE");
	JButton btnNewButton_3 = new JButton("RECUPERAR PROJECTE");
	JButton button_4 = new JButton("MARCAR COM A PAGAT");
	JButton button_5 = new JButton("FILTRAR COMANDA");
	JButton button4 = new JButton("CALCULAR COST TOTAL");
	JButton button_6 = new JButton("VEURE DESCRIPCIÓ");
	private JTextField textField;

	
	/** FUNCIÓ PER A CRIDAR A LA FUNCIÓ QUE COMPOSA ELS ELEMENTS DE LA PANTALLA I A LES FUNCIONS DE CONSTRUCCIÓ DE LA TAULA */

	public Client(String idEmpresa) throws ClassNotFoundException, SQLException {
		
		this.idEmpresa = idEmpresa;
		initialize();
		construirTaula();
		
	}

	
	/** FUNCIÓ PER A CONSTRUÏR LA TAULA DE COMANDES DEL CLIENT */
	/** També es controla quins botóns de control de comanda apareixen disponíbles i quins no */

	private void construirTaula() throws ClassNotFoundException, SQLException {
		
		String cap[] = {"Producte","Estat", "Data", "Data Límit", "Hores", "Cost Total", "Numero Comanda","Pagat"};
		String info[][] = obtenirMatriu();
		
		table = new JTable(info, cap);
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				/** Per defecte, els botons de gestió de comanda estàn deshabilitats */
				
				button.setVisible(false);
				button2.setVisible(false);
				btnNewButton_1.setVisible(false);
				btnNewButton_2.setVisible(false);
				btnNewButton_3.setVisible(false);
				button_4.setVisible(false);
				button4.setVisible(false);
				button_6.setVisible(true);

				if(table.getModel().getValueAt(table.getSelectedRow(), 1).equals("p")) {
					
					/** Si la comanda seleccionada està "Pendent", s'habilitaran els botons d'Iniciar i el d'Eliminar projecte */
					
					button.setVisible(true);
					button_4.setVisible(false);
					button4.setVisible(false);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(false);
					btnNewButton_3.setVisible(false);
					button_6.setVisible(true);
					
				}
				
				if(table.getModel().getValueAt(table.getSelectedRow(), 1).equals("ep")) {
					
					/** Si la comanda seleccionada està "En Procés", s'habilitaran els botons d'Anul·lar i Finlaitzar projecte */
					
					button2.setVisible(true);
					button_4.setVisible(false);
					button4.setVisible(false);
					btnNewButton_1.setVisible(false);
					btnNewButton_2.setVisible(true);
					btnNewButton_3.setVisible(false);
					button_6.setVisible(true);
					
				}
				
				if(table.getModel().getValueAt(table.getSelectedRow(), 1).equals("f")) {
					
					/** Si la comanda seleccionada està "Finalitzada", s'habilitaran els botons de Calcular cost total i Marcar com a Pagat */

					String lTest = (String)table.getModel().getValueAt(table.getSelectedRow(),5);
					System.out.println(lTest);
					System.out.println(lTest.charAt(lTest.length()-1));
					lTest.length();
					
					if(lTest.charAt(lTest.length()-1) == '€') {
						
						button4.setVisible(false);
						
					} else {
						
						button4.setVisible(true);
						
					}
					
					button_4.setVisible(true);
					btnNewButton_1.setVisible(false);
					btnNewButton_2.setVisible(false);
					btnNewButton_3.setVisible(false);
					button_6.setVisible(true);
				}
				
				if(table.getModel().getValueAt(table.getSelectedRow(), 1).equals("c")) {
					
					/** Si la comanda seleccionada està "Cancel·lada", s'habilitarà el botó de Recuperar Projecte */
					
					button.setVisible(false);
					button2.setVisible(false);
					btnNewButton_1.setVisible(false);
					btnNewButton_2.setVisible(false);
					btnNewButton_3.setVisible(true);
					button_6.setVisible(true);
					
				}
				
				if(!table.getModel().getValueAt(table.getSelectedRow(), 7).equals("no")) {
					
					button_4.setVisible(false);
					
				}
				
			}
			
		});
		
		scrollPane.setViewportView(table);
		
	}
	
	
	/** FUNCIÓ PER A RECOPILAR LA INFORMACIÓ DE LES COMANDES AMB LA QUE S'OMPLIRÁ LA TAULA */
	/** Amb la consulta "consultarComandesClient" poden recorre totes les comandes que pertanyen a aquest client i anar assignant-les a una posició d'un ArrayList */
	/** Aquest ArrayList es retorna un cop omplert i es torna a distribuïr a la funció anterior, on cada posició del Array s'assigna a una fila */
	
	private String[][] obtenirMatriu() throws ClassNotFoundException, SQLException {
		
			ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
			String matInfo[][] = new String[miLista.size()] [8];

			for (int i = 0; i < miLista.size(); i++) {
				
				matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
				matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
				matInfo[i][2] = miLista.get(i).getData()+"";
				matInfo[i][3] = miLista.get(i).getDataLimit()+"";
				matInfo[i][4] = miLista.get(i).getHores()+"";
				matInfo[i][5] = miLista.get(i).getTotal()+"";
				matInfo[i][6] = miLista.get(i).getNumComanda()+"";
				matInfo[i][7] = miLista.get(i).getPagat()+"";
				
			}
	
		return matInfo;
		
	}
	
	
	/** FUNCIÓ ON ES CONSTRUEIXEN TOTS ELS ELEMENTS DE LA PANTALLA I S'APLIQUEN LES CONSULTES SQL, ENTRE ALTRES FUNCIONS */
	
	private void initialize() {
	
		
		/** Aquí es declaren les característiques que tindrà la base de la pantalla (resolució, color, mida fixe) */
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 880, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		/** Inici del conjunt d'elements que composen la capçalera */
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 874, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(249, 11, 375, 60);
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
		lblNewLabel.setIcon(new ImageIcon(Client.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		/** Fi del conjunt d'elements que composen la capçalera */

		
		/** Inici del "Scroll Panel" que conté la taula de comandes */
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 138, 655, 181);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		/** Fi del "Scroll Panel" que conté la taula de comandes */

		
		/** Inici del "JPanel" que emmagatzema els botóns de la dreta de la pantalla */
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(712, 81, 162, 346);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		/** Inici del conjunt de codi que composa als botons de la dreta de la pantalla */
		/** Inici del botó "Afegir Comanda" */
		
		JButton btnNewButton = new JButton("AFEGIR COMANDA");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton.setBackground(Color.BLACK);
				btnNewButton.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton.setBackground(Color.WHITE);
				btnNewButton.setForeground(Color.BLACK);
				
			}
			
		});
		
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** Al clicar el botó, s'obrirà la pantalla "CrearComanda" */
					
					CrearComanda frm = new CrearComanda(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnNewButton.setBounds(0, 0, 162, 69);
		panel_1.add(btnNewButton);
		
		/** Fi del botó "Afegir Comanda" */

		/** Inici del botó "Calcular Cost Total" */
		
		button4.setForeground(Color.BLACK);
		button4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button4.setBackground(Color.BLACK);
				button4.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button4.setBackground(Color.WHITE);
				button4.setForeground(Color.BLACK);
				
			}
			
		});
		
		button4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button4.setVisible(false);
		button4.setFocusPainted(false);
		button4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button4.setBackground(Color.WHITE);
		button4.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					double cTot = Double.valueOf((String)table.getModel().getValueAt(table.getSelectedRow(),5)) * Double.valueOf((String) table.getModel().getValueAt(table.getSelectedRow(),4));
					sqlC.incrementarPreu(table.getModel().getValueAt(table.getSelectedRow(),6).toString(), cTot+"€");
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button4.setBounds(263, 342, 190, 51);
		frame.getContentPane().add(button4);
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
		
		button_4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_4.setVisible(false);
		button_4.setFocusPainted(false);
		button_4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_4.setBackground(Color.WHITE);
		button_4.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					sqlC.modificarPagament(table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_4.setBounds(495, 343, 190, 51);
		frame.getContentPane().add(button_4);
		/** Fi del botó "Calcular Cost Total" */

		
		/** Inici del botó "Info Contacte" */
		
		JButton btnConsultarInformaciDe = new JButton("INFO CONTACTE");
		btnConsultarInformaciDe.setForeground(Color.BLACK);
		btnConsultarInformaciDe.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnConsultarInformaciDe.setBackground(Color.BLACK);
				btnConsultarInformaciDe.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnConsultarInformaciDe.setBackground(Color.WHITE);
				btnConsultarInformaciDe.setForeground(Color.BLACK);
				
			}
			
		});
		
		btnConsultarInformaciDe.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnConsultarInformaciDe.setFocusPainted(false);
		btnConsultarInformaciDe.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnConsultarInformaciDe.setBackground(Color.WHITE);
		btnConsultarInformaciDe.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		btnConsultarInformaciDe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar, s'obrirà una pestanya que mostrarà alguna informació del client */
					
					Info frm = new Info(idEmpresa);
					frm.frame.setVisible(true); 
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnConsultarInformaciDe.setBounds(0, 68, 162, 69);
		panel_1.add(btnConsultarInformaciDe);
		
		/** Fi del botó "Info Contacte" */

		
		/** Inici del botó "Modificar Client" */
		
		JButton button_1 = new JButton("MODIFICAR CLIENT");
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
		button_1.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar aquest botó, s'obrirà la pantalla "ModificarClient" */
					
					ModificarClient frm = new ModificarClient(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_1.setBounds(0, 135, 162, 69);
		panel_1.add(button_1);
		
		/** Fi del botó "Modificar Client" */

		
		/** Inici del botó "Generar Factura" */
		
		JButton button_2 = new JButton("GENERAR FACTURA");
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
		
		button_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_2.setFocusPainted(false);
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_2.setBackground(Color.WHITE);
		button_2.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** En clicar aquest botó, s'obrirà la pantalla "GenerarFactura" */
				
				GenerarFactura frm = new GenerarFactura(idEmpresa);
				frm.frame.setVisible(true);
				frame.setVisible(false);
				
			}
			
		});
		
		button_2.setBounds(0, 203, 162, 69);
		panel_1.add(button_2);
		
		/** Fi del botó "Generar Factura" */
		
		
		/** Inici del botó "Tornar Enrere" */

		JButton button_3 = new JButton("TORNAR ENRERE");
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
		
		button_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_3.setFocusPainted(false);
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_3.setBackground(Color.WHITE);
		button_3.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, tornarem a la pantalla principal */
					
					Principal frm = new Principal();
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_3.setBounds(0, 270, 162, 76);
		panel_1.add(button_3);
		
		/** Fi del conjunt de codi que composa als botons de la dreta */
		/** Fi del "JPanel" que conté als botons de la dretaq */

		
		/** Inici del conjun de codi que composa als botons de tractament de comanda */
		/** Inici del botó "Eliminar Projecte" */
		
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton_1.setBackground(Color.BLACK);
				btnNewButton_1.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.setForeground(Color.BLACK);
				
			}
			
		});
		
		btnNewButton_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, s'eliminarà la comanda seleccionada */
					
					sqlC.eliminarComandes(table.getModel().getValueAt(table.getSelectedRow(), 6).toString(), idEmpresa);
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
				catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnNewButton_1.setBounds(30, 342, 190, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		/** Fi del botó "Eliminar Projecte" */

		
		/** Inici del botó "Anular Projecte" */
		
		btnNewButton_2.setVisible(false);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton_2.setBackground(Color.BLACK);
				btnNewButton_2.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton_2.setBackground(Color.WHITE);
				btnNewButton_2.setForeground(Color.BLACK);
				
			}
			
		});
		
		btnNewButton_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, es modificara l'estat de la comanda a "Cancel·lat" */
					
					sqlC.modificarEstatComanda("c", table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
				catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnNewButton_2.setBounds(30, 342, 190, 51);
		frame.getContentPane().add(btnNewButton_2);

		/** Fi del botó "Anular Projecte" */

		
		/** Inici del botó "Recuperar Projecte" */

		btnNewButton_3.setVisible(false);
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton_3.setBackground(Color.BLACK);
				btnNewButton_3.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton_3.setBackground(Color.WHITE);
				btnNewButton_3.setForeground(Color.BLACK);
				
			}
		});
		
		btnNewButton_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, es modificara l'estat de la comanda a "En Procés" */
					
					sqlC.modificarEstatComanda("ep", table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
				catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnNewButton_3.setBounds(30, 342, 190, 51);
		frame.getContentPane().add(btnNewButton_3);
		
		/** Fi del botó "Recuperar Projecte" */

		
		/** Inici del botó "Calcular Cost Total" */
		
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
		
		button_4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_4.setVisible(false);
		button_4.setFocusPainted(false);
		button_4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_4.setBackground(Color.WHITE);
		button_4.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, es calculará el cost total de la comanda */
					
					sqlC.modificarPagament(table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_4.setBounds(495, 343, 190, 51);
		frame.getContentPane().add(button_4);
		
		/** Fi del botó "Calcular Cost Total" */

		
		/** Inici del botó "Iniciar Projecte" */
		
		button.setVisible(false);
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
		button.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, es modificara l'estat de la comanda a "En Procés" */
					
					sqlC.modificarEstatComanda("ep", table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					sqlC.iniciarComanda(table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button.setBounds(263, 342, 190, 51);
		frame.getContentPane().add(button);
		
		/** Fi del botó "Iniciar Projecte" */

		
		/** Inici del botó "Finalitzar Projecte" */
		
		button2.setVisible(false);
		button2.setForeground(Color.BLACK);
		button2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button2.setBackground(Color.BLACK);
				button2.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button2.setBackground(Color.WHITE);
				button2.setForeground(Color.BLACK);
				
			}
			
		});
		
		button2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button2.setFocusPainted(false);
		button2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button2.setBackground(Color.WHITE);
		button2.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					/** En clicar el botó, es modificara l'estat de la comanda a "Finalitzat" */
					
					sqlC.modificarEstatComanda("f", table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
					sqlC.finalitzarComanda(table.getModel().getValueAt(table.getSelectedRow(),6).toString(), table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button2.setBounds(263, 342, 190, 51);
		frame.getContentPane().add(button2);
		
		/** Fi del botó "Finalitzar Projecte" */

		
		textField = new JTextField();
		textField.setBackground(new Color(0, 0, 0));
		
		try {
			
			textField.setText(sqlCl.consultarNomClient(idEmpresa).toUpperCase());
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			
		}
		
		textField.setSelectionColor(Color.GRAY);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setAutoscrolls(false);
		textField.setBounds(24, 93, 146, 37);
		frame.getContentPane().add(textField);

		/** Fi del conjun de codi que composa als botons de tractament de comanda */

		
		/** Inici del conjun de codi que composa als botons de la part de dalt */
		/** Inici codi botó "Filtrar Comanda" */
		
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
		
		button_5.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_5.setFocusPainted(false);
		button_5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_5.setBackground(Color.WHITE);
		button_5.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/** En clicar aquest botó, s'obrirà la pantalla "FiltreComandes" */

				try {
					
					FiltreComandes frm = new FiltreComandes(idEmpresa);
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_5.setBounds(176, 90, 190, 37);
		frame.getContentPane().add(button_5);
		
		/** Fi codi botó "Filtrar Comanda" */

		
		/** Inici codi boto "Veure Descripció" */
		
		button_6.setFocusPainted(false);
		button_6.setForeground(Color.BLACK);
		button_6.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				button_6.setBackground(Color.BLACK);
				button_6.setForeground(Color.WHITE);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				button_6.setBackground(Color.WHITE);
				button_6.setForeground(Color.BLACK);
				
			}
			
		});
		
		button_6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/** En clicar aquest botó s'obrirà un panell de text mostrant la descripció de el que vol el client */
				
				try {
					
					JOptionPane.showMessageDialog(null, sqlC.veureDescripcio(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()),"Info Comanda",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_6.setVisible(false);
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_6.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_6.setBackground(Color.WHITE);
		button_6.setBounds(412, 90, 190, 37);
		frame.getContentPane().add(button_6);
		
		/** Fi codi boto "Veure Descripció" */
		/** Fi del conjun de codi que composa als botons de la part de dalt */
		
	}
	
}
