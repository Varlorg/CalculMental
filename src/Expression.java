import java.lang.Math.*;

enum Operateur { moins, plus, multiplie, divise}

public class Expression 
{
    private int borneSup;
    private int operandeG;
    private int operandeD;
    private Operateur operateur;

    public Expression()
    {
		borneSup = 20;
		calculExpression();
    }
    
    public Expression(int borne)
    {
		borneSup = borne;
		calculExpression();
	}

	public void calculExpression()
	{
        operandeG = randomOperande();
        operandeD = randomOperande();
        operateur = randomOperateur();	
	}
	
    public int getOperandeG()
    {
        return this.operandeG;
    }

    public int getOperandeD()
    {
        return this.operandeD;
    }

    public Operateur getOperateur()
    {
        return this.operateur;
    }

    int randomOperande()
    {
        return (int) (Math.random()*borneSup) ;
    }

    Operateur randomOperateur()
    {
        int decision = (int) (Math.random()*2);
        Operateur op =null;
        switch(decision)
        {
            case 0:
                op = Operateur.moins;
                break;
            case 1:
                op =  Operateur.plus;
                break;
            default:
                break;
        }
        return op;
    
    }

    public int solution()
    {
        int solution = 0 ;

        switch(operateur)
        {
            case plus:
                solution = this.operandeG + this.operandeD;
                break;
            case moins:
                solution = this.operandeG - this.operandeD;
                break;
            case multiplie:
                solution = this.operandeG * this.operandeD;
                break;
            case divise:
                solution = this.operandeG / this.operandeD;
                break;
            default:
                break;
        }
        return solution;
    }
                

    public void afficherExpression()
    {
        System.out.print(this.operandeG);
        switch(this.operateur)
        {
            case plus :
                System.out.print(" + ");
                break;
            case moins:
                System.out.print(" - ");
                break;
            default:
                break;
        }
        System.out.print(this.operandeD + " : ");
        //System.out.println( this.solution()) ;
    }
    
    public String toString()
    {
        String exp = null;
        exp = ""+this.operandeG + " ";
        switch(this.operateur)
        {
            case plus :
                exp += " + ";
                break;
            case moins:
                exp += " - ";
                break;
            default:
                break;
        }

        exp += this.operandeD +" = ";
        return exp;
    }

    public String toString2()
    {
        String exp = null;
        exp = ""+this.operandeG + " ";
        switch(this.operateur)
        {
            case plus :
                exp += " + ";
                break;
            case moins:
                exp += " - ";
                break;
            default:
                break;
        }

        exp += this.operandeD +  "";
        return exp;
    }

}
