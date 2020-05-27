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

import DADES.*;

public class CrearUsuari {

	SQLUsuari sqlU = new SQLUsuari();
	public JFrame frame;
	


	public CrearUsuari() {
		
		initialize();
		
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.getContentPane().setFocusable(false);
		frame.getContentPane().setFocusTraversalKeysEnabled(false);
		frame.setBounds(730, 300, 473, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//CAPÇALERA COMENÇAMENT
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 467, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(77, 11, 374, 60);
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
		lblNewLabel.setIcon(new ImageIcon(CrearUsuari.class.getResource("/VISTA/img/logo.png")));
		lblNewLabel.setBounds(0, 0, 102, 82);
		panel.add(lblNewLabel);
		
		//CAPÇALERA FINAL
		
		JTextPane textPane = new JTextPane();
		textPane.setText("");
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(44, 135, 159, 35);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Nom");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_1.setFocusable(false);
		textPane_1.setFocusTraversalKeysEnabled(false);
		textPane_1.setFocusCycleRoot(false);
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.BLACK);
		textPane_1.setBounds(100, 93, 61, 42);
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
		textPane_3.setText("Cognom");
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_3.setFocusable(false);
		textPane_3.setFocusTraversalKeysEnabled(false);
		textPane_3.setFocusCycleRoot(false);
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.BLACK);
		textPane_3.setBounds(304, 93, 103, 42);
		frame.getContentPane().add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("DNI");
		textPane_4.setForeground(Color.WHITE);
		textPane_4.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_4.setFocusable(false);
		textPane_4.setFocusTraversalKeysEnabled(false);
		textPane_4.setFocusCycleRoot(false);
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.BLACK);
		textPane_4.setBounds(105, 181, 47, 42);
		frame.getContentPane().add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("");
		textPane_5.setForeground(Color.BLACK);
		textPane_5.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_5.setFocusTraversalKeysEnabled(false);
		textPane_5.setFocusCycleRoot(false);
		textPane_5.setBackground(Color.WHITE);
		textPane_5.setBounds(44, 223, 159, 35);
		frame.getContentPane().add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("");
		textPane_6.setForeground(Color.BLACK);
		textPane_6.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_6.setFocusTraversalKeysEnabled(false);
		textPane_6.setFocusCycleRoot(false);
		textPane_6.setBackground(Color.WHITE);
		textPane_6.setBounds(268, 223, 159, 35);
		frame.getContentPane().add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("Contrasenya");
		textPane_7.setForeground(Color.WHITE);
		textPane_7.setFont(new Font("HelveticaNeue", Font.PLAIN, 22));
		textPane_7.setFocusable(false);
		textPane_7.setFocusTraversalKeysEnabled(false);
		textPane_7.setFocusCycleRoot(false);
		textPane_7.setEditable(false);
		textPane_7.setBackground(Color.BLACK);
		textPane_7.setBounds(290, 181, 135, 42);
		frame.getContentPane().add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("");
		textPane_8.setForeground(Color.BLACK);
		textPane_8.setFont(new Font("Dialog", Font.PLAIN, 22));
		textPane_8.setFocusTraversalKeysEnabled(false);
		textPane_8.setFocusCycleRoot(false);
		textPane_8.setBackground(Color.WHITE);
		textPane_8.setBounds(44, 310, 383, 35);
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
		textPane_9.setBounds(215, 269, 47, 42);
		frame.getContentPane().add(textPane_9);
		
		JButton button = new JButton("ACABAR I CONTINUAR");
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
				
				if(textPane.getText().length()==0 || textPane_2.getText().length()==0 || textPane_5.getText().length()==0 || textPane_8.getText().length()==0 || textPane_6.getText().length()==0) {
					
					JOptionPane.showMessageDialog(null, "Has deixat camps sense omplir!","ERROR",JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					if(textPane_5.getText().length()>9) {
						
						JOptionPane.showMessageDialog(null, "El DNI és massa llarg","ERROR",JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						sqlU.crearPerfil(textPane.getText(), textPane_2.getText(), textPane_5.getText(), textPane_8.getText(), textPane_6.getText());
						
						try {
							
							LogIn frm = new LogIn();
							frm.frame.setVisible(true);
							frame.setVisible(false);
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
							
						}
						
					}
					
				}
				
			}
			
		});
		
		button.setBounds(159, 377, 159, 42);
		frame.getContentPane().add(button);
	}

}
