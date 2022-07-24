import java.util.*;

public class HashMap274<K, V> implements Map<K, V> {

	// Ordinarily, the instance variables would be private.
	// But leave them public. Why? Because it makes it easier for you
	// (and your instructors!) to test your code.
	// Each array location contains a LinkedList of Key-Value pairs
	// So, it's an array of LinkedLists
	public LinkedList<Entry<K, V>>[] buckets;
	public int size;
	public static final int DEFAULT_CAPACITY = 11; // prime
	public static final double LOAD_FACTOR = 0.6;

	@SuppressWarnings("unchecked")
	public HashMap274() {
		buckets = (LinkedList<Entry<K, V>>[])new LinkedList[DEFAULT_CAPACITY];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Entry<K, V>>();
		}
		size = 0;
	}
	
	//PRIVATE METHOD HELPER
	private boolean loadFactorOK() {
		return false;
	}
	
	@Override
	public V put(K key, V value) {
		//get hash code for the key
		// if key is in the map already, update value return value
		//else add new (key, value) return null
		
		/*
		Entry temp = new Entry(key);
		
		if (buckets[getHashIndex(key)].contains(temp)) {
			buckets[getHashIndex(key)].
		} else {
			buckets[getHashIndex(key)].add(new Entry(key, value));
			if ( ! loadFactorOK()) resize();
				return null;
		}
		return null;
		*/
		
		int hashIndex = getHashIndex(key);
		Entry entry = new Entry(key, value);
		V result = null;
		ListIterator<Entry<K,V>> iter = buckets[hashIndex].listIterator();
		
		while (iter.hasNext()) {
			Entry item = iter.next();
			if (item.key.equals(key)) {
				result = (V) item.value;
				iter.remove();
				size--;
				break;
			}
		}
		
		iter.add(entry);
		size++;
		
		double counter = 0.0;
		
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i].size() > 0) {
				counter++;
			}
		}
		
		if (counter / buckets.length >= LOAD_FACTOR) {
			resize();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		LinkedList<Entry<K, V>>[] oldBuckets = buckets;
		buckets = (LinkedList<Entry<K, V>>[])new LinkedList[firstPrime(2*oldBuckets.length)];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Entry<K, V>>();
		}
		
		for (int i = 0; i < oldBuckets.length; i++) {
			while (!oldBuckets[i].isEmpty()) {
				Entry<K, V> entry = oldBuckets[i].remove(0);
				int newIndex = getHashIndex(entry.key);
				buckets[newIndex].add(entry);	
			}
		}
	}

	@Override
	public V get(K key) {
		int bucketIndex = getHashIndex(key);
		
		if (buckets[bucketIndex] == null) return null;
		
		LinkedList<Entry<K, V>> list = buckets[bucketIndex];
		
		for (Entry<K,V> entry : list) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
		}
		
		
		
		return null;
	}

	@Override
	public V remove(K key) {
		int bucketIndex = getHashIndex(key);
		
		if (buckets[bucketIndex] == null) return null;
		
		LinkedList<Entry<K, V>> list = buckets[bucketIndex];
		
		for (Entry<K,V> entry : list) {
			if (entry.key.equals(key)) {
				Entry<K, V> temp = entry;
				list.remove(entry);
				size--;
				return temp.value;
			}
		}
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		Entry<K, V> temp = new Entry<K, V>(key);
		int bucketIndex = getHashIndex(key); // tells us which bucket to check
		return buckets[bucketIndex].indexOf(temp) != -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		for (LinkedList<Entry<K,V>> list : buckets) {
			list.clear();
		}
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public Set<K> keySet() {
		Set<K> result = new TreeSet<K>();
		for (int i = 0; i < buckets.length; i++) {
			for ( Entry<K, V> entry : buckets[i]) {
				result.add(entry.key);
			}
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		
		for (int i = 0; i < buckets.length; i++) {
			result += "[" + i + "]\t" + buckets[i] + "\n";
		}
 		result += "size: " + size + "\n";
		return result;
	}
	
	// Gets the index of the bucket where a given string should go,
	// by computing the hashCode, and then compressing it to a valid index.
	private int getHashIndex(K key) {
		int index = key.hashCode() % buckets.length;
		if (index < 0)
			index += buckets.length;
		return index;
	}

	// Returns true if a number is prime, and false otherwise
	private static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	// Returns the first prime >= n
	private static int firstPrime(int n) {
		while (!isPrime(n))
			n++;
		return n;
	}

	// Ordinarily, the inner class would be private.
	// But leave it public. Why? Because it makes it easier for you
	// (and your instructors!) to test your code.
	public class Entry<K, V> {
		public K key;
		public V value;
		// constructors
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public Entry(K key) {
			this(key, null);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Entry<K,V> other = (Entry<K,V>) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}

		public String toString() {
			return "<" + key + ", " + value + ">";
		}
	}
	
	public static void main(String[] args) {
		HashMap274<String, Integer> map = new HashMap274<>();
		System.out.println(map);
		
		System.out.println(map.put("cat", 1));
		System.out.println(map.put("dog", 1));
		System.out.println(map.put("cat", 99));
		System.out.println(map);

		map.put("A", 3);
		map.put("B", 4);
		map.put("C", 5);
		System.out.println(map.get("cat")); //99
		System.out.println(map.get("tree")); //null
		System.out.println(map.get("C")); //5
//		Adding these next values should cause resize()
		map.put("D", 6);
		map.put("E", 7);
		System.out.println(map);
		
		System.out.println(map.remove("D"));
		System.out.println(map.remove("E"));
		System.out.println(map);
		System.out.println(map.keySet());
	}

	

}
