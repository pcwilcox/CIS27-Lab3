package spellcheck;

/**
 * Created by Pete on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value>
{
    private Node first;

    private class Node
    {
        // linked list
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key)
    {
        // search for the key, return value
        for (Node x = first; x != null; x = x.next)
        {
            // search hit
            if (key.equals(x.key)) return x.val;
        }

        // search miss
        return null;
    }

    public void put(Key key, Value val)
    {
        // search for key, update value if found, add to list if not
        for (Node x = first; x != null; x = x.next)
        {
            // search hit
            if (key.equals(x.key))
            {
                x.val = val;
                return;
            }

        }
        // search miss
        first = new Node(key, val, first);
    }
}

