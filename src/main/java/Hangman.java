import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    List<String> words = List.of("programmin", "aplication", "christmas", "story", "party");
    String searchedWord;
    char[] userWord;

    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start gry");

        Random random = new Random();
        searchedWord = words.get(random.nextInt(words.size()));

        userWord = new char[searchedWord.length()]; //tablica o długości wylosowanego słowa
        Arrays.fill(userWord, '_');

        while (!gameEnded()) {
            System.out.println(userWord);
            System.out.println();
            System.out.println("Enter another letter: ");

            char letter = scanner.nextLine().charAt(0); //póki co zakładamy, że użytkownik poda tylko jedną literę

            checkLetter(letter);

        }

        scanner.close();

    }

    private void checkLetter(char letter) {
        /* sprawdzamy czy litera istnieje w wylosowanym słowie,
        jesli tak uzupełnimy tablicę userWord o te literę dokładnie pod wskazanym indeksem*/

        for (int i = 0; i<searchedWord.length(); i++){
            if (searchedWord.charAt(i) == letter){
                userWord[i] = letter;
            }
        }
    }

    private boolean gameEnded() {
        return searchedWord.equals(String.valueOf(userWord));
    }
}
