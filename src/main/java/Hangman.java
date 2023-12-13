import java.util.*;

public class Hangman {
    List<String> words = List.of("hogwart", "hedwiga", "zgredek", "dobby", "ron", "harry", "dumbledore", "voldemort");
    String searchedWord;
    char[] userWord;
    int lives = 8;

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        searchedWord = words.get(random.nextInt(words.size()));

        userWord = new char[searchedWord.length()]; //tablica o długości wylosowanego słowa
        Arrays.fill(userWord, '_');

        while (!gameEnded()) {
            try {
                System.out.println("Drawn word:");
                System.out.println(userWord);
                System.out.println();
                System.out.println("Remaining lives: " + lives);
                System.out.println("Enter another letter: ");

                char letter = scanner.next().toLowerCase().charAt(0);
                checkLetter(letter);
            } catch (NotALetter ex) {
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

    private void checkLetter(char letter) throws NotALetter {
        /* sprawdzamy czy litera istnieje w wylosowanym słowie,
        jesli tak uzupełnimy tablicę userWord o te literę dokładnie pod wskazanym indeksem*/

        boolean foundLetter = false;
        String tempLetter = Character.toString(letter);

        for (int i = 0; i < searchedWord.length(); i++) {
            if (Character.isDigit(letter)) {
                lives--;
                foundLetter = false;
                throw new NotALetter("This is not a letter. Try again");

            }else {
                if (searchedWord.charAt(i) == letter) {
                    userWord[i] = letter;
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




