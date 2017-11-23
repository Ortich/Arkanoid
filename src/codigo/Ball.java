package codigo;
import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;
/**
 * 
 * @author Daniel Ortiz Vallejo
 *	Clase de las pelotas
 *	(Interpretalo a tu gusto)
 */
public class Ball extends GOval{

	double xVelocidad = 3;
	double yVelocidad = -3;
	/**
	 * Constructor tal y como lo usa la clase GOval
	 * @param ancho
	 * 			ancho de la pelota
	 * @param alto
	 * 			alto de la pelota
	 */
	public Ball(double _width, double _height){
		super(_width, _height);
	}
	/**
	 * Este constructor recibe un solo parámetro, el radio, dado que solo hay bolas. Luego rellena  la bola. 
	 * @param Radio
	 * 			El radio de la bola
	 * @param Color
	 * 			El color de la bola
	 */
	public Ball(double _width, Color _color){
		super(_width, _width);
		if(_width<=0){
			this.setSize(1, 1);
		}
		this.setFillColor(_color);
		this.setFilled(true);
	}
	/**
	 * Se encarga de mover a la ball y checkear si ha habido colisiones
	 */
	public void moveBall(Arkanoid _game){
		if(this.getX() + this.getWidth() >= _game.getWidth()||this.getX()<0){
			xVelocidad *=-1;
		}
		if(this.getY()<=0){
			yVelocidad *=-1;
		}
		if(this.getY()>=_game.getHeight()-this.getWidth()){
			yVelocidad*=0;
			xVelocidad*=0;
		}

		if(checkCollision(getX(), getY(), _game)){//esquina superior izquierda
			if(checkCollision(getX()+getHeight(), getY(), _game)){//esquina superior derecha
				if(checkCollision(getX(), getY()+getHeight(), _game)){//esquina inferior izquierda
					if(checkCollision(getX()+getHeight(), getY()+getHeight(), _game)){//esquina inferior derecha

					}

				}
			}
		}
		move(xVelocidad,yVelocidad);
	}
	//checkeo generico para saber con que ha colisionado la bola.
	private boolean checkCollision(double posX, double posY, Arkanoid _game){
		boolean collisionClear = true;
		GObject auxiliar;
		auxiliar = _game.getElementAt(posX, posY);

		if (auxiliar instanceof Brick){
			if(auxiliar.getY() == posY || auxiliar.getY()+auxiliar.getHeight() == posY){
				yVelocidad*=-1;
			}
			else if(auxiliar.getX() == posX || auxiliar.getX()+auxiliar.getWidth() == posX){
				xVelocidad*=-1;
			}
			_game.remove(auxiliar);
			collisionClear = false;
		}
		else if(auxiliar instanceof Bar){
			yVelocidad*=-1;
			collisionClear = false;
		}
		return collisionClear;
	}
}
