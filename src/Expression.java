import java.lang.Math.*;

/*  les différents types d'opération possible */
enum Operateur { SOUSTRACTION , ADDITION, MULTIPLICATION, DIVISION }

public class Expression 
{
    private int borneSup;
    private int operandeG;
    private int operandeD;
    private Operateur operateur;
    private char difficulte;

    public Expression()
    {
		borneSup = 20;
		calculExpression();
    }
    
    public Expression(char niveau)
    {
        difficulte = niveau;
        switch(niveau)
        {
		    case 'e':
                break;
            case 'f':
		        borneSup = 20;
                break;
            case 'm':
		        borneSup = 40;
                break;
            case 'd':
		        borneSup = 100;
                break;
            default:
                break;
        }
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
                op = Operateur.SOUSTRACTION;
                break;
            case 1:
                op =  Operateur.ADDITION;
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
            case ADDITION:
                solution = this.operandeG + this.operandeD;
                break;
            case SOUSTRACTION:
                solution = this.operandeG - this.operandeD;
                break;
            case MULTIPLICATION:
                solution = this.operandeG * this.operandeD;
                break;
            case DIVISION:
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
            case ADDITION :
                System.out.print(" + ");
                break;
            case SOUSTRACTION:
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
        exp = this.toString2() + " = ";
        return exp;
    }

    public String toString2()
    {
        String exp = null;
        exp = ""+this.operandeG + " ";
        switch(this.operateur)
        {
            case ADDITION :
                exp += " + ";
                break;
            case SOUSTRACTION:
                exp += " - ";
                break;
            default:
                break;
        }

        exp += this.operandeD +  "";
        return exp;
    }

}
