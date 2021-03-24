package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.swing.text.DocumentFilter.FilterBypass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTester {
	private Random rng = new Random();
	private ArrayList<Integer> emptyArrayList = new ArrayList<Integer>();
	
	private BinarySearchTree<Integer> emptyBST;

	
	@BeforeEach
	void setUp() throws Exception {
		emptyBST = new BinarySearchTree<>();
	}
	
// add()
	@Test
	void addOneToEmpty() {
		assertTrue(emptyBST.add(1));
		assertEquals(1, emptyBST.size());
		assertTrue(emptyBST.contains(1));
	}
	
	@Test
	void addManyRandomToEmpty() {
		for (int i = 0; i < 1000; i++) {
			int randInt = rng.nextInt();
			emptyBST.add(randInt);
			assertEquals(i + 1, emptyBST.size());
			assertTrue(emptyBST.contains(randInt));
		}
	}
	
// addAll()	
	@Test
	void addAllToEmpty() {
		for (int i = 0; i < 1000; i++)
			emptyArrayList.add(i);
		
		emptyBST.addAll(emptyArrayList);
		
		assertEquals(1000, emptyBST.size());
		assertTrue(emptyBST.containsAll(emptyArrayList));
	}
	
// clear()
	@Test
	void clearEmptyTree() {
		emptyBST.clear();
		assertTrue(emptyBST.isEmpty());
	}

	@Test
	void clearLargeTree() {
		for (int i = 0; i < 1000; i++)
			emptyBST.add(i);
		
		emptyBST.clear();
		assertTrue(emptyBST.isEmpty());
	}
	
// contains()
	@Test
	void containsOnEmptyTree() {
		assertFalse(emptyBST.contains(1));
	}
	
	@Test
	void containsOneElement() {
		emptyBST.add(0);
		assertTrue(emptyBST.contains(0));
		assertFalse(emptyBST.contains(1));
	}
	
	@Test
	void containsSeveralElements() {
		for (int i = 0; i < 5; i++)
			emptyBST.add(i);
		
		assertTrue(emptyBST.contains(0));
		assertTrue(emptyBST.contains(1));
		assertTrue(emptyBST.contains(2));
		assertTrue(emptyBST.contains(3));
		assertTrue(emptyBST.contains(4));
	}
	
// containsAll()
	@Test
	void containsManyRandomElements() {
		for (int i = 0; i < 1000; i++) {
			int randInt = rng.nextInt();
			
			emptyArrayList.add(randInt);
			emptyBST.add(randInt);
		}
		
		assertEquals(1000, emptyBST.size());
		assertTrue(emptyBST.containsAll(emptyArrayList));
	}
	
// first()
	@Test
	void emptyTreeFirst() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyBST.first();
		});
	}
	
	@Test
	void singleElementFirst() {
		emptyBST.add(0);
		
		assertEquals(0, emptyBST.first());
	}
	
	@Test
	void manyRandomElementsFirst() {
		for (int i = 0; i < 1000; i++)
			emptyBST.add(rng.nextInt(100));
		emptyBST.add(-1);
		
		assertEquals(-1, emptyBST.first());
	} 
	
// last()
	@Test
	void emptyTreeLast() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyBST.last();
		});
	}
	
	@Test
	void singleElementLast() {
		emptyBST.add(0);
		
		assertEquals(0, emptyBST.last());
	}
	
	@Test
	void manyRandomElementsLast() {
		for (int i = 0; i < 1000; i++)
			emptyBST.add(rng.nextInt(100));
		emptyBST.add(100);
		
		assertEquals(100, emptyBST.last());
	} 
	
// remove()
	@Test
	void emptyTreeRemove() {
		assertFalse(emptyBST.remove(1));
	}
	
	@Test
	void singleElementRemove() {
		emptyBST.add(1);
		assertTrue(emptyBST.remove(1));
	}
	
	// removeLeaf()
	@Test
	void removeLeafRoot() {
		emptyBST.add(1);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(1));
