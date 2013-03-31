import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FenetreModele
{
    JFrame fenetre = new JFrame();

    //Font fSoluce = new Font("Cambria",Font.BOLD | Font.ITALIC, 12);
    public static final Font fLibe = new Font("Liberation Sans",Font.BOLD , 14);
    public static final Font fCamb = new Font("Cambria",Font.BOLD , 14);
    public static final Font fCambMini = new Font("Cambria",Font.BOLD , 13);

    public FenetreModele()
    {
        /*Creation de la fenetre générale
         *
         */
        //fenetre.setSize(700, 300);
        this.fenetre.setPreferredSize(new Dimension(450,200));
        this.fenetre.setResizable(false);
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.fenetre.setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.fenetre.getContentPane().setLayout(new BorderLayout());

		this.menu();

        this.fenetre.pack();
        this.fenetre.setVisible(true);
     }



    public void jeu(char n)
    {
        fenetre.getContentPane().removeAll();
        FenetreJeu ecranJeu = new FenetreJeu(this,n);
		this.fenetre.getContentPane().add(ecranJeu);
		ecranJeu.revalidate();
    }

    public void menu()
    {
        fenetre.getContentPane().removeAll();
        FenetreMenu ecranMenu = new FenetreMenu(this);
		this.fenetre.getContentPane().add(ecranMenu);
		ecranMenu.revalidate();
    }

    public void niveau()
    {
        fenetre.getContentPane().removeAll();
        FenetreNiveau ecranNiveau = new FenetreNiveau(this);
		this.fenetre.getContentPane().add(ecranNiveau);
		ecranNiveau.revalidate();
    }

    public JFrame getJFrame()
    {
        return this.fenetre;
    }

    public void setTitre(String nom)
    {
        this.fenetre.setTitle(nom);
    }


    


}
