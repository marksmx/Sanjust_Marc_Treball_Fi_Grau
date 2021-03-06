package DADES;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	
	/** DECLARACI� INICIAL DE VARIABLES I ALTRES */
	
	Connection c;
	JComponent frame;
	Statement sentencia;

	
	/** FUNCIO CONNEXI� A LA BASE DE DADES */

	public Connection conectar() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:onTimeDB.db");
			System.out.println("Exito al conectar con base de datos");

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al conectar a la base de dades","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}

		return c;
	}
	
	
	/** FUNCIO PER A CREAR UNA COMANDA NOVA */
	
	public void crearComanda(String estatComanda, String numComanda, String idProducte, String data, String hores, String total, String idEmpresa, String dataLimit, String descripcio) {
		
		conectar();
		String consultaSql = "INSERT INTO comanda (estatComanda, numComanda, idProducte, data, hores, total, idEmpresa, pagat, dataLimit, descripcio)"+
		"VALUES ("
		+ "'"+estatComanda+"'"
		+","
		+ "'"+numComanda+"'"
		+","
		+ "'"+idProducte+"'"
		+","
		+ "'"+data+"'"
		+","
		+ "'"+hores+"'"
		+","
		+ "'"+total+"'"
		+","
		+ "'"+idEmpresa+"'"
		+","
		+ "'no'"
		+","
		+ "'"+dataLimit+"'"
		+","
		+ "'"+descripcio+"')"
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
	
	
	/** FUNCIO PER A CONSULTAR UNA COMANDA EXISTENT */

	public ArrayList<ComandaCl> consultarComandes() throws SQLException {
		
		conectar();
		ArrayList<ComandaCl> miLista = new ArrayList<ComandaCl>();
		ComandaCl com = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM comanda;";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				
				String test = rs.getString("estatComanda");
				com = new ComandaCl(rs.getString("estatComanda"), rs.getString("numComanda"), rs.getString("idProducte"), rs.getString("data"), rs.getString("hores"), rs.getString("total"), rs.getString("idEmpresa"),rs.getString("pagat"), rs.getString("dataLimit"), rs.getString("descripcio"));
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
	
	
	/** FUNCIO PER A ELIMINAR UNA COMANDA EXISTENT */

	public void eliminarComandes(String numC, String idEmp) throws SQLException {
		
		conectar();
		sentencia = c.createStatement();
		String consultaSql = "DELETE FROM comanda WHERE numComanda = '"+numC+"' AND idEmpresa = '"+idEmp+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
	}
	
	
	/** FUNCIO PER A CONSULTAR UNA COMANDA EXISTENT A TRAV�S DEL ID DEL CLIENT */

	public ArrayList<ComandaCl> consultarComandesClient(String idEmpresa) throws SQLException {
		
		conectar();
		ArrayList<ComandaCl> miLista = new ArrayList<ComandaCl>();
		ComandaCl com = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM comanda WHERE idEmpresa = '"+idEmpresa+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				
				String test = rs.getString("estatComanda");
				com = new ComandaCl(rs.getString("estatComanda"), rs.getString("numComanda"), rs.getString("idProducte"), rs.getString("data"), rs.getString("hores"), rs.getString("total"), rs.getString("idEmpresa"),rs.getString("pagat"), rs.getString("dataLimit"),rs.getString("descripcio"));
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
	
	
	/** FUNCIO PER A CONSULTAR ELS ARTICLES QUE COMPOSEN UNA COMANDA EXISTENT */

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
	
	
	/** FUNCIO PER A CONSULTAR EL ID D'UNA COMANDA EXISTENT A TRAV�S DEL SEU ID DE COMANDA */

	public String consultarNumComanda(String id) throws SQLException {
		
		conectar();
		sentencia = c.createStatement();
		String num = null;
		String consultaSql = "SELECT numComanda FROM comanda WHERE numComanda = '"+id+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			num = rs.getString("numComanda");
			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		return num;
		
	}

	
	/** FUNCIO PER A CONSULTAR LA DATA D'UNA COMANDA EXISTENT A TRAV�S DEL SEU ID DE COMANDA  */

	public String consultarDataComandes(String id) throws SQLException {
		
		conectar();
		String data = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT data FROM comanda WHERE numComanda = '"+id+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				
				data = rs.getString("data");
				
			}
			
			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		return data;
		
	}
	
	
	/** FUNCIO PER A FER UN RECOMPTE DE COMANDES EXISTENT */

	public int contarComandes() throws SQLException, ClassNotFoundException {
		
		conectar();
		int amount = 0;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM comanda;";
		ResultSet rs = sentencia.executeQuery(consultaSql);
		
		while (rs.next()) {
			
			++amount;
			
		}
			
		rs.close();
		sentencia.close();
		c.close();
		return amount;
		
	}
	

	/** FUNCIO PER A FER UN RECOMPTE DE COMANDES EXISTENTS A TRAV�S DEL ID D'UN CLIENT */

	public int contarComandesClient(String idEmpresa) throws SQLException, ClassNotFoundException {
		
		conectar();
		int amount = 0;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM comanda WHERE idEmpresa = '"+idEmpresa+"';";
		ResultSet rs = sentencia.executeQuery(consultaSql);
		
		while (rs.next()) {
			
			++amount;
			
		}
			
		rs.close();
		sentencia.close();
		c.close();
		return amount;
		
	}
	
	
	/** FUNCIO PER A CONSULTAR UNA COMANDA EXISTENT */

	public int contarComandesFin() throws SQLException, ClassNotFoundException {
		
		conectar();
		int amount = 0;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM comanda WHERE estatComanda = 'f';";
		ResultSet rs = sentencia.executeQuery(consultaSql);

		while (rs.next()) {
			
			++amount;
			
		}
		
		rs.close();
		sentencia.close();
		c.close();
		return amount;
		
	}
	
	
	/** FUNCIO PER A FER UN RECOMPTE DE COMANDES EXISTENTS EN PROC�S */

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

	
	/** FUNCIO PER A FER UN RECOMPTE DE COMANDES EXISTENTS PENDENTS */
	
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
	
	
	/** FUNCIO PER A FER UN RECOMPTE DE COMANDES EXISTENTS A TRAV�S DEL ID D'UN PRODUCTE I DEL ID D'UN CLIENT */
	
	public int contarComandesPerProd(String idProd, String idEmpresa) throws SQLException, ClassNotFoundException {
		
		conectar();
		int amount = 0;
		sentencia = c.createStatement();
		String consultaSql = "SELECT idProducte FROM comanda WHERE idProducte = '"+idProd+"' AND idEmpresa = '"+idEmpresa+"';";
		ResultSet rs = sentencia.executeQuery(consultaSql);
	
		while (rs.next()) {
			
			++amount;
			
		}
		
		rs.close();
		sentencia.close();
		c.close();
		return amount;
		
	}

	
	/** FUNCIO PER A CONSULTAR L'ESTAT D'UNA COMANDA EXISTENTS A TRAV�S DEL SEU ID */
	
	public String consultarEstatComanda(String num) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT estatComanda FROM comanda WHERE numComanda = '"+num+"';";
		ResultSet rs = sentencia.executeQuery(consultaSql);
		stat = rs.getString("estatComanda");
		rs.close();
		sentencia.close();
		c.close();
		return stat;
		
	}
	
	
	/** FUNCIO PER A A OMPLIR EL TAULER RECORDATORI DE COMANDES */
	
	public String omplirTauler(String num) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT estatComanda FROM comanda WHERE numComanda = '"+num+"' AND estatComanda = 'ep' AND estatComanda = 'p';";
		ResultSet rs = sentencia.executeQuery(consultaSql);
		stat = rs.getString("estatComanda");
		rs.close();
		sentencia.close();
		c.close();
		return stat;
		
	}
	
	
	/** FUNCIO PER A MODIFICAR L'ESTAT D'UNA COMANDA EXISTENT A TRAV�S DEL SEU ID */

	public String modificarEstatComanda(String est, String nCom) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		sentencia = c.createStatement();
		String consultaSql = "UPDATE comanda SET estatComanda = '"+est+"' WHERE numComanda = '"+nCom+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		sentencia.close();
		c.close();
		return stat;
		
	}
	
	
	/** FUNCIO PER A MARCAR UNA COMANDA EXISTENT COM A PAGADA EN BASE AL SEU ID */

	public String modificarPagament(String nCom) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		LocalDate dataS = java.time.LocalDate.now();  
		sentencia = c.createStatement();
		String consultaSql = "UPDATE comanda SET pagat = 'si / "+dataS+"' WHERE numComanda = '"+nCom+"';";

		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		sentencia.close();
		c.close();
		return stat;
		
	}
	
	
	/** FUNCIO PER A MARCAR UNA COMANDA EXISTENT COM A INICIADA EN BASE AL SEU ID */

	public String iniciarComanda(String nCom) throws SQLException, ClassNotFoundException {
		
		conectar();
		String hora = java.time.LocalTime.now().toString();
		sentencia = c.createStatement();
		String consultaSql = "UPDATE comanda SET hores = '"+hora+"' WHERE numComanda = '"+nCom+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		sentencia.close();
		c.close();
		return hora;
		
	}
	

	/** FUNCIO PER A MARCAR UNA COMANDA EXISTENT COM A FINALITZADA EN BASE AL SEU ID, TAMB� CALCULAR� QUANTES HORES HEM TARDAT A REALITZAR LA COMANDA */

	public String finalitzarComanda(String nCom, String priHora) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		sentencia = c.createStatement();

		int tHores = 0;
		int horaA = 0;
		int minutA = 0;
		
		horaA += java.time.LocalTime.now().toString().charAt(0);
		horaA += java.time.LocalTime.now().toString().charAt(1);
		minutA += java.time.LocalTime.now().toString().charAt(3);
		minutA += java.time.LocalTime.now().toString().charAt(4);
		
		int horaP = 0;
		int minutP = 0;
		horaP += priHora.charAt(0);
		horaP += priHora.charAt(1);
		minutP += priHora.charAt(3);
		minutP += priHora.charAt(4);
		
		if((horaA - horaP) == 0) {
			
			tHores = 1;
			
		} 
		
		if((horaA - horaP) > 0 && (minutA - minutP) == 0) {
			
			tHores = horaA - horaP;
			
		}
		
		if((horaA - horaP) == 0 && (minutA - minutP) > 0) {
			
			tHores = 1;
			
		}
		
		if((horaA - horaP) > 0 && (minutA - minutP) > 0) {
			
			tHores = (horaA - horaP)+1;
			
		}
		
		String consultaSql = "UPDATE comanda SET hores = '"+Integer.toString(tHores)+"' WHERE numComanda = '"+nCom+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		sentencia.close();
		c.close();
		return Integer.toString(tHores);
		
	}
	
	
	/** FUNCIO PER A CALCULAR EL COST TOTAL  D'UNA COMANDA EXISTENT EN BASE AL SEU ID */

	public String incrementarPreu(String nCom, String costAct) throws SQLException, ClassNotFoundException {
		
		conectar();
		String hora = java.time.LocalTime.now().toString();
		sentencia = c.createStatement();
		String consultaSql = "UPDATE comanda SET total = '"+costAct+"' WHERE numComanda = '"+nCom+"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println("ERROR");
			
		}
		
		sentencia.close();
		c.close();
		return hora;
		
	}

	
	/** FUNCIO PER A VEURE LA DESCRIPCI�  D'UNA COMANDA EXISTENT EN BASE AL SEU ID */
	
	public String veureDescripcio(String numComanda) throws SQLException, ClassNotFoundException {
		
		conectar();
		String stat = null;
		sentencia = c.createStatement();
		
		String consultaSql = "SELECT descripcio FROM comanda WHERE numComanda = '"+numComanda+"';";
		ResultSet rs = sentencia.executeQuery(consultaSql);
		stat = rs.getString("descripcio");
		rs.close();
		sentencia.close();
		c.close();
			
		return stat;
		
	}
	
}
