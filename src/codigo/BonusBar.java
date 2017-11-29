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
}
//	public void moveBonus(Arkanoid _game){
//		
//	}
//}
//
////Voy a dividir la barra en 3 partes
//if(checkBarCollision(getX()+25, getY(), _game)){
//	if(checkBarCollision(getX()+25, getY()-15, _game)){
//		if(checkBarCollision(getX(), getY(), _game)){
//			if(checkBarCollision(getX(), getY()-15, _game)){
//				if(checkBarCollision(getX()+getWidth(), getY(), _game)){
//					if(checkBarCollision(getX()+getWidth(), getY()-15, _game)){
//
//					}
//				}
//			}
//		}
//	}
//}
//}
//private boolean checkBarCollision(double posX, double posY, Arkanoid _game ){
//boolean checkCollisionClear = true;
//GObject auxiliar;
//auxiliar = _game.getElementAt(posX, posY);
////Aqui entra si se encuentra con el bonus de la barra
//if(auxiliar instanceof BonusBar){
//	_game.bar_1.setSize(120, 10);
//	_game.remove(auxiliar);
//	checkCollisionClear = false;
//}
////-----------------------------------
////Aqui entra si se encuentra con el debuff de la barra
//else if(auxiliar instanceof DebuffBar){
//	_game.bar_1.setSize(30, 10);
//	_game.remove(auxiliar);
//	checkCollisionClear = false;
//}
////--------------------------------
////Aqui entra si se encuentra con el debuff de la barra
//else if(auxiliar instanceof BonusBalls){
//	_game.add(_game.ball_2,_game.getWidth()/2-_game.bar_1.getWidth(), _game.getHeight()*0.70-_game.ball_2.getHeight());
//	_game.multipleBalls=1;
//	_game.remove(auxiliar);
//	checkCollisionClear = false;
//}
//return checkCollisionClear;
//}
