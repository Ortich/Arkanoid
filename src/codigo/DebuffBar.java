package codigo;
import java.awt.Color;

import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Debufo de la barra en rojoque dividira por dos el tamaño de la barra.
 */
public class DebuffBar extends GRect{
	/**
	 * Construye un Bonus
	 * 
	 * @param _width - double
	 * 			Ancho del bonus
	 * @param _height - double
	 * 			Alto del bonus
	 * @param _color - Color
	 * 			Color del bonus
	 */
	public DebuffBar(double _width, double _height) {
		super(_width, _height);
		setFilled(true);
		setFillColor(Color.RED);
	}

}
