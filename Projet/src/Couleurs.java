import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Couleurs extends JPanel implements MouseListener {

	BufferedImage imagePalette;
	Rectangle palette;
	Color couleur;
	Main main;
	int numCouleurs;
	JPanel appercus;

	public Couleurs(Main main, int numCouleurs) {
		this.addMouseListener(this);
		this.numCouleurs = numCouleurs;
		this.main = main;
		this.appercus = new JPanel();
		try {
			imagePalette = ImageIO.read(this.getClass().getResource("/palette.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		palette = new Rectangle(new Dimension(imagePalette.getWidth(null), imagePalette.getHeight(null)));
		g.drawRect(15, 125, (int) palette.getWidth(), (int) palette.getHeight());
		g.drawImage(imagePalette, 15, 125, null);

	}

	public void mouseClicked(MouseEvent arg0) {
		if (palette.contains(arg0.getX() - 15, arg0.getY() - 125)) {
			int x = arg0.getX() - 15;
			int y = arg0.getY() - 125;
			couleur = new Color(imagePalette.getRGB(x, y));
			main.select.setR(couleur.getRed());
			main.select.setG(couleur.getGreen());
			main.select.setB(couleur.getBlue());
			main.red.setValue(main.select.r);
			main.green.setValue(main.select.g);
			main.blue.setValue(main.select.b);
		}
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void mouseDragged(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent arg0) {

	}
}
