package spellcheck2;

import java.util.Random;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class DictionaryTester
{
    public static void main(String[] args) {
        System.out.println("Creating dictionary...");
        Dictionary dict = new Dictionary();

        System.out.println(dict.match("aardvark"));
        System.out.println(dict.match("mtah"));
        System.out.println(dict.match("wette"));


    }


}
