package com.yang.chen;

public class TrieST<Value> {
	private static final int R = 256;
	private Node root = new Node();
	private int n;
	
	private static class Node{
		private Object value;
		private Node[] next = new Node[R];
	}
	
	public void put(String key, Value val){
		root = put(root, key, val, 0);
	}
	
	private Node put(Node root, String key, Value val, int d){
		if(root == null) root = new Node();
		if(d == key.length()){
			root.value = val;
			return root;
		}
		char c = key.charAt(d);
		root.next[c] = put(root.next[c], key, val, d+1);
		return root;
	}
	
	public boolean contains(String key){
		if(get(key) == null) return false;
		else return true;
	}
	
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x == null) return null;
		else return (Value)x.value;
	}
	
	private Node get(Node root, String key, int d){
		if(root == null) return root;
		if(d == key.length()) return root;
		char c = key.charAt(d);
		return get(root.next[c], key, d+1);
	}
	
	
}
