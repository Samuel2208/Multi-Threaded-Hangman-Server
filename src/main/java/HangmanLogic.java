import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class HangmanLogic {

    private Integer attemptedGuesses;
    private String secretWord;
    private HashMap<String, Integer> words;
    private Category category;

    public String getSecretWord(){
        return secretWord;
    }

    public Integer getAttemptedGuesses(){
        return attemptedGuesses;
    }

    public Integer secretWordSize(){
        return secretWord.length();
    }

    HangmanLogic(int categoryNum){
        if(categoryNum == 1){
            category = new Category("Colors.txt",1);
        }else if (categoryNum == 2) {
            category = new Category("Pets.txt",2);
        }else if (categoryNum == 3) {
            category = new Category("US_States.txt",3);
        }else if (categoryNum == 23) {
            category = new Category("test.txt",3);
        }

        words = category.getContainer();

        do{
            Object[] crunchifyKeys = words.keySet().toArray();
            Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];

            secretWord = key.toString();

        }while (words.get(secretWord) != 1);

        words.merge(secretWord,1,Integer::sum);

        attemptedGuesses = 0;

    }


//    public ArrayList partiallyRight(String userGuess){
//
//        char arr[] = secretWord.toCharArray();
//        ArrayList<Integer> correctletters = new ArrayList<>();
//
//        for (int i = 0; i < arr.length; i++) {
//            if(arr[i] == userGuess.charAt(i)){
//                correctletters.add(i);
//            }
//        }
//
//        attemptedGuesses += 1;
//        return correctletters;
//
//    }

    public ArrayList isLetterInWord(Character userGuess){

        ArrayList<Integer> correctLetters = new ArrayList<>();

        for (int i = 0; i < secretWord.length(); i++) {
            if(Character.toLowerCase(secretWord.charAt(i)) == Character.toLowerCase(userGuess)){
                correctLetters.add(i);
            }
        }

        if(!correctLetters.isEmpty()){
            return correctLetters;
        }else {
            attemptedGuesses += 1;
            return null;
        }


    }

}
