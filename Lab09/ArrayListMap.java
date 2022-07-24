import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;


public class ArrayListMap<K, V> implements Map<K, V> {
	
	ArrayList<Entry> arrayMap;

	public ArrayListMap() {
		arrayMap = new ArrayList<Entry>();
	}
	
	@Override
	public V put(K key, V value) {
		Entry temp = new Entry(key, value);		
		
		for ( Entry entry : arrayMap) {
			if ( entry.key.equals(key)) {
				V tempReturnValue = entry.value;
				arrayMap.remove(entry);
				arrayMap.add(temp);
				return tempReturnValue;
			}
		}
		
		arrayMap.add(temp);
		return null;
	}

	@Override
	public V get(K key) {
		for ( Entry entry : arrayMap) {
			if ( entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}

	@Override
	public V remove(K key) {
		for ( Entry entry : arrayMap) {
			if ( entry.key.equals(key)) {
				V tempReturnValue = entry.value;
				
				arrayMap.remove(entry);
				
				return tempReturnValue;
			}
		}
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		for ( Entry entry : arrayMap) {
			if ( entry.key.equals(key)) return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return arrayMap.isEmpty();
	}

	@Override
	public void clear() {
		arrayMap.clear();
	}

	@Override
	public int size() {
		return arrayMap.size();
	}

	@Override
	public Set<K> keySet() {
		Set<K> result = new HashSet<K>();
		for ( Entry entry : arrayMap) {
			result.add(entry.key);
		}
		
		return null;
	}

	private class Entry {
		private K key;
		private V value;
		// constructors
		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		private Entry(K key) {
			this(key, null);
		}
	}

}
