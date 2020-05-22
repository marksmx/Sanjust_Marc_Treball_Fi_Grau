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



public class SQLClients {
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
	
	public void crearClient(String empresa, String concepte, String nif, String id, String mail) {
		conectar();
		String consultaSql = "INSERT INTO client (empresa, concepte, nif, id, mail)"+
		"VALUES ("
		+ "'"+empresa+"'"
		+","
		+ "'"+concepte+"'"
		+","
		+ "'"+nif.toLowerCase()+"'"
		+","
		+ "'"+id+"'"
		+","
		+ "'"+mail+"')"
		+ ";";
		
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
	
	
	public ArrayList<ClientCl> consultarClient() throws SQLException {
		conectar();
		ArrayList<ClientCl> miLista = new ArrayList<ClientCl>();
		ClientCl cli = null;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM client;";

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				cli = new ClientCl(rs.getString("empresa"), rs.getString("concepte"), rs.getString("nif"), rs.getString("id"), rs.getString("mail"));
				miLista.add(cli);
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return miLista;
	}
	
	public String consultarNomClient(String id) throws SQLException {
		conectar();
		String fullName = null;
		
		sentencia = c.createStatement();

		String consultaSql = "SELECT concepte FROM client WHERE id = '"+id+"';";
		System.out.println(consultaSql);

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				fullName = rs.getString("concepte");
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return fullName;
	}
	
	public String consultarEmpresaClient(String id) throws SQLException {
		conectar();
		String fullName = null;
		
		sentencia = c.createStatement();

		String consultaSql = "SELECT empresa FROM client WHERE id = '"+id+"';";
		System.out.println(consultaSql);

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				fullName = rs.getString("empresa");
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return fullName;
	}
	
	public String consultarMailClient(String id) throws SQLException {
		conectar();
		String fullName = null;
		
		sentencia = c.createStatement();

		String consultaSql = "SELECT mail FROM client WHERE id = '"+id+"';";
		System.out.println(consultaSql);

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				fullName = rs.getString("mail");
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return fullName;
	}
	
	public int recompteClients() throws SQLException {
		conectar();
		int rec = 1;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM client;";

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				++rec;
			}
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return rec;
	}
}
