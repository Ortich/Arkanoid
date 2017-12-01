package codigo;
import java.awt.Color;
import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	La clase de la barra del Arkanoid
 */
public class Bar extends GRect {
	/**
	 * Constructor de la barra del Arkanoid
	 * 
	 * @param _width - double
	 * 			Ancho del cursor
	 * @param _height - double
	 * 			Alto del cursor
	 * @param _color - Color
	 * 			Color del cursor
	 */
	public Bar(double _width, double _height, Color _color) {
		super(_width, _height);
		setFilled(true);
		setFillColor(_color);
	}
	/**
	 * Reposiciona la bara usando las coordenadas X e Y, X dada e Y obtenida de la barra.
	 * 
	 * @param xPos - int
	 * 			Posicion en el eje X de la barra
	 * @param _weightScreen - int
	 * 			Ancho de la pantalla
	 */
	public void moveBar(int xPos, int _weightScreen, Arkanoid _game){
		if(xPos + getWidth() < _weightScreen&& xPos>= 0){
			setLocation(xPos, getY());
		}
	}

}
