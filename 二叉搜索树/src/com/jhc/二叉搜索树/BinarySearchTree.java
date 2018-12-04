package com.jhc.二叉搜索树;

public class BinarySearchTree<T extends Comparable<T>> {
	private class Node<T>{
		public T data;
		public Node<T> left;
		public Node<T> right;
		public Node(T data1){
			this(data1,null,null);
		}
		public Node(T data,Node<T> left,Node<T> right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	private Node<T> root;
	/**
	 * 没有参数默认初始化为null
	 */
	public BinarySearchTree(){
		this.root = null;
	}
	public void makeEmpty(){
		this.root = null;
	}
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(T value){
		return contains(root,value);
	}
	public boolean contains(Node<T> root,T value){
		if(root == null){
			return false;
		}
		int result = value.compareTo(root.data);
		if(result < 0){
			return contains(root.left,value);
		}else if(result > 0){
			return contains(root.right,value);
		}else{
			return true;
		}		
	}
	/**
	 * 查找最小值分为两个方法一个是用来查找最小的值
	 * 一个用来查找最小的节点
	 * 查找最小的节点先判断根节点是不是为null
	 * 如果判断不为null 然后去判断是否含有左结点
	 * @return
	 */
	public T findMin(){
		return findMin(root).data;
	}
	public Node<T> findMin(Node<T> root){
		if(root == null){
			return null;
		}else if(root.left == null){
			return root;
		}
		return findMin(root.left);
	}
	public T findMax(){
		return findMax(root).data;
	}
	public Node<T> findMax(Node<T> root) {
		if(root == null){
			return null;
		}else if(root.right == null){
			return root;
		}
		
		return findMax(root.right);
	}
	/**
	 * 插入值 返回的是根节点
	 * 这个是不含有重复值的二叉搜索树
	 * 用递归的方式去实现插入，即每次插入的左边的值都要比比较值小，右边的值比比较值大
	 * 
	 */
	public void insert(T value){
		root = insert( value,root);
	}
	public Node<T> insert(T value,Node<T> root){
		if(root == null){
			return new Node(value,null,null);
		}
		int result = value.compareTo(root.data);
		if(result < 0){
			root.left = insert(value,root.left);
		}else if(result > 0){
			root.right = insert(value,root.right);
		}
		return root;
	}
	/**
	 * 删除节点
	 * 首先确定节点的位置
	 * 确定完位置以后的基础之上在去判断如果只有一个结点或者没有子节点直接删除就可以了赋值为null
	 * 如果有两个结点 
	 * 首先赋值把当前值改成右子树最小的值 然后在右子树中删除那个值
	 * 
	 * @param value
	 */
	public void remove(T value){
		root = remove(root,value);
	}
	/**
	 * 对于删除二叉搜索树中的节点
	 * 首先查找当前节点值的value的位置
	 * 然后在该位置判断如果只有有左结点和右结点，则查找出右子树中最小值然后用最小值代替该值，并且在右子树中删除最小值的节点
	 * 因为我们每次递归返回的都是当前查找子树的根节点，可以直接赋值。
	 * @param root
	 * @param value
	 * @return
	 */
	private Node<T> remove(Node<T> root,T value){
		if(root == null){
			return root;
		}
		int result = value.compareTo(root.data);
		if(result < 0){
			root.left = remove(root.left,value);
		}else if(result > 0){
			root.right = remove(root.right,value);
		}else if(root.left != null && root.right != null){
			root.data = findMin(root.right).data;
			root.right = remove(root.right,root.data);
		}else{
			root = (root.left!=null)?root.left:root.right;
		}
		return root;
	}
	/**
	 * 中序遍历
	 */
	public void LDRTree(){	
		LDRTree(root);
	}
	private void LDRTree(Node<T> root){
		if(root!=null){
			LDRTree(root.left);
			System.out.print(root.data+"  ");
			LDRTree(root.right);
		}
	}
	/**
	 * 先序遍历
	 */
	public void PreLDRTree(){
		PreLDRTree(root);
	}
	private void PreLDRTree(Node<T> root){
		if(root != null){
			System.out.print(root.data+"  ");
			PreLDRTree(root.left);
			PreLDRTree(root.right);
		}
	}
	/**
	 * 后序遍历
	 */
	public void AfterLDRTree(){
		AfterLDRTree(root);
	}
	public void AfterLDRTree(Node<T> root){
		if(root != null){
			AfterLDRTree(root.left);
			AfterLDRTree(root.right);
			System.out.print(root.data+"  ");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
