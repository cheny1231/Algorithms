package com.yang.chen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	private Node<Item> first;	//the beginning of the bag
	private int n;				//the number of elements in the bag
	
	// helper linked list class
	private static class Node<Item>{
		Item item;
		Node<Item> next;
	}
	
	/**
	 * Initialize an empty bag
	 * */
	public Bag(){
		first = null;
		n = 0;
	}
	
	/**
	 * Return true if the bag is empty
	 * 
	 * @return {@code true} if this bag is empty;
     *		   {@code false} otherwise
	 * */
	public boolean isEmpty(){
		return first == null;
	}
	
	/**
	 * Return the number of items in the bag
	 * 
	 * @return the number of items in the bag
	 * */
	public int size(){
		return n;
	}
	
	/**
	 * Add the item to the bag
	 * 
	 * @param item the item to add to the bag
	 * */
	public void add(Item item){
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	
	/**
	 * Add the item to the bag
	 * 
	 * @param item the item to add to the bag
	 * */
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;

		public ListIterator(Node<Item> first){
			current = first;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		
	}
}
