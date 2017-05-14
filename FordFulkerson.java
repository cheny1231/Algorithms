package com.yang.chen;

public class FordFulkerson {
	private boolean[] marked;	//true if s->v is in the residual network 
	private FlowEdge[] edgeTo;	//last edge on s->v path
	private double value;		//value of flow
	
	/**
	 * Initialize the Flow Network with FordFulkerson Algorithm
	 * 
	 * @param G the flow network with 0 flow
	 * @param s the source of the flow network
	 * @param t the target of the flow network
	 * 
	 * */
	public FordFulkerson(FlowNetWork G, int s, int t){
		value = 0.0;
		while(hasAugmentingPath(G, s, t)){
			double bottle = Double.POSITIVE_INFINITY;
			for(int v = t; v != s; v = edgeTo[v].other(v))
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			
			for(int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);
			
			value += bottle;
		}
	}
	
	/**
	 * return true if the flow network has augmenting path from s to t
	 * 
	 * @return {@code true} if the flow network has augmenting path from s to t
	 * 		   {@code false} otherwise
	 * */
	private boolean hasAugmentingPath(FlowNetWork G, int s, int t){
		edgeTo = new FlowEdge[G.V()];
		marked = new boolean[G.V()];
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		marked[s] = true;
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			
			for(FlowEdge e : G.adj(v)){
				int w = e.other(v);
				if(e.residualCapacityTo(w) > 0 && !marked[w]){
					marked[w] = true;
					edgeTo[w] = e;
					queue.enqueue(w);
				}
			}
		}
		return marked[t];
	}
	
	
	/**
	 * return the value of flow
	 * 
	 * @return the value of flow
	 * */
	public double value(){
		return value;
	}
	
	/**
	 * return true if the vertex is in the residual path
	 * 
	 * @return {@code true} if the vertex is in the residual path 
	 * */
	public boolean inCut(int vertex){ 
		return marked[vertex];
	}
	
	public static void main(String[] args){
		FlowNetWork G = new FlowNetWork(8, 45);
		System.out.println(G.toString());
		
		FordFulkerson ford = new FordFulkerson(G, 0, 4);
		System.out.println(ford.value());
		System.out.println(G.toString());
	}
	
}
