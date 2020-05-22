package DADES;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import VISTA.CrearUsuari;
import VISTA.LogIn;
import VISTA.Principal;

import java.text.SimpleDateFormat;
public class SQLUsuari {
	Connection c;
	JComponent frame;
	Statement sentencia;
	
	boolean exist=true;
	boolean correct=false;
	
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
	
	public boolean exist() throws SQLException {
		conectar();
		String consultaSql = " SELECT COUNT(*) FROM usuari;";

		try {
			sentencia = c.createStatement();
			ResultSet rs = sentencia.executeQuery(consultaSql);
			System.out.println();
			
			if(rs.getString("COUNT(*)").equals("0")) {
				exist = false;
				System.out.println("No hi ha");
			} else {
				exist = true;
				System.out.println("Hi Ha");
			}
			
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			frame.setVisible(false);
			System.out.println("ERROR");
		}
		return exist;
	}
	
	public void crearPerfil(String nom, String cognom, String dni, String mail, String contrasenya) {
		conectar();
		String consultaSql = "INSERT INTO usuari (nom, cognoms, dni, mail, contrasenya)"+
		"VALUES ("
		+ "'"+nom+"'"
		+","
		+ "'"+cognom+"'"
		+","
		+ "'"+dni.toLowerCase()+"'"
		+","
		+ "'"+mail+"'"
		+","
		+ "'"+contrasenya+"')"
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
	
	public boolean iniciarSessi�(String dni, String contrasenya) throws SQLException {
		conectar();
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM usuari WHERE dni = '"+dni.toLowerCase()+"' AND contrasenya = '"+contrasenya+"';";

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			JOptionPane.showMessageDialog(null, "Benvingut/da, "+rs.getString("nom")+"!","You're In!",JOptionPane.INFORMATION_MESSAGE);
			correct = true;
			
			sentencia.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DNI o Contrasenya incorrectes!","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
		return correct;
	}
	
}
