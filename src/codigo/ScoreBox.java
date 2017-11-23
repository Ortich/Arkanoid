package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class ScoreBox extends GRect {
	GLabel text = new GLabel("");
	public ScoreBox(double width, double height) {
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		text.setLabel("Hola");
		text.setFont(new Font("Verdana", Font.BOLD, 18));
	}
	public void drawScoreBox(Arkanoid _arkanoid){
		_arkanoid.add(this, getX(), getY());
		_arkanoid.add(text, getX()+10, getY()+30);
	}
}
