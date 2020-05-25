package DADES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import MODEL.ComandaCl;
import MODEL.LiniaFacturaCl;

public class SQLFactura {
	
	Connection c;
	JComponent frame;
	Statement sentencia;
	
	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Marc Sanjust\\eclipse-workspace\\ProjecteFiGrau\\src\\DADES\\onTimeDB.db");
			System.out.println("Exito al conectar con base de datos");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar a la base de dades","ERROR",JOptionPane.ERROR_MESSAGE);
		}

		return c;
	}
	
	public void crearLiniaFactura(String idFactura, String descripcio, String quantitat, String preuUnitari, String total) {
		conectar();
		String consultaSql = "INSERT INTO liniaFactura (idFactura, descripcio, quantitat, preuUnitari, total)"+
		"VALUES ("
		+ "'"+idFactura+"'"
		+","
		+ "'"+descripcio+"'"
		+","
		+ "'"+quantitat+"'"
		+","
		+ "'"+preuUnitari+"'"
		+","
		+ "'"+total+"')"
		+ ";";
		
		System.out.println(consultaSql);
		
		try {
			sentencia = c.createStatement();
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	public ArrayList<LiniaFacturaCl> consultarLiniaFactura() throws SQLException {
		conectar();
		ArrayList<LiniaFacturaCl> miLista = new ArrayList<LiniaFacturaCl>();
		LiniaFacturaCl linea = null;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM liniaFactura;";
		
		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				linea = new LiniaFacturaCl(rs.getString("idFactura"), rs.getString("descripcio"), rs.getString("quantitat"), rs.getString("preuUnitari"), rs.getString("total"));
				miLista.add(linea);
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return miLista;
	}
}
