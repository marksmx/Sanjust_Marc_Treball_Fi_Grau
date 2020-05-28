package VISTA;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import DADES.SQLClients;
import DADES.SQLComandes;
import DADES.SQLProductes;
import MODEL.ComandaCl;

import javax.swing.JComboBox;

public class FiltreComandes {
	
	
	/** IMPORTACIÓ I DECLARACIÓ DELS CONTROLADORS DE CONSULTES SQL QUE S'UTILITZEN EN AQUESTA PANTALLA */

	SQLComandes sqlC = new SQLComandes();
	SQLClients sqlCl = new SQLClients();
	SQLProductes sqlP = new SQLProductes();
	
	
	/** DECLARACIÓ DEL JFRAME, DE BOTONS, DE CAMPS DE TEXT I ALTRES */

	public JFrame frame;
	private JTable table;
	private JScrollPane scrollPane; 
	private String idEmpresa;
	private boolean filtrar1 = false;
	private boolean filtrar2 = false;
	private boolean filtrar3 = false;
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBox_1 = new JComboBox<String>();
	JComboBox<String> comboBox_2 = new JComboBox<String>();
	JButton button = new JButton("FILTRAR PER MES (CREACIÓ)");
	JButton button_1 = new JButton("FILTRAR PER ESTAT");
	JButton button_2 = new JButton("FILTRAR PER PAGAMENT");
	JButton button_3 = new JButton("TORNAR AL INICI");

	
	/** FUNCIÓ PER A CRIDAR A LA FUNCIÓ QUE COMPOSA ELS ELEMENTS DE LA PANTALLA I A LES FUNCIONS DE CONSTRUCCIÓ DE LA TAULA */
	
	public FiltreComandes(String idEmpresa) throws ClassNotFoundException, SQLException {
		
		this.idEmpresa = idEmpresa;
		initialize();
		
	}

	
	/** FUNCIÓ PER A CONSTRUÏR LA TAULA DE COMANDES DEL CLIENT */
	/** Depenent del filtre que utilitzem, s'utilitzarà una funció per a omplir la taula o una altre */
	
	private void construirTaula() throws ClassNotFoundException, SQLException {
		
		String cap[] = {"Producte","Estat", "Data", "Data Límit", "Hores", "Cost Total", "Numero Comanda","Pagat"};
		
		if(filtrar1 == true && filtrar2 == false && filtrar3 == false) {
			
			String info[][] = filtreMes();
			table = new JTable(info, cap);
			scrollPane.setViewportView(table);
			
		}
		
		if(filtrar1 == false && filtrar2 == true && filtrar3 == false) {
			
			String info[][] = filtreEstat();
			table = new JTable(info, cap);
			scrollPane.setViewportView(table);
			
		}
		
		if(filtrar1 == false && filtrar2 == false && filtrar3 == true) {
			
			String info[][] = filtrePagat();
			table = new JTable(info, cap);
			scrollPane.setViewportView(table);
			
		}
		
	}
		
	private String[][] filtreMes() throws ClassNotFoundException, SQLException {
		
		ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
		String matInfo[][] = new String[miLista.size()] [8];
		
		for (int i = 0; i < miLista.size(); i++) {
			
			String mes = Character.toString(miLista.get(i).getData().charAt(5)) + Character.toString(miLista.get(i).getData().charAt(6));
			
			if(mes.equals(comboBox.getSelectedItem().toString())) {
				
				matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
				matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
				matInfo[i][2] = miLista.get(i).getData()+"";
				matInfo[i][3] = miLista.get(i).getDataLimit()+"";
				matInfo[i][4] = miLista.get(i).getHores()+"";
				matInfo[i][5] = miLista.get(i).getTotal()+"";
				matInfo[i][6] = miLista.get(i).getNumComanda()+"";
				matInfo[i][7] = miLista.get(i).getPagat()+"";
				
			} 
			
		}
		
		return matInfo;
		
	}
	
