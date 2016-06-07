import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Appercus extends JPanel{
	
	JPanel panelAppercu;
	int r,g,b;
	public Appercus(){
		this.panelAppercu = new JPanel();
		panelAppercu.setPreferredSize(new Dimension(50, 50));
		Random rd = new Random();
		this.r= rd.nextInt(256);
		this.g= rd.nextInt(256);
		this.b= rd.nextInt(256);
	}
	
	public Appercus(int r, int g, int b){
		this.panelAppercu = new JPanel();
		this.r= r;
		this.g= g;
		this.b= b;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(r, this.g, b));
		g.fillRect(1, 1, 100,100);
	}
	
	public void setR(int r){
		this.r= r;
		repaint();
	}
	
	public void setG(int g){
		this.g = g;
		repaint();
	}
	
	public void setB(int b){
		this.b = b;
		repaint();
	}
}
