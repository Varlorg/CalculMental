import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreMenu  extends JPanel
{
    FenetreModele fm;

    JPanel pAction = new JPanel();
    JPanel pCentre = new JPanel();

    JButton bJeu = new JButton("Jouer");
    JButton bScore = new JButton("Score");
    JButton bQuitter = new JButton("Quitter");

    JLabel labelPresentation = new JLabel("Bienvenue dans ce jeu de calcul mental !");

    public FenetreMenu(FenetreModele f)
    {
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Menu");

        labelPresentation.setText("Bienvenue dans ce jeu de calcul mental !");
        labelPresentation.setFont(new Font(null, Font.BOLD, 17));
        bQuitter.addActionListener(new ActionQuitter());
        bJeu.addActionListener(new ActionJouer());
        bScore.addActionListener(new ActionScore());
		f.mJouer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                    fm.niveau();
            }
        });
        
        pCentre.add(labelPresentation);
        this.add(Box.createRigidArea(new Dimension(0,100)));
        pAction.add(bJeu);
        pAction.add(bScore);
        pAction.add(bQuitter);

        this.add(pCentre, BorderLayout.CENTER);
        this.add(pAction, BorderLayout.SOUTH);
    }
    
    class ActionJouer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fm.niveau();
        }
    }

    class ActionScore implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fm.score();
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
