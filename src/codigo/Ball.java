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
	double magicalCounter = 0;//Para aumentar la velocidad de la bola
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
//			yVelocidad*=0;
//			xVelocidad*=0;
			yVelocidad*=-1;
		}
		//Voy a dividir la bola en 8 puntos para hacer las comprobaciones pertinentes. 
		//Los puntos estaran repartdos de la sieguiente manera.
		//
		//	1---2---3
		//  |       |
		//  8   O   4
		//  |       |
		//  7---6---5
		//
		//
		//Ahora lo que hago es entrar en los if siempre que este lobre y asi pueda compruebe las todas las esquinas. 
		if(checkCollision(getX(), getY(), _game)){//Punto numero 1
			if(checkCollision(getX() +getHeight()/2, getY(), _game)){//Punto numero 2
				if(checkCollision(getX()+getHeight(),getY() , _game)){//Punto numero 3
					if(checkCollision(getX()+getHeight(), getY()+getHeight()/2, _game)){//Punto numero 4
						if(checkCollision(getX()+getHeight(), getY()+getHeight(), _game)){//Punto numero 5
							if(checkCollision(getX()+getHeight()/2, getY()+getHeight(), _game)){//Punto numero 6
								if(checkCollision(getX(), getY()+getHeight(), _game)){//Punto numero 7
									if(checkCollision(getX(), getY()+getHeight()/2, _game)){										
									}
								}								
							}							
						}						
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
		 		
		//-----------------------------
		//TODO Arreglar la colision
		//Ahora ,si ha chocado conun Brick entra aqui
		if (auxiliar instanceof Brick){
			//Voy  a dividir la colision en dos partes. La primera si Choca por arriba o abajo.
			if(auxiliar.getY()+auxiliar.getHeight()<= getY() 	//Si choca con la parte de abajo de un brick
					|| auxiliar.getY()>=getY()){				//O con la parte de arriba de un brick
				yVelocidad*=-1;									//Cambia la velocidad.
			}
			//La segunda si choca por algun lateral.
			else if(auxiliar.getX()+auxiliar.getWidth()>=getX() 		//Si choca con la parte derecha de un brick
					|| auxiliar.getX()<=getX()){				//O la parte izquierda
				xVelocidad*=-1;									//Cambia la velocidad
			}
			_game.remove(auxiliar);
			_game.scoreBox.setMarker(20);
			collisionClear = false;
		}
		
		//------------------------	
		
		//Si ha chocado con la barra
		else if(auxiliar instanceof Bar){
			//Creamos el rebote en las puntas de la barra
			//Para ello vamos a dividir la barra de la siguiente manera:
			//  _______________________
			// |  |  |  |     |  |  |  |
			// |__|__|__|_____|__|__|__|
			//   1  2  3   4    5  6  7
			// La dividimos en 8 partes iguales, uniendo las dos centrales para el golpe "normal".
			// Iremos dividiendo las colisiones una por una, para que vaya detectando donde golpea.
			// 
			// Lista de velocidades para la Y.
			// 1 y 7: 0.5 
			// 2 y 6: 1.0
			// 3 y 5: 2.0
			// 4    : 3.0
			
			//Antes de nada declaramos lo siguiente
			double centroBola = getX() + getWidth()/2;
			//De esta forma tenemos en centro de la pelota para toma como referencia.
			
			
			//Punto 1
			if(auxiliar.getX()<= centroBola && auxiliar.getX()+auxiliar.getWidth()/8>centroBola){
				yVelocidad = -0.5;
			}
			//Punto 2
			if(auxiliar.getX()+auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+auxiliar.getWidth()/4>centroBola){
				yVelocidad = -1.0;
			}
			//Punto 3
			if(auxiliar.getX()+auxiliar.getWidth()/4<=centroBola && auxiliar.getX()+3*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -2.0;
			}
			//Punto 4
			if(auxiliar.getX()+ 3*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+5*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -3;
			}
			//Punto 5
			if(auxiliar.getX() + 5*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+3*auxiliar.getWidth()/4>centroBola){
				yVelocidad = -2.0;
			}
			//Punto 6
			if(auxiliar.getX() + 3*auxiliar.getWidth()/4<=centroBola && auxiliar.getX()+7*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -1.0;
			}
			if(auxiliar.getX() + 7*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+auxiliar.getWidth() >=centroBola){
				yVelocidad = -0.5;
			}
			collisionClear = true;
			//TODO Hacer mas variacion de rebotes 
//			 
//			if(centroBola > auxiliar.getX() + auxiliar.getWidth()/3 &&
//					centroBola< auxiliar.getX() +2*auxiliar.getWidth()/3){
//				yVelocidad=-1;
//				
//			}
//			else{
//				yVelocidad=-0.5;
//			}
//			collisionClear = false;
		}
		
		//-------------------
		
		//Si llega hasta aquí es que no hay colision con nada.
		return collisionClear;
	}
}
