import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser
{
	//private Context context; 


	private TokenList lexerList; 
	private Queue<Character> list; 
	private Stack<String> stack; 
	private Context context; 


	public Parser () {
		//This lexeroutput file is from the example.spl file in this directory 
		lexerList = new TokenList("lexeroutput"); 
		list = convertToParseFormat(lexerList);
		stack = new Stack<String>();
		context = new Context();

		this.makeTree();
	} 

	private Queue<Character> convertToParseFormat(TokenList list){ 
		TokenNode tmp = null; 
		Queue<Character> tmpQ = new LinkedList<Character>();

		//All this business is adding 
		while (!list.isEmpty()){ 
			tmp = list.removeFromHead(); 
			// for integers
			// integer -> b

			// for user-defined name 
			// user-defined name -> u

			// for short strings
			// short string -> s
			if (tmp.tokenClass.equals("integer") || tmp.tokenClass.equals("user-defined name") || tmp.tokenClass.equals("short string")) {
				if (tmp.tokenClass.equals("integer")) {
					tmpQ.add('b');
				} else if (tmp.tokenClass.equals("user-defined name")) {
					tmpQ.add('u');
				} else if (tmp.tokenClass.equals("short string")) {
					tmpQ.add('s');
				}
			} else {
				if (tmp.tokenClass.equals("keyword")) {
					switch (tmp.snippet) {
						// "eq" -> e/
						// "and"-> a/
						// "or" -> o/
						// "not" -> n
						// "add" -> d
						// "sub" -> q 
						// "mult" -> m
						// "if" -> f 
						// "then" -> t
						// "else" -> l
						// "while" -> w
						// "for" -> r
						// "input" -> i
						// "output" -> z
						// "halt" -> h
						// "proc" -> p
						case "eq":
							tmpQ.add('e');
							break;
						case "and": 
							tmpQ.add('a');
							break;
						case "or": 
							tmpQ.add('o');
							break;
						case "not": 
							tmpQ.add('n');
							break; 
						case "add":
							tmpQ.add('d');
							break;
						case "sub":
							tmpQ.add('q');
							break;
						case "mult":
							tmpQ.add('m');
							break;
						case "if":
							tmpQ.add('f');
							break;
						case "then":
							tmpQ.add('t');
							break;
						case "else":
							tmpQ.add('l');
							break;
						case "while":
							tmpQ.add('w');
							break;
						case "for":
							tmpQ.add('r');
							break;
						case "input":
							tmpQ.add('i');
							break;
						case "output":
							tmpQ.add('z');
							break;
						case "halt":
							tmpQ.add('h');
							break;
						case "proc":
							tmpQ.add('p');
							break;
						default: 
							System.out.println("system error with keyword parse conversion ");
							break; 
					}
				} else if (tmp.tokenClass.equals("grouping symbol")) {
					//Snippets for grouping symbols
					// (
					// )
					// {
					// }
					// ,
					// ;
					tmpQ.add(tmp.snippet.charAt(0));

				} else if (tmp.tokenClass.equals("comparison symbol")) {
					tmpQ.add(tmp.snippet.charAt(0));
				} else if (tmp.tokenClass.equals("assignment operator")) {
					tmpQ.add(tmp.snippet.charAt(0));
				} else {
					System.out.println("system error with parse conversion");
				}
			}

		}


//snippets for comparison symbols
// <
// > 

//Snippets for assignment operator
// =
		return tmpQ; 

	}

	private void makeTree() {
		char tbr = list.remove();
		String num = context.getState(tbr); 
		System.out.println(num);
		if (num.substring(0,1).equals("s")) {
			shift(tbr, Integer.parseInt(num.substring(1)));

			//while (!list.isEmpty()) {
			tbr = list.remove();
			System.out.println(tbr);
			num = context.getState(tbr, Integer.parseInt(num.substring(1)));
			System.out.println(num);
			//}
		}
	}






	//TODO: because when you reduce you use a production number, there has to be some kind of data structure with all the productions

	//Get a symbol and number 
	//r means reduce 
	//reduce has a production number next to it
	//s means shift
	//shift is a state number next to it


	//TODO: shift
	//A symbol is read from the input and pushed on the stack
	//after the symbol the state is put 
	private void shift(char symbol, int nextState) {
		stack.push("" + symbol);
		stack.push("" + nextState);
	}

	//TODO: reduce
	//The right hand side of a production are replaced by the 
	//nonterminal on the left hand side. 
	//and popped off the stack 
	//important: the last symbol on the right hand side is the top of the stack
	//When things are reduced the new top of the stack is used for the next state
	//then the left hand side is put and then the state. (always symbol then state)
	//when reduced the right hand side is the children of the left hand side 
	private void reduce() { 
		//when reduced the right hand side is the children of the left hand side 

	}

}