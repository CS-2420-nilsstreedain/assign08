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
		emptyBST.add(5);
		emptyBST.add(2);
		emptyBST.add(3);
		emptyBST.add(8);
		emptyBST.add(1);
		emptyBST.add(-10);
		emptyBST.add(40);
		emptyBST.add(22);
		
		System.out.println(emptyBST.toArrayList());
		
		System.out.println(emptyBST.size());
	}

}
