import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTable<T> {
    private LinkedList<HashEntry>[] elements; // Array of linked hash entries
    private int numItems; // Initialized to 0

    public HashTable() {
        this(10);
    }

    public HashTable(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        elements = new LinkedList[capacity];
    }

    public T get(String key) {
        if (elements.length == 0) throw new NoSuchElementException(); // Done first to avoid divide by 0 error
        int index = Math.abs(key.hashCode()) % elements.length;
        if (elements[index] != null) {
            for (HashEntry entry : elements[index]) {
                if (entry.getKey().equals(key)) return entry.getValue();
            }
        }
        throw new NoSuchElementException();
    }

    public void put(String key, T value) {
        if (numItems >= elements.length) rehash(); // Load factor is 1, so need to rehash
        int index = Math.abs(key.hashCode() % elements.length);
        if (elements[index] == null) elements[index] = new LinkedList<HashEntry>();
        // Check if the value exists yet (don't insert again if it does but increase the frequency counter)
        for (HashEntry entry : elements[index]) {
            if (entry.getKey().equals(key)) {
                if (value instanceof Integer) entry.setValue((T)(Integer)((Integer)entry.getValue() + (Integer)value));
                else entry.setValue(value);
                return;
            }
        }
        elements[index].add(new HashEntry(key, value));
        numItems++;
    }

    public T remove(String key) {
        if (elements.length == 0) throw new NoSuchElementException(); // Done first to avoid divide by 0 error
        int index = Math.abs(key.hashCode()) % elements.length;
        if (elements[index] != null) {
            for (HashEntry e : elements[index]) {
                if (e.getKey().equals(key)) {
                    elements[index].remove(e);
                    numItems--;
                    return e.getValue();
                }
            }
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return numItems;
    }

    private void rehash() {
        LinkedList<HashEntry>[] newHash = new LinkedList[elements.length * 2 + 1];
        for(LinkedList<HashEntry> chain : elements) {
            if (chain != null) {
                for (HashEntry entry : chain) {
                    int index = Math.abs(entry.getKey().hashCode()) % newHash.length;
                    if (newHash[index] == null) newHash[index] = new LinkedList<HashEntry>();
                    // No need to account for duplicate cases
                    newHash[index].add(entry);
                }
            }
        }
        elements = newHash;
    }

    public class HashEntry implements Comparable<HashEntry>{
        private String key;
        private T value;
    
        // Initializes a HashEntry with a key and a value. (Constant time)
        public HashEntry(String key, T value) {
            this.key = key;
            this.value = value;
        }
    
        // Returns this HashEntry's key. (Constant time)
        public String getKey() { return key; }
    
        // Returns this HashEntry's value. (Constant time)
        public T getValue() { return value; }
    
        // Sets this HashEntry's value to the specified int. (Constant time)
        public void setValue(T value) { this.value = value; }
    
        // Compares the HashEntries by value (input minus this). (Constant time)
        public int compareTo(HashEntry entry) {
            if (value instanceof Integer) return (Integer)entry.getValue() - (Integer)getValue();
            return 0;
        }
    }
    
}
