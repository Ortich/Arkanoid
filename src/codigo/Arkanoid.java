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
public class Arkanoid extends acm.program.GraphicsProgram{
	//TODO Crear vidas y cambiar el nivel
	//Truco para el nivel, jugar con una variable booleana llamada GameOver. 
	int brickHeight = 15;
	int brickWidth = 25;
	Ball ball_1 = new Ball(10, Color.BLUE);
	Bar bar_1 = new Bar(60, 10, Color.DARK_GRAY);
	
	ScoreBox scoreBox = new ScoreBox(20, 40);
	
	public void init(){
		addMouseListeners();
		setSize(400,600);	
		
		add(ball_1,0, getHeight()*0.75-ball_1.getHeight());
		add(bar_1, 0, getHeight()*0.80);
	}
	public void run(){		
		level_01();
		scoreBox.drawScoreBox(this);
		add(scoreBox, 10, 10);
		add(scoreBox.text, 0, 20);
		while(true){
			ball_1.moveBall(this);
			bar_1.setLocation((ball_1.getX()+ball_1.getHeight()/2)-bar_1.getWidth()/2, bar_1.getY());
			pause(20);
		}
	}
	
	public void mouseMoved(MouseEvent _event){
		bar_1.moveBar(_event.getX()-30, getWidth());
	}
	
	private void level_01(){
		int brickNumber = 14;
		for(int j=0; j<brickNumber; j++){
			for(int i=j; i<brickNumber; i++){
				Brick myBrick = new Brick(((getWidth()/2)-(brickNumber/2)*brickWidth)+(brickWidth*i -brickWidth*j/2), brickHeight*j+brickHeight, brickWidth, brickHeight, Color.GREEN);
				add(myBrick);
				pause(1);
			}
		}
	}
	
	
}
