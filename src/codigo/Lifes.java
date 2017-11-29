package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Lifes extends GRect {
	GLabel text = new GLabel("");
	int lifes = 0;
	public Lifes(double width, double height, int _lifes) {
		super(width, height);
		lifes = _lifes;
		setFilled(true);
		setFillColor(Color.CYAN);
		text.setLabel("Lifes:"+lifes);
		text.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	public void drawLifesBox(Arkanoid _arkanoid){
		_arkanoid.add(this, getX(), getY());
		_arkanoid.add(text, getX()+10, getY()+30);
	}
	public void setMoreLifes(int moreLifes){
		lifes += moreLifes;
		text.setLabel("Lifes: " + lifes);
	}
	public void setLessLifes(int lessLifes){
		lifes -= lessLifes;
		text.setLabel("Lifes " + lifes);
	}
}
