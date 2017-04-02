import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Context
{
	private char rowHeader [];
	private String [][] transitionTable = new String[133][50];
	// 50 columns 
	public Context () {
		char rowStuff [] = {'(', ')', ',', '0', '1', ';', '<', '=', '>', 'a', 'b', 'd', 'e', 'f', 'h', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'z', '{', '}', '$', 'A', 'B', 'C', 'D', 'I', 'L', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		rowHeader = rowStuff; 
		

		File file = new File("parse_table.txt");
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter("\n");
			String str = "";
			int row = 0; 
			while (scan.hasNext()){
				//System.out.print("Row " + row + ": ");
				
				str = scan.next();
				char [] charArr = str.toCharArray();
				//System.out.println(charArr);
				String [] strArr = new String [50];
				int count = 0;
				String tmp = "";

				int padding = 0;
				boolean pad = true;  
				while (pad)  {
					if (charArr[padding] != '\t') 
						padding++;
					else 
						pad = false; 
				} padding++;
				for (int i2 = padding; i2 < charArr.length; i2++) {
					if(charArr[i2] == '\t') {
						strArr[count] = null;
						count++;
					} else {
						tmp = "";
						while (charArr[i2] != '\t' && i2 < charArr.length-1) {
							tmp += charArr[i2]; 
							if (i2 < charArr.length-1) 
								i2++;
						}
						if (i2 == charArr.length-1)
							if (charArr[charArr.length-1] != '\t') 
								tmp += charArr[charArr.length-1];

						strArr[count] = tmp;
						count++;
					}
				}
				for (int i = 0; i < strArr.length; i++) {
					transitionTable[row][i] = strArr[i];
					//System.out.print(strArr[i] + "|");
				} //System.out.println();
				//System.out.println(count);
				row++;
			}
			System.out.println (printRow(0));
			
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	private String printRow(int row) {
		String tmp = "";
		for (int i = 0; i < 50; i++) {
			tmp += transitionTable[row][i] += " ";
		}
		return tmp; 
	}


}