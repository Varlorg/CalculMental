import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class FenetreScore  extends JPanel
{
    FenetreModele fm;
    JTextArea textScore  = new JTextArea(5, 20);

    JPanel pAction = new JPanel();
    JButton bJeu = new JButton("Jouer");
    JButton bScore = new JButton("Retour");
    JButton bQuitter = new JButton("Quitter");

    public FenetreScore(FenetreModele f)
    {
        JScrollPane scrollPane = new JScrollPane(textScore);
        textScore.setEditable(false);
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Score");

        //Affichage score
        HighscoreManager hm = new HighscoreManager();
        System.out.print(hm.getHighscoreString());
        textScore.append(hm.getHighscoreString());

        bJeu.addActionListener(new ActionJouer());
        bScore.addActionListener(new ActionRetour());
        bQuitter.addActionListener(new ActionQuitter());
        pAction.add(bJeu);
        pAction.add(bScore);
        pAction.add(bQuitter);

        this.add("North",textScore);
        this.add("Center",Box.createRigidArea(new Dimension(0,100)));
        this.add("PAGE_END",pAction);
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

}
