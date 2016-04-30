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

        System.out.println(dict.match("aardvark"));
        System.out.println(dict.match("mtah"));
        System.out.println(dict.match("wette"));
        System.out.println(dict.match("objectvie"));
        System.out.println(dict.match("itnerview"));
        System.out.println(dict.match("sucecss"));
        System.out.println(dict.match("pple"));
        System.out.println(dict.match("sbaseball"));
    }
}

/* Program Output:

Creating dictionary...
Testing aardvark
aardvark
Testing mtah
math
Testing wette
wetted
wetter
Testing objectvie
objective
Testing itnerview
interview
Testing sucecss
success
Testing pple
apple
Testing sbaseball
baseball
 */