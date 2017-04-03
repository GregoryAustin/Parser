
public class Main
{
	public static void main(String[] args)
	{
		//testing the new parser tokenList
		/*TokenList tl = new TokenList("lexeroutput");
		System.out.println(tl);
		while (!tl.isEmpty()){
			TokenNode temp = tl.removeFromHead();
			System.out.println("Removed: " +  temp.tokenClass);
		}
		System.out.println(tl);
	}*/ 
		Parser prsr = new Parser();
		prsr.parse();
		System.out.println("__________Parser output_____________");
		prsr.print();
		//Context cont = new Context();

	}


}

