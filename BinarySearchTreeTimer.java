package assign08;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/**
 * This class collects running times for methods of GraphUtility.
 * 
 * @author Erin Parker, Paul Nuffer, Nils Streedain
 * @version March 10, 2021
 */
public class BinarySearchTreeTimer {
	public static void main(String[] args) {
		Random rng = new Random(1);
		System.out.println("N\tnanoTime");
		
		
		int incr = 2500;
		for (int probSize = 10000; probSize <= 200000; probSize += incr) {

			int timesToLoop = 2;
			
			// adding in random order
			ArrayList<Integer> intArray = new ArrayList<>();
			ArrayList<Integer> randomArray = new ArrayList<>();
			for (int j = 0; j < probSize; j++)
				intArray.add(j);
			for (int j = probSize; j > 0; j--) {
				int temp = intArray.remove(rng.nextInt(j));
				randomArray.add(temp);
			}

			
				
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();
				
			for (int i = 0; i < timesToLoop; i++) {
	//			BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
	//			bst.addAll(randomArray);
				TreeSet<Integer> balanced = new TreeSet<>(randomArray);
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(probSize + "\t" + String.format("%.5f", averageTime));
		}
	}
}
