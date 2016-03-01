package tree;

import java.awt.RenderingHints.Key;

public class AVL_tree {
	
	static Node root;
	
	
	//get the height of the tree
	int height(Node node){
		if(node == null)
			return 0;
		else return node.height;
	}
	
	int Balance_factor(Node node){
		if(node == null)
			return 0;
		else return (height(node.left) - height(node.right));
	}
	
	Node RotateLeft(Node node){
		Node right = node.right;
		Node right_left = right.left;
		
		right.left = node;
		node.right = right_left;
		
		node.height = Math.max(height(node.left), height(node.right))+1;
		right.height = Math.max(height(right.left), height(right.right))+1;
		
		return right;
		
	}
	Node RotateRight(Node node){
		Node left = node.left;
		Node left_right = left.right;
		
		left.right = node;
		node.left = left_right;
		
		node.height = Math.max(height(node.left), height(node.right))+1;
		left.height = Math.max(height(left.left), height(left.right))+1;
		
		return left;
	}
	
	Node insert(Node node, int data){
		
		if(node == null)
			return new Node(data);
		else if(node.data < data)
			node.right = insert(node.right, data);
		else node.left =  insert(node.left, data);
		
		node.height = Math.max(height(node.left), height(node.right))+1;
		
		//left left case
		if(Balance_factor(node)>1 && data<node.left.data){
			return RotateRight(node);
		}
		//right right case
		if (Balance_factor(node)<-1 && data > node.right.data) {
			return RotateLeft(node);
		}
		//left right case
		if (Balance_factor(node)>1 && data > node.left.data) {
			node.left = RotateLeft(node.left);
			return RotateRight(node);
		}
		//right left case
		if(Balance_factor(node)<-1 && data < node.right.data){
			node.right = RotateRight(node.right);
			return RotateLeft(node);
		}
		
		return node;
	}
	
	void PreOrder(Node node){
		if (node != null) {
			System.out.println(node.data);
			PreOrder(node.left);
			PreOrder(node.right);
		}
	}
	
}
