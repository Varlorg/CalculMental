package vue;

import score.*;

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
    JTextArea labelScore = new JTextArea(4,20);
    JTextField entreePseudo;
    JLabel labelPseudo = new JLabel();
    String pseudo;
    int score;

    JPanel pAction = new JPanel();
    JPanel pRecord = new JPanel();
    JPanel pText = new JPanel();
    JButton bJeu = new JButton("Jouer");
    JButton bScore = new JButton("Retour");
    JButton bQuitter = new JButton("Quitter");
    JButton bRecord = new JButton("Sauvegarder score");

    public FenetreRecord(FenetreModele f, int points)
    {
        score = points;
        System.out.println("" + score);
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Enregistrement");

        /* Demande du pseudo */
        labelScore.setEditable(false);
        labelScore.append("Partie terminée ! \nVous avez obtenu un score de " + score);
        pText.add(labelScore);

        labelPseudo.setText("Rentrez votre pseudo : ");
        labelPseudo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pRecord.add(labelPseudo);
        entreePseudo = new JTextField(12);
        //entreePseudo.setPreferredSize( new Dimension(60, 24 ) );
        entreePseudo.addKeyListener(this);
        entreePseudo.setEnabled(true);

        pRecord.add(entreePseudo);


        bQuitter.addActionListener(new ActionQuitter());
        bRecord.addActionListener(new ActionSauvegarde());
        bJeu.addActionListener(new ActionJouer());
        bScore.addActionListener(new ActionRetour());

        pAction.add(bJeu);
        pAction.add(bRecord);
        pAction.add(bScore);
        pAction.add(bQuitter);

        this.add(pText, BorderLayout.NORTH);
        this.add(pRecord, BorderLayout.CENTER);
        this.add(pAction, BorderLayout.SOUTH);
    }
    class ActionJouer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fm.niveau();
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

    class ActionSauvegarde implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            pseudo = entreePseudo.getText();
            hm.addScore(pseudo, score);
            System.out.println("Ajout de "+pseudo + " avec un score de "+score);
            fm.menu();
        }
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {

        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            pseudo = entreePseudo.getText();
            hm.addScore(pseudo, score);
            System.out.println("Ajout de "+pseudo + " avec un score de "+score);
            fm.menu();
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }

}
