package assign08;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class collects running times for methods of GraphUtility.
 * 
 * @author Erin Parker, Paul Nuffer, Nils Streedain
 * @version March 10, 2021
 */
public class BinarySearchTreeTimer {
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		System.out.println("N\tnanoTime");
		
		int incr = 10000;
		for (int probSize = 10000; probSize <= 200000; probSize += incr) {

			int timesToLoop = 6000;
			
			// adding in sorted order
			for (int i = 0; i < probSize; i++)
				bst.add(i);
			
			// adding in random order
//			Random rng = new Random();
//			ArrayList<Integer> intArray = new ArrayList<>();
//			
//			for (int i = 0; i < probSize; i++)
//				intArray.add(i);
//
//			for (int i = probSize; i > 0; i--)
//				bst.add(intArray.remove(rng.nextInt(i)));
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();
				
			for (int i = 0; i < timesToLoop; i++) {
				bst.contains(i);
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(probSize + "\t" + String.format("%.5f", averageTime));
		}
	}
}
