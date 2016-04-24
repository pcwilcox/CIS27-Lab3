package spellcheck2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 * <p>
 * Hashmap dictionary-based spellchecker.
 */
public class Dictionary
{
    private int           M;
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
        {
            words[i] = new ListOfWords();
        }

        M = capacity;
        readWords();
    }

    // Load dictionary data from a file
    private void readWords()
    {
        File wordlist = new File("C:\\Users\\Lord Yod\\IdeaProjects\\CIS27-Lab3\\src\\spellcheck2\\words.txt");

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

    // Add a word to the list
    public void put(String word)
    {
        words[hash(word)].put(word);
    }

    // Return the hash for a word
    private int hash(String word)
    {
        return (word.hashCode() & 0x7fffffff) % M;
    }

    // Search for a word in the dictionary
    public String get(String word)
    {
        return (String) words[hash(word)].get(word);
    }

    // Search for a word in the dictionary, if it's not found, find alternatives
    public String match(String input)
    {
        System.out.println("Testing " + input);
        if (get(input) != null)
        {
            return input;
        }
        return mix(input);
    }

    // Try to modify the input to find valid words
    private String mix(String input)
    {
        ArrayList<String> outputBuilder = new ArrayList<>();
        String            output        = "";
        outputBuilder.add(addFirst(input));
        outputBuilder.add(addLast(input));
        outputBuilder.add(removeFirst(input));
        outputBuilder.add(removeLast(input));
        outputBuilder.add(exchange(input));

        for (String s : outputBuilder)
        {
            if (s != null && !s.equals(""))
            {
                output = output + "\n" + s;
            }
        }

        return output;
    }

    // Add a letter to the start
    private String addFirst(String input)
    {
        String output = "";
        for (int i = 0; i < 26; i++)
        {
            if (get(chars.substring(i, i + 1) + input) != null)
            {
                output = output + get(chars.substring(i, i + 1) + input) + "\n";
            }
        }
        return output;
    }

    // Add a letter to the end
    private String addLast(String input)
    {
        String output = "";
        for (int i = 0; i < 26; i++)
        {
            if (get(input + chars.substring(i, i + 1)) != null)
            {
                output = output + get(input + chars.substring(i, i + 1)) + "\n";
            }
        }
        return output;
    }

    // Remove first letter
    private String removeFirst(String input)
    {
        return get(input.substring(1, input.length()));
    }

    // Remove last letter
    private String removeLast(String input)
    {
        return get(input.substring(input.length() - 1, input.length()));
    }

    // Swap adjacent characters
    private String exchange(String input)
    {
        String output = "";
        char[] chars  = input.toCharArray();
        int    size   = input.length();

        for (int i = 0; i < size - 2; i++)
        {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String possible = get(new String(chars));
            if (possible != null)
            {
                output = output + possible + "\n";
            }

            temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }

        return output;
    }


}
