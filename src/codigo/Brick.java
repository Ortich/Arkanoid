package codigo;
import java.awt.Color;

import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Pos eso, los ladrillos del Arkanoid coñe.
 */
public class Brick extends GRect{
	/**
	 * Construye un ladrillo
	 * 
	 * @param x - double
	 * 			Posicion X del ladrillo
	 * @param y - double
	 * 			Posicion Y del ladrillo
	 * @param _width - double
	 * 			Ancho del ladrillo
	 * @param _height - double
	 * 			Alto del ladrillo
	 * @param _color - Color
	 * 			Color del ladrillo
	 */
	public Brick(double x, double y, double _width, double _height, Color _color) {
		super(x, y, _width, _height);
		setFilled(true);
		setFillColor(_color);
	}

}
