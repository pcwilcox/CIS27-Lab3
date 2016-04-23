package spellcheck2;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class ListOfWords
{
    Node first;
    private int size;

    private class Node
    {
        Node next;
        String word;

        public Node(String word, Node next)
        {
            this.word = word;
            this.next = next;
        }
    }

    public void put(String word)
    {
        Node temp = new Node(word, first);
        first = temp;
        size++;
    }

    public String pop()
    {
        if (first == null) return null;
        Node temp = first;
        first = first.next;
        size--;
        return temp.word;
    }

    public String get(String word)
    {
        for (Node temp = first; temp != null; temp = temp.next)
            if (word.equals(temp.word)) return temp.word;

        return null;

    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public ListOfWords()
    {
        size = 0;
    }

}