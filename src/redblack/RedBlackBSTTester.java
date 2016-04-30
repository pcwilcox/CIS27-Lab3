package redblack;

/**
 * Created by Pete on 4/9/2016.
 */
public class RedBlackBSTTester
{
    public static void main(String[] args) {
        RedBlackBST<Integer, String> test = new RedBlackBST<>();

        for (int i = 1; i <= 60; i++) {
            test.put(i, "");
        }

        System.out.println(test);

        for (int i = 20; i > 0; i--) {
            test.delete(i);
        }

        System.out.println(test);
    }
}

/* Program Output:

[1,1,false][2,3,false][3,1,false][4,7,false][5,1,false][6,3,false][7,1,false]
[8,15,false][9,1,false][10,3,false][11,1,false][12,7,false][13,1,false]
[14,3,false][15,1,false][16,31,true][17,1,false][18,3,false][19,1,false]
[20,7,false][21,1,false][22,3,false][23,1,false][24,15,false][25,1,false]
[26,3,false][27,1,false][28,7,false][29,1,false][30,3,false][31,1,false]
[32,60,false][33,1,false][34,3,false][35,1,false][36,7,false][37,1,false]
[38,3,false][39,1,false][40,15,true][41,1,false][42,3,false][43,1,false]
[44,7,false][45,1,false][46,3,false][47,1,false][48,28,false][49,1,false]
[50,3,false][51,1,false][52,7,true][53,1,false][54,3,false][55,1,false]
[56,12,false][57,1,false][58,4,false][59,1,true][60,2,false]

[21,1,false][22,3,false][23,1,false][24,7,true][25,1,false][26,3,false]
[27,1,false][28,11,false][29,1,false][30,3,false][31,1,false][32,19,false]
[33,1,false][34,3,false][35,1,false][36,7,false][37,1,false][38,3,false]
[39,1,false][40,40,false][41,1,false][42,3,false][43,1,false][44,7,false]
[45,1,false][46,3,false][47,1,false][48,20,false][49,1,false][50,3,false]
[51,1,false][52,7,true][53,1,false][54,3,false][55,1,false][56,12,false]
[57,1,false][58,4,false][59,1,true][60,2,false]

 */