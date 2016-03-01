package tree;

public class Main {

	public static void main(String[] args){
		AVL_tree tree = new AVL_tree();

		Node root = new Node(10);
		
		root = tree.insert(root, 20);
		root = tree.insert(root, 30);
		root = tree.insert(root, 40);
		root = tree.insert(root, 50);
		root = tree.insert(root, 25);
		
		tree.PreOrder(root);
	}
}
