import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;

class MyTest {

	//setup expected values arrays from files in src/main/resources
//	@BeforeAll
//	static void setup() {
//		readInValues("file1.txt", 1);
//		readInValues("file2.txt", 2);
//		readInValues("file3.txt", 3);
//		readInValues("file4.txt", 4);
//
//	}

	//Testing category class:
	@Test
	void categoryTest() {

		Category category = new Category("test.txt",23);

		assertEquals(1, category.getContainer().size(), "Wrong container");
		assertEquals(23, category.getFileType(), "Wrong file type");
		assertEquals(1, category.getContainer().get("Green"), "Wrong value");
        assertTrue(category.getContainer().containsKey("Green"), "Wrong key");


	}

	//Testing HangmanLogic class:
	@Test
	void HangmanLogicConstructorTest() {

		HangmanLogic logic = new HangmanLogic(23);

		assertEquals("Green", logic.getSecretWord(), "Wrong word");
		assertEquals(5, logic.secretWordSize(), "Wrong size");


	}

	@Test
	void isLetterInWord() {

		HangmanLogic logic = new HangmanLogic(23);

		ArrayList<Integer> arr = logic.isLetterInWord('e');

		assertEquals(2, arr.size(), "Wrong size");
		assertEquals(2, arr.get(0), "Wrong position");
		assertEquals(3, arr.get(1), "Wrong position");
		assertEquals(0, logic.getAttemptedGuesses(), "Wrong guess num");

		assertEquals(null, logic.isLetterInWord('a'), "Wrong logic");
		assertEquals(1, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('R');
		assertEquals(1, arr.size(), "Wrong size");
		assertEquals(1, arr.get(0), "Wrong position");
		assertEquals(1, logic.getAttemptedGuesses(), "Wrong guess num");

		arr = logic.isLetterInWord('g');
		assertEquals(1, arr.size(), "Wrong size");
		assertEquals(0, arr.get(0), "Wrong position");

		assertEquals(null, logic.isLetterInWord('z'), "Wrong logic");
		assertEquals(2, logic.getAttemptedGuesses(), "Wrong guess num");


	}



}
