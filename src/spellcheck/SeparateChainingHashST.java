package spellcheck;

/**
 * Created by Pete Wilcox on 4/23/2016.
 * petercwilcox@gmail.com
 */
public class SeparateChainingHashST<Key, Value>
{
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(13);
    }

    public SeparateChainingHashST(int N) {
        this.M = N;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[N];
        for (int i = 0; i < N; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    private void resize() {

    }
}
