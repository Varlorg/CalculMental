package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreNiveau extends JPanel
{
    FenetreModele fm;

    JPanel pAction = new JPanel();
    JPanel pNiveau = new JPanel();
    JPanel pText = new JPanel();
    JLabel LabelNiveau  = new JLabel();

    JButton bRetour = new JButton("Retour");
    JButton bQuitter = new JButton("Quitter");

    public FenetreNiveau(FenetreModele f)
    {
        this.fm = f;
        this.fm.setTitre("Calcul Mental - Niveau");

        /*
         * Niveau
         */
        LabelNiveau.setText("Choisissez un niveau\n");
        LabelNiveau.setFont(new Font(null, Font.BOLD, 14));
        pText.add(LabelNiveau);

        JButton bFacile = new JButton("Facile");
        bFacile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fm.jeu('f');
            }
        });
        pNiveau.add(bFacile);
        JButton bMoyen = new JButton("Moyen");
        bMoyen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fm.jeu('m');
            }
        });
        pNiveau.add(bMoyen);
        JButton bDifficile = new JButton("Difficile");
        bDifficile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fm.jeu('d');
            }
        });
        pNiveau.add(bDifficile);

        bQuitter.addActionListener(new ActionQuitter());
        bRetour.addActionListener(new ActionRetour());
        pAction.add(bRetour);
        pAction.add(bQuitter);

        this.add(pText, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(pNiveau, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0,100)));
        this.add(pAction, BorderLayout.SOUTH);

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
