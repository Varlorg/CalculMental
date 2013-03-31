import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreMenu  extends JPanel
{
    FenetreModele fm;
    JFrame fenetre;

    JPanel pAction = new JPanel();
    JButton bJeu = new JButton("Niveau");
    JButton bScore = new JButton("Score");
    JButton bQuitter = new JButton("Quitter");
    JMenuItem mReset;
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

    public FenetreMenu(FenetreModele f)
    {
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Menu");
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

    mReset = new JMenuItem("Nouvelle Partie");
    mReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                  KeyEvent.CTRL_MASK));
    mReset.addActionListener(new ActionReset());
    mJeu.add(mJouer);
    mJeu.add(mReset);
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



        bQuitter.addActionListener(new ActionQuitter());
        mJouer = new JMenuItem("Niveau");
        mJouer.setFont(FenetreModele.fCambMini);
        mJouer.addActionListener(new ActionJouer());

        mJeu.add(mJouer);
        mJeu.add(mQuitter);

        bJeu.addActionListener(new ActionJouer());
        pAction.add(bJeu);
        pAction.add(bScore);
        pAction.add(bQuitter);
        fenetre.add("South",pAction);
        fenetre.pack();
        fenetre.setVisible(true);

    }
    class ActionJouer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fm.niveau();
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
