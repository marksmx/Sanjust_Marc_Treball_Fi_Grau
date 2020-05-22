package CONTROLADOR;

import java.sql.SQLException;
import DADES.*;

public class SQLTester {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SQLUsuari sqlU = new SQLUsuari();
		//sqlU.conectar();
		//sqlU.exist();
		//sqlU.crearPerfil("", "", "", "", "");
		sqlU.iniciarSessió("qwd", "qwd");
	}
	
	
}
