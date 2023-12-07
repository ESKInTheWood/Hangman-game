import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    List<String> words = List.of("programmin", "aplication", "christmas", "story", "party");
    String searchedWord;
    char[] userWord;
    int lives = 3;

    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game start:");

        Random random = new Random();
        searchedWord = words.get(random.nextInt(words.size()));

        userWord = new char[searchedWord.length()]; //tablica o długości wylosowanego słowa
        Arrays.fill(userWord, '_');

        while (!gameEnded()) {
            System.out.println(userWord);
            System.out.println();
            System.out.println("Enter another letter: ");
            System.out.println("Remaining lives: " + lives);

            char letter = scanner.nextLine().charAt(0); //póki co zakładamy, że użytkownik poda tylko jedną literę

            checkLetter(letter);

        }

        if (lives == 0){
            System.out.println("Unfortunately, you didn't win.");
        } else {
            System.out.println("Bravo! You have successfully guessed the drawn word!");
        }

        scanner.close();

    }

    private void checkLetter(char letter) {
        /* sprawdzamy czy litera istnieje w wylosowanym słowie,
        jesli tak uzupełnimy tablicę userWord o te literę dokładnie pod wskazanym indeksem*/

        boolean foundLetter = false;

        for (int i = 0; i<searchedWord.length(); i++){
            if (searchedWord.charAt(i) == letter){
                userWord[i] = letter;
                foundLetter = true;
            }
        }
        if(!foundLetter){
            System.out.println("Unfortunately, there is no such letter.");
            lives --;
        }
    }

    private boolean gameEnded() {
        return lives == 0 || searchedWord.equals(String.valueOf(userWord));
    }
}
