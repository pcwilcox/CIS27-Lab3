package spellcheck2;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class DictionaryTester
{
    public static void main(String[] args) {
        System.out.println("Creating dictionary...");
        Dictionary dict = new Dictionary();

        System.out.println(dict.get("aardvark"));
    }
}
