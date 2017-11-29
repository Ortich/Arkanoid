package codigo;
import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GRect;

/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Debufo de la barra en rojoque dividira por dos el tamaño de la barra.
 */
public class DebuffBar extends GRect{
	int xSpeed = 0;
	int ySpeed = 1;
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
	public void moveBonus(Arkanoid _game){
		//	Divido el bonus de la siguiente manera
		//	
		//	1-------------2
		//	|			  |
		//	|			  |
		//	4-------------3
		if(checkBarCollision(getX(), getY(), _game)){//Punto 1
			if(checkBarCollision(getX()+getWidth(), getY(), _game)){//Punto 2
				if(checkBarCollision(getX()+getWidth(), getY()+getHeight(), _game)){//Punto3
					if(checkBarCollision(getX(), getY()+getWidth(), _game)){//Punto4		
					}
				}
			}
		}
		move(xSpeed,ySpeed);
	}

	private boolean checkBarCollision(double posX, double posY, Arkanoid _game){
		boolean collisionClear = true;
		GObject auxiliar;
		auxiliar = _game.getElementAt(posX, posY);
		if(auxiliar instanceof Bar ){
			//Aqui entra si se encuentra con el debuff de la barra
			_game.bar_1.setSize(30, 10);
			_game.remove(auxiliar);
			collisionClear = false;
		}
		return collisionClear;
	}
}
