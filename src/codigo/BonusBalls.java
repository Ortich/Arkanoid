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
			_game.add(_game.ball_2,_game.getWidth()/2-_game.bar_1.getWidth(), _game.getHeight()*0.70-_game.ball_2.getHeight());
			_game.multipleBalls=1;
			_game.remove(this);
			collisionClear = false;	
		}
		return collisionClear;
	}
}


