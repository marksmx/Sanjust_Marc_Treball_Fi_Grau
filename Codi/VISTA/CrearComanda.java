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
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import DADES.SQLComandes;
import DADES.SQLProductes;
import MODEL.ComandaCl;
import MODEL.ProducteCl;
import MODEL.ServeiCl;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CrearComanda {
	private String idClient;
	private double iva;
	private double total;
	SQLProductes sqlP = new SQLProductes();
	SQLComandes sqlC = new SQLComandes();
	JFrame frame;
	ArrayList<ProducteCl> miLista = sqlP.veureProducte();
	ArrayList<ServeiCl> miLista2 = sqlP.veureServei();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date data;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CrearComanda(String idClient) throws SQLException {
		this.idClient = idClient;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 618, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//CAPÇALERA COMENÇAMENT
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 612, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(148, 11, 383, 60);
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Marc Sanjust\\eclipse-workspace\\ProjecteFiGrau\\src\\VISTA\\img\\logo.png"));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Afegir Servei");
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane.setFocusable(false);
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setEditable(false);
		textPane.setBackground(Color.BLACK);
		textPane.setBounds(42, 228, 89, 70);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Afegir Producte");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(42, 147, 105, 70);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setText("");
		textPane_2.setForeground(Color.BLACK);
		textPane_2.setFont(new Font("Dialog", Font.PLAIN, 30));
		textPane_2.setFocusTraversalKeysEnabled(false);
		textPane_2.setFocusCycleRoot(false);
		textPane_2.setBackground(Color.WHITE);
		textPane_2.setBounds(494, 202, 89, 45);
		frame.getContentPane().add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Cost Total");
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_3.setFocusable(false);
		textPane_3.setFocusTraversalKeysEnabled(false);
		textPane_3.setFocusCycleRoot(false);
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.BLACK);
		textPane_3.setBounds(367, 211, 117, 36);
		frame.getContentPane().add(textPane_3);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		
		JButton button2 = new JButton("FINALITZAR COMANDA");
		
		
	
		
		JButton button_1 = new JButton("TORNAR ENRERE");
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
				try {
					if(textPane_2.getText().equals("")) {
						Client frm = new Client(idClient);
						frm.frame.setVisible(true);
						frame.setVisible(false);
					}else {
						CrearComanda frm = new CrearComanda(idClient);
						frm.frame.setVisible(true);
						frame.setVisible(false);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(435, 327, 148, 53);
		frame.getContentPane().add(button_1);
		
		comboBox.setFont(new Font("HelveticaNeue", Font.PLAIN, 11));
		comboBox.setBounds(157, 157, 128, 36);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("");
		
		for (int i = 0; i < miLista.size(); i++) {
			comboBox.addItem(miLista.get(i).getElement()+", "+miLista.get(i).getUnitats());
		}
		
		comboBox_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 11));
		comboBox_1.setBounds(157, 241, 128, 36);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("");

		for (int i = 0; i < miLista2.size(); i++) {
			comboBox_1.addItem(miLista2.get(i).getElement()+", "+miLista2.get(i).getUnitats()+" per "+miLista2.get(i).getFrequencia());
		}

		JButton button = new JButton("VALIDAR COMANDA");
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
				System.out.println();
				
				if(comboBox.getSelectedItem().equals("") && !comboBox_1.getSelectedItem().equals("") || !comboBox.getSelectedItem().equals("") && comboBox_1.getSelectedItem().equals("")) {
					button.setVisible(false);
					button2.setVisible(true);
					comboBox.enable(false);
					comboBox_1.enable(false);
				}
				
				if(!comboBox.getSelectedItem().equals("") && !comboBox_1.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null, "Tria només un servei o un producte","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
				if(comboBox.getSelectedItem().equals("") && comboBox_1.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null, "No has triat cap servei ni producte","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				if(!comboBox.getSelectedItem().equals("") && comboBox_1.getSelectedItem().equals("")) {
					textPane_2.setText(miLista.get(comboBox.getSelectedIndex()-1).getTotal()+"€");
				}
				if(comboBox.getSelectedItem().equals("") && !comboBox_1.getSelectedItem().equals("")) {
					textPane_2.setText(miLista2.get(comboBox_1.getSelectedIndex()-1).getTotal()+"€");
				}
			}
		});
		button.setBounds(283, 327, 148, 53);
		frame.getContentPane().add(button);
		
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
		button2.setFont(new Font("HelveticaNeue", Font.BOLD, 12));
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(comboBox.getSelectedIndex()>=1 && comboBox_1.getSelectedIndex()==0) {
						data = new Date(0);
						iva = Integer.parseInt(miLista.get(comboBox.getSelectedIndex()-1).getBase())*0.21;
						total = Integer.parseInt(miLista.get(comboBox.getSelectedIndex()-1).getBase())+(Integer.parseInt(miLista.get(comboBox.getSelectedIndex()-1).getBase())*0.21);
						String rec = Integer.toString(sqlC.contarComandes()+1);
						String id =  miLista.get(comboBox.getSelectedIndex()-1).getIdProducte();
						String dataS = dateFormat.format(data).toString();
						String baseS = miLista.get(comboBox.getSelectedIndex()-1).getBase()+"€";
						String ivaS = Double.toString(iva)+"€";
						String totalS = Double.toString(total)+"€";

						sqlC.crearComanda("p", rec, id, dataS, "", baseS, ivaS, totalS, idClient);
					} 
					
					if(comboBox_1.getSelectedIndex()>=1 && comboBox.getSelectedIndex()==0) {
						iva = Integer.parseInt(miLista2.get(comboBox_1.getSelectedIndex()-1).getBase())*0.21;
						total = Integer.parseInt(miLista2.get(comboBox_1.getSelectedIndex()-1).getBase())+(Integer.parseInt(miLista2.get(comboBox_1.getSelectedIndex()-1).getBase())*0.21);
						String rec = Integer.toString(sqlC.contarComandes()+1);
						String id =  miLista2.get(comboBox_1.getSelectedIndex()-1).getIdProducte();
						String dataS = dateFormat.format(data).toString();
						String baseS = miLista2.get(comboBox_1.getSelectedIndex()-1).getBase()+"€";
						String ivaS = Double.toString(iva)+"€";
						String totalS = Double.toString(total)+"€";

						sqlC.crearComanda("p", rec, id, dataS, "", baseS, ivaS, totalS, idClient);
					} 
					
					JOptionPane.showMessageDialog(null, "S'ha afegit la comanda","",JOptionPane.INFORMATION_MESSAGE);
					Client frm = new Client(idClient);
					frm.frame.setVisible(true);
					frame.setVisible(false);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button2.setBounds(262, 327, 169, 53);
		frame.getContentPane().add(button2);
	}
}
