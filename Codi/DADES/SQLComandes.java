package DADES;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import MODEL.*;
import VISTA.CrearUsuari;
import VISTA.LogIn;
import VISTA.Principal;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
public class SQLComandes {
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
	
	public ArrayList<ComandaCl> consultarComandes() throws SQLException {
		conectar();
		ArrayList<ComandaCl> miLista = new ArrayList<ComandaCl>();
		ComandaCl com = null;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM comanda;";
		
		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			System.out.println(consultaSql);
			
			while (rs.next()) {
				String test = rs.getString("estatComanda");
				System.out.println(test);
				
				com = new ComandaCl(rs.getString("estatComanda"), rs.getString("numComanda"), rs.getString("idProducte"), rs.getString("data"), rs.getString("hores"), rs.getString("base"), rs.getString("iva"), rs.getString("total"), rs.getString("idEmpresa"));

				miLista.add(com);
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return miLista;
		
	}
	
	public String consultarArticleComanda(String id) throws SQLException {
		conectar();
		sentencia = c.createStatement();
		
		String consultaSql = " SELECT * FROM producte WHERE idProducte = '"+id+"';";
		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return consultaSql;

	}
	
	public int contarComandesFin() throws SQLException, ClassNotFoundException {
		conectar();
		int amount = 0;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM comanda WHERE estatComanda = 'f';";
		
			ResultSet rs = sentencia.executeQuery(consultaSql);
			System.out.println(consultaSql);
		
			while (rs.next()) {
				++amount;
			}
			
			rs.close();
			sentencia.close();
			c.close();
		return amount;
		
	}
	
	public int contarComandesEnProces() throws SQLException, ClassNotFoundException {
		conectar();
		int amount = 0;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM comanda WHERE estatComanda = 'ep';";
		
			ResultSet rs = sentencia.executeQuery(consultaSql);
		
			while (rs.next()) {
				++amount;
			}
			
			rs.close();
			sentencia.close();
			c.close();
		return amount;
		
	}
	
	public int contarComandesP() throws SQLException, ClassNotFoundException {
		conectar();
		int amount = 0;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM comanda WHERE estatComanda = 'p';";
		
			ResultSet rs = sentencia.executeQuery(consultaSql);
		
			while (rs.next()) {
				++amount;
			}
			
			rs.close();
			sentencia.close();
			c.close();
			
		return amount;
		
	}
}
