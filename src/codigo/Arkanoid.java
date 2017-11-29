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
public class Arkanoid extends acm.program.GraphicsProgram{
	//Variables de apoyo
	int bonusCounter = 0;
	int brickHeight = 15;
	int brickWidth = 25;
	int brickNumberLvl01 = 0;
	int barBonusCounter = 0;
	int multipleBalls = 0;
	//Constructores de los objetos
	Ball ball_1 = new Ball(10, Color.BLACK);
	Bar bar_1 = new Bar(60, 10, Color.DARK_GRAY);
	ScoreBox scoreBox = new ScoreBox(150, 40);
	Lifes lifes = new Lifes(150, 40, 3);
	GLabel gameOver = new GLabel("GAME OVER");
	GLabel finalPuntuation = new GLabel("");
	BonusBar bonusBar = new BonusBar(25, 15);
	DebuffBar debuffBar = new DebuffBar(25, 15);
	BonusBalls bonusBalls= new BonusBalls(25,15);
	//Bolas de apoyo
	BallAux ball_2 = new BallAux(10, Color.BLUE);
	BallAux ball_3 = new BallAux(10, Color.RED);

	public void init(){
		addMouseListeners();
		setSize(400,600);	
		//Añade los distintos elementos iniciales
		add(ball_1,getWidth()/2-bar_1.getWidth(), getHeight()*0.70-ball_1.getHeight());
		add(bar_1, getWidth()/2-bar_1.getWidth(), getHeight()*0.75);
		add(bonusBar, 150, 200);
		add(debuffBar, 100, 200);
		add(bonusBalls, 100, 200);
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
		finalPuntuation.setFont(new Font("Verdana", Font.BOLD, 25));
		
		//Mientras haya vidas el juego permanece activo.
		while(true && lifes.lifes>0 && brickNumberLvl01>0){
			//Cuenta tiempo para lanzar los bonus
			//Cuando termina de contar entra y mueve los bonus hasta encontrarse con la pelota o llegar al final
			if(bonusCounter >= 30){
				//Movimiento del bonus de barra
				if(bonusBar.getY()<getHeight()){
					bonusBar.move(0, 1);
					if(bonusBar.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
						remove(bonusBar);
					}
				}
				//Movimiento del debufo de la barra
				if(debuffBar.getY()<getHeight()){
					debuffBar.move(0, 1);
					if(debuffBar.getY()==getHeight()){
						remove(debuffBar);
					}
				}
				//Movimiento del bonus de las pelotas
				if(bonusBalls.getY()<getHeight()){
					bonusBalls.move(0, 1);
					if(bonusBalls.getY()==getHeight()){
						remove(bonusBalls);
					}
				}
				//Si se activa el bonusballs entra
				if(multipleBalls>0){
					ball_2.moveBall(this);
					ball_3.moveBall(this);
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
			pause(20);
			bonusCounter ++;
		}
		//Cuando las vidas esten  a 0 termina el juego
		if(lifes.lifes<=0){
//			remove(bar_1);
//			remove(ball_1);
//			remove(lifes);
//			remove(scoreBox);
//			remove(lifes.text);
//			remove(scoreBox.text);
			removeAll();
			add(gameOver, 80, 300);
			pause(50);
			finalPuntuation.setLabel("Score: "+scoreBox.points);
			add(finalPuntuation, 80, 400);

		}
	}

	public void mouseMoved(MouseEvent _event){
		bar_1.moveBar(_event.getX()-30, getWidth());
	}

	private void level_01(){
		int brickNumber = 14;
		for(int j=0; j<brickNumber; j++){
			for(int i=j; i<brickNumber; i++){
				Brick myBrick = new Brick(((getWidth()/2)-(brickNumber/2)*brickWidth)+(brickWidth*i -brickWidth*j/2), brickHeight*j+brickHeight, brickWidth, brickHeight, Color.ORANGE);
				add(myBrick);
				brickNumberLvl01+=1;
				pause(1);
			}
		}
	}


}
