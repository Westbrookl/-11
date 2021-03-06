package com.jhc.test2;

public class AVLTree<T extends Comparable> {
	private AVLNode<T> root = null;
	class AVLNode<T extends Comparable>{
		T data;
		AVLNode<T> parent;
		AVLNode<T> left;
		AVLNode<T> right;
		int depth;
		int balance;
		public AVLNode(T data){
			this.data = data;
			left = null;
			right = null;
			depth = 1;
			balance = 0;
			
		}
	}
	public int calDepth(AVLNode<T> node){
		int depth = 0;
		if(node.left != null){
			depth = node.left.depth;
		}
		if(node.right != null && node.right.depth > depth){
			depth = node.right.depth;
		}
		depth++;
		return depth;
	}
	public int calBalance(AVLNode<T> node){
		int leftDepth = (node.left != null)?node.left.depth:0;
		int rightDepth = (node.right != null)?node.right.depth:0;
		return leftDepth - rightDepth;
	}
	public void insert(T data){
		root = insert(data,root);
	}
	private AVLNode<T> insert(T data,AVLNode<T> node){
		if(node == null){
			return new AVLNode<T>(data);
		}
		@SuppressWarnings("unchecked")
		int result = data.compareTo(node.data);
		if(result < 0){
			node.left = insert(data,node.left);
			int balance = calBalance(node.left);
			if(balance == 2 || balance == -2){
				if(balance == 2){
					 node.left = rightRotate(node.left);
				}else{
					node.left = leftRightRotate(node.left);
				}
			}
		}else if(result > 0){
			node.right = insert(data,node.right);
			int balance = calBalance(node.right);
			if(balance == 2 || balance == -2){
				if(balance == 2){
					node.right = rightLeftRotate(node.right);
				}else {
					node.right = leftRotate(node.right);
				}
			}
			
		}
		node.depth = calDepth(node);
		node.balance = calBalance(node);
		
		return node;
		
	}
	/**
	 * ����
	 * ����Ϊ��
	 * ��������
	 * ���������
	 * @param node
	 * @return
	 */
	private AVLNode<T> rightRotate(AVLNode<T> node){
		AVLNode<T> parent = node.parent,
				leftSon = node.left,
				rightGrandSon = leftSon.right;
		leftSon.parent = parent;
		if(parent.left == node){
			parent.left = leftSon;
		}else if(parent.right == node){
			parent.right = leftSon;
		}
		leftSon.right = node;
		node.parent = leftSon;
		
		node.left = rightGrandSon;
		if(rightGrandSon != null){		
			rightGrandSon.parent = node;
		}
		node.depth = calDepth(node);
		node.balance = calBalance(node);
		
		leftSon.depth = calDepth(leftSon);
		leftSon.balance = calBalance(leftSon);
		
		return leftSon;
	} 
	/**
	 * ˫��ת
	 */
	private AVLNode<T> leftRightRotate(AVLNode<T> node){
		node.left = leftRotate(node.left);
		return rightRotate(node);
	}
	/**
	 * ����ת
		���ӱ丸
		��������
		�����ӱ��������
	 */
	private AVLNode<T> leftRotate(AVLNode<T> node){
		AVLNode<T> rightSon = node.right,
				   parent = node.parent,
				   leftGrandSon = rightSon.right;
		rightSon.parent = parent;
		if(parent.left == node){
			parent.left = rightSon;
		}else if( parent.right == node){
			parent.right = rightSon;
		}
		rightSon.left = node;
		node.parent = rightSon;
		/**
		 * �����ӱ��������
		 */
		node.right = leftGrandSon;
		if( leftGrandSon != null){
			leftGrandSon.parent = node;
		}
		node.balance = calBalance(node);
		node.depth = calDepth(node);
		/**
		 * 
		 */
		rightSon.depth = calDepth(rightSon);
		rightSon.balance = calBalance(rightSon);
		return rightSon;
	}
	private AVLNode<T> rightLeftRotate(AVLNode<T> node){
		node.right = rightRotate(node.right);
		return leftRotate(node);
	}
	public void preOrder(){
		 preOrder(root);
	}
	private void preOrder(AVLNode<T> node){
		if(node != null){
			System.out.println(node.data+" ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	public  void midOrder(){
		midOrder(root);
	}
	public void midOrder(AVLNode<T> node){
		if(node != null){
			midOrder(node.left);
			System.out.print(node.data+" ");
			midOrder(node.right);
		}
	}
	public void remove(T data){
		root = remove(root,data);
	}
	private AVLNode<T> remove(AVLNode<T> node,T data){
		if(node == null){
			return node;
		}
		int result = data.compareTo(node.data);
		if(result < 0){
			node.left = remove(node.left,data);
			node.balance = calBalance(node);
			if(node.balance == 2 || node.balance == -2){
					if(node.right.balance == 1){
						node.right = rightRotate(node.right);
					}else if(node.right.balance == -1){
						node.right = leftRightRotate(node.right);
					}
				
			}
		}else if(result > 0){
			node.right = remove(node.right,data);
			int temp = calBalance(node);
			if(temp == 2 || temp == -2){
				if(node.left.balance == 1){
					node.left = leftRotate(node.left);
				}else if(node.left.balance == -1){
					node.left = rightLeftRotate(node.left);
				}
			}
		}else {
				if(node.left != null && node.right !=null){
					node.data = findMin(node.right).data;
					node.right = remove(node.right,node.data);
				}else{
					node = (node.left!=null)?node.left:node.right;
				}
			
		}
		node.balance = calBalance(node);
		node.depth = calDepth(node);
		return node;
	}
	public void findMin(){
		root = findMin(root);
	}
	private AVLNode<T> findMin(AVLNode<T> node){
		if(node.left == null){
			return node;
		}
		return findMin(node.left);
	}
	
}
