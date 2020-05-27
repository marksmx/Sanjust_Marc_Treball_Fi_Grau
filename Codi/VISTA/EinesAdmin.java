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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import DADES.SQLClients;

import javax.swing.JComboBox;

public class EinesAdmin {
	
	SQLClients sqlCl = new SQLClients();
	JComboBox comboBox = new JComboBox();
	JFrame frame;

	public EinesAdmin() {
		
		initialize();
		
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 686, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	//CAP�ALERA COMEN�AMENT
		
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
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Eliminar Client");
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
		btnNewButton.setFont(new Font("HelveticaNeue", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					sqlCl.eliminarClient(Integer.toString(comboBox.getSelectedIndex()));
					EinesAdmin frm = new EinesAdmin();
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
			
		});
		
		btnNewButton.setBounds(57, 113, 219, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("Tornar al Inici");
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
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Principal frm = new Principal();
					frm.frame.setVisible(true);
					frame.setVisible(false);
					
				} catch (Exception e2) {
					
					System.out.println("ERROR");
					
				}
				
			}
			
		});
		
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("HelveticaNeue", Font.BOLD, 20));
		button_1.setFocusPainted(false);
		button_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(57, 273, 565, 53);
		frame.getContentPane().add(button_1);
		
		comboBox.setBounds(57, 189, 219, 35);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("");
		
		try {
			
			for(int i=1; i<=sqlCl.contarClients();i++) {
				
				comboBox.addItem(sqlCl.consultarNomClient(Integer.toString(i)));
				
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			
		}
		
	}
	
}
