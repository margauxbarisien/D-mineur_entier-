/*BARISIEN BROUART TDB 31/01/20222*/
package démineur_barisien_brouart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author marga
 */
public class Démineur_BARISIEN_BROUART extends JFrame implements ActionListener,
        MouseListener {

    int lignes = 10;
    int colonnes = 10;
    int nbMines = 10;//initialisation d'une grille de jeu 10x10 avec 10 mines
    GridLayout layout = new GridLayout(lignes, colonnes);//création grille en interface
    boolean[] mines = new boolean[lignes * colonnes];
    boolean[] caseNonCliquée = new boolean[lignes * colonnes];
    boolean perdu = false;
    boolean gagné = false;
    int vies = 3;//initialisation du nbr de vies
    int[] nombres = new int[lignes * colonnes];
    JButton[] boutons = new JButton[lignes * colonnes];//chaque cellule devient un bouton 
    boolean[] caseCliquée = new boolean[lignes * colonnes];
    JMenuItem nouvellePartie = new JMenuItem("new game");//bouton pour faire une nouvelle partie 
    JMenuItem menuOptions = new JMenuItem("options");//bouton pour changer la grille de jeu 
    JLabel mineLabel = new JLabel("mines: " + nbMines + " marquées: 0");//affichage nbr mines totales et trouvées
    JPanel p = new JPanel();//création nouvelle interface

    public Démineur_BARISIEN_BROUART() {
        p.setLayout(layout);
        setupI();
        for (int i = 0; i < (lignes * colonnes); i++) {
            p.add(boutons[i]);//ajout des boutons 
        }
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("file");
        nouvellePartie.addActionListener(this);
        m.add(nouvellePartie);//affichage de "nouvellePartie" dans le menu Item
        menuOptions.addActionListener(this);
        m.add(menuOptions);//affichage de "Options" dans le menu Item
        mb.add(m);
        this.setJMenuBar(mb);
        this.add(p);
        this.add(mineLabel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    public void placerMines() {
        int minestot = nbMines;
        while (minestot > 0) {
            int x = (int) Math.floor(Math.random() * lignes);
            int y = (int) Math.floor(Math.random() * colonnes);//placer aléatoirement des mines dans les lignes et colonnes de la grille
            if (mines[(lignes * y) + x] == false) {
                mines[(lignes * y) + x] = true;//test s'il y a déjà une mine sur la case
                minestot--;//réduit le compteur de mines qu'il reste à placer
            }
        }
    }

    public void placerNombres() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                int cases = (lignes * y) + x;
                if (mines[cases] == false) {
                    nombres[cases] = 0;
                    continue;//test si mine sur case
                }
                int compteur = 0;//initialisation du compteur de chiffre affiché
                boolean g = (x - 1) >= 0;
                boolean d = (x + 1) < lignes;
                boolean h = (y - 1) >= 0;
                boolean b = (y + 1) < colonnes;//initialisation des cases adjacentes 
                int gauche = (lignes * (y)) + (x - 1);
                int droite = (lignes * (y)) + (x + 1);
                int haut = (lignes * (y - 1)) + (x);
                int hautgauche = (lignes * (y - 1)) + (x - 1);
                int hautdroit = (lignes * (y - 1)) + (x + 1);
                int bas = (lignes * (y + 1)) + (x);
                int basgauche = (lignes * (y + 1)) + (x - 1);
                int basdroite = (lignes * (y + 1)) + (x + 1);//initialisation des diagonales
                if (h) {
                    if (mines[haut]) {
                        compteur++;
                    }
                    if (g) {
                        if (mines[hautgauche]) {
                            compteur++;
                        }
                    }
                    if (d) {
                        if (mines[hautdroit]) {
                            compteur++;
                        }
                    }
                }
                if (b) {
                    if (mines[bas]) {
                        compteur++;
                    }
                    if (g) {
                        if (mines[basgauche]) {
                            compteur++;
                        }
                    }
                    if (d) {
                        if (mines[basdroite]) {
                            compteur++;
                        }
                    }
                }
                if (g) {
                    if (mines[gauche]) {
                        compteur++;
                    }
                }
                if (d) {
                    if (mines[droite]) {
                        compteur++;//on incrémente le compteur à chaque fois qu'une mine se trouve à coté de la case 
                    }
                }
                nombres[cases] = compteur;
            }
        }
    }

    public void placerKits() {

    }

    public void setupI() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                mines[(lignes * y) + x] = false;
                caseCliquée[(lignes * y) + x] = false;
                caseNonCliquée[(lignes * y) + x] = true;
                boutons[(lignes * y) + x] = new JButton();
                boutons[(lignes * y) + x].setPreferredSize(new Dimension(
                        50, 50)); //dimensionnement cellule de grille
                //couleur ?
                boutons[(lignes * y) + x].addActionListener(this);
                boutons[(lignes * y) + x].addMouseListener(this);
            }
        }
        placerMines();//les mines se placent sur la grille
        placerNombres();//les nbr aussi
        //placerKits();
    }

    public void setupI2() {
        this.remove(p);
        p = new JPanel();
        layout = new GridLayout(lignes, colonnes);
        p.setLayout(layout);
        boutons = new JButton[lignes * colonnes];
        mines = new boolean[lignes * colonnes];
        caseCliquée = new boolean[lignes * colonnes];
        caseNonCliquée = new boolean[lignes * colonnes];
        nombres = new int[lignes * colonnes];
        setupI();
        for (int i = 0; i < (lignes * colonnes); i++) {
            p.add(boutons[i]);
        }
        this.add(p);
        this.pack();
        placerMines();
        placerNombres();
        //placerKits();
    }

    public void setup() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                mines[(lignes * y) + x] = false;
                caseCliquée[(lignes * y) + x] = false;
                caseNonCliquée[(lignes * y) + x] = true;
                boutons[(lignes * y) + x].setEnabled(true);//désactive la case
                boutons[(lignes * y) + x].setText("");//la case est vide (pas de mine ni de chiffre)
            }
        }
        placerMines();
        placerNombres();
        //placerKits();
        perdu = false;
        mineLabel.setText("mines: " + nbMines + " marquées: 0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
