/**
 * 
 */
package vista;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * 
 */
public class VentanaOficial extends JFrame {
	public VentanaOficial(String titulo) {
		super(titulo);
		configInicial();
		inicializarComponentes();
	}
	
	private void configInicial() {
		// ventana se cierra con la X
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// AbsoluteLayout (ponemos los componentes donde nos dé la gana)
		getContentPane().setLayout(null);
		
		// tamaño de la ventana
		setSize(600, 400);
	}
	
	private void inicializarComponentes() {
		
	}
}
