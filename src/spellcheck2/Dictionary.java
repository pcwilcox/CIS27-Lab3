package spellcheck2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class Dictionary
{
    private int M;
    private ListOfWords[] words;
    private static final String chars = "abcdefghijklmnopqrstuvwxyz";

    public Dictionary()
    {
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
        //System.out.println("Adding " + word + " with hash code " + hash(word) + ".");
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
        System.out.println("Testing " + input);
        if (get(input) != null) return input;
        return mix(input);
    }

    private String mix(String input)
    {
        // apply the various transpositions etc and see what comes up
        ArrayList<String> outputBuilder = new ArrayList<>();
        String output = "";
        outputBuilder.add(addFirst(input));
        outputBuilder.add(addLast(input));
        outputBuilder.add(removeFirst(input));
        outputBuilder.add(removeLast(input));
        outputBuilder.add(exchange(input));

        for (String s : outputBuilder) {
            if (s != null && !s.equals("")) {
                output = output + "\n" + s;
            }
        }

        return output;
    }

    private String addFirst(String input)
    {
        String output = "";
        for (int i = 0; i < 26; i++)
        {
            if (get(chars.substring(i, i + 1) + input) != null)
                output = output + "\n" + get(chars.substring(i, i + 1) + input);
        }
        return output;
    }

    private String addLast(String input)
    {
        String output = "";
        for (int i = 0; i < 26; i++)
        {
            if (get(input + chars.substring(i, i + 1)) != null)
                output = output + "\n" + get(input + chars.substring(i, i + 1));
        }
        return output;
    }

    private String removeFirst(String input)
    {
        return get(input.substring(1, input.length()));
    }

    private String removeLast(String input)
    {
        return get(input.substring(input.length() - 1, input.length()));
    }

    private String exchange(String input)
    {
        String output = "";
        char[] chars = input.toCharArray();
        int size = input.length();

        for (int i = 0; i < size - 2; i++)
        {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String possible = get(new String(chars));
            if (possible != null) output = output + possible + "\n";
            else output = possible;

            temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }

        return output;
    }


}
