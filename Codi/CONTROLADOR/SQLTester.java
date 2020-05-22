package CONTROLADOR;

import java.sql.SQLException;
import DADES.*;
import MODEL.ComandaCl;

public class SQLTester {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SQLUsuari sqlU = new SQLUsuari();
		SQLClients sqlC = new SQLClients();
		SQLComandes sqlCo = new SQLComandes();
		SQLProductes sqlP = new SQLProductes();
		//sqlU.conectar();
		//sqlU.exist();
		//sqlU.crearPerfil("", "", "", "", "");
		//sqlU.iniciarSessió("qwd", "qwd");
		//sqlC.consultarClient();
		//sqlCo.consultarComandes();
		//sqlCo.conectar();
		//sqlCo.contarComandesFin();
		//sqlC.crearClient("coco", "carpes", "test", "5", "test");
		//sqlP.crearProducte("Test", "1 per Setmana", "1", "33", "2", "35", "1");
		//sqlP.crearServei("Test", "1 per Setmana", "1", "33", "2", "35");
	}
	
	
}
