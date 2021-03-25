package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpellCheckerTester {
	SpellChecker spellChecker;
	File doc = new File("src/assign08/good_luck.txt");
	
	@BeforeEach
	void setUp() throws Exception {
		spellChecker = new SpellChecker(new File("src/assign08/dictionary.txt"));
	}
	
// addToDictionary()
	@Test
	void addWordToDictionary() {
		
		List<String> misspelledWords = spellChecker.spellCheck(doc);
		assertTrue(misspelledWords.contains("cs"));
		
		spellChecker.addToDictionary("cs");
		
		misspelledWords = spellChecker.spellCheck(doc);
		assertFalse(misspelledWords.contains("cs"));
	}
	
// buildDictionary()
	@Test
	void buildDictionary() {
		List<String> misspelledWords = spellChecker.spellCheck(doc);
		assertTrue(misspelledWords.contains("cs"));
		assertTrue(misspelledWords.contains("bst"));
		
		ArrayList<String> words = new ArrayList<>();
		words.add("cs");
		words.add("bst");
		SpellChecker testChecker = new SpellChecker(words);
		
		misspelledWords = testChecker.spellCheck(doc);
		assertFalse(misspelledWords.contains("cs"));
		assertFalse(misspelledWords.contains("bst"));
	}
}
