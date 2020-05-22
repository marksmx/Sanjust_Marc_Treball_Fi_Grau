package VISTA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.util.ArrayList;

public class Client {

	SQLComandes sqlC = new SQLComandes();
	SQLClients sqlCl = new SQLClients();
	SQLProductes sqlP = new SQLProductes();
	private String idEmpresa;
	JFrame frame;
	private JTable table;
	private JScrollPane scrollPane; 
	JButton button = new JButton("INICIAR PROJECTE");
	JButton btnNewButton_1 = new JButton("ELIMINAR PROJECTE");
	JButton button_4 = new JButton("MARCAR COM A PAGAT");
	JButton button_f = new JButton("MARCAR COM FINALITZAT");
	private JTextField textField;

	public Client(String idEmpresa) throws ClassNotFoundException, SQLException {
		this.idEmpresa = idEmpresa;
		initialize();
		construirTaula();
	}

	private void construirTaula() throws ClassNotFoundException, SQLException {
		String cap[] = {"Producte","Estat", "Data", "Hores", "Cost Total"};
		String info[][] = obtenirMatriu();
		
		table = new JTable(info, cap);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getModel().getValueAt(table.getSelectedRow(), 1).equals("p")) {
					button.setVisible(true);
					btnNewButton_1.setVisible(true);

				}
			}
		});
		scrollPane.setViewportView(table);

	}
	
	
	private String[][] obtenirMatriu() throws ClassNotFoundException, SQLException {
			ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
			
			String matInfo[][] = new String[miLista.size()] [5];

			for (int i = 0; i < miLista.size(); i++) {
				matInfo[i][0] = sqlP.consultarProducte(miLista.get(i).getIdProducte())+"";
				matInfo[i][1] = miLista.get(i).getEstatComanda()+"";
				matInfo[i][2] = miLista.get(i).getData()+"";
				matInfo[i][3] = miLista.get(i).getHores()+"";
				matInfo[i][4] = miLista.get(i).getTotal()+"";

			}
			
	
		return matInfo;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 880, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
	//CAPÇALERA COMENÇAMENT
		
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Marc Sanjust\\Desktop\\logo.png"));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
	//CAPÇALERA FINAL
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 138, 655, 181);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

	//INICI BARRA LATERAL BOTONS
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(712, 81, 162, 346);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//INICI BOTÓ AFEGIR COMANDA
		
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
					CrearComanda frm = new CrearComanda(idEmpresa);
					frm.frame.setVisible(true);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(0, 0, 162, 69);
		panel_1.add(btnNewButton);
		
		//FI BOTÓ AFEGIR COMANDA

		//INICI BOTÓ INFO DE CONTACTE

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
					Info frm = new Info(idEmpresa);
					frm.frame.setVisible(true); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConsultarInformaciDe.setBounds(0, 68, 162, 69);
		panel_1.add(btnConsultarInformaciDe);
		
		//FI BOTÓ INFO DE CONTACTE
		
		//INICI BOTÓ MODIFICAR CLIENT
		
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
			}
		});
		button_1.setBounds(0, 135, 162, 69);
		panel_1.add(button_1);
		
		//FI BOTÓ MODIFICAR CLIENT
		
		//INICI BOTÓ GENERAR FACTURA
		
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
			}
		});
		button_2.setBounds(0, 203, 162, 69);
		panel_1.add(button_2);
		
		//FI BOTÓ GENERAR FACTURA
		
		//INICI BOTÓ TORNAR ENRERE

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
					Principal frm = new Principal();
					frm.frame.setVisible(true); 
					frame.setVisible(false); 
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setBounds(0, 270, 162, 76);
		panel_1.add(button_3);
		
		//INICI BOTÓ TORNAR ENRERE
		
	//FI BARRA LATERAL BOTONS
		
		//INICI BOTÓ ELIMINAR PROJECTE
		
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
			}
		});
		btnNewButton_1.setBounds(30, 342, 190, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		//FI BOTÓ ELIMINAR PROJECTE
		
	

		//INICI BOTÓ MARCAR COM A PAGAT
		
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
			}
		});
		button_4.setBounds(495, 343, 190, 51);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("FILTRAR COMANDA");
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
			}
		});
		button_5.setBounds(263, 90, 190, 37);
		frame.getContentPane().add(button_5);
		
		//FI BOTÓ MARCAR COM A PAGAT
		
		//INICI BOTÓ INICIAR PROJECTE

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
			
			}
		});
		button.setBounds(263, 342, 190, 51);
		frame.getContentPane().add(button);
		
		//FI BOTÓ INICIAR PROJECTE
		
		//INICI BOTÓ MARCAR COM FINALITZAT

		button_f.setVisible(false);
		button_f.setForeground(Color.BLACK);
		button_f.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_f.setBackground(Color.BLACK);
				button_f.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_f.setBackground(Color.WHITE);
				button_f.setForeground(Color.BLACK);
			}
		});
		button_f.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_f.setFocusPainted(false);
		button_f.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button_f.setBackground(Color.WHITE);
		button_f.setFont(new Font("HelveticaNeue", Font.BOLD, 13));
		button_f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		button_f.setBounds(263, 342, 190, 51);
		frame.getContentPane().add(button_f);
		
		textField = new JTextField();
		textField.setBackground(Color.BLACK);
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
		textField.setBounds(24, 93, 196, 47);
		frame.getContentPane().add(textField);
		
		//FI BOTÓ INICIAR PROJECTE
	}
}
