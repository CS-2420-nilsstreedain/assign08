package assign08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTester {
	
	private BinarySearchTree<Integer> emptyBST;

	@BeforeEach
	void setUp() throws Exception {
		emptyBST = new BinarySearchTree<>();
	}
	
// add()
	@Test
	void addToEmpty() {
		System.out.println(emptyBST.add(5));
		System.out.println(emptyBST.add(2));
		System.out.println(emptyBST.add(3));
		System.out.println(emptyBST.add(8));
		System.out.println(emptyBST.add(1));
		System.out.println(emptyBST.add(-10));
		System.out.println(emptyBST.add(40));
		System.out.println(emptyBST.add(22));
		
		System.out.println(emptyBST.toArrayList());
		
		System.out.println(emptyBST.size());
	}

}
