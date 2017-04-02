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

	public LinkedList<TreeNode> getChildren() {
		return children; 
	}
}