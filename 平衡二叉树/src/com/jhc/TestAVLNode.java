package com.jhc;
/**
 * ɾ����û��д
 * @author jhc
 *
 */
public class TestAVLNode {
	public static void main(String[] args){
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(2);
		tree.insert(5);
		tree.insert(3);
		tree.insert(7);
	
		tree.insert(4);
		tree.insert(6);
		tree.insert(1);
//		tree.disPlay();
		System.out.println("------");
		tree.disPlayInorder();
		System.out.println();
//		System.out.println("---------delete");
//		tree.delete(5);
//		tree.disPlayInorder();
//		System.out.println("--------");
	}
}
