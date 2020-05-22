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

public class SQLProductes {
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
	
	public String consultarProducte(String id) throws SQLException {
		conectar();
		String name = null;
		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM producte WHERE idProducte = '"+id+"';";
		
		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			name = rs.getString("element");
			
			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
		return name;
	}
	
}
