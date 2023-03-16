public class PriorityQueue<K extends Comparable<? super K>,V> {
    public PriorityQueue() {}
    public PriorityQueue(K[] keys, V[] values) {}
    public void add(K key, V value) {}
    public void update(K key, V value) {}
    public V peek() {return null;}
    public V[] peek(int k) {return null;}
    public V poll() {return null;}
    public K poll(V value) {return null;}
    public int size() {return 0;}
}