import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser
{
	private Context context; 


	private TokenList lexerList; 
	private Queue<Character> list; 
	private Stack<String> stack; 


	public Parser () {
		//This lexeroutput file is from the example.spl file in this directory 
		lexerList = new TokenList("lexeroutput"); 
		list = convertToParseFormat(lexerList);
		//System.out.println(list);

		//Prints out the table ready queue (just to see it)
		while (!list.isEmpty()) {
			System.out.print(list.remove());
		}
		System.out.println();
		stack = new Stack<String>();
		context = new Context();
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
	
	public void parse()
	{
////////////////////////////////////
System.out.println("In Parse:");
lexerList = new TokenList("lexeroutput"); 		
list = convertToParseFormat(lexerList);
		list.add('$');
		
////////////////////////////////////
System.out.println("In Parse: size " + list.size());
		
		
		int curState = 0;
		stack.push(Integer.toString(curState));
		Character curSymbol;
		String tempSymbol = "";
				
		for (int i = 0; i < list.size(); i++)	//may not be size
		{
			////////////////////////////////////
System.out.println("------------------------In Parse: for---------------------" + stack);		
			curState = Integer.parseInt(stack.peek());
	
			curSymbol = list.remove();	
////////////////////////////////////
System.out.println("In Parse: about to context " + curSymbol);				
System.out.println("In Parse: about to context " + curState);				
			tempSymbol = context.getState(curSymbol, curState);	//may need char not Character

////////////////////////////////////
System.out.println("In Parse: tempsymbol " + tempSymbol);									
System.out.println("In Parse: curstate " + curState);									

////////////////////////////////////
System.out.println("In Parse: after remove" );					
			while (tempSymbol.charAt(0) != 's') //change to while
			{
				if (tempSymbol.charAt(0) == 'r')
				{
	////////////////////////////////////
	System.out.println("In Parse: before reduce" );						
	System.out.println("In Parse: curstate " + curState );						
	System.out.println("In Parse: tempSymbol " + tempSymbol);						
	System.out.println("In Parse: tempSymbol/sub1 " + tempSymbol.substring(1));						
					Character tmpChar = reduce(Integer.parseInt(tempSymbol.substring(1)));
					//curState = Integer.parseInt(tempSymbol.substring(1));
					//stack.push(Integer.toString(curState));				
					curState = Integer.parseInt(stack.peek());
					tempSymbol = context.getState(tmpChar, curState);
					stack.push(Character.toString(tmpChar));
					stack.push(tempSymbol);
					curSymbol = tmpChar;
	////////////////////////////////////
	System.out.println("In Parse: after reduce : tempSymbol " + tempSymbol );						
				}
				else
				{
	////////////////////////////////////
	System.out.println("In Parse: in go tmpsymb " + tempSymbol );						
	System.out.println("In Parse: in go curState " + curState);						
	System.out.println("In Parse: in go curSumb " + curSymbol);						
					
					//curState = Integer.parseInt(stack.peek());
				
					
					tempSymbol = context.getState(curSymbol, curState);	//may need char not Character
		////////////////////////////////////
	System.out.println("In Parse: in go2 tmpsymb " + tempSymbol );						
	System.out.println("In Parse: in go2 curState " + curState);						
	System.out.println("In Parse: in go2 curSumb " + curSymbol);						

				
					stack.push(Integer.toString(curState));					
				}
				
			}
			
			if (tempSymbol.charAt(0) == 's')
			{
			///////////////////////////	
				System.out.println("=============shifting state " + curState + " ;symbol " + curSymbol);
				
				curState = Integer.parseInt(tempSymbol.substring(1));
			///////////////////////////////////
				System.out.println("=============shifting state " + curState);
				System.out.println("=============shifting state " + curSymbol);
				shift(curSymbol, curState);
			}
			else 
			{
			///////////////////////////	
			//	System.out.println("go state " + curState);				
			//	stack.push(Integer.toString(curState));
			}

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
	//The symbol
	//after the symbol the state is put 
	private void shift(Character symbol, int state) 
	{
		//create new node
		//push new node to reference stack
		stack.push(symbol.toString());
		stack.push(Integer.toString(state));
	}

	//TODO: reduce
	//The right hand side of a production are replaced by the 
	//nonterminal on the left hand side. 
	//and popped off the stack 
	//important: the last symbol on the right hand side is the top of the stack
	//When things are reduced the new top of the stack is used for the next state
	//then the left hand side is put and then the state. (always symbol then state)
	//when reduced the right hand side is the children of the left hand side 
	private Character reduce(int production) 
	{ 
	/*	//make newNode
		switch (production)
		{
			case 18:
			{
				stack.pop;
				stack.pop;
				//Node n1 = stack.pop;
				//newNode.child = n1;
				stack.push('S');
			};break;
			default: System.out.println("didn't do that one yet..");
		}
		*/
	////////////////////////////////////////////
		System.out.println("REDUCE:reducing on production: " + production);
		
		////////////////////////////////////////////
		System.out.println("REDUCE: just popped " + stack.pop());	
		System.out.println("REDUCE: just popped " + stack.pop());	
		System.out.println("REDUCE: stack is " + stack);	
		//stack.push("S");
		return 'S';
	}

}