import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JeuCalculGUI implements KeyListener
{
    JeuCalcul jeu;
    
    JFrame fenetre;

    JMenuBar menuBar;
    
    JMenu mJeu;
    JMenuItem mQuitter;
    JMenuItem mReset;
    JMenuItem mJouer;

    JMenu mApropos;
    JMenuItem mVersion;
    JMenuItem mAbout;


    JPanel pBouton;
    JButton bJouer;
    JButton bReset;
    JButton bQuitter;
    
    JPanel pChrono;
    java.util.Timer horloge;
    JLabel labelTemps;
    int temps= 30;
    JLabel textTemps;
   
    JPanel pExpression;
    JLabel labelExpression;
    JTextField labelReponse;
    int reponse;

    JPanel pInfo;
    JLabel labelScore;


    public JeuCalculGUI()
    {
        fenetre = new JFrame("Calcul mental");
        //fenetre.setSize(700, 300);
        fenetre.setPreferredSize(new Dimension(450,170));
        //Nous demandons maintenant à notre objet de se positionner au centre
        fenetre.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.getContentPane().setLayout(new BorderLayout());
      

        
        menuBar = new JMenuBar();
        
        mJeu = new JMenu("Jeu");
        mQuitter = new JMenuItem("Quitter");
        mQuitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
            }        
            });
        mJouer = new JMenuItem("Jouer");
        mJouer.addActionListener(new ActionJouer());
        mReset = new JMenuItem("Nouvelle Partie");
        mReset.addActionListener(new ActionReset());
        mJeu.add(mJouer);
        mJeu.add(mReset);
        mJeu.add(mQuitter);
        menuBar.add(mJeu);
    
        mApropos = new JMenu("Aide");
        menuBar.add(mApropos);
        
        fenetre.setJMenuBar(menuBar);
        
        pBouton = new JPanel();
        pExpression = new JPanel();
        pInfo = new JPanel();
        pChrono = new JPanel();

        /* Creation des bouton */
        bJouer = new JButton("Jouer");
        pBouton.add(bJouer);
        bJouer.addActionListener(new ActionJouer());

        bReset = new JButton("Nouvelle Partie");
        pBouton.add(bReset);
        bReset.addActionListener(new ActionReset());
        
        bQuitter = new JButton("Quitter");
        pBouton.add(bQuitter);
        bQuitter.addActionListener(new ActionQuitter());

        fenetre.getContentPane().add(pBouton, BorderLayout.SOUTH);


        jeu = new JeuCalcul(1);

        /* Panneau contenant l'expression et la saisie */
        //labelExpression = new JLabel (jeu.retournerExpression().toString());
        labelExpression = new JLabel ("");
        labelExpression.setPreferredSize( new Dimension( 80, 40 ) );
        pExpression.add(labelExpression);
        reponse = jeu.retournerExpression().solution();
        labelReponse = new JTextField();
        labelReponse.setPreferredSize( new Dimension( 40, 24 ) );
        labelReponse.addKeyListener(this); 
        pExpression.add(labelReponse);

        fenetre.getContentPane().add(pExpression, BorderLayout.CENTER);

        /*Panneau du score*/
        JLabel textScore = new JLabel("Score : ");
        pInfo.add(textScore);
        labelScore = new JLabel(jeu.getScore()+"");
        labelScore.setPreferredSize(new Dimension(40,24));
        pInfo.add(labelScore);
        
        fenetre.getContentPane().add(pInfo, BorderLayout.EAST);
        
       /* COmpte a rebour*/ 
        textTemps = new JLabel("Temps Restant : ");
        labelTemps = new JLabel(Integer.toString(temps));
        pChrono.add(textTemps);
        pChrono.add(labelTemps);
        fenetre.getContentPane().add(pChrono, BorderLayout.NORTH);


        fenetre.pack();
        fenetre.setVisible(true);
    }

    public static void main(String[] args)
    {
        new JeuCalculGUI();
        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            try
            {
                    if(reponse == Integer.parseInt(labelReponse.getText()))
                    {
                        jeu.incScore();
                    }
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

            jeu.supprimerExpression();
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
                horloge = new java.util.Timer();
                horloge.schedule( new Decompte()  ,1000,1000);
                bJouer.setEnabled(false);
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

