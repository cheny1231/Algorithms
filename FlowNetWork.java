package com.yang.chen;


public class FlowNetWork {
	private final int V;
	private int E;
	private Bag<FlowEdge>[] adj;
	
	/**
     * Create an empty flow network with V vertices
     * 
     * @param V the number of vertices
     * 
     * @throws IllegalArgumentException if {@code V < 0}
     * */
	public FlowNetWork(int V){
		if(V < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<FlowEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<FlowEdge>();		
	}
	
	/**
     * Create an random flow network with V vertices and E edges with capacity less than 100
     * 
     * @param V the number of vertices
     * @param E the number of edges
     * 
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     * */
	public FlowNetWork(int V, int E){
		this(V);
		if(E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be nonnegative");
		for(int i = 0; i < E; i++){
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double capacity = StdRandom.uniform(100);
			addEdge(new FlowEdge(v, w, capacity));
		}
	}
	
	/**
     * Construct flow network input stream
     * */
//	public FlowNetWork(In in){
//		
//	}
	
	/**
     * Add flow edge e to this flow network
     * 
     * @param e the edge to add
     * */
	public void addEdge(FlowEdge e){ 
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	/**
	 * forward and backward edges incident to v
	 * */
	public Iterable<FlowEdge> adj(int v){
		validateVertex(v);
		return adj[v];
	}
	
	/**
	 * all edges in this flow network
	 * */
	public Iterable<FlowEdge> edges(){
		Bag<FlowEdge> bag = new Bag<FlowEdge>();
		for(int v = 0; v < V; v++){
			for(FlowEdge e : adj[v]){
				if(e.to() != v) bag.add(e);
			}
		}
		return bag;
	}
	
	/**
	 * number of vertices
	 * 
	 * @return the number of vertices
	 * */
	public int V(){
		return V;
	}
	
	/**
	 * number of edges
	 * 
	 * @return the number of edges
	 * */
	public int E(){
		return E;
	}
	
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public String toString(){
    	StringBuilder s = new StringBuilder();
    	for(int v = 0; v < V; v++){
    		s.append(v + ": ");
			for(FlowEdge e : adj[v]){
				if(e.to() != v) s.append(e + " ");
			}
    		s.append("\n");
		}
    	return s.toString();
    }
}
