package com.yang.chen;

import java.util.Random;

public class StdRandom {
	private static Random random;	// pseudo-random number generator
	private static long seed;		// pseudo-random number generator seed
	
	// static initializer
	static{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	//do not instantiate
	private StdRandom(){	}
	
	/**
	 * Sets the seed of the pseudo-random number generator.
	 * */
	public static void setSeed(long s){
		seed = s;
		random = new Random(seed);
	}
	
	/**
     * Returns the seed of the pseudo-random number generator.
     *
     * @return the seed
     */
	public static long getSeed(){
		return seed;
	}
	
	/**
     * Returns a random real number uniformly in [0, 1)
     *
     * @return a random real number uniformly in [0, 1)
     */
	public static double uniform(){
		return random.nextDouble();
	}
	
	/**
     * Returns a random integer uniformly in [0, n)
     *
     * @return a random integer uniformly in [0, n)
     */
	public static int uniform(int n){
		if (n <= 0) throw new IllegalArgumentException("argument must be positive");
		return random.nextInt(n);
	}
	
	/**
     * Returns a random integer uniformly in [a, b)
     *
     * @return a random integer uniformly in [a, b)
     */
	public static int uniform(int a, int b){
		if (b <= a || ((long) b - a >= Integer.MAX_VALUE)) throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
		return a + uniform(b - a);
	}
	
	/**
     * Returns a random real number uniformly in [a, b)
     *
     * @return a random real number uniformly in [a, b)
     */
	public static double uniform(double a, double b){
		if(!(a < b)) throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
		return a + uniform() * (b - a);
	}
	
}
