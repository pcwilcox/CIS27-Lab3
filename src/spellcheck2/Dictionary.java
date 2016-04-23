package spellcheck2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class Dictionary
{
    private int M;
    private ListOfWords[] words;

    public Dictionary() {
        this(33617); // arbitrary prime number
    }

    public Dictionary(int capacity)
    {
        words = new ListOfWords[capacity];
        for (int i = 0; i < capacity; i++)
            words[i] = new ListOfWords();

        M = capacity;
        readWords();
    }

    private void readWords()
    {
        // read list of words from file into the dictionary
        File wordlist = new File("C:\\Users\\Pete\\IdeaProjects\\CIS27-Lab3\\src\\spellcheck2\\words.txt");

        try
        {
            Scanner in = new Scanner(wordlist);
            in.useDelimiter("\r\n");
            while (in.hasNext())
            {
                String token = in.next();
                put(token);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void put(String word)
    {
        System.out.println("Adding " + word + " with hash code " + hash(word) + ".");
        words[hash(word)].put(word);
    }

    private int hash(String word)
    {
        return (word.hashCode() & 0x7fffffff) % M;
    }

    public String get(String word)
    {
        return (String) words[hash(word)].get(word);
    }

    public String match(String input)
    {
        if (input.equals(get(input))) return input;
        return mix(input);
    }

    private String mix(String input)
    {
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
