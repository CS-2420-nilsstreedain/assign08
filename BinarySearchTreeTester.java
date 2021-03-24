package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

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
		
		System.out.println(emptyBST.remove(99));
	}
	
	@Test
	void manyElementRemove() {
		for (int i = 0; i < 10; i++) 
			emptyBST.add(rng.nextInt(100));
		emptyBST.generateDotFile("src/assign08/before");
		for (int i = 0; i < 50; i++) 
			emptyBST.remove(rng.nextInt(100));
		emptyBST.generateDotFile("src/assign08/after");

	}

}
