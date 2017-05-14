package com.yang.chen;

public class FlowEdge {
	private final int v, w;			//the from and to vertex
	private final double capacity;  //the capacity of the edge
	private double flow;	        //the flow of the edge
	
	/**
     * Create a flow edge v->w
     * 
     * @param v the tail vertex
     * @param w the head vertex
     * 
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code capacity < 0.0}
     * */
	public FlowEdge(int v, int w, double capacity){
		if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
		if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
		if (capacity < 0) throw new IllegalArgumentException("capacity must be non-negative");
		this.v = v;
		this.w = w;
		this.capacity = capacity;
		this.flow = 0.0;
	}
	
	/**
     * Get the from vertex
     * */
	public int from(){
		return v;
	}
	
	/**
     * Get the to vertex
     * */
	public int to(){
		return w;
	}
	
	/**
     * Get the vertex other than the given vertex
     * 
     * @param vertex the given vertex
     * */
	public int other(int vertex){
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new RuntimeException("Illegal endpoint");
	}
	
	/**
     * Get the capacity of the edge
     * */
	public double capacity(){
		return capacity;
	}
	
	/**
     * Get the flow of the edge
     * */
	public double flow(){
		return flow;
	}
	
	/**
     * Get the residual capacity towards vertex
     * 
     * @param vertex the vertex to calculate the residual capacity to it
     * */
	public double residualCapacityTo(int vertex){
		if(vertex == v) return flow;
		else if(vertex == w) return capacity - flow;
		else throw new IllegalArgumentException();
	}
	
	/**
     * Add delta flow towards v
     * 
     * @param vertex the vertex to add residual flow to
     * @param delta the residual flow to add
     * */
	public void addResidualFlowTo(int vertex, double delta){
		if(vertex == v) flow -= delta;
		else if(vertex == w) flow += delta;
		else throw new IllegalArgumentException();
	}
	
	public String toString(){
		return v + "->" + w + " " + flow + "/" + capacity;
		
	}
}
