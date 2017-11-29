package codigo;
import java.awt.Color;

import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Bonus para la barra en verde que hara la barra el doble de grande.
 */
public class BonusBalls extends GRect{
	/**
	 * Construye un bonus
	 * 
	 * @param _width - double
	 * 			Ancho del bonus
	 * @param _height - double
	 * 			Alto del bonus
	 * @param _color - Color
	 * 			Color del bonus
	 */
	public BonusBalls(double _width, double _height) {
		super(_width, _height);
		setFilled(true);
		setFillColor(Color.BLUE);
	}

}
