package CONTROLADOR;

import java.sql.SQLException;
import DADES.*;
import MODEL.ComandaCl;


/** FUNCI� PER  */

public class SQLTester {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SQLUsuari sqlU = new SQLUsuari();
		SQLClients sqlC = new SQLClients();
		SQLComandes sqlCo = new SQLComandes();
		SQLProductes sqlP = new SQLProductes();
		sqlC.conectar();
		//sqlU.exist();
		//sqlU.crearPerfil("", "", "", "", "");
		//sqlU.iniciarSessi�("qwd", "qwd");
		//sqlC.consultarClient();
		//sqlCo.consultarComandes();
		//sqlCo.conectar();
		//sqlCo.contarComandesFin();
		//sqlC.crearClient("coco", "carpes", "test", "5", "test");
		//sqlP.crearProducte("Test", "1 per Setmana", "1", "33", "2", "35", "1");
		//sqlP.crearServei("Test", "1 per Setmana", "1", "33", "2", "35");
		//sqlC.modificarClient("1", "BroMomento", "Coco2", "1234", "1", "coco@coco.co");
		//sqlCo.finalitzarComanda("2", "15:10:19.113");
		//System.out.println(sqlP.contarProductes());
		//System.out.println(sqlP.contarServeis());
	}
}
