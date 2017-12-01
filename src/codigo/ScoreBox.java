package codigo;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *	
 *	Esta clase crea una caja con la Puntuacion del jugador
 */

public class ScoreBox extends GRect {
	//Primero declaramos un texto donde poner la puntuacion y una variable que reogera los puntos obtenidos
	GLabel text = new GLabel("");
	int points = 0;
	/**
	 * Constructor de la caja
	 * @param width-Ancho de la caja
	 * @param height-Alto de la caja
	 */
	public ScoreBox(double width, double height) {
		super(width, height);//LLama al constructor padre y lo usa para hacer la caja.
		setFilled(true);//La rellena
		setFillColor(Color.GREEN);//La pinta de verda
		text.setLabel("Score: " + 0);//Declara la puntuacion para añadirla mas tarde
		text.setFont(new Font("Verdana", Font.BOLD, 18));//Le da tamaño y fuente 
	}
	/**
	 * Con este método dibuja la caja con el texto dentro
	 * @param _arkanoid-El juego donde se va a meter
	 */
	public void drawScoreBox(Arkanoid _arkanoid){
		_arkanoid.add(this, getX(), getY());//Da coordenadas a la caja 
		_arkanoid.add(text, getX()+10, getY()+30);//Da coordenadas al texto para que vaya dentro de la caja
	}
	/**
	 * Con este método podremos incrementar o disminuir la cantidad de puntos del jugador
	 * @param _points-Puntos a meter
	 */
	public void setMarker(int _points){
		points+=_points;
		text.setLabel("Score: " + points);
	}
}


