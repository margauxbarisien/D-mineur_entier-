import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
/**MineSweeper by SciWizEH*/
public class Démineur_BARISIEN_BROUART extends JFrame implements ActionListener,
        MouseListener {
 
    int lignes = 10;
    int colonnes = 10;
    int nbMines = 10;//initialisation d'une grille de jeu 10x10 avec 10 mines
    int nbKits = 1;
    GridLayout layout = new GridLayout(lignes, colonnes);//création grille en interface

    boolean[] mines = new boolean[lignes * colonnes];
    boolean[] caseNonCliquée = new boolean[lignes * colonnes];
    boolean[] kits = new boolean[lignes * colonnes];
    boolean perdu = false;
    boolean gagné = false;
    int vies = 3;//initialisation du nbr de vies
    int[] nombres = new int[lignes * colonnes];
    JButton[] boutons = new JButton[lignes * colonnes];//chaque cellule devient un bouton 
    boolean[] caseCliquée = new boolean[lignes * colonnes];
    JMenuItem nouvellePartie = new JMenuItem("new game");//bouton pour faire une nouvelle partie 
    JMenuItem menuOptions = new JMenuItem("options");//bouton pour changer la grille de jeu 
    JLabel mineLabel = new JLabel("mines: " + nbMines + " marquées: 0 "+ "nombre de vies : "+vies);//affichage nbr mines totales et trouvées
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
            if (!mines[(lignes * y) + x]) {
                mines[(lignes * y) + x] = true;//test s'il y a déjà une mine sur la case
                minestot--;//réduit le compteur de mines qu'il reste à placer
            }
        }
    }
 
    public void placerNombres() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                int cases = (lignes * y) + x;
                if (mines[cases]) {
                    nombres[cases] = 0;
                    continue;//test si mine sur case
                }
                int compteur = 0;//initialisation du compteur de chiffre affiché
                boolean g = (x - 1) >= 0;
                boolean d = (x + 1) < lignes;
                boolean h = (y - 1) >= 0;
                boolean b = (y + 1) < colonnes;//initialisation des cases adjacentes
                int gauche = (lignes * (y)) + (x - 1);
                int droit = (lignes * (y)) + (x + 1);
                int haut = (lignes * (y - 1)) + (x);
                int hautgauche = (lignes * (y - 1)) + (x - 1);
                int hautdroit = (lignes * (y - 1)) + (x + 1);
                int bas = (lignes * (y + 1)) + (x);
                int basgauche = (lignes * (y + 1)) + (x - 1);
                int basdroit = (lignes * (y + 1)) + (x + 1);//initialisation des diagonales
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
                        if (mines[basdroit]) {
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
                    if (mines[droit]) {
                        compteur++;//on incrémente le compteur à chaque fois qu'une mine se trouve à coté de la case 
                    }
                }
                nombres[cases] = compteur;
            }
        }
    }
 
    public void setupI() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                mines[(lignes * y) + x] = false;
                caseCliquée[(lignes * y) + x] = false;
                caseNonCliquée[(lignes * y) + x] = true;
                boutons[(lignes * y) + x] = new JButton( /*"" + ( x * y )*/);
                boutons[(lignes * y) + x].setPreferredSize(new Dimension(
                        50, 50));//dimensionnement cellule de grille
                //couleur ?

                boutons[(lignes * y) + x].addActionListener(this);
                boutons[(lignes * y) + x].addMouseListener(this);
            }
        }
        placerMines();//les mines se placent sur la grille
        placerNombres();//les nbr aussi
        placerKits();
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
        placerKits();
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
        perdu = false;
        mineLabel.setText("mines: " + nbMines + " marquées: 0 " + "nombre de vies : "+vies);
    }
 
    public static void main(String[] args) {
        new Démineur_BARISIEN_BROUART();//création partie
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuOptions) {
            lignes = Integer.parseInt((String) JOptionPane.showInputDialog(
                    this, "Rows", "Rows", JOptionPane.PLAIN_MESSAGE, null,
                    null, 10));
            colonnes = Integer.parseInt((String) JOptionPane.showInputDialog(
                    this, "Columns", "Columns", JOptionPane.PLAIN_MESSAGE,
                    null, null, 10));
            nbMines = Integer.parseInt((String) JOptionPane.showInputDialog(this, "Mines", "Mines",
                    JOptionPane.PLAIN_MESSAGE, null, null, 10));
             nbKits = Integer.parseInt((String) JOptionPane.showInputDialog(
                    this, "Kits de déminage", "Kits de déminage", JOptionPane.PLAIN_MESSAGE, null, null, 1));
            setupI2();
        }//fenetre de menu avec choix des options
        if (!gagné) {
            for (int x = 0; x < lignes; x++) {
                for (int y = 0; y < colonnes; y++) {
                    if (e.getSource() == boutons[(lignes * y) + x]
                            && !gagné && caseNonCliquée[(lignes * y) + x]) {
                        doCheck(x, y);
                        break;
                    }
                }
            }
        }
        if (e.getSource() == nouvellePartie) {
            setup();
            gagné = false;
            return;
 
        }
        verifVictoire();
    }
     public void placerKits() {
        int kitstot = nbKits;
        while (kitstot > 0) {
            int x = (int) Math.floor(Math.random() * lignes);
            int y = (int) Math.floor(Math.random() * colonnes);//placer aléatoirement des kits dans les lignes et colonnes de la grille
            if (kits[(lignes * y) + x] == false) {
                kits[(lignes * y) + x] = true;//test s'il y a déjà un kit sur la case
                kitstot--;//réduit le compteur de mines qu'il reste à placer
            }
        }
    }
    public void mouseEntered(MouseEvent e) {
    }//permet à l'utilisateur de savoir quelle case il survole
 
    public void mouseExited(MouseEvent e) {
    }//permet de remettre la case dans son état initial
 
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            int n = 0;
            for (int x = 0; x < lignes; x++) {
                for (int y = 0; y < colonnes; y++) {
                    if (e.getSource() == boutons[(lignes * y) + x]) {
                        caseNonCliquée[(lignes * y) + x] = !caseNonCliquée[(lignes * y)
                                + x];
                    }
                    if (!caseCliquée[(lignes * y) + x]) {
                        if (!caseNonCliquée[(lignes * y) + x]) {
                            boutons[(lignes * y) + x].setText("X");
                            n++;//si clique droit on place un "X" on le joueur pense qu'il y a une mine
                        } else {
                            boutons[(lignes * y) + x].setText("");
                        }
                        mineLabel.setText("mines: " + nbMines + " marquées: "
                                + n + " nombre de vies : "+vies);
                    }
                }
            }
        }
    }
 
    public void mouseReleased(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }
 
    public void doCheck(int x, int y) {
        int cases = (lignes * y) + x;
        boolean l = (x - 1) >= 0;
        boolean r = (x + 1) < lignes;
        boolean u = (y - 1) >= 0;
        boolean d = (y + 1) < colonnes;
        int gauche = (lignes * (y)) + (x - 1);
        int droit = (lignes * (y)) + (x + 1);
        int up = (lignes * (y - 1)) + (x);
        int upgauche = (lignes * (y - 1)) + (x - 1);
        int updroit = (lignes * (y - 1)) + (x + 1);
        int bas = (lignes * (y + 1)) + (x);
        int basgauche = (lignes * (y + 1)) + (x - 1);
        int basdroit = (lignes * (y + 1)) + (x + 1);//verif si il y a une bombe 
 
        caseCliquée[cases] = true;
        boutons[cases].setEnabled(false);
        if (nombres[cases] == 0 && !mines[cases] && !perdu && !gagné) {
            if (u && !gagné) {
                if (!caseCliquée[up] && !mines[up]) {
                    caseCliquée[up] = true;
                    boutons[up].doClick();//découvre la case quand il n'y a pas de mine 
                }
                
                if (l && !gagné) {
                    if (!caseCliquée[upgauche] && nombres[upgauche] != 0
                            && !mines[upgauche]) {
                        caseCliquée[upgauche] = true;
                        boutons[upgauche].doClick();//découvre la case quand il n'y a pas de mine 
                    }
                }
                if (r && !gagné) {
                    if (!caseCliquée[updroit] && nombres[updroit] != 0
                            && !mines[updroit]) {
                        caseCliquée[updroit] = true;
                        boutons[updroit].doClick();//découvre la case quand il n'y a pas de mine 
                    }
                }
            }
            if (d && !gagné) {
                if (!caseCliquée[bas] && !mines[bas]) {
                    caseCliquée[bas] = true;
                    boutons[bas].doClick();//découvre la case quand il n'y a pas de mine 
                }
                if (l && !gagné) {
                    if (!caseCliquée[basgauche] && nombres[basgauche] != 0
                            && !mines[basgauche]) {
                        caseCliquée[basgauche] = true;
                        boutons[basgauche].doClick();//découvre une case adjacente à une ou plusieurs bombes
                    }
                }
                if (r && !gagné) {
                    if (!caseCliquée[basdroit]
                            && nombres[basdroit] != 0
                            && !mines[basdroit]) {
                        caseCliquée[basdroit] = true;
                        boutons[basdroit].doClick();//découvre une case adjacente à une ou plusieurs bombes
                    }
                }
            }
            if (l && !gagné) {
                if (!caseCliquée[gauche] && !mines[gauche]) {
                    caseCliquée[gauche] = true;
                    boutons[gauche].doClick();//découvre une case adjacente à une ou plusieurs bombes
                }
            }
            if (r && !gagné) {
                if (!caseCliquée[droit] && !mines[droit]) {
                    caseCliquée[droit] = true;
                    boutons[droit].doClick();//découvre une case adjacente à une ou plusieurs bombes
                }
            
            }
        } else {
            boutons[cases].setText("" + nombres[cases]);
            if (!mines[cases] && nombres[cases] == 0) {
                boutons[cases].setText("");//pas de message si case vide
            }
        }
        if (mines[cases] && !gagné) {
            if (kits[cases]){
                vies=vies;
            }else{
            boutons[cases].setText("0");
            
            vies--;
            mineLabel.setText("mines: " + nbMines + " marquées: 0 "+ " nombre de vies : "+vies);
            if (vies==0){
            // moins 1 vie et 0 vie = perdu
            Perdre();
  
       
        }
        }
            }
      
    }
    public void verifVictoire() {
        for (int x = 0; x < lignes; x++) {
            for (int y = 0; y < colonnes; y++) {
                int cases = (lignes * y) + x;
                if (!caseCliquée[cases]) {
                    if (mines[cases]) {
                        continue;//si aucune case avec mine n'a été découverte alors la partie peut encore être gagnée
                    } else {
                        return;
                    }
                }
            }
        }
 
        Gagner();
    }
 
    public void Gagner() {
        if (!perdu && !gagné) {
            gagné = true;//gagné
            JOptionPane.showMessageDialog(null,
                    "Vous avez gagné, bravo", "partie gagnante",
                    JOptionPane.INFORMATION_MESSAGE);//affichage message partie gagnée
            vies=3;
            nouvellePartie.doClick();
        }
    }
 
    public void Perdre() {
        if (!perdu && !gagné) {
            perdu = true;//perdu
            for (int i = 0; i < lignes * colonnes; i++) {
                if (!caseCliquée[i]) {
                    boutons[i].doClick(0);
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Vous avez perdu, dommage", "partie perdante",
                    JOptionPane.ERROR_MESSAGE);//affichage de message pour dire que la partie est perdue
            vies=3;
            setup();
        }
    }
}
     //   public void placerKits() {
   //     int kitstot = nbKits;
     //   while (kitstot > 0) {
       //     int x = (int) Math.floor(Math.random() * lignes);
         //   int y = (int) Math.floor(Math.random() * colonnes);//placer aléatoirement des kits dans les lignes et colonnes de la grille
//            if (kits[(lignes * y) + x] == false) {
  //              kits[(lignes * y) + x] = true;//test s'il y a déjà un kit sur la case
    //            kitstot--;//réduit le compteur de mines qu'il reste à placer
     //       }
   //     }
  //  }
