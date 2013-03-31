import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreJeu extends JPanel implements KeyListener 
{
    JeuCalcul jeu;
    FenetreModele fm;
    JFrame fenetre;

    JMenuItem mReset;
    JMenuItem mJouer;


    JPanel pBouton;
    JButton bJouer;
    JButton bReset;
    JButton bQuitter;

    JPanel pChrono;
    java.util.Timer horloge;
    JLabel labelTemps;
    int temps= 30;
    JLabel textTemps;

    JPanel pJeu = new JPanel(new BorderLayout());
    JPanel pExpression;
    JLabel labelExpression;
    JLabel labelResultat;
    ImageIcon iResultatTrouve;
    ImageIcon iResultatFaux;
    JTextField labelReponse;
    int reponse;

    JLabel labelSolution = new JLabel("",SwingConstants.CENTER);
    JPanel pInfo;
    JLabel labelScore;

    public FenetreJeu(FenetreModele f,char n)
    {
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Jeu");
        fenetre = fm.getJFrame();

        /*Creation du menu
          *
          */
        /* menu Jeu */
       /* mJeu = new JMenu("Jeu");
        mJeu.setFont(fCambMini);
        mQuitter = new JMenuItem("Quitter");
        mQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                                KeyEvent.CTRL_MASK));
        mQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mJouer = new JMenuItem("Jouer");
        mJouer.setFont(fCambMini);
        mJouer.addActionListener(new ActionJouer());

        mReset = new JMenuItem("Nouvelle Partie");
        mReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                              KeyEvent.CTRL_MASK));
        mReset.addActionListener(new ActionReset());
        mJeu.add(mJouer);
        mJeu.add(mReset);
        mJeu.add(mQuitter);
        menuBar.add(mJeu);*/

        /*
         * Initialisation des panel
         */
        pBouton = new JPanel();
        pExpression = new JPanel();
        pInfo = new JPanel();
        pChrono = new JPanel();

        /* Creation des bouton */
        bJouer = new JButton("Commencer");
        bJouer.setFont(FenetreModele.fCamb);
        pBouton.add(bJouer);
        bJouer.addActionListener(new ActionJouer());

        bReset = new JButton("Arrêter");
        bReset.setFont(FenetreModele.fCamb);
        bReset.setEnabled(false);

        pBouton.add(bReset);
        bReset.addActionListener(new ActionReset());

        bQuitter = new JButton("Quitter");
        bQuitter.setFont(FenetreModele.fCamb);
        pBouton.add(bQuitter);
        bQuitter.addActionListener(new ActionQuitter());

        fenetre.getContentPane().add(pBouton, BorderLayout.SOUTH);

        jeu = new JeuCalcul(n);

        /* Panneau contenant l'expression et la saisie */
        //labelExpression = new JLabel (jeu.retournerExpression().toString());
        labelExpression = new JLabel ("");
        labelExpression.setPreferredSize( new Dimension( 80, 40 ) );
        pExpression.add(labelExpression);

        reponse = jeu.retournerExpression().solution();
        labelReponse = new JTextField();
        labelReponse.setPreferredSize( new Dimension( 40, 24 ) );
        labelReponse.addKeyListener(this);
        labelReponse.setEnabled(false);
        pExpression.add(labelReponse);


        labelResultat = new JLabel("");
        labelResultat.setPreferredSize(new Dimension(64,48));
        labelResultat.setHorizontalAlignment(SwingConstants.RIGHT);
        pExpression.add(labelResultat);

        iResultatTrouve = new ImageIcon("img/right.png");
        iResultatFaux = new ImageIcon("img/wrong.png");

        pJeu.add(pExpression,BorderLayout.CENTER);
        //fenetre.getContentPane().add(pExpression, BorderLayout.CENTER);
        fenetre.getContentPane().add(pJeu, BorderLayout.CENTER);

        /*Panneau du score*/
        JLabel textScore = new JLabel("Score : ");
        textScore.setFont(FenetreModele.fCamb);
        pInfo.add(textScore);
        labelScore = new JLabel(jeu.getScore()+"");
        labelScore.setFont(FenetreModele.fCamb);
        labelScore.setPreferredSize(new Dimension(42,24));
        pInfo.add(labelScore);

        fenetre.getContentPane().add(pInfo, BorderLayout.EAST);

        /* Compte a rebour*/
        textTemps = new JLabel("Temps Restant : ");
        textTemps.setFont(FenetreModele.fCamb);
        labelTemps = new JLabel(Integer.toString(temps));
        labelTemps.setFont(FenetreModele.fCamb);
        pChrono.add(textTemps);
        pChrono.add(labelTemps);
        fenetre.getContentPane().add(pChrono, BorderLayout.NORTH);


        fenetre.pack();
        fenetre.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try
            {
                labelSolution.setText("");
                labelSolution.setFont(FenetreModele.fLibe);
                if(reponse == Integer.parseInt(labelReponse.getText()))
                {
                    jeu.incScore();
                    //labelResultat  = labelResultatTrouve;
                    labelResultat.setIcon(iResultatTrouve);
                    labelSolution.setForeground(Color.green);
                }
                else
                {
                    //labelResultat = labelResultatFaux;
                    labelResultat.setIcon(iResultatFaux);
                    labelSolution.setForeground(Color.red);
                }
                labelSolution.setText( "La solution de " + jeu.retournerExpression().toString2() + " est " + reponse  );
                pJeu.add(labelSolution, BorderLayout.SOUTH);
                labelSolution.setPreferredSize(new Dimension(170,30));
                jeu.supprimerExpression();
                labelExpression.setText(jeu.retournerExpression().toString());
                reponse = jeu.retournerExpression().solution();

            }
            catch(NumberFormatException exc)
            {
                JOptionPane.showMessageDialog(fenetre,"Un nombre est attendu comme réponse","Erreur", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                labelReponse.setText("");
                labelScore.setText(jeu.getScore()+"");
            }

        }


    }

    public void keyReleased(KeyEvent e) {
    }

    class ActionQuitter implements ActionListener
    {
        public void actionPerformed(ActionEvent ec)
        {
            System.exit(0);
        }
    }

    class ActionReset implements ActionListener
    {
        public void actionPerformed(ActionEvent ec)
        {
            jeu.razScore();
            labelScore.setText(jeu.getScore()+"");

            labelSolution.setText("");
            jeu.supprimerExpression();
            labelResultat.setIcon(null);
            //labelExpression.setText(jeu.retournerExpression().toString());
            labelExpression.setText("");
            reponse = jeu.retournerExpression().solution();
            labelReponse.setText("");
            if(horloge != null)
            {
                horloge.cancel();
                horloge.purge();
                horloge= null;
            }
            if( !bJouer.isEnabled())
                bJouer.setEnabled(true);

            temps = 30;
            labelTemps.setText(temps+"");
        }
    }

    class ActionJouer implements ActionListener
    {
        public void actionPerformed(ActionEvent ec)
        {
            labelExpression.setText(jeu.retournerExpression().toString());
            if(horloge == null)
            {
                labelReponse.setEnabled(true);
                horloge = new java.util.Timer();
                horloge.schedule( new Decompte(),1000,1000);
                bJouer.setEnabled(false);
                bReset.setEnabled(true);
            }
        }
    }


    class Decompte extends java.util.TimerTask {
        public void run() {
            if(temps !=0)
                temps--;
            if (temps == 0)
            {
                //labelReponse.disable();
                labelReponse.setEnabled(false);
                horloge.cancel();
                horloge.purge();
                horloge= null;
            }
            labelTemps.setText(temps+"");
        }
    }
    
}
