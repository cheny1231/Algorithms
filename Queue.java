package com.yang.chen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first;	//beginning of the queue
	private Node<Item> last;	//end of the queue
	private int n;				//number of elements in the queue
	
	private class Node<Item>{
		Item item;
		Node<Item> next;
	}
	
	/**
	 * Initialize an empty queue
	 * */
	public Queue(){
		first = null;
		last = null;
		n = 0;
	}
	
	/**
	 * return true if the queue is empty
	 * 
	 * @return {@code true} if the queue is empty;
	 * 		   {@code false} otherwise
	 * */
	public boolean isEmpty(){
		return first == null;
	}
	
	/**
	 * return the number of elements on queue
	 * 
	 * @return the number of elements on queue
	 * */
	public int size(){
		return n;
	}
	
	/**
	 * return the element last recently added to the queue
	 * 
	 * @return the element last recently added to the queue
	 * 
	 * @throws NoSuchElementException if this queue is empty
	 * */
	public Item peek(){
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return first.item;
	}
	
	/**
	 * add the item to the queue
	 * 
	 * @param item the item to add to the queue
	 * */
	public void enqueue(Item item){
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if(isEmpty()) first = last;
		else oldlast.next = last;
		n++;
	}
	
	/**
	 * return and remove the element last recently added to the queue
	 * 
	 * @return the element last recently added to the queue
	 * 
	 * @throws NoSuchElementException if this queue is empty
	 * */
	public Item dequeue(){
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if(isEmpty()) last = null;
		return item;
	}
	
	/**
	 * return a string representation of the queue
	 * 
	 * @return a string representation of the queue
	 * */
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item item : this){
			s.append(item);
			s.append(" ");
		}
		return s.toString();
	}
	
	/**
	 * return a iterator of the queue in the FIFO order
	 * 
	 * @return a iterator of the queue in the FIFO order
	 * */
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item>{
		Node<Item> current;

		public ListIterator(Node<Item> first){
			current = first;
		}
		
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		
	}
}
