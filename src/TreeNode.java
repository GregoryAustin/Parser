import java.util.*; 


public class TreeNode extends TokenNode {
	private LinkedList<TreeNode> children;

	public TreeNode(int number, String tokenClass, String snippet) {
		super(number, tokenClass, snippet);
		children = new LinkedList<TreeNode>();
	}

	public void addChild(TreeNode node) {
		children.addFirst(node);
	}

	//TODO: implement addParent, so that the right hand side of the production can be linked to the left hand side 
	// Left is parent, right is children 

	public LinkedList<TreeNode> getChildren() {
		return children; 
	}
	
	public int childrenSize()
	{		
		return children.size();
	}
	
	public TreeNode getChild(int i)
	{
		return children.get(i);
	}
	
	public String toString()
	{
		String ret = "||Node " + tokenNo + "\t" + tokenClass 
				+ "\t" + snippet + "||";
		return ret;
	}
		

	//TODO: implement breadth first print function that prints to the tree to a file
}