//		System.out.println(emptyBST.toArrayList());
		
	}
	
	@Test
	void removeLeftLeaf() {
		emptyBST.add(3);
		emptyBST.add(2);
		emptyBST.add(1);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(1));
//		System.out.println(emptyBST.toArrayList());
	}
	
	@Test
	void removeRightLeaf() {
		emptyBST.add(1);
		emptyBST.add(2);
		emptyBST.add(3);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(3));
//		System.out.println(emptyBST.toArrayList());
	}
	
	// bypassNode()
	@Test
	void bypassRootNode() {
		emptyBST.add(1);
		emptyBST.add(2);
		emptyBST.add(3);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(1));
//		System.out.println(emptyBST.toArrayList());
	}
	
	@Test
	void bypassRootLeftChild() {
		emptyBST.add(3);
		emptyBST.add(2);
		emptyBST.add(1);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(2));
//		System.out.println(emptyBST.toArrayList());
	}
	
	@Test
	void bypassRootRightChild() {
		emptyBST.add(1);
		emptyBST.add(2);
		emptyBST.add(3);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(2));
//		System.out.println(emptyBST.toArrayList());
	}
	
	// remove Node with two children
	
	@Test
	void removeNodeWithTwoChild() {
		emptyBST.add(4);
		emptyBST.add(2);
		emptyBST.add(1);
		emptyBST.add(3);
//		System.out.println(emptyBST.toArrayList());
		assertTrue(emptyBST.remove(2));
//		System.out.println(emptyBST.toArrayList());
	}
	
	@Test
	void twoChildRightSemiHeavy() {
		emptyBST.add(10);
		emptyBST.add(2);
		emptyBST.add(1);
		emptyBST.add(9);
		emptyBST.add(7);
		emptyBST.add(8);
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/before.txt");
		assertTrue(emptyBST.remove(2));
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/after.txt");
	}
	
	@Test
	void twoChildLeftSemiHeavy() {
		emptyBST.add(10);
		emptyBST.add(15);
		emptyBST.add(14);
		emptyBST.add(16);
		emptyBST.add(12);
		emptyBST.add(13);
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/before.txt");
		assertTrue(emptyBST.remove(15));
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/after.txt");
	}
	
	@Test
	void twoChildLeftHeavy() {
		emptyBST.add(10);
		emptyBST.add(5);
		emptyBST.add(6);
		emptyBST.add(4);
		emptyBST.add(3);
		emptyBST.add(2);
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/before.txt");
		assertTrue(emptyBST.remove(5));
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/after.txt");
	}
	
	@Test
	void twoChildRightHeavy() {
		emptyBST.add(10);
		emptyBST.add(3);
		emptyBST.add(2);
		emptyBST.add(4);
		emptyBST.add(5);
		emptyBST.add(6);
		emptyBST.add(7);
		emptyBST.add(8);
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/before.txt");
		assertTrue(emptyBST.remove(3));
//		System.out.println(emptyBST.toArrayList());
//		emptyBST.generateDotFile("src/assign08/after.txt");
	}
	
	
	
	@Test
	void moreElementRemove() {
		emptyBST.add(0);
		emptyBST.add(3);
		emptyBST.add(2);
		emptyBST.add(4);
		emptyBST.add(5);
		emptyBST.add(-3);
		emptyBST.add(-2);
		emptyBST.add(-4);
		emptyBST.add(5);
		
		emptyBST.remove(3);
		assertFalse(emptyBST.contains(3));	
	}
	
	@Test
	void bypassNode() {
		emptyBST.add(0);
		emptyBST.add(10);
		emptyBST.add(11);
		emptyBST.add(12);
		
//		System.out.println(emptyBST.remove(99));
	}
	
	@Test
	void manyElementRemove() {
		for (int i = 0; i < 400; i++) 
			emptyBST.add(rng.nextInt(200));
		emptyBST.generateDotFile("src/assign08/before.txt");
		for (int i = 0; i < 100; i++) 
			emptyBST.remove(rng.nextInt(200));
		emptyBST.generateDotFile("src/assign08/after.txt");

	}

}
