package com.jhc.test2;

public class Test {
	public static void main(String[] args){
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(2);
		tree.insert(5);
		tree.insert(3);
		tree.insert(7);
		tree.insert(4);
		tree.insert(6);
		tree.insert(1);
		tree.midOrder();
		System.out.println();
		tree.remove(3);
		tree.midOrder();
	}
}
