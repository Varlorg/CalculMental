import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreNiveau extends JPanel
{
    FenetreModele fm;
    JFrame fenetre;

    JPanel pAction = new JPanel();
    JPanel pNiveau = new JPanel();
    JButton bRetour = new JButton("Retour");
    JButton bQuitter = new JButton("Quitter");
    JMenuItem mRetour;
    JMenuItem mJouer;

    JMenuBar menuBar;

    JMenu mJeu;
    JMenuItem mQuitter;


    JMenu mApropos;
    JMenuItem mVersion;
    JMenuItem mAbout;
    //bQuitter.addActionListener(new ActionQuitter());

    /*fMenu.pack();
    fMenu.setVisible(true);*/

    public FenetreNiveau(FenetreModele f)
    {
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Niveau");
        this.fenetre = fm.getJFrame();

   /*Creation du menu
     *
     */
    menuBar = new JMenuBar();
    /* menu Jeu */
    mJeu = new JMenu("Jeu");
    mJeu.setFont(FenetreModele.fCambMini);
    mQuitter = new JMenuItem("Quitter");
    mQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                            KeyEvent.CTRL_MASK));
    mQuitter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    });
    /*mJouer = new JMenuItem("Jouer");
    mJouer.setFont(fCambMini);
    mJouer.addActionListener(new ActionJouer());

    mRetour = new JMenuItem("Nouvelle Partie");
    mRetour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                  KeyEvent.CTRL_MASK));
    mRetour.addActionListener(new ActionRetour());
    mJeu.add(mJouer);
    mJeu.add(mRetour);
    mJeu.add(mQuitter);*/
    menuBar.add(mJeu);

    /* menu Aide */
    mApropos = new JMenu("Aide");
    mApropos.setFont(FenetreModele.fCambMini);
    mVersion = new JMenuItem("Version");
    mAbout = new JMenuItem("A propos");
    mApropos.add(mAbout);
    mApropos.add(mVersion);
    menuBar.add(mApropos);

    /* Ajout de la barre  a la fenetre */
    fenetre.setJMenuBar(menuBar);

        mJouer = new JMenuItem("Niveau");
        mJouer.setFont(FenetreModele.fCambMini);
        mJouer.addActionListener(new ActionJouer());

        mJeu.add(mJouer);
        mJeu.add(mQuitter);



        /*
         *Niveau 
         */

        JButton bFacile = new JButton("Facile"); 
        bFacile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            fm.jeu('f');
            }
        });
        pNiveau.add(bFacile);
        JButton bMoyen = new JButton("Moyen");
        bMoyen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            fm.jeu('m');
            }
        });
        pNiveau.add(bMoyen);
        JButton bDifficile = new JButton("Difficile");
        bDifficile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            fm.jeu('d');
            }
        });
        pNiveau.add(bDifficile);

        bQuitter.addActionListener(new ActionQuitter());
        pAction.add(bRetour);
        pAction.add(bQuitter);
        fenetre.add("North",pNiveau);
        fenetre.add("South",pAction);
        /*fenetre.pack();
        fenetre.setVisible(true);*/

    }
    class ActionJouer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //fm.jeu();
            /*fenetre.dispose();
            new FenetreJeu();*/
        }
    }

    class ActionQuitter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}
