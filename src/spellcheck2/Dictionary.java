package spellcheck2;

import java.util.Scanner;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class Dictionary
{
    private static final int M = 997;
    private List[] words;

    public Dictionary() {
        words = new List[M];
        for (int i = 0; i < M; i++)
            words[i] = new List();

        readWords();
    }

    private void readWords() {
        // read list of words from file into the dictionary
        Scanner in = new Scanner("words.txt");
        in.useDelimiter("\n");
        while (in.hasNext()) {
            String token = in.next();
            put(token);
        }
    }

    public void put(String word) {
        words[hash(word)].put(word);
    }

    private int hash(String word) {
        return (word.hashCode() & 0x7fffffff) % M;
    }

    public String get(String word) {
        return (String) words[hash(word)].get(word);
    }

    public String match(String input) {
        if (input.equals(get(input))) return input;
        return mix(input);
    }

    private String mix(String input) {
        // apply the various transpositions etc and see what comes up
        String possibles = "";
        /* TODO:
         a: add one character to beginning
         b: add one character to end
         c: remove one character at beginning
         d: remove one character at end
         e: exchange adjacent characters
          */
        return possibles;
    }

}
