package VISTA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextPane;

import DADES.SQLClients;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.SwingConstants;

public class Info {
	
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	SQLClients sqlC = new SQLClients();
	
	private String idClient;
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Info(String idClient) throws SQLException {
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
		frame.setBounds(750, 400, 500, 300);
		frame.getContentPane().setLayout(null);

		
		textField = new JTextField();
		textField.setText("Informació Client");
		textField.setSelectionColor(Color.GRAY);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("HelveticaNeue", Font.PLAIN, 50));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.BLACK);
		textField.setAutoscrolls(false);
		textField.setBounds(10, 11, 399, 60);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("EMPRESA");
		textField_1.setSelectionColor(Color.GRAY);
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_1.setFocusable(false);
		textField_1.setFocusTraversalKeysEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.BLACK);
		textField_1.setAutoscrolls(false);
		textField_1.setBounds(35, 82, 111, 41);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setText("CONCEPTE");
		textField_3.setSelectionColor(Color.GRAY);
		textField_3.setForeground(Color.LIGHT_GRAY);
		textField_3.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_3.setFocusable(false);
		textField_3.setFocusTraversalKeysEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_3.setBackground(Color.BLACK);
		textField_3.setAutoscrolls(false);
		textField_3.setBounds(274, 82, 114, 41);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("MAIL");
		textField_4.setSelectionColor(Color.GRAY);
		textField_4.setForeground(Color.LIGHT_GRAY);
		textField_4.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_4.setFocusable(false);
		textField_4.setFocusTraversalKeysEnabled(false);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(Color.BLACK);
		textField_4.setAutoscrolls(false);
		textField_4.setBounds(193, 153, 53, 41);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText(sqlC.consultarNomClient(idClient));
		textField_5.setSelectionColor(Color.GRAY);
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_5.setFocusable(false);
		textField_5.setFocusTraversalKeysEnabled(false);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		textField_5.setBackground(Color.BLACK);
		textField_5.setAutoscrolls(false);
		textField_5.setBounds(10, 120, 163, 41);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setText(sqlC.consultarMailClient(idClient));
		textField_6.setSelectionColor(Color.GRAY);
		textField_6.setForeground(Color.WHITE);
		textField_6.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_6.setFocusable(false);
		textField_6.setFocusTraversalKeysEnabled(false);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBorder(null);
		textField_6.setBackground(Color.BLACK);
		textField_6.setAutoscrolls(false);
		textField_6.setBounds(119, 195, 231, 41);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setText(sqlC.consultarEmpresaClient(idClient));
		textField_7.setSelectionColor(Color.GRAY);
		textField_7.setForeground(Color.WHITE);
		textField_7.setFont(new Font("HelveticaNeue", Font.PLAIN, 20));
		textField_7.setFocusable(false);
		textField_7.setFocusTraversalKeysEnabled(false);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBorder(null);
		textField_7.setBackground(Color.BLACK);
		textField_7.setAutoscrolls(false);
		textField_7.setBounds(210, 120, 249, 41);
		frame.getContentPane().add(textField_7);
		
	}
}
