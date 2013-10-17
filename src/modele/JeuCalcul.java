package modele;

import java.util.*;

public class JeuCalcul
{
    ArrayList<Expression> listExpression;
    int score;
    char difficulte;

    public JeuCalcul()
    {
        listExpression = new ArrayList<Expression>();
        score = 0;
        difficulte = 'e';
        Expression e = new Expression();
        this.listExpression.add(e);
    }

    public JeuCalcul(char niveau)
    {
        listExpression = new ArrayList<Expression>();
        score = 0;
        difficulte = niveau;
        Expression e = new Expression(difficulte);
        this.listExpression.add(e);
    }

    public void incScore()
    {
        this.score++;
        System.out.println(" Point marqué ");
    }

    public int getScore()
    {
        return this.score;
    }

    public void razScore()
    {
        this.score = 0;
    }

    public void  ajouterExpression()
    {
        Expression e = new Expression(difficulte);
        this.listExpression.add(e);
    }

    public Expression retournerExpression()
    {
        if(this.listExpression.size() != 0)
        {
            this.ajouterExpression();
            return this.listExpression.get(0);

        }
        else
            return null;
    }

    public void supprimerExpression()
    {
        if(this.listExpression.size() != 0)
        {
            this.listExpression.remove(0);

        }
    }

    public void afficherExpression()
    {
        this.retournerExpression().afficherExpression();
    }

    public static void main(String[] args)
    {
        JeuCalcul jeu= new JeuCalcul();
        Scanner sc = new Scanner(System.in);
        for(int j = 0; j <15; j++)
        {
            try
            {
                Expression e = jeu.retournerExpression();
                e.afficherExpression();
                int solutionUser = sc.nextInt();
                if(e.solution() == solutionUser)
                {
                    jeu.score++;
                    System.out.println("Bravo ! ");
                }
                else
                {
                    System.out.println("Mauvaise réponse ! ");
                }
                jeu.supprimerExpression();

            }
            catch(InputMismatchException exc )
            {
                System.out.println("Il faut entrer un nombre ! ");
                sc.next();
            }
        }
    }
}
