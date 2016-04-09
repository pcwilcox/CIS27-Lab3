package redblack;

/**
 * Created by Pete on 4/9/2016.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value>
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node
    {
        Key key;
        Value val;
        Node left, right;
        int weight;
        boolean color;

        Node(Key k, Value v, int w, boolean c)
        {
            this.key = k;
            this.val = v;
            this.weight = w;
            this.color = c;
        }
    }

    private boolean isRed(Node n)
    {
        if (n == null) return false;
        return n.color == RED;
    }

    Node rotateLeft(Node h)
    {
        Node n = h.right;
        h.right = n.left;
        n.color = h.color;
        h.color = RED;
        n.weight = h.weight;
        h.weight = 1 + size(h.left) + size(h.right);
        return n;
    }

    Node rotateRight(Node h)
    {
        Node n = h.left;
        h.left = n.right;
        n.right = h;
        n.color = h.color;
        h.color = RED;
        n.weight = h.weight;
        h.weight = 1 + size(h.left) + size(h.right);
        return n;
    }

    public Value get(Key k)
    {
        return get(root, k);
    }

    private Value get(Node n, Key k)
    {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp < 0) return get(n.left, k);
        else if (cmp > 0) return get(n.right, k);
        else return n.val;
    }

    public Key floor(Key k)
    {
        Node n = floor(root, k);
        if (n == null) return null;
        return n.key;
    }

    private Node floor(Node n, Key k)
    {
        if (n == null) return null;
        int cmp = k.compareTo(n.key);
        if (cmp == 0) return n;
        if (cmp < 0) return floor(n.left, k);
        Node m = floor(n.right, k);
        if (m != null) return m;
        else return n;
    }

    public Key select(int k)
    {
        return select(root, k).key;
    }

    private Node select(Node n, int k)
    {
        if (n == null) return null;
        int t = size(n.left);
        if (t > k) return select(n.left, k);
        else if (t < k) return select(n.right, k - t - 1);
        else return n;
    }

    public int rank(Key k)
    {
        return rank(k, root);
    }

    private int rank(Key k, Node n)
    {
        if (n == null) return 0;
        int cmp = k.compareTo(n.key);
        if (cmp < 0) return rank(k, n.left);
        else if (cmp > 0) return 1 + size(n.left) + rank(k, n.right);
        else return size(n.left);
    }



    public int size()
    {
        return size(root);
    }

    private int size(Node n)
    {
        if (n == null) return 0;
        n.weight = 1 + size(n.left) + size(n.right);
        return n.weight;
    }

    public void put(Key k, Value v)
    {
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node n, Key k, Value v)
    {
        if (n == null) return new Node(k, v, 1, RED);
        int cmp = k.compareTo(n.key);
        if (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put(n.right, k, v);
        else n.val = v;
        if (isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        n.weight = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node moveRedLeft(Node n)
    {
        flipColors(n);
        if (isRed(n.right.left))
        {
            n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }

    public void deleteMin()
    {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    public boolean isEmpty()
    {
        return size(root) == 0;
    }

    private Node deleteMin(Node n)
    {
        if (n.left == null) return null;
        if (!isRed(n.left) && !isRed(n.left.left)) n = moveRedLeft(n);
        n.left = deleteMin(n.left);
        return balance(n);
    }

    private Node balance(Node n)
    {
        if (isRed(n.right)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        n.weight = size(n.left) + size(n.right) + 1;
        return n;
    }

    private void flipColors(Node n)
    {
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
    }

    private Node moveRedRight(Node n)
    {
        flipColors(n);
        if (isRed(n.left.left)) n = rotateRight(n);
        return n;
    }

    public void deleteMax()
    {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node n)
    {
        if (isRed(n.left)) n = rotateRight(n);
        if (n.right == null) return null;
        if (!isRed(n.right) && !isRed(n.right.left)) n = moveRedRight(n);
        n.right = deleteMax(n.right);
        return balance(n);
    }

    public void delete(Key k)
    {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = delete(root, k);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node n, Key k)
    {
        if (k.compareTo(n.key) < 0)
        {
            if (!isRed(n.left) && !isRed(n.left.left)) n = moveRedLeft(n);
            n.left = delete(n.left, k);
        } else
        {
            if (isRed(n.left)) n = rotateRight(n);
            if (k.compareTo(n.key) == 0 && n.right == null) return null;
            if (!isRed(n.right) && !isRed(n.right.left)) n = moveRedRight(n);
            if (k.compareTo(n.key) == 0)
            {
                Node m = min(n.right);
                n.key = m.key;
                m.val = m.val;
                n.right = deleteMin(n.right);
            } else n.right = delete(n.right, k);
        }
        return balance(n);
    }

    private Node min(Node n)
    {
        if (n == null) return null;
        return min(n.left);
    }


}
