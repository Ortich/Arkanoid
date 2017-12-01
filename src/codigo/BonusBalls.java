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
public class BonusBalls extends GRect{
	int xSpeed = 0;
	int ySpeed = 1;
	/**
	 * Construye el Bonus de las pelotas
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
	/**
	 * Ahora creo un método para mover el bonus mientras checkea las colisiones
	 * 
	 * @param _game-Aquí vendra el juego donde estará el Bonus
	 */
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
		//Cuando haya comprobado las colisiones se mueve.
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
			//En este caso añade una nueva bola y quita el Bonus
			_game.add(_game.ball_2,_game.getWidth()/2-_game.bar_1.getWidth(), _game.getHeight()*0.70-_game.ball_2.getHeight());
			_game.multipleBalls=1;
			_game.remove(this);
			collisionClear = false;	
		}
		return collisionClear;
	}
}


