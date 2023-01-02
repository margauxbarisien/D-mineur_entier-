/*BARISIEN BROUART TDB 31/01/20222*/
package démineur_barisien_brouart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    int vies =3;//initialisation du nbr de vies
    int[] nombres = new int[lignes * colonnes];
    JButton[] boutons = new JButton[lignes * colonnes];//chaque cellule devient un bouton 
    boolean[] clickdone = new boolean[lignes * colonnes];
    JMenuItem nouvellePartie = new JMenuItem("new game");//bouton pour faire une nouvelle partie 
    JMenuItem menuOptions = new JMenuItem("options");//bouton pour changer la grille de jeu 
    JLabel mineLabel = new JLabel("mines: " + nbMines + " marked: 0");
    JPanel p = new JPanel();
// déclaration de toutes les variables et 
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
    