	private String[][] filtreEstat() throws ClassNotFoundException, SQLException {
		
		ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
		String matInfo[][] = new String[miLista.size()] [8];
		
		for (int i = 0; i < miLista.size(); i++) {
			
			String estat = matInfo[i][1] = miLista.get(i).getEstatComanda();

			if(estat.equals("p") && comboBox_1.getSelectedIndex() == 1) {
				
				matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
				matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
				matInfo[i][2] = miLista.get(i).getData()+"";
				matInfo[i][3] = miLista.get(i).getDataLimit()+"";
				matInfo[i][4] = miLista.get(i).getHores()+"";
				matInfo[i][5] = miLista.get(i).getTotal()+"";
				matInfo[i][6] = miLista.get(i).getNumComanda()+"";
				matInfo[i][7] = miLista.get(i).getPagat()+"";
				
			} else {
				
				if(estat.equals("ep") && comboBox_1.getSelectedIndex() == 2) {
					
					matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
					matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
					matInfo[i][2] = miLista.get(i).getData()+"";
					matInfo[i][3] = miLista.get(i).getDataLimit()+"";
					matInfo[i][4] = miLista.get(i).getHores()+"";
					matInfo[i][5] = miLista.get(i).getTotal()+"";
					matInfo[i][6] = miLista.get(i).getNumComanda()+"";
					matInfo[i][7] = miLista.get(i).getPagat()+"";
					
				} else {
					
					if(estat.equals("f") && comboBox_1.getSelectedIndex() == 3) {
						
						matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
						matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
						matInfo[i][2] = miLista.get(i).getData()+"";
						matInfo[i][3] = miLista.get(i).getDataLimit()+"";
						matInfo[i][4] = miLista.get(i).getHores()+"";
						matInfo[i][5] = miLista.get(i).getTotal()+"";
						matInfo[i][6] = miLista.get(i).getNumComanda()+"";
						matInfo[i][7] = miLista.get(i).getPagat()+"";
						
					} else {
						
						if(estat.equals("c") && comboBox_1.getSelectedIndex() == 4) {
							
							matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
							matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
							matInfo[i][2] = miLista.get(i).getData()+"";
							matInfo[i][3] = miLista.get(i).getDataLimit()+"";
							matInfo[i][4] = miLista.get(i).getHores()+"";
							matInfo[i][5] = miLista.get(i).getTotal()+"";
							matInfo[i][6] = miLista.get(i).getNumComanda()+"";
							matInfo[i][7] = miLista.get(i).getPagat()+"";
							
						} else {
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return matInfo;
		
	}
	
	private String[][] filtrePagat() throws ClassNotFoundException, SQLException {
		
		ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
		String matInfo[][] = new String[miLista.size()] [8];
		
		for (int i = 0; i < miLista.size(); i++) {
			
			String pagat =  miLista.get(i).getPagat();
			
			if(pagat.equals("si") && comboBox_2.getSelectedIndex() == 1) {
				
				matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
				matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
				matInfo[i][2] = miLista.get(i).getData()+"";
				matInfo[i][3] = miLista.get(i).getDataLimit()+"";
				matInfo[i][4] = miLista.get(i).getHores()+"";
				matInfo[i][5] = miLista.get(i).getTotal()+"";
				matInfo[i][6] = miLista.get(i).getNumComanda()+"";
				matInfo[i][7] = miLista.get(i).getPagat()+"";
				
			} else {
				
				if(pagat.equals("no") && comboBox_2.getSelectedIndex() == 2) {
					
					matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
					matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
					matInfo[i][2] = miLista.get(i).getData()+"";
					matInfo[i][3] = miLista.get(i).getDataLimit()+"";
					matInfo[i][4] = miLista.get(i).getHores()+"";
					matInfo[i][5] = miLista.get(i).getTotal()+"";
					matInfo[i][6] = miLista.get(i).getNumComanda()+"";
					matInfo[i][7] = miLista.get(i).getPagat()+"";
					
				}
				
			}
	
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
		frame.setBounds(730, 300, 743, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		/** Inici del conjunt d'elements que composen la capçalera */
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 737, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(179, 11, 452, 60);
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
		
		/** Fi del conjunt d'elements que composen la capçalera */
		
		
		/** Inici del Scroll Panel que conté la taula */
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 276, 655, 181);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		/** Fi del Scroll Panel que conté la taula */

		
		/** Inici conjunt de codi que composa els comboBox */

		comboBox.setBounds(80, 201, 146, 30);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("");
		for(int i=01; i<=12; i++) {
			
			if(Integer.toString(i).length() == 1) {
				
				comboBox.addItem("0"+Integer.toString(i));
				
			}
			
			if(Integer.toString(i).length() == 2) {
				
				comboBox.addItem(Integer.toString(i));
				
			}
			
		}
		
		comboBox_1.setBounds(295, 203, 146, 30);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("");
		comboBox_1.addItem("Pendent");
		comboBox_1.addItem("En Procés");
		comboBox_1.addItem("Finalitzat");
		comboBox_1.addItem("Cancel·lat");
		
		comboBox_2.setBounds(517, 201, 146, 30);
		frame.getContentPane().add(comboBox_2);
		comboBox_2.addItem("");
		comboBox_2.addItem("Pagat");
		comboBox_2.addItem("No Pagat");
		
		/** Fi conjunt de codi que composa els comboBox */

		
		/** Inici conjunt de codi que composa els botons */
		/** Inici botó "Filtrar per Mes de creació" */
		
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
				
				filtrar1 = true;
				filtrar2 = false;
				filtrar3 = false;
				
				try {
					
					construirTaula();
					
				} catch (Exception e2) {
					
					System.out.println("ERROR");
					
				}
				
			}
			
		});
		
		button.setBounds(40, 135, 226, 55);
		frame.getContentPane().add(button);

		/** Fi botó "Filtrar per Mes de creació" */

		
		/** Inici botó "Filtrar per Pagament" */

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
		
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				filtrar1 = false;
				filtrar2 = true;
				filtrar3 = false;
				
				try {
					
					construirTaula();
					
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_1.setFocusPainted(false);
		button_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(295, 135, 146, 55);
		frame.getContentPane().add(button_1);
		
		/** Fi botó "Filtrar per Pagament" */

		
		/** Inici botó "Filtrar per Pagament" */

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
			
			public void actionPerformed(ActionEvent e) {
				
				filtrar1 = false;
				filtrar2 = false;
				filtrar3 = true;
				
				try {
					
					construirTaula();
					
				} catch (ClassNotFoundException | SQLException e2) {
					
					e2.printStackTrace();
					
				}
				
			}
			
		});
		
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_2.setFocusPainted(false);
		button_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(467, 135, 228, 55);
		frame.getContentPane().add(button_2);
	
		/** Fi botó "Filtrar per Pagament" */

		
		/** Inici botó "Tornar al Inici" */

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
		
		button_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Client frm = new Client(idEmpresa);
					frm.frame.setVisible(true); 
					frame.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_3.setFocusPainted(false);
		button_3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(295, 469, 146, 55);
		frame.getContentPane().add(button_3);
		
		/** Fi botó "Tornar al Inici" */
		/** Fi conjunt de codi que composa els botons */

		
	}
	
}
