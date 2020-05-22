package VISTA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import DADES.SQLComandes;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Test {

	private JFrame frame;
	SQLComandes sqlCo = new SQLComandes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public void paint(Graphics g){
    	boolean bandera = true;
    	
        //super.paint(g);
        if(bandera == true){
            
            int int_rojo = 0;
            int int_verde = 0;
            int int_azul = 0;
            
			try {
				int_verde = sqlCo.contarComandesFin();
				int_rojo = sqlCo.contarComandesEnProces();
		        int_azul = sqlCo.contarComandesP();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            
            int total_votos = int_rojo + int_verde + int_azul;
            
            int grados_rojo = int_rojo * 360 / total_votos;
            int grados_verde = int_verde * 360 / total_votos;
            int grados_azul = int_azul * 360 / total_votos;
            
            g.setColor(new Color(255, 0, 0));
            g.fillArc(25, 80, 200, 200, 0, grados_rojo);
            
            g.setColor(new Color(0, 130, 0));
            g.fillArc(25, 80, 200, 200, grados_rojo, grados_verde);
            
            g.setColor(new Color(0, 0, 255));
            g.fillArc(25, 80, 200, 200, grados_rojo + grados_verde, grados_azul);
        }
        
    }
}
