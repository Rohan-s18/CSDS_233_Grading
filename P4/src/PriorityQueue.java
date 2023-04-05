import java.util.NoSuchElementException;

public class PriorityQueue<K extends Comparable<? super K>, V> {
  private K[] keys;
  private V[] values;
  private int size;

  public PriorityQueue() {
    keys = (K[]) new Comparable[0];
    values = (V[]) new Object[0];
  }

  public PriorityQueue(K[] keys, V[] values) {
    if (values.length != keys.length)
      throw new IllegalArgumentException();
    this.keys = keys;
    this.values = values;
    size = keys.length;
    heapify();
  }

  public int size() {
    return size;
  }
  
  // Swaps key-value pairs at given indices
  private void swap(int i1, int i2) {
    K temp1 = keys[i1];
    V temp2 = values[i1];
    keys[i1] = keys[i2];
    values[i1] = values[i2];
    keys[i2] = temp1;
    values[i2] = temp2;
  }
  
  // Sift down element at index i (Log time)
  private void heapifyDown(int i) {
    if (i*2 + 1 >= size) return; // No children
    if (i*2 + 2 == size) { // Only left child
      if (keys[i].compareTo(keys[i*2 + 1]) < 0) {
        swap(i, i*2 + 1);
      }
    }
    else { // Both children exist
      if (keys[i*2 + 1].compareTo(keys[i*2 + 2]) >= 0) { // Left child
        if (keys[i].compareTo(keys[i*2 + 1]) < 0) {
          swap(i, i*2 + 1);
          heapifyDown(i*2 + 1);
        }
      }
      else if (keys[i].compareTo(keys[i*2 + 2]) < 0) {
        swap(i, i*2 + 2);
        heapifyDown(i*2 + 2);
      }
    }
  }

  // Sift up element at index i (Log time)
  private void heapifyUp(int i) {
    if (i == 0 || keys[i].compareTo(keys[(i-1) / 2]) <= 0) return;
    swap(i, (i-1) / 2);
    heapifyUp((i-1) / 2);
  }

  // Build heap from initialization lists (Linear time)
  private void heapify() {
    if (keys.length == 0) return; // Vacuously a heap
    // Find first non-null index
    int firstNonNull = keys.length - 1;
    while (keys[firstNonNull] == null && firstNonNull > 0) 
      firstNonNull--;
    // Index halfway between length of array that is the first index of the second-to-last level, so begin comparison there
    int begIndex = firstNonNull/2 - 1;
    // Build heap
    for (int i = begIndex; i >= 0; i--) 
      heapifyDown(i);
  }
  
  public void add(K key, V value) {
    size++;
    // If current queue is full, create a new array of double length, copy indices, and assign to be new keys and values
    if (size() > keys.length) {
      K[] temp1 = (K[]) new Comparable[keys.length * 2 + 1];
      V[] temp2 = (V[]) new Object[values.length * 2 + 1];
      for (int i = 0; i < keys.length; i++) {
        temp1[i] = keys[i];
        temp2[i] = values[i];
      }
      keys = temp1;
      values = temp2;
    }
    // Insert key and value into last available index, then heapify last index to ensure array is heap
    keys[size() - 1] = key;
    values[size() - 1] = value;
    heapifyUp(size() - 1);
  }
  
  public K update(K key, V value) {
    for (int i = 0; i < size; i++) {
      if (values[i].equals(value)) {
        K oldKey = keys[i];
        keys[i] = key;
        heapify();
        return oldKey;
      }
    }
    throw new NoSuchElementException();
  }
  
  public V peek() {
    if (size == 0) throw new NoSuchElementException();
    return values[0];
  }
  
  public V poll() {
    if (size() == 0)
      throw new NoSuchElementException();
    // Save top of heap
    V topOfHeap = values[0];
    // Reassign top of heap to be last element;
    values[0] = values[size() - 1];
    keys[0] = keys[size() - 1];
    // Adjust size
    size--;
    // Heapify first index to get heap structure
    // "Remove" last index by making it null
    values[size()] = null;
    keys[size()] = null;
    heapifyDown(0);
    // Return value removed
    return topOfHeap;
  }

  public V[] peek(int k) {
    if (k > values.length) throw new IllegalArgumentException();
    V[] values = (V[]) new Object[k];
    K[] keys = (K[]) new Comparable[k];
    // Collect largest keys and corresponding values
    for (int i = 0; i < k; i++) {
      values[i] = this.values[0];
      keys[i] = this.keys[0];
      this.poll();
    }
    // Reintroduce them into the original heap
    for (int i = 0; i < k; i++) 
      this.add(keys[i], values[i]);
    return values;
  }
  
  public K poll(V value) {
    int foundIndex = -1;
    for (int i = 0; i < size; i++) {
      if (values[i].equals(value)) {
        foundIndex = i;
        break;
      }
    }
    if (foundIndex == -1) throw new NoSuchElementException();
    // Rebuild heap
    K foundKey = keys[foundIndex];
    keys[foundIndex] = keys[size - 1];
    values[foundIndex] = values[size - 1];
    keys[size - 1] = null;
    values[size-- - 1] = null;
    heapify();
    return foundKey;
  }
}