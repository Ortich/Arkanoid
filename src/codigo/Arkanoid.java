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
//TODO Crear segundo nivel
//TODO Comentar todo un poco
//TODO Arreglar algun bloque mas duro de lo normal
import acm.util.RandomGenerator;
public class Arkanoid extends acm.program.GraphicsProgram{
	/**
	 * Lo primero declaro variables que me serviran mas adelante.
	 */
	//Variables de apoyo
	int bonusCounter = 0; //Contador de tiempo para que losBonus caigan 
	int brickHeight = 15;//Alto de los bricks
	int brickWidth = 25;//Ancho de losbricks
	int brickNumber = 0;//Numero de bricks presentes
	int barBonusCounter = 0;//Contador de tiempo para el bonus/debuff de la barra
	int multipleBalls = 0;//Contador de apoyo para multiples bolas
	float speed = 10;//Float que disminuira el pause para aumentar la velocidad de la bola
	
	//Constructores de apoyo
	RandomGenerator random = new RandomGenerator();//Generador de randoms
	
	//Constructores de los objetos del juego
	Ball ball_1 = new Ball(10, Color.BLACK);//Constructor de la bola
	Bar bar_1 = new Bar(60, 10, Color.DARK_GRAY);//Constructor de la barra
	
	//Constructores  de las cajas de Informacion
	ScoreBox scoreBox = new ScoreBox(150, 40);//Caja de la puntuacion
	Lifes lifes = new Lifes(150, 40, 3);//Caja de las vidas
	GLabel gameOver = new GLabel("GAME OVER");//Texto del Game Over
	GLabel finalGame = new GLabel("GG WP");//Texto para el juego completado
	GLabel finalPuntuation = new GLabel("");//Puntuacion final
	
	//Constructores de los bonus. Hay 3 tipos:-BonusBar(Hace mas grande la barra)-DebuffBar(Hace mas pequeñala barra)-BonusBalls(crea una pelota adicional)
	BonusBar bonusBar = new BonusBar(25, 15);//Primer BonusBar
	BonusBar bonusBar2 = new BonusBar(25, 15);//Segundo Bonus Bar
	DebuffBar debuffBar = new DebuffBar(25, 15);//Primer DebuffBar
	DebuffBar debuffBar2 = new DebuffBar(25, 15);//Segundo DebuffBar
	BonusBalls bonusBalls= new BonusBalls(25,15);//El único Bonus de las pelotas
	
	//Bolas de apoyo
	BallAux ball_2 = new BallAux(10, Color.BLUE);//Constructor para las bolas de apoyo
	
	/**
	 * Ahora iniciamos la ventana.
	 */
	public void init(){
		addMouseListeners();//Añadimos los "escuchadores" para el mouse
		setSize(400,600);//Le damos tamaño a la pantalla
		add(ball_1,getWidth()/2-bar_1.getWidth(), getHeight()*0.70-ball_1.getHeight());//Añadimos la bola
		add(bar_1, getWidth()/2-bar_1.getWidth(), getHeight()*0.75);//Añadimos la barra
	}
	
