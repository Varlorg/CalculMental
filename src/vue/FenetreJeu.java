package vue;

import modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreJeu extends JPanel implements KeyListener
{
    final int duree = 30;
    JeuCalcul jeu;
    FenetreModele fm;
    //JFrame fenetre;

    JPanel pBouton;
    JButton bJouer;
    JButton bReset;
    JButton bQuitter;

    JPanel pChrono;
    java.util.Timer horloge;
    JLabel labelTemps;
    int temps=duree;
    JLabel textTemps;

    JPanel pJeu = new JPanel();
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

        /*
         * Initialisation des panel
         */
        pBouton = new JPanel();
        pInfo = new JPanel();
        pChrono = new JPanel();

        /* Creation des bouton */
        bJouer = new JButton("Commencer");
        bJouer.setFont(FenetreModele.fCamb);
        bJouer.addActionListener(new ActionJouer());
        pBouton.add(bJouer);

        bReset = new JButton("Retour");
        bReset.setFont(FenetreModele.fCamb);
        bReset.setEnabled(true);
        bReset.addActionListener(new ActionReset());
        pBouton.add(bReset);

        bQuitter = new JButton("Quitter");
        bQuitter.setFont(FenetreModele.fCamb);
        bQuitter.addActionListener(new ActionQuitter());
        pBouton.add(bQuitter);

        jeu = new JeuCalcul(n);
        /* Panneau contenant l'expression et la saisie */
        //labelExpression = new JLabel (jeu.retournerExpression().toString());
        labelExpression = new JLabel ();
        labelExpression.setPreferredSize( new Dimension( 80, 40 ) );
        pJeu.add(labelExpression);

        reponse = jeu.retournerExpression().solution();
        labelReponse = new JTextField();
        labelReponse.setPreferredSize( new Dimension( 40, 24 ) );
        labelReponse.addKeyListener(this);
        labelReponse.setEnabled(false);
        pJeu.add(labelReponse);


        labelResultat = new JLabel();
        labelResultat.setPreferredSize(new Dimension(64,48));
        labelResultat.setHorizontalAlignment(SwingConstants.RIGHT);
        pJeu.add(labelResultat);

        iResultatTrouve = new ImageIcon("img/right.png");
        iResultatFaux = new ImageIcon("img/wrong.png");

        /*Panneau du score*/
        JLabel textScore = new JLabel("Score : ");
        textScore.setFont(FenetreModele.fCamb);
        pInfo.add(textScore);
        labelScore = new JLabel(jeu.getScore()+"");
        labelScore.setFont(FenetreModele.fCamb);
        labelScore.setPreferredSize(new Dimension(42,24));
        pInfo.add(labelScore);

        /* Compte a rebours*/
        textTemps = new JLabel("Temps Restant : ");
        textTemps.setFont(FenetreModele.fCamb);
        labelTemps = new JLabel(Integer.toString(temps));
        labelTemps.setFont(FenetreModele.fCamb);
        pChrono.add(textTemps);
        pChrono.add(labelTemps);

        this.fm.getContentPane().setLayout(new BorderLayout());
        this.add(pChrono, BorderLayout.NORTH);
        this.add("Center",Box.createRigidArea(new Dimension(40,0)));
        this.add(pInfo, BorderLayout.EAST);
        this.add(pJeu, BorderLayout.CENTER);
        this.add(pBouton, BorderLayout.SOUTH);
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {

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
                    labelSolution.setText( "La solution de " + jeu.retournerExpression().toString2() + " est " + reponse  );
                    pJeu.add(labelSolution, BorderLayout.SOUTH);
                    labelSolution.setPreferredSize(new Dimension(170,30));
                }

                jeu.supprimerExpression();
                labelExpression.setText(jeu.retournerExpression().toString());
                reponse = jeu.retournerExpression().solution();

            }
            catch(NumberFormatException exc)
            {
                JOptionPane.showMessageDialog(this,"Un nombre est attendu comme réponse","Erreur", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                labelReponse.setText("");
                labelScore.setText(jeu.getScore()+"");
            }

        }


    }

    public void keyReleased(KeyEvent e)
    {
    }

    class ActionQuitter implements ActionListener
    {
        public void actionPerformed(ActionEvent ec)
        {
            if ( horloge != null )
            {
                horloge.cancel();
                horloge.purge();
                horloge = null;
            }
            System.exit(0);
        }
    }

    class ActionReset implements ActionListener
    {
        public void actionPerformed(ActionEvent ec)
        {
            if(bReset.getText().equals("Arrêter"))
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

                bReset.setText("Retour");
                temps = duree;
                labelTemps.setText(temps+"");
            }
            else
            {
                fm.menu();
            }
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
                bReset.setText("Arrêter");
                bReset.setEnabled(true);
            }
        }
    }

    class Decompte extends java.util.TimerTask
    {
        public void run()
        {
            if(temps !=0)
                if (temps > duree*3/4)
                {
                    labelTemps.setForeground(FenetreModele.VERT);
                }
                else if (temps > duree*1/2)
                {
                    labelTemps.setForeground(FenetreModele.JAUNE);
                }
                else if (temps > duree*1/4)
                {
                    labelTemps.setForeground(FenetreModele.ORANGE);
                }
                else
                {
                    labelTemps.setForeground(FenetreModele.ROUGE);
                }
            temps--;
            if (temps <= 0)
            {
                //labelReponse.disable();
                labelReponse.setEnabled(false);
                horloge.cancel();
                horloge.purge();
                horloge = null;
                bJouer.setEnabled(false);
                bReset.setEnabled(false);
                fm.record(jeu.getScore());
            }
            labelTemps.setText(temps+"");
        }
    }

}
