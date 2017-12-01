package codigo;
import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Bonus para la barra en verde que hara la barra el doble de grande.
 */
public class BonusBar extends GRect{
	int xSpeed = 0;
	int ySpeed = 1;
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
	public BonusBar(double _width, double _height) {
		super(_width, _height);
		setFilled(true);
		setFillColor(Color.GREEN);
	}
	public void moveBonus(Arkanoid _game){
		//	Divido el bonus de la siguiente manera y hago los checkeos en cada punto
		//	
		//	1-------------2
		//	|			  |
		//	|			  |
		//	4-------------3
		if(checkBarCollision((int)getX(), (int)getY(), _game)){//Punto 1
			if((checkBarCollision((int)getX()+(int)getWidth(), (int)getY(), _game))){//Punto 2
				if((checkBarCollision((int)getX()+(int)getWidth(), (int)getY()+(int)getHeight(), _game))){//Punto3
					if(checkBarCollision((int)getX(), (int)getY()+(int)getWidth(), _game)){//Punto4		
					}
				}
			}
		}
		//Se mueve cuando haya comprobado las colisiones
		move(xSpeed,ySpeed);
	}
	/**
	 * Para hacer las comprobaciones llamo  a este método con las siguientes cosas y voy comprobando si hay algo. 
	 * @param posX-Posicion x a comprobar
	 * @param posY-Posicion y a comprobar
	 * @param _game-Juego donde se ejecutara
	 * @return-Retorna verdadero si no ha colisionado
	 */
	private boolean checkBarCollision(int posX, int posY, Arkanoid _game){
		boolean collisionClear = true;//Primero declaro el boolean que dira si ha chocado
		GObject auxiliar;//Objeto auxiliar que estara vacio si no hay colisionado
		auxiliar = _game.getElementAt((int)posX, (int)posY);//El elemento que hay en la posicion lo guarda en auxiliar
		if(auxiliar instanceof Bar ){//Si es la barra ejecuta el Bonus
			//En este caso la barra se hace el doble de grande
			_game.bar_1.setSize(120, 10);
			_game.remove(this);
			collisionClear = false;
		}
		return collisionClear;
	}
}
