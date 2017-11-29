package codigo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.*;
/**
 * 
 * @author Daniel Ortiz Vallejo
 *
 *	Este va a ser el Arkanoid tio, EL PUTO ARKANOID
 */
//TODO Sistema de Bonus para subir nota.
//TODO Agregar algun Bonus mas y colocarlos por encima para que vayan cayendo gradualmente, ademas puedo mirar como ponerlos en posiciones random.
//TODO Arreglar la mierda de las colisiones con los bricks.
//TODO Crear segundo nivel
//TODO Comentar todo un poco
//TODO Arreglar algun bloque mas duro de lo normal
import acm.util.RandomGenerator;
public class Arkanoid extends acm.program.GraphicsProgram{
	//Variables de apoyo
	int bonusCounter = 0;
	int brickHeight = 15;
	int brickWidth = 25;
	int brickNumber = 0;
	int barBonusCounter = 0;
	int multipleBalls = 0;
	float speed = 10;
	//Construcoter de apoyo
	RandomGenerator random = new RandomGenerator();
	//Constructores de los objetos
	Ball ball_1 = new Ball(10, Color.BLACK);
	Bar bar_1 = new Bar(60, 10, Color.DARK_GRAY);
	//Constructores  de las cajas de Informacion
	ScoreBox scoreBox = new ScoreBox(150, 40);
	Lifes lifes = new Lifes(150, 40, 3);
	GLabel gameOver = new GLabel("GAME OVER");
	GLabel finalPuntuation = new GLabel("");
	//Constructores de los bonus
	BonusBar bonusBar = new BonusBar(25, 15);
	BonusBar bonusBar2 = new BonusBar(25, 15);
	DebuffBar debuffBar = new DebuffBar(25, 15);
	DebuffBar debuffBar2 = new DebuffBar(25, 15);
	BonusBalls bonusBalls= new BonusBalls(25,15);
	//Bolas de apoyo
	BallAux ball_2 = new BallAux(10, Color.BLUE);
	

	public void init(){
		addMouseListeners();
		setSize(400,600);	
		//Añade los distintos elementos iniciales
		add(ball_1,getWidth()/2-bar_1.getWidth(), getHeight()*0.70-ball_1.getHeight());
		add(bar_1, getWidth()/2-bar_1.getWidth(), getHeight()*0.75);
	}
	public void run(){
		//Pinta el primer nivel
		level_01();
		//Pinta la caja de puntuacion
		scoreBox.drawScoreBox(this);
		add(scoreBox,210, 480);
		add(scoreBox.text, 215, 505);
		//Pinta la caja de las vidas
		lifes.drawLifesBox(this);
		add(lifes, 10, 480);
		add(lifes.text, 15, 505);
		//Prepara el cartel de Game Over para cuando se acabe la partida
		gameOver.setFont(new Font("Verdana", Font.BOLD, 30));
		gameOver.setColor(Color.RED);
		finalPuntuation.setFont(new Font("Verdana", Font.BOLD, 25));
		
		//Mientras haya vidas el juego permanece activo.
		while(true && lifes.lifes>0 && brickNumber>0){
			//Cuenta tiempo para lanzar los bonus
			//Cuando termina de contar entra y mueve los bonus hasta encontrarse con la pelota o llegar al final
			if(bonusCounter >= 30){
				//Movimiento del primer bonus de barra
				if(bonusBar.getY()<getHeight()){
					bonusBar.move(0, 1);
					if(bonusBar.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
						remove(bonusBar);
					}
				}
				//Movimiento del segundo bonus de barra
				if(bonusBar2.getY()<getHeight()){
					bonusBar2.move(0, 1);
					if(bonusBar2.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
						remove(bonusBar2);
					}
				}
				//Movimiento del primer debufo de la barra
				if(debuffBar.getY()<getHeight()){
					debuffBar.move(0, 1);
					if(debuffBar.getY()==getHeight()){
						remove(debuffBar);
					}
				}
				//Movimiento del primer debufo de la barra
				if(debuffBar2.getY()<getHeight()){
					debuffBar2.move(0, 1);
					if(debuffBar2.getY()==getHeight()){
						remove(debuffBar2);
					}
				}
				//Movimiento del bonus de las pelotas
				if(bonusBalls.getY()<getHeight()){
					bonusBalls.moveBonus(this);;
					if(bonusBalls.getY()==getHeight()){
						remove(bonusBalls);
					}
				}
				//Si se activa el bonusballs entra
				if(multipleBalls>0){
					ball_2.moveBall(this);
				}
			}
			//Cuando un bonus modifica la barra, tarda un tiempo y vuelve a establecerla como estaba
			if(bar_1.getWidth()>60 || bar_1.getWidth()<60){
				if(barBonusCounter>500){
					bar_1.setSize(60, 10);
					barBonusCounter=-1;
				}
				barBonusCounter++;
			}
			ball_1.moveBall(this);
			//bar_1.setLocation((ball_1.getX()+ball_1.getHeight()/2)-bar_1.getWidth()/2, bar_1.getY());
			if(speed >=2.5){
				speed -=0.001;
			}
			pause(speed);
			bonusCounter ++;
		}
		//Cuando las vidas esten  a 0 termina el juego
		if(lifes.lifes<=0){
			removeAll();
			add(gameOver, 80, 200);
			pause(1000);
			finalPuntuation.setLabel("Score: "+scoreBox.points);
			add(finalPuntuation, 80, 300);

		}
	}

	public void mouseMoved(MouseEvent _event){
		bar_1.moveBar(_event.getX()-30, getWidth(), this);
	}

	private void level_01(){
		int brickNumber = 14;
		for(int j=0; j<brickNumber; j++){
			for(int i=j; i<brickNumber; i++){
				Brick myBrick = new Brick(((getWidth()/2)-(brickNumber/2)*brickWidth)+(brickWidth*i -brickWidth*j/2), brickHeight*j+brickHeight, brickWidth, brickHeight, Color.ORANGE);
				add(myBrick);
				this.brickNumber+=1;
				pause(1);
			}
			add(bonusBar, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(bonusBar2, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(debuffBar, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(debuffBar2, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(bonusBalls, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			
		}
	}


}
