import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

class MyTest {
	static ArrayList<Character> green = new ArrayList<>();

	//setup expected values arrays from files in src/main/resources
	@BeforeAll
	static void setup() {
		green.add('#');
		green.add('#');
		green.add('e');
		green.add('e');
		green.add('#');


	}

	//Testing category class:
	@Test
	void categoryTest() {

		Category category = new Category("test.txt",23);

		assertEquals(1, category.getContainer().size(), "Wrong container");
		assertEquals(23, category.getFileType(), "Wrong file type");
		assertEquals(1, category.getContainer().get("Green"), "Wrong value");
        assertTrue(category.getContainer().containsKey("Green"), "Wrong key");

	}

	@Test
	void getContainerTest() {

		Category category = new Category("test.txt",23);
		HashMap<String,Integer> hashMap  = category.getContainer();
		assertEquals(1, hashMap.get("Green"), "Wrong value");
		assertEquals(1, hashMap.size(), "Wrong value");
		assertTrue(category.getContainer().containsKey("Green"), "Wrong key");

	}

	@Test
	void getFileTypeTest() {

		Category category = new Category("test.txt",23);
		assertEquals(23, category.getFileType(), "Wrong file type");

		category = new Category("test.txt",1);
		assertEquals(1, category.getFileType(), "Wrong file type");

		category = new Category("test.txt",2);
		assertEquals(2, category.getFileType(), "Wrong file type");

		category = new Category("test.txt",3);
		assertEquals(3, category.getFileType(), "Wrong file type");

	}

	//Testing HangmanLogic class:
	@Test
	void HangmanLogicConstructorTest() {

		HangmanLogic logic = new HangmanLogic(23);

		assertEquals("Green", logic.getSecretWord(), "Wrong word");
		assertEquals(5, logic.secretWordSize(), "Wrong size");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");

	}

	@Test
	void isLetterInWord() {

		HangmanLogic logic = new HangmanLogic(23);

		ArrayList<Character> arr = logic.isLetterInWord('e');

		assertEquals(5, arr.size(), "Wrong size");
		assertEquals('e', arr.get(2), "Wrong position");
		assertEquals('e', arr.get(3), "Wrong position");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");
		for (int i = 0; i < 5; i++) {
			assertEquals(green.get(i), arr.get(i), "Wrong array");
		}

	}

	@Test
	void letterIsNotInWordTest() {

		HangmanLogic logic = new HangmanLogic(23);

		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");
		assertEquals(null, logic.isLetterInWord('a'), "Wrong logic");
		assertEquals(1, logic.getAttemptedGuesses(), "Wrong guess num");
		assertEquals(null, logic.isLetterInWord('z'), "Wrong logic");
		assertEquals(2, logic.getAttemptedGuesses(), "Wrong guess num");

	}

	@Test
	void positionTest() {

		HangmanLogic logic = new HangmanLogic(23);

		ArrayList<Character> arr = logic.isLetterInWord('e');


		arr = logic.isLetterInWord('R');
		assertEquals(5, arr.size(), "Wrong size");
		assertEquals('r', arr.get(1), "Wrong position");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('N');
		assertEquals(5, arr.size(), "Wrong size");
		assertEquals('n', arr.get(4), "Wrong position");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('g');
		assertEquals(5, arr.size(), "Wrong size");
		assertEquals('g', arr.get(0), "Wrong position");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");

	}

	@Test
	void RandomWordTest() {

		HangmanLogic logic = new HangmanLogic(3);

		Set<String> uniqueWords = new HashSet<>();
		ArrayList<String> words = new ArrayList<>();

		String currSecretWord = "";
		for (int i = 0; i < 10; i++) {
			logic.newSecretWord();
			currSecretWord = logic.getSecretWord();
			uniqueWords.add(currSecretWord);
			words.add(currSecretWord);

		}

		assertEquals(words.size(), uniqueWords.size(), "Wrong logic");

	}

	@Test
	void getAttemptedGuesses() {

		HangmanLogic logic = new HangmanLogic(23);

		ArrayList<Character> arr = logic.isLetterInWord('e');


		arr = logic.isLetterInWord('z');
        assertNull(arr, "Wrong array");
		assertEquals(1, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('a');
		assertNull(arr, "Wrong array");
		assertEquals(2, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('N');
		assertNotNull(arr, "Wrong array");
		assertEquals('n', arr.get(4), "Wrong position");
		assertEquals(2, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('x');
		assertNull(arr, "Wrong array");
		assertEquals(3, logic.getAttemptedGuesses(), "Wrong guess num");

	}

	@Test
	void secretWordSize() {

		HangmanLogic logic = new HangmanLogic(23);
		assertEquals(5, logic.secretWordSize(), "Wrong guess num");

		HangmanLogic logic2 = new HangmanLogic(1);
		assertEquals(logic2.getSecretWord().length(), logic2.secretWordSize(), "Wrong guess num");

		HangmanLogic logic3 = new HangmanLogic(2);
		assertEquals(logic3.getSecretWord().length(), logic3.secretWordSize(), "Wrong guess num");

		HangmanLogic logic4 = new HangmanLogic(3);
		assertEquals(logic4.getSecretWord().length(), logic4.secretWordSize(), "Wrong guess num");


	}

}
