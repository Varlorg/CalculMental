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

    Component c = this.getGlassPane();
    JFrame f = this;

    JMenuBar menuBar;

    JMenu mJeu;
    JMenuItem mJouer;
    JMenuItem mTheme;
    JMenuItem mQuitter;

    JMenu mApropos;
    JMenuItem mVersion;
    JMenuItem mAbout;

    public FenetreModele()
    {
			setBestLookAndFeelAvailable();
        /*
         *
         * Creation du menu
         *
         */
		menuBar = new JMenuBar();

		mJeu = new JMenu("Jeu");
		mTheme = new JMenu("Thèmes");
		mJouer = new JMenuItem("Jouer");
		//mActualiser = new JMenuItem("Actualiser");
		mQuitter = new JMenuItem("Quitter");

        JRadioButtonMenuItem radioTheme1 = new JRadioButtonMenuItem("Nimbus");
        JRadioButtonMenuItem radioTheme2 = new JRadioButtonMenuItem("Gtk");
        JRadioButtonMenuItem radioTheme3 = new JRadioButtonMenuItem("Basic");

        //On met nos radios dans un ButtonGroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioTheme1);
        bg.add(radioTheme2);
        bg.add(radioTheme3);
        //On présélectionne la première radio
        radioTheme3.setSelected(true);
        this.mTheme.add(radioTheme1);
        this.mTheme.add(radioTheme2);
        this.mTheme.add(radioTheme3);

        ThemeListener fl = new ThemeListener();
        radioTheme1.addActionListener(fl);
        radioTheme2.addActionListener(fl);
        radioTheme3.addActionListener(fl);

		mApropos = new JMenu("Aide");
		mVersion = new JMenuItem("Version");
		mAbout = new JMenuItem("A propos");
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
        mJeu.add(mTheme);
        //mJeu.add(mActualiser);
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

    class ThemeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
                String theme = (((JRadioButtonMenuItem)e.getSource()).getText());
                if(theme.equals("Nimbus"))
                {
                   UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }else if (theme.equals("Gtk"))
                {
                   UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }else if (theme.equals("Basic"))
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
            }catch(Exception ex){
            }
            SwingUtilities.updateComponentTreeUI(f);
            f.pack();
        }    
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
    
    //Permet de changer le look graphique 
	public static void setBestLookAndFeelAvailable(){
	   String system_lf = UIManager.getSystemLookAndFeelClassName().toLowerCase();
	   if(system_lf.contains("metal")){
		   try {
			   //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			   //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		   }catch (Exception e) {}
	   }else{
		   try {
			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		   }catch (Exception e) {}
	   }
	 }
}

