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
import java.awt.Canvas;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import DADES.*;
import MODEL.*;

public class Principal {

	public JFrame frame;
	private JTable table_1;
	private JTable table_2;
	int filaIndex;
	int columnaIndex;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	
	public Principal() throws ClassNotFoundException, SQLException {
		initialize();
		construirTaula();
		construirTauler();
	}
	
	//INICI FUNCIONS TAULA PRINCIPAL

	private void construirTaula() throws ClassNotFoundException, SQLException {
		String cap[] = {"Empresa","Concepte", "NIF"};
		String info[][] = obtenirMatriu();
		
		table_1 = new JTable(info, cap);
		scrollPane.setViewportView(table_1);

	}
	
	
	private String[][] obtenirMatriu() throws ClassNotFoundException, SQLException {
		SQLClients sqlU = new SQLClients();
			ArrayList<ClientCl> miLista = sqlU.consultarClient();
			
			String matInfo[][] = new String[miLista.size()] [3];

			for (int i = 0; i < miLista.size(); i++) {
				matInfo[i][0] = miLista.get(i).getEmpresa()+"";
				matInfo[i][1] = miLista.get(i).getConcepte()+"";
				matInfo[i][2] = miLista.get(i).getNif()+"";
			}
			
	
		return matInfo;
	}

	//FI FUNCIONS TAULA PRINCIPAL

	/**INICI FUNCIONS TAULER*/

		private void construirTauler() throws ClassNotFoundException, SQLException {
			String cap[] = {"Tauler"};
			String info[][] = obtenirMatr();
			
			table_2 = new JTable(info, cap);
			scrollPane_1.setViewportView(table_2);

		}
		
		
		private String[][] obtenirMatr() throws ClassNotFoundException, SQLException {
			SQLComandes sqlC = new SQLComandes();
				ArrayList<ComandaCl> miLista = sqlC.consultarComandes();
				
				String matInfo[][] = new String[miLista.size()] [2];

				for (int x = 0; x < miLista.size(); x++) {
					matInfo[x][0] = miLista.get(x).getIdEmpresa()+"eee";
				}
				
		
			return matInfo;
		}

	/**FI FUNCIONS TAULER*/
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 686, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	//CAPÇALERA COMENÇAMENT
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 680, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(158, 11, 391, 60);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(25, 93, 30, 30);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.YELLOW);
		panel_2.setBounds(216, 93, 30, 30);
		frame.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(403, 93, 30, 30);
		frame.getContentPane().add(panel_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Finalitzats");
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane.setFocusable(false);
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setEditable(false);
		textPane.setBackground(Color.BLACK);
		textPane.setBounds(65, 93, 107, 27);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("En Proces");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(256, 93, 137, 40);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Pendents");
		textPane_2.setForeground(Color.WHITE);
		textPane_2.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_2.setFocusable(false);
		textPane_2.setFocusTraversalKeysEnabled(false);
		textPane_2.setFocusCycleRoot(false);
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.BLACK);
		textPane_2.setBounds(443, 93, 137, 27);
		frame.getContentPane().add(textPane_2);
		
		JButton btnNewButton = new JButton("Consultar Client");
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
		btnNewButton.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(221, 332, 125, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Generar Factura");
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
			}
		});
		button.setBounds(488, 332, 125, 44);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Afegir Client");
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
			}
		});
		button_1.setBounds(182, 404, 164, 44);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Afegir Producte");
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
		button_2.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(488, 404, 164, 44);
		frame.getContentPane().add(button_2);
		
		//INICI SCROLL PANEL
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 144, 470, 171);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table_1);

		//FI SCROLL PANEL

		//INICI SCROLL PANEL

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(19, 318, 153, 130);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(table_2);

		//FI SCROLL PANEL
		
	}
	
}
