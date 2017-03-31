
public class Main
{
	public static void main(String[] args)
	{
		//testing the new parser tokenList
		TokenList tl = new TokenList("lexeroutput");
		System.out.println(tl);
		while (!tl.isEmpty())
			System.out.println("Removed: " + tl.removeFromHead().tokenClass);
		System.out.println(tl);
	}
}