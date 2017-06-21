package com.yang.chen;

public class TST<Value> {
	private Node root;
	
	private class Node{
		private Value val;
		private char c;
		private Node left, mid, right;
	}
	
	public void put(String key, Value val){
		root = put(root, key, val, 0);
	}
	
	private Node put(Node root, String key, Value value, int d){
		char c = key.charAt(d);
		if(root == null) {
			root = new Node();
			root.c = c;
		}
		if(d == key.length()) {
			root.val = value;
			return root;
		}
		if(c < root.c) put(root.left, key, value, d);
		else if(c > root.c) put(root.right, key, value, d + 1);
		else put(root.left, key, value, d);
		return root;
	}
	
	public boolean contains(String key){
		return get(key) != null;
	}
	
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x == null) return null;
		return x.val;
	}
	
	private Node get(Node root, String key, int d){
		if(root == null) return null;
		if(d == key.length()) return root;
		char c = key.charAt(d);
		if(c < root.c) get(root.left, key, d);
		else if(c > root.c) get(root.right, key, d);
		else get(root.mid, key, d + 1);
		return root;
	}
}
