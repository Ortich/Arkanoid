package codigo;

import java.awt.Color;
import java.awt.Font;
import acm.graphics.GLabel;
import acm.graphics.GRect;
/**
 * 
 * @author Daniel Ortiz Vallejo	
 * Esta clase medira las vidas del jugador
 *
 */
public class Lifes extends GRect {
	//Primero declaramos el texto donde iran las vidas y el contador de vidas
	GLabel text = new GLabel("");
	int lifes = 0;
	/**
	 * Constructor de las Vidas
	 * @param width-Ancho de la caja
	 * @param height-Alto de la caja
	 * @param _lifes-Numero de vidas a meter dentro
	 */
	public Lifes(double width, double height, int _lifes) {
		super(width, height);
		lifes = _lifes;
		setFilled(true);
		setFillColor(Color.CYAN);//El recuadro del contador de vidas irá relleno y con un color en concreto.
		text.setLabel("Lifes:"+lifes);
		text.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	
	/**
	 * Dibuja  las vidas
	 * @param _arkanoid-Como hay que modificar el juego, se añade el donde usarlo. En este caso irá en Arkanoid
	 */
	public void drawLifesBox(Arkanoid _arkanoid){
		_arkanoid.add(this, getX(), getY());//Añade el recuadro al juego
		_arkanoid.add(text, getX()+10, getY()+30);//Añade el texto en el recuadro
	}
	
	/**
	 * Este método añadirá vidas cuando sea necesario
	 * @param moreLifes-Vidas a sumar
	 */
	public void setMoreLifes(int moreLifes){
		lifes += moreLifes;//Simplemente añade el número de vidas a meter
		text.setLabel("Lifes: " + lifes);//Y las muestra en pantalla
	}
	
	/**
	 * Hace lo mismo que el superior, pero restando en este caso.
	 * @param lessLifes
	 */
	public void setLessLifes(int lessLifes){
		lifes -= lessLifes;
		text.setLabel("Lifes " + lifes);
	}
}
