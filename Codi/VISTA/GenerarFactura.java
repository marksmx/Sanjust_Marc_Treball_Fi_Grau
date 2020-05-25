package VISTA;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.omg.CORBA.portable.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DADES.SQLComandes;
import DADES.SQLFactura;
import MODEL.ComandaCl;
import MODEL.LiniaFacturaCl;
/**
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
*/
import javax.swing.JComboBox;



public class GenerarFactura {

	SQLFactura sqlF = new SQLFactura();
	SQLComandes sqlC = new SQLComandes();

	JFrame frame;
	private JTextField textField;
	private String idEmpresa;

	/**
	 * Create the application.
	 */
	public GenerarFactura(String idEmpresa) {
		this.idEmpresa = idEmpresa;
		initialize();
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
		frame.setBounds(730, 300, 591, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
	//CAPÇALERA COMENÇAMENT
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 588, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextField txtLogIn = new JTextField();
		txtLogIn.setBounds(126, 11, 375, 60);
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
		
		textField = new JTextField();
		textField.setText("Mes a Facturar");
		textField.setSelectionColor(Color.GRAY);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("HelveticaNeue", Font.PLAIN, 30));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.BLACK);
		textField.setAutoscrolls(false);
		textField.setBounds(194, 104, 212, 60);
		frame.getContentPane().add(textField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(204, 175, 190, 33);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton_1 = new JButton("GENERAR FACTURA");
		btnNewButton_1.setVisible(true);
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
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String ruta = "C:\\Users\\Marc Sanjust\\Desktop\\test";
				String contingut = "ID Comanda   Descripció    Unitats    Preu Unitat    Preu Total"+"\n";
					try {
						for(int i=0; sqlC.contarComandes()>=i; i++) {
							ArrayList<ComandaCl> miLista = sqlC.consultarComandesClient(idEmpresa);
							int unitats = 1;
							//System.out.println("1" + miLista.get(i).getDescripcio() + Integer.toString(unitats) + miLista.get(i).getTotal() + miLista.get(i).getTotal());
							sqlF.crearLiniaFactura("1", miLista.get(i).getDescripcio(), Integer.toString(unitats), miLista.get(i).getTotal(), miLista.get(i).getTotal());
							contingut += "1"+ "    " + miLista.get(i).getDescripcio()+ "    " + Integer.toString(unitats) + "    "+ miLista.get(i).getTotal() + "    " + miLista.get(i).getTotal() +"\n";
							FileOutputStream pdf = new FileOutputStream(ruta+".pdf");
							Document doc = new Document();
							PdfWriter.getInstance(doc, pdf);
							doc.open();
							doc.add(new Paragraph(contingut));
							doc.close();
						}

					} catch (Exception e2) {
						System.out.println("bruh");
					}
			}
		});
		btnNewButton_1.setBounds(204, 233, 190, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("TORNAR A INICI");
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
			}
		});
		btnNewButton_2.setBounds(204, 314, 190, 51);
		frame.getContentPane().add(btnNewButton_2);
		
		
	}
}
