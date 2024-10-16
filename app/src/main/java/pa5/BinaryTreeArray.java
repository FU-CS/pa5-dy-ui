/*
 * This source file was generated by the Gradle 'init' task
 */
package pa5;

import java.util.ArrayList;

/**
 *  Interface for a binary tree implemented using an array
*   The tree must adhere to the properties of a complete binary tree: 
*      A complete binary tree is a binary tree in which every level,
*      The tree is completely filled on all levels except possibly 
*      for the lowest level, which is filled from left to right.
 */
interface ArrayBasedBinaryTree {

    /**
     * Inserts an element into the tree. 
     * Assume `element` is always a positive integer
     */
    void insert(int element);

    /**
     * Returns the tree in level order
     */
    String levelOrder();

    /**
     * Returns the tree in in-order
     */
    String inOrder();

    /**
     * Returns the tree in pre-order
     */
    String preOrder();

    /**
     * Returns the tree in post-order
     */
    String postOrder();

    /**
     * Return the length of the longest path in the tree
     */
    int longestPath();

    /**
     * Delete an element from the tree
     * Ensure that the tree remains a complete binary tree
     */
    void delete(int element);
}

 // Uncomment the following code to implement the BinaryTreeArray class
public class BinaryTreeArray implements ArrayBasedBinaryTree{
	ArrayList<Integer> arr = new ArrayList<Integer>();
	public BinaryTreeArray(int x) {
	
	}

    public static void main(String[] args){

    }

	@Override
	public void insert(int element) {
		arr.add(element);
		int idx = arr.size() - 1;
		while (arr.get((idx-1)/2) > element) {
			swap(idx, (idx-1) /2);
			idx = (idx-1)/2;
		}
		
	}

	@Override
	public String levelOrder() {
		String res = "";
		for(int i=0; i<arr.size()-1;i++)
			res += arr.get(i).toString() + ' ';
		res += arr.get(arr.size()-1).toString();
		return res;
	}

	@Override
	public String inOrder() {
		return inOrder(0);
	}
	
	private String inOrder(int idx) {
		if (idx >= arr.size())
			return "";
		return inOrder(2*idx + 1) + arr.get(idx).toString() + " " + inOrder(2*idx + 2);
	}

	@Override
	public String preOrder() {
		return preOrder(0);
	}
	
	private String preOrder(int idx) {
		if (idx >= arr.size())
			return "";
		return arr.get(idx).toString() + " " + preOrder(2*idx + 1) + preOrder(2*idx + 2);
	}


	@Override
	public String postOrder() {
		return postOrder(0);
	}
	
	private String postOrder(int idx) {
		if (idx >= arr.size())
			return "";
		return postOrder(2*idx + 1) + postOrder(2*idx + 2) + arr.get(idx).toString()+" ";
	}

	@Override
	public int longestPath() {
		if (arr.size()==0)
			return 0;
		return (int) (Math.log(arr.size())/Math.log(2)) + 1;
	}

	@Override
	public void delete(int element) {
		int i = 0;
		while (i < arr.size() && arr.get(i) != element) {
			i += 1;
		}
		if (i == arr.size())
			return;
					
		arr.set(i, arr.get(arr.size()-1));
		arr.remove(arr.size()-1);
		if (i == arr.size())
			return;
		int val = arr.get(i);
		while (arr.get((i-1)/2) > val) {
			swap(i, (i-1) / 2);
			i = (i-1)/2;
		}
		
		move_down(i);
		
	}   
	
	private void move_down(int idx) {
		if (2*idx+1 >= arr.size())
			return;
		
		int left = 2*idx+1;
		if (arr.size()-1 == left) {
			if (arr.get(left) < arr.get(idx)) {
				swap(left, idx);
			}
			return;
		}
		
		int right = 2*idx + 2;
		if ((arr.get(left) <= arr.get(right)) && (arr.get(left) < arr.get(idx))) {
			swap(idx, left);
			move_down(left);
			return;
		}
		else if (arr.get(right) < arr.get(idx)) {
			swap(idx, right);
			move_down(right);
		}
	}
	private void swap(int i, int j) {
		int tmp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, tmp);
	}
	
	public int get(int i) {
		return arr.get(i);
	}
	
	public int size() {
		return arr.size();
	}
	
	public boolean isHeap() {
		return isHeap(0);
	}
	private boolean isHeap(int i) {
		if (i >= arr.size())
			return true;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if (left < arr.size() && arr.get(left) < arr.get(i)) {
			return false;
		}
		if (right < arr.size() && arr.get(right) < arr.get(i)) {
			return false;
		}
		return isHeap(left) && isHeap(right);
	}
}




