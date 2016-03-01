package tree;

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
	Node Successor(Node node){
		Node tmp = node.right;
		if(tmp == null)
			return null;
		else{
			while(tmp.left != null){
				tmp = tmp.left;
			}
			return tmp;
		}
	}
	
	Node Delete(Node node,int data){
		//perform normal binary search tree deletion
		if (node == null) {
			return node;
		}
		else if(node.data < data)
			node.right = Delete(node.right, data);
		else if(node.data > data)
			node.left = Delete(node.left, data);
		//find the node to delete
		else{
			//if node only has one child
			if(node.left == null || node.right == null){
				
				
				//no child
				if(node.left == null && node.right == null){
					node = null;
				}
				else if(node.left == null){
					node = node.right;
				}
				else{
					node = node.left;
				}
			}
			//node has two children
			else{
				Node successor = Successor(node);
				node.data = successor.data;
				node.right = Delete(node.right, successor.data);
			}
		}
		
		int balance = Balance_factor(node);
		
		//update current height of node
		node.height = Math.max(height(node.left), height(node.right))+1;
		
		//left right case
		if(balance > 1 && Balance_factor(node.left)<0){
			node.left = RotateRight(node.left);
			return RotateRight(node);
		}
		//left left case
		if(balance >1 && Balance_factor(node.left)>=0){
			return RotateRight(node);
		}
		
		//right left case
		if(balance < -1 && Balance_factor(node.right)>0){
			node.right = RotateLeft(node.right);
			return RotateRight(node);
		}
		
		//right right case
		if(balance < -1 && Balance_factor(node.right)<=0){
			return RotateLeft(node);
		}
		
		return node;
	}
	Node Search(Node node, int data){
		if(node == null)
			return null;
		if (node.data == data) {
			return node;
		}
		else if(node.data < data)
			return Search(node.right, data);
		else return Search(node.left, data);
	}
	
	void PreOrder(Node node){
		if (node != null) {
			System.out.println(node.data);
			PreOrder(node.left);
			PreOrder(node.right);
		}
	}
	
}
