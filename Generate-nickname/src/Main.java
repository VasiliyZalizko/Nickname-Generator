import java.util.Random;
import static utils.Generator.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[10000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
            if (isPalindrome(text) && !isSameChar(text)) {
                incrementCounter(text.length());
            }
            }
        });
        palindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread ascendingOrder = new Thread (() -> {
            for (String text : texts) {
                if (!isSameChar(text) && isAscendingOrder(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();

        sameChar.join();
        ascendingOrder.join();
        palindrome.join();

        System.out.println("Красивых слов длиной 3: " + counter3);
        System.out.println("Красивых слов длиной 4: " + counter4);
        System.out.println("Красивых слов длиной 5: " + counter5);
    }
}