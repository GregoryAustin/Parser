import java.util.*; 


public class TreeNode extends TokenNode {
	private LinkedList<TreeNode> children;
	private TreeNode parent; 

	public TreeNode(int number, String tokenClass, String snippet) {
		super(number, tokenClass, snippet);
		children = new LinkedList<TreeNode>();
		parent = null; 
	}

	public void addChild(int number, String tokenClass, String snippet) {
		children.add(new TreeNode(number, tokenClass, snippet));
	}

	//TODO: implement addParent, so that the right hand side of the production can be linked to the left hand side 
	// Left is parent, right is children 
	public void addParent(LinkedList<TreeNode> children) {

	}

	public LinkedList<TreeNode> getChildren() {
		return children; 
	}

	//TODO: implement breadth first print function that prints to the tree to a file
}