	/**
	 * Aqui empieza el juego.
	 */
	public void run(){
		//Pinta la caja de puntuacion y le da unas coordenadas
		scoreBox.drawScoreBox(this);
		add(scoreBox,210, 480);
		add(scoreBox.text, 215, 505);
		
		//Pinta la caja de las vidas y le da unas coordenadas
		lifes.drawLifesBox(this);
		add(lifes, 10, 480);
		add(lifes.text, 15, 505);
		
		//Prepara el cartel de Game Over y de final de partida para cuando se acabe la partida
		gameOver.setFont(new Font("Verdana", Font.BOLD, 30));
		gameOver.setColor(Color.RED);
		finalGame.setFont(new Font("Verdana", Font.BOLD, 30));
		finalGame.setColor(Color.GREEN);
	 	finalPuntuation.setFont(new Font("Verdana", Font.BOLD, 25));
	 	
	 	//Pinta el primer nivel
	 			level_01();
		//Mientras haya vidas y bricks, el juego funcionara
		while(lifes.lifes>0 && brickNumber>0){
			startGame();//Este método esta descrito mas abajo, basicamente hace moverse al juego.
		}
		//Si queremos meter nuevos niveles ,repetiremos la formula de arriba, pintaremos el nivel y luego llamaremos
		//a startGame para que funcione, dentro de un while para que cuando no haya vidas o se acabe el nivel salga de ahi.
		//Para que reconozca bien cuando pintar niveles, pondremos un if, para que los pinte si quedan vidas. 
		//De esta forma queda de la siguiente manera:
		//
		//if(lifes.lifes>0){
		//level_X;
		//while(lifes.lifes>0 && brickNumber>0){
		//	startGame();
		//	}
		//}
		//<------------------------------------------------------------------------------------>
		
		//Cuando las vidas esten  a 0 termina el juego
		if(lifes.lifes<=0){
			removeAll();//Primero quitamos todolo que hay en pantalla
			pause(1000);
			add(gameOver, 80, 200);//Ponemos el GAME OVER despues de una pausa
			pause(1000);
			finalPuntuation.setLabel("Score: "+scoreBox.points);//Despues de otra pausa ponemos la puntucacion del jugador
			add(finalPuntuation, 80, 300);
		}
		
		//Si acabamos todos los niveles con al menos una vida sobrante, el juego termina.
		if(brickNumber<=0 && lifes.lifes>0){
			removeAll();//Primero quita todo lo que hay en pantalla
			pause(1000);
			add(finalGame, 80, 200);//Pone el cartel final
			pause(1000);
			finalPuntuation.setLabel("Score: "+scoreBox.points);//Despues de otra pausa ponemos la puntucacion del jugador
			add(finalPuntuation, 80, 300);
		}
	}
	/**
	 * Aqui esta el "escuchador" del raton que nos ayudara a mover la barra.
	 */
	public void mouseMoved(MouseEvent _event){
		bar_1.moveBar(_event.getX()-30, getWidth(), this);//Cuando se mueve el raton, mueve la barra consigo.
	}
	/**
	 * Este método tan largo moverálos elementos del juego y hará uso de las distintas clases 
	 * para que el juego pueda ir funcionando
	 * 
	 */
	public void startGame(){
		
		/**
		 * Lo primero son los bonus, tiene un "temporizados" para lanzarlos. Para ello usamos el bonusCounter.
		 * Cuando termina de contar entra y mueve los bonus hasta encontrarse con la pelota o llegar al final
		 */
		if(bonusCounter >= 30){//Primero pone como condicion que el contador este en algun punto. Cuando lo esté, moverá los bonus.
			
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
				if(debuffBar.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
					remove(debuffBar);
				}
			}
			//Movimiento del primer debufo de la barra
			if(debuffBar2.getY()<getHeight()){
				debuffBar2.move(0, 1);
				if(debuffBar2.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
					remove(debuffBar2);
				}
			}
			//Movimiento del bonus de las pelotas
			if(bonusBalls.getY()<getHeight()){
				bonusBalls.moveBonus(this);;
				if(bonusBalls.getY()==getHeight()){//En cuanto llegue abajo se elimina para evitar bugs.
					remove(bonusBalls);
				}
			}
			//Si se activa el bonusballs entra y mueve las demas pelotas
			if(multipleBalls>0){
				ball_2.moveBall(this);
			}
		}
		//Cuando un bonus modifica la barra, tarda un tiempo y vuelve a establecerla como estaba.
		if(bar_1.getWidth()>60 || bar_1.getWidth()<60){
			if(barBonusCounter>500){//Cuando termine el"contador", reestablerecemos la barra
				bar_1.setSize(60, 10);
				barBonusCounter=-1;//Para evitar bugs, el contador lo ponemos a menos uno dado que se acgualizara a 0 en el siguiente paso.
			}
			barBonusCounter++;//Este es el contador que ira sumando puntitos para reestablecer el tamaño de la barra
		}
		
		//Una vez terminados los Bonus, empieza el movimiento de la bola y su modificacion de velocidad
		ball_1.moveBall(this);//Aqui simplemente llamamos al método dentro de la bola para que se mueva
		//bar_1.setLocation((ball_1.getX()+ball_1.getHeight()/2)-bar_1.getWidth()/2, bar_1.getY());//La barra sigue a la bola. Siver para hacer pruebas.
		if(speed >=2.5){//Ahora con la variable inicialidad antes, vamos a ir aumentando la velocidad de la bola poco a poco.
			speed -=0.001;//Para ello iremos disminuyendo el pause.
		}
		pause(speed);
		bonusCounter ++;//Y aqui esta el contador para que los Bonus empiecen a moverse.
	}
	/**
	 * Este método dibujará con Bricks el primer nivel, que consistira en una pirámide invertida.
	 */
	private void level_01(){
		int brickNumber = 14;//Primero establece el número de bricks de la base
		for(int j=0; j<brickNumber; j++){//Luego con los dos for pinta la piramide
			for(int i=j; i<brickNumber; i++){
				Brick myBrick = new Brick(((getWidth()/2)-(brickNumber/2)*brickWidth)+(brickWidth*i -brickWidth*j/2), brickHeight*j+brickHeight, brickWidth, brickHeight, Color.ORANGE);
				add(myBrick);//Una vez determinada la posicion,añade el brick al juego
				this.brickNumber+=1;//Añade un brick al contador de bricks del juego
				pause(10);//Y añade un pause para que se vea mas bonito
			}
			//Ahora añade los distintos Bonus en posiciones aleatorias para que vayan caigan de forma distinta cada partida.
			add(bonusBar, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(bonusBar2, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(debuffBar, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(debuffBar2, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			add(bonusBalls, random.nextInt(0, getWidth()-25),random.nextInt(-100, -4000));
			
		}
	}


}
