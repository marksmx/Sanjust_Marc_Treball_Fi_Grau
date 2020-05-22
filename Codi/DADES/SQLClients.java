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
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Marc Sanjust\\eclipse-workspace\\OnTimeAgency\\src\\DADES\\onTimeDB.db");
			System.out.println("Exito al conectar con base de datos");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar a la base de dades","ERROR",JOptionPane.ERROR_MESSAGE);
		}

		return c;
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
				System.out.println(rs.getString("empresa"));
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
	
}
