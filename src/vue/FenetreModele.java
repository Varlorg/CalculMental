package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreModele extends JFrame
{
    /** La couleur bleu.*/
    public static final Color VERT = new Color(48, 145, 0, 255);

    /** La couleur bleu.*/
    public static final Color JAUNE = Color.ORANGE;

    /** La couleur bleu.*/
    public static final Color ORANGE = new Color(249, 137, 0, 255);

    /** La couleur bleu.*/
    public static final Color ROUGE = Color.RED;

    /** La couleur bleu.*/
    public static final Color BLEU = new Color(22, 61, 201, 255);

    //Font fSoluce = new Font("Cambria",Font.BOLD | Font.ITALIC, 12);
    public static final Font fLibe = new Font("Liberation Sans",Font.BOLD , 14);
    public static final Font fCamb = new Font("Cambria",Font.BOLD , 14);
    public static final Font fCambMini = new Font("Cambria",Font.BOLD , 13);

    JMenuBar menuBar = new JMenuBar();

    JMenu mJeu = new JMenu("Jeu");
    JMenuItem mJouer = new JMenuItem("Jouer");
    JMenuItem mQuitter = new JMenuItem("Quitter");

    JMenu mApropos = new JMenu("Aide");
    JMenuItem mVersion = new JMenuItem("Version");
    JMenuItem mAbout = new JMenuItem("A propos");

    public FenetreModele()
    {
        /*
         *
         * Creation du menu
         *
         */

        /* sous-menu Jeu */
        mJeu.setFont(FenetreModele.fCambMini);
        mQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                KeyEvent.CTRL_MASK));
        mQuitter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.exit(0);
            }
        });
        mJouer.setFont(FenetreModele.fCambMini);

        mJeu.add(mJouer);
        mJeu.add(mQuitter);

        /* sous-menu A propos */
        mApropos.setFont(FenetreModele.fCambMini);
        mApropos.add(mAbout);
        mApropos.add(mVersion);

		mAbout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                    JOptionPane.showMessageDialog((Component)event.getSource(), "Créé par Adrian Poiget\nCopyright © 2013\nThis program is free software, you can redistribute it and/or modify it \nunder the terms of the GNU General Public License as published by the Free Software Foundation");
            }
        });
		mVersion.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                    JOptionPane.showMessageDialog((Component)event.getSource(), "1.0");
            }
        });
        menuBar.add(mJeu);
        menuBar.add(mApropos);
        /* Ajout de la barre  a la fenetre */
        this.setJMenuBar(menuBar);


        /*Creation de la fenetre générale
         *
         */
        //fenetre.setSize(700, 300);
        this.setPreferredSize(new Dimension(450,200));
        this.setResizable(false);
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.getContentPane().setLayout(new BorderLayout());

        this.menu();

        this.pack();
        this.setVisible(true);
    }

    public void jeu(char n)
    {
        this.getContentPane().removeAll();
        FenetreJeu ecranJeu = new FenetreJeu(this,n);
        this.getContentPane().add(ecranJeu);
        ecranJeu.revalidate();
    }

    public void menu()
    {
        this.getContentPane().removeAll();
        FenetreMenu ecranMenu = new FenetreMenu(this);
        this.getContentPane().add(ecranMenu);
        ecranMenu.revalidate();
    }

    public void niveau()
    {
        this.getContentPane().removeAll();
        FenetreNiveau ecranNiveau = new FenetreNiveau(this);
        this.getContentPane().add(ecranNiveau);
        ecranNiveau.revalidate();
    }

    public void score()
    {
        this.getContentPane().removeAll();
        FenetreScore ecranScore = new FenetreScore(this);
        this.getContentPane().add(ecranScore);
        ecranScore.revalidate();
    }

    public void record(int score)
    {
        this.getContentPane().removeAll();
        FenetreRecord ecranRecord = new FenetreRecord(this, score);
        this.getContentPane().add(ecranRecord);
        ecranRecord.revalidate();
    }

    public void setTitre(String nom)
    {
        this.setTitle(nom);
    }
}
