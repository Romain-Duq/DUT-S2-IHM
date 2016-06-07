import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JPanel {

	int numCouleurs = 1;
	ArrayList<Appercus> rectsCouleur = new ArrayList<>();
	ArrayList<Appercus> rectsGris = new ArrayList<>();
	ArrayList<JLabel> labelsCouleurs = new ArrayList<>();
	ArrayList<JRadioButton> bouton = new ArrayList<>();
	ButtonGroup bg = new ButtonGroup();
	JFrame fenetre;
	JPanel panel = new JPanel();
	JPanel fin = new JPanel();
	JPanel intermediaire = new JPanel();
	GridLayout intermediaireLayout;
	Appercus select;
	JSlider red = new JSlider();
	JSlider blue = new JSlider();
	JSlider green = new JSlider();
	JLabel labelRed;
	JLabel labelGreen;
	JLabel labelBlue;
	int idxCouleur = 0;
	int i = 0;

	public Main() {
		fenetre = new JFrame("Projet");
		fenetre.setPreferredSize(new Dimension(1000, 600));
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.setVisible(true);
		Dimension sizeFenetre = fenetre.getPreferredSize();
		intermediaireLayout = new GridLayout(numCouleurs, 4);
		intermediaire.setLayout(intermediaireLayout);
		for (int i = 0; i < numCouleurs; i++) {
			int tmp2 = i;
			rectsCouleur.add(new Appercus());
			rectsGris.add(new Appercus());
			JRadioButton tmp = new JRadioButton();
			tmp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					idxCouleur = tmp2;
					select = rectsCouleur.get(idxCouleur);	
					red.setValue(select.r);
					green.setValue(select.g);
					blue.setValue(select.b);
				}
			});
			tmp.setSelected(true);
			bouton.add(tmp);
			select = rectsCouleur.get(idxCouleur);
			labelsCouleurs.add(new JLabel("RGB (" + select.r + ", " + select.g + ", " + select.b + ")"));
		}

		intermediaire.add(bouton.get(0));
		intermediaire.add(rectsCouleur.get(0));
		intermediaire.add(rectsGris.get(0));
		intermediaire.add(labelsCouleurs.get(0));
		convertirGris(rectsGris.get(idxCouleur));

		JButton aide = new JButton("Aide");
		Dimension sizeAide = aide.getPreferredSize();
		aide.setBounds(15, 15, sizeAide.width, sizeAide.height);
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane j1 = new JOptionPane();
				j1.showMessageDialog(null,
						"Changez le nombre de couleurs via la liste déroulante, située au milieu du haut de la fenêtre. \n\n Choisissez les couleurs à modifier et modifiez les en cliquant sur la palette de couleurs, ou via les 3 potentiomètres situés en haut à gauche.",
						"Aide", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		fenetre.add(aide);

		JPanel choixNb = new JPanel();
		GridLayout layoutChoix = new GridLayout(2, 1);
		choixNb.setLayout(layoutChoix);
		JLabel labelNombre = new JLabel("Nombre de couleurs :");
		choixNb.add(labelNombre, BorderLayout.NORTH);
		String[] tabNb = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		final JComboBox nombre = new JComboBox(tabNb);
		choixNb.add(nombre, BorderLayout.SOUTH);
		Dimension sizeChoixNb = choixNb.getPreferredSize();
		choixNb.setBounds(sizeFenetre.width / 2 - sizeChoixNb.width / 2 - 50, 5, sizeChoixNb.width, sizeChoixNb.height);
		fenetre.add(choixNb);

		JPanel sliders = new JPanel();
		JPanel labelSliders = new JPanel();
		GridLayout sliderLayout = new GridLayout(3, 1);
		sliders.setLayout(sliderLayout);
		labelSliders.setLayout(sliderLayout);
		red = new JSlider(0, 255, 1);
		red.setValue(select.r);
		green = new JSlider(0, 255, 1);
		green.setValue(select.g);
		blue = new JSlider(0, 255, 1);
		blue.setValue(select.b);
		labelRed = new JLabel("R : " + red.getValue());
		labelGreen = new JLabel("G : " + green.getValue());
		labelBlue = new JLabel("B : " + blue.getValue());
		sliders.add(red);
		sliders.add(green);
		sliders.add(blue);
		labelSliders.add(labelRed);
		labelSliders.add(labelGreen);
		labelSliders.add(labelBlue);
		Dimension sizeSlider = sliders.getPreferredSize();
		sliders.setBounds(sizeFenetre.width - sizeSlider.width - 50, 15, sizeSlider.width, sizeSlider.height);
		Dimension sizeLabelSliders = labelSliders.getPreferredSize();
		labelSliders.setBounds(sizeFenetre.width - 50, 15, sizeLabelSliders.width + 20, sizeLabelSliders.height);
		fenetre.add(labelSliders);
		fenetre.add(sliders);
		red.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelRed.setText("" + select.r);
				select.r = red.getValue();
				convertirGris(rectsGris.get(idxCouleur));
				labelsCouleurs.get(idxCouleur).setText("RGB (" + select.r + ", " + select.g + ", " + select.b + ")");
				select.repaint();
			}
		});
		green.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelGreen.setText("" + green.getValue());
				select.g = green.getValue();
				convertirGris(rectsGris.get(idxCouleur));
				labelsCouleurs.get(idxCouleur).setText("RGB (" + select.r + ", " + select.g + ", " + select.b + ")");
				select.repaint();
			}
		});
		blue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				labelBlue.setText("" + blue.getValue());
				select.b = blue.getValue();
				convertirGris(rectsGris.get(idxCouleur));
				labelsCouleurs.get(idxCouleur).setText("RGB (" + select.r + ", " + select.g + ", " + select.b + ")");
				select.repaint();
			}
		});
		JPanel centre = new JPanel();
		JLabel palette = new JLabel(new ImageIcon("ressources/palette.png"));
		Dimension sizePalette = palette.getPreferredSize();
		palette.setPreferredSize(
				new Dimension((int) (sizePalette.getWidth() * 1.2), (int) (sizePalette.getHeight() * 1.2)));

		nombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bouton.get(0).setSelected(false);
				rectsCouleur.removeAll(rectsCouleur);
				rectsGris.removeAll(rectsGris);
				labelsCouleurs.removeAll(labelsCouleurs);
				numCouleurs = nombre.getSelectedIndex() + 1;
				intermediaire.removeAll();
				intermediaireLayout = new GridLayout(numCouleurs, 3);
				intermediaire.setLayout(intermediaireLayout);
				for (int i = 0; i < numCouleurs; i++) {
					final int tmp2 = i;
					JRadioButton tmp = new JRadioButton();
					tmp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							idxCouleur = tmp2 + 1;
							select = rectsCouleur.get(idxCouleur);
							red.setValue(select.r);
							green.setValue(select.g);
							blue.setValue(select.b);
						}
					});
					tmp.setSelected(false);
					bouton.add(tmp);
					rectsCouleur.add(new Appercus());
					int teinte = (int) (0.3 * rectsCouleur.get(i).r + 0.59 * rectsCouleur.get(i).g
							+ 0.11 * rectsCouleur.get(i).b);
					rectsGris.add(new Appercus(teinte, teinte, teinte));
					labelsCouleurs.add(new JLabel("RGB (" + rectsCouleur.get(i).r + ", " + rectsCouleur.get(i).g + ", "
							+ rectsCouleur.get(i).b + ")"));
				}
				for (int j = 0; j < nombre.getSelectedIndex() + 1; j++) {
					if (j == 0){
						bouton.get(j).setSelected(true);
					}
					bg.add(bouton.get(j));
					intermediaire.add(bouton.get(j));
					intermediaire.add(rectsCouleur.get(j));
					intermediaire.add(rectsGris.get(j));
					intermediaire.add(labelsCouleurs.get(j));
				}
				select = rectsCouleur.get(0);
				intermediaire.revalidate();
			}
		});
		centre.setLayout(new BorderLayout());
		centre.add(new Couleurs(this, numCouleurs), BorderLayout.CENTER);
		GridLayout finLayout = new GridLayout(1, 2);
		GridBagLayout panelLayout = new GridBagLayout();
		panel.setLayout(panelLayout);
		panel.add(intermediaire);
		fin.setLayout(finLayout);
		fin.add(centre);
		fin.add(panel);
		fenetre.add(fin, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}

	public void convertirGris(Appercus cible) {
		int teinte = (int) (0.3 * select.r + 0.59 * select.g + 0.11 * select.b);
		cible.b = teinte;
		cible.r = teinte;
		cible.g = teinte;
		cible.repaint();
	}

	
}
