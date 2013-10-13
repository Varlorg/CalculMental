import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class FenetreRecord  extends JPanel implements KeyListener 
{
    FenetreModele fm;
    JFrame fenetre;
    
	HighscoreManager hm = new HighscoreManager();
	JLabel labelScore= new JLabel();
	JTextField labelPseudo;
	String pseudo;
	int score;
	
    JPanel pAction = new JPanel();
    JPanel pRecord = new JPanel();
    JButton bJeu = new JButton("Jouer");
    JButton bScore = new JButton("Retour");
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

    public FenetreRecord(FenetreModele f, int points)
    {
		score = points;
		System.out.println("" + score);
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Enregistrement");
        this.fenetre = fm.getJFrame();

		/*try{
			FileInputStream fscores = new FileInputStream("scores");
			ObjectInputStream ois = new ObjectInputStream(fscores);
			scores = (Hashtable) ois.readObject();
		}catch(Exception e){
			System.out.println("Table des scores inexistante !");
		}*/
		
	   /*Creation du menu
		 *
		 */
		menuBar = new JMenuBar();
		/* menu Jeu */
		mJeu = new JMenu("Jeu");
		mJeu.setFont(FenetreModele.fCambMini);
		mQuitter = new JMenuItem("Quitter");
		mQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
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

		/* Demande du pseudo */
		labelScore.setText("Vous avez obtenu un score de " + score);
		pRecord.add(labelScore);
        labelPseudo = new JTextField();
        labelPseudo.setPreferredSize( new Dimension( 100, 24 ) );
        labelPseudo.addKeyListener(this);	
        labelPseudo.setEnabled(true);
        pRecord.add(labelPseudo);

        bQuitter.addActionListener(new ActionQuitter());
        mJouer = new JMenuItem("Jouer");
        mJouer.setFont(FenetreModele.fCambMini);
        mJouer.addActionListener(new ActionJouer());

        mJeu.add(mJouer);
        mJeu.add(mQuitter);

        bJeu.addActionListener(new ActionJouer());
        bScore.addActionListener(new ActionRetour());
        
        pAction.add(bJeu);
        pAction.add(bScore);
        pAction.add(bQuitter);
        
        
       /* fenetre.add("South",pAction);
        fenetre.add("Center",pRecord);*/
        fenetre.getContentPane().add(pRecord, BorderLayout.NORTH);
        fenetre.getContentPane().add(pAction, BorderLayout.SOUTH);
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
    
    class ActionRetour implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fm.menu();
        }
    }
    
    class ActionQuitter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
     public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
			pseudo = labelPseudo.getText();
			hm.addScore(pseudo, score);
			System.out.println("Ajout de "+pseudo + "avec un score de "+score);
			fm.menu();
        }


    }

    public void keyReleased(KeyEvent e) {
    }

}
