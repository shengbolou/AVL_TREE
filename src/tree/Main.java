package tree;

public class Main {

	public static void main(String[] args){
		AVL_tree tree = new AVL_tree();

		Node root = new Node(9);
		
		root = tree.insert(root, 5);
		root = tree.insert(root, 10);
		root = tree.insert(root, 0);
		root = tree.insert(root, 6);
		root = tree.insert(root, 11);
		root = tree.insert(root, -1);
		root = tree.insert(root, 1);
		root = tree.insert(root, 2);
		
		root = tree.Delete(root, 10);
		
		tree.PreOrder(root);
		
		System.out.println(tree.Search(root, 1111));
	}
}
