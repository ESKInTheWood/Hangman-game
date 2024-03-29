import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private List<String> words = Arrays.asList("hogwart", "hedwiga", "zgredek", "dobby", "ron", "harry", "dumbledore", "voldemort");
    private String searchedWord;
    private char[] userWord;
    private int lives = 8;
    private String letter;
    Scanner scanner = new Scanner(System.in);

    public void play() {
        searchedWord = getRandomWord();

        userWord = new char[searchedWord.length()]; //tablica o długości wylosowanego słowa
        Arrays.fill(userWord, '_');

        while (!gameEnded()) {
            try {
                System.out.println("Drawn word:");
                System.out.println(userWord);
                System.out.println();
                System.out.println("Remaining lives: " + lives);
                System.out.println("Enter letter: ");

                letter = getUserInput();
                checkLetter(letter);
            } catch (NotALetterException ex) {
                System.out.println(ex.getMessage());
                ex.getStackTrace();
            }
        }

        if (lives == 0) {
            System.out.println("Unfortunately, you didn't win.");
        } else {
            System.out.println("Bravo! You have successfully guessed the drawn word!");
        }
        scanner.close();
    }

    public String getUserInput(){

        return new Scanner(System.in).next().toLowerCase();
    }

    public String getRandomWord(){
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private void checkLetter(String letter) throws NotALetterException {
        /* sprawdzamy czy litera istnieje w wylosowanym słowie,
        jesli tak uzupełnimy tablicę userWord o te literę dokładnie pod wskazanym indeksem*/

        boolean foundLetter = false;
        char singleLetter = letter.charAt(0);

        for (int i = 0; i < searchedWord.length(); i++) {
            if (letter.length() > 1) {
                lives--;
                throw new NotALetterException("This is not a single letter. Try again");
            }else if (!Character.isLetter(singleLetter)) {
                lives--;
                throw new NotALetterException("This is not a letter. Try again");
            }else {
                if (searchedWord.charAt(i) == singleLetter) {
                    userWord[i] = singleLetter;
                    foundLetter = true;
                }
            }
        }

        if (!foundLetter) {
            System.out.println("Unfortunately, there is no such letter.");
            lives--;
        }
    }

    private boolean gameEnded () {
        return lives == 0 || searchedWord.equals(String.valueOf(userWord));
    }
}




