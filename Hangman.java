import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
    private int correctLetters;
    private String newWord;
    private int lives;
    String[] hangmanAscii;
    ArrayList<Character> guessedLetters;

    Hangman() {
        guessedLetters = new ArrayList<>();
        int hangmanIndex = 0;
        int lives = 6;

        System.out.println("WELCOME TO HANGMAN");
        String[] hangmanAscii = buildHangmanAscii();
        System.out.println(hangmanAscii[hangmanIndex]);

        String word = getWordFromPlayerOne();
        int numberOfLetters = countLetters(word);

        char[] wordBoard = createWordBoard(word);
        System.out.println(wordBoard);

        startGameLoop(word, wordBoard, numberOfLetters, hangmanIndex, lives, hangmanAscii);

    }
    public static String getWordFromPlayerOne() {
        Scanner wordInput = new Scanner(System.in);
        System.out.print("Player 1 give me a word: ");
        return wordInput.nextLine().toLowerCase();
    }
    public static char[] createWordBoard(String word) {
        char[] wordBoard = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                wordBoard[i] = ' ';
            } else {
                wordBoard[i] = '_';
            }
        }
        return wordBoard;
    }
    private static int countLetters(String word) {
        int count = 0;
        for(int i =0; i < word.length(); i++) {
            if(word.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }
    private void startGameLoop(String word, char[] wordBoard, int numberOfLetters, int hangmanIndex, int lives, String[] hangmanAscii) {
        while (true) {
            if (numberOfLetters == correctLetters) {
                playerTwoWins();
                break;
            }
            Scanner guessInput = new Scanner(System.in);
            System.out.print("Player 2 Provide me with a letter: ");
            char letter = guessInput.next().charAt(0);
            if (!Character.isLetter(letter)) {
                continue;
            } else if(guessedLetters.contains(letter)) {
                continue;
            }
            guessedLetters.add(letter);
            if(word.indexOf(letter) == -1) {
                System.out.println("The letter: " + letter + " was not in the word");
                lives -= 1;
                hangmanIndex++;
                System.out.println(hangmanAscii[hangmanIndex]);
                if(lives <= 0 ) {
                    playerOneWins();
                    break;
                }
                continue;
            }
            for(int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    correctLetters += 1;
                    wordBoard[i] = letter;
                }
            }
            System.out.println(wordBoard);
        }
    }
    private static void playerOneWins() {
        System.out.println("Congratulations you beat player 1 YAYYYYY");
    }
    private static void playerTwoWins() {
        System.out.println("Congratulations you beat player 1 YAYYYYY");
    }
    private static String[] buildHangmanAscii() {
        return new String[] {
                """
 ____ 
|/   |
|
|
|
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|
|
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|    |
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|   /
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   \\|/
|    |
|   / \\
|
|_____
""",
                """
 ____ 
|/   |
|   (_)
|   /|\\
|    |
|   | |
|
|_____
"""
        };
    }
}
