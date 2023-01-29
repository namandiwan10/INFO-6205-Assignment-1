package edu.neu.coe.info6205.randomwalk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomWalk {

	private int x = 0;
	private int y = 0;

	private final static Random random = new Random();

	/**
	 * Private method to move the current position, that's to say the drunkard moves
	 *
	 * @param dx the distance he moves in the x direction
	 * @param dy the distance he moves in the y direction
	 */
	private void move(int dx, int dy) {
		x += dx; // FIXME do move by replacing the following code
		y += dy; //move 1 step either in x-direction or y-direction
		// END
	}

	/**
	 * Perform a random walk of m steps
	 *
	 * @param m the number of steps the drunkard takes
	 */
	private void randomWalk(int m) {
		for (int i = 0; i < m; i++) {
			randomMove();   // move m steps randomly
		} // FIXME
			// END
	}

	/**
	 * Private method to generate a random move according to the rules of the
	 * situation. That's to say, moves can be (+-1, 0) or (0, +-1).
	 */
	private void randomMove() {
		boolean ns = random.nextBoolean();
		int step = random.nextBoolean() ? 1 : -1;
		move(ns ? step : 0, ns ? 0 : step);
	}

	/**
	 * Method to compute the distance from the origin (the lamp-post where the
	 * drunkard starts) to his current position.
	 *
	 * @return the (Euclidean) distance from the origin to the current position.
	 */
	public double distance() {
		// FIXME by replacing the following code
		return Math.sqrt(((x * x) + (y * y))); // Euclidean distance formula
		// END
	}

	/**
	 * Perform multiple random walk experiments, returning the mean distance.
	 *
	 * @param m the number of steps for each experiment
	 * @param n the number of experiments to run
	 * @return the mean distance
	 */
	public static double randomWalkMulti(int m, int n) {
		double totalDistance = 0;
		List<Double> totalDistanceCollection = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			RandomWalk walk = new RandomWalk();
			walk.randomWalk(m);
			totalDistance = totalDistance + walk.distance();
			totalDistanceCollection.add(totalDistance);
			totalDistance = 0;
		}
		
		double avg = 0.0;
		for (int f = 0; f < totalDistanceCollection.size(); f++) {
			avg += totalDistanceCollection.get(f);
			
		}
		return avg/n;
	}

	public static void main(String[] args) {
		
		if (args.length == 0)
			throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
		
		int m = Integer.parseInt(args[0]);
		int n = 1000;
		if (args.length > 1)
			n = Integer.parseInt(args[1]);

		List<Double> binomialMeanDistance = new ArrayList<Double>();
		
		double meanDistance = 0.0;
		
		List<Integer> stepCount = new ArrayList<Integer>();
		
		for (int u = 0; u < m; u++) { // adding 0 to 50 steps
			stepCount.add(u);
		}

		for (int i = 0; i < m; i++) {
			meanDistance = randomWalkMulti(i, n);
			binomialMeanDistance.add(meanDistance);
			System.out.println(i + " steps: " + meanDistance + " over " + n + " experiments");
		}
		
	}

	}