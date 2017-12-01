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
	//Primero definimos unas velocidades fijas para la bola. Mas tarde se podrá ir modificando.
	double xVelocidad = 1;
	double yVelocidad = -1;
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
	 * Se encarga de mover a la bola y checkear si hay colisiones
	 */
	public void moveBall(Arkanoid _game){
		
		//Primero mira si esta en alguna pared, si lo esta da la vuelta.
		if(this.getX() + this.getWidth() >= _game.getWidth()||this.getX()<0){
			xVelocidad *=-1;
		}
		//Luego mira si ha llegado ariba, si lo está da la vuelta.
		if(this.getY()<=0){
			yVelocidad *=-1;
		}
		//Por último mira si ha llegado abajo, si es el caso hace lo siguiente.
		if(this.getY()>=_game.getHeight()-this.getWidth()){
			//yVelocidad*=-1;      //Esta hecho para hacer comprobaciones. Simplemente rebota en el suelo
			
			yVelocidad*=0;//Primero para las dos velocidades.
			xVelocidad*=0;
			_game.lifes.setLessLifes(1);//Quita una vida
			_game.ball_1.setLocation(_game.bar_1.getX(), _game.getHeight()*0.82-getHeight());//Reposiciona la bola en la barra
			_game.speed=10;//Reestablece la velocidad a la inicial
			pause(600);//Se espera un momento para que el jugador sepa qué está pasando.
			xVelocidad = 1;//Vuelve a empezar a moverse
			yVelocidad = -1;
		}
		//Ahora toca hacer los checkeos con otrosobjetos
		//Para ello voy a dividir la bola en 8 puntos para hacer las comprobaciones pertinentes. 
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
		move(xVelocidad,yVelocidad);//Cuando todo este listo, muevo la pelota.
	}
	/**
	 * Con este método checkeo si hay colisiones,si las hay, dependiendo de qué sea hará unas cosas u otras.
	 * 
	 * @param posX - Posicion x a comprobar
	 * @param posY - Posicion y a comprobar 
	 * @param _game - Esto me vale para modificar cierto parametros
	 * @return Devuelve verdadero si no ha chocado, falso si lo ha hecho.
	 */
	private boolean checkCollision(double posX, double posY, Arkanoid _game){
		boolean collisionClear = true;//Primero creamos una variable que nos dirá si el camino esta libre.
		GObject auxiliar;//Ahora delaramos un objeto que nos servirá de apoyo. 
		auxiliar = _game.getElementAt(posX, posY);//A ese objeto le decimos que se "transforme" en aquello que esta tocando la bola

		//-----------------------------
		
		//Si ha chocado con un Brick entra aqui
		if (auxiliar instanceof Brick){
			//Si ha chocado por arriba o abajo cambia la velocidad y
			if((int)auxiliar.getY()+(int)auxiliar.getHeight()== (int)posY|| (int)auxiliar.getY()==(int)posY){
				yVelocidad*=-1;
			}
			//Si ha chocado por algun lateral, cambia la velocidad x
			else if((int)auxiliar.getX()+ (int)auxiliar.getWidth()==posX || (int)auxiliar.getX()==(int)posX){
				xVelocidad*=-1;
			}
			//Una vez que se ha cambiado la velocidad, procedemos a quitar el brick.
			_game.remove(auxiliar);//Quitamos el brick
			_game.scoreBox.setMarker(20);//Añadimos 20 puntitos
			_game.brickNumber--;//Quitamos un brick del numero de bricks totales
			collisionClear=false;//Y devolvemos un choque, traducido como false
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
				yVelocidad = -0.7;
			}
			//Punto 3
			if(auxiliar.getX()+auxiliar.getWidth()/4<=centroBola && auxiliar.getX()+3*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -0.8;
			}
			//Punto 4
			if(auxiliar.getX()+ 3*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+5*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -1;
			}
			//Punto 5
			if(auxiliar.getX() + 5*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+3*auxiliar.getWidth()/4>centroBola){
				yVelocidad = -0.8;
			}
			//Punto 6
			if(auxiliar.getX() + 3*auxiliar.getWidth()/4<=centroBola && auxiliar.getX()+7*auxiliar.getWidth()/8>centroBola){
				yVelocidad = -0.7;
			}
			if(auxiliar.getX() + 7*auxiliar.getWidth()/8<=centroBola && auxiliar.getX()+auxiliar.getWidth() >=centroBola){
				yVelocidad = -0.5;
			}
			collisionClear = true;//Devolvemos un choque, traducido como false
		}

		//-------------------
		
		//Aqui entra si se encuentra con el bonus de la barra
		else if(auxiliar instanceof BonusBar){
			_game.bar_1.setSize(120, 10);//Cambia el tamaño de la barra
			_game.remove(auxiliar);//Quita el bonus
			collisionClear = false;//Devolvemos un choque, traducido como false
		}
		
		//-----------------------------------
		
		//Aqui entra si se encuentra con el debuff de la barra
		else if(auxiliar instanceof DebuffBar){
			_game.bar_1.setSize(30, 10);
			_game.remove(auxiliar);//Quita el bonus
			collisionClear = false;//Devolvemos un choque, traducido como false
		}
		
		//--------------------------------
		
		//Aqui entra si se encuentra con el debuff de la barra
		else if(auxiliar instanceof BonusBalls){
			//Lo primero añadimos una nueva bola y la hacemos moverse con el multipleBalls de Arkanoid
			_game.add(_game.ball_2,_game.getWidth()/2-_game.bar_1.getWidth(), _game.getHeight()*0.70-_game.ball_2.getHeight());
			_game.multipleBalls=1;
			_game.remove(auxiliar);//Quita el bonus
			collisionClear = false;//Devolvemos un choque, traducido como false
		}
		
		//--------------------------------
		
		//Aqui entra si hay otra bola
		else if(auxiliar instanceof BallAux){//Simplemente cambiamos todas las velocidades
			yVelocidad*=-1;
			xVelocidad*=-1;
			collisionClear = false;//Devolvemos un choque, traducido como false
		}
		
		//--------------------------------
		
		//Si llega hasta aquí es que no hay colision con nada.
		return collisionClear;
	}
}
