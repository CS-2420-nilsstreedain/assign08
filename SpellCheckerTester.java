package assign08;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpellCheckerTester {
	SpellChecker spellChecker;
	
	@BeforeEach
	void setUp() throws Exception {
		spellChecker = new SpellChecker(new File("src/assign08/dictionary.txt"));
	}
	
// addToDictionary()
	@Test
	void addWordToDictionary() {
		spellChecker.addToDictionary("cs");
		assertTrue(spellChecker.spellCheck("cs"));
	}
}
