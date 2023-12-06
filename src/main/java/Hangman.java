import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hangman {
    List<String> words = List.of("programmin", "aplication", "christmas", "story", "party");
    String searchWord;
    char[] userWord;

    public void play(){
        System.out.println("Start gry");

        Random random = new Random();
        searchWord = words.get(random.nextInt(words.size()));

        userWord = new char[searchWord.length()]; //tablica o długości wylosowanego słowa
        Arrays.fill(userWord, '_');

        System.out.println(userWord);





    }
}
