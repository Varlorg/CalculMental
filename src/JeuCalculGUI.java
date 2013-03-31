import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class JeuCalculGUI
{
    public JeuCalculGUI()
    {
        new FenetreMenu(new FenetreModele());
    }

    public static void main(String[] args)
    {
        new JeuCalculGUI();
    }
}
