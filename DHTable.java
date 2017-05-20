package elice;

public class DHTable<K, E> {
	
	private int manyItems;
	private Object[] keys;
	private Object[] data;
	private boolean[] hasBeenUsed;
	
	public DHTable(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity is negative");
		keys = new Object[capacity];
		data = new Object[capacity];
		hasBeenUsed = new boolean[capacity];
	}
	
	public boolean containsKey(K key) {
		// Student implementation
		return findIndex(key) != -1;
	}
	
	private int findIndex(K key)
		// Postcondition: If the specified key is found in the table, then the
		// return value is the index of the specified key. Otherwise, the return
		// value is -1.
		{
			int count = 0;
			int i = hash1(key);

			while (count < data.length && hasBeenUsed[i]) {
				visits++;
				if (key.equals(keys[i]))
					return i;
				count++;
				i = nextIndex(i,key);
			}
			return -1;
		}
		
	private int nextIndex(int i, K key)
		// The return value is normally i+1. But if i+1 is data.length, then the
		// return value is zero instead.
		{
			return (i + hash2(key)) % data.length;
		}
	
	// DON'T ERASE THIS. WE USE THIS VARIABLE FOR THE TEST.
	public int visits = 0;
	
	public E get(K key) {
		// Student implementation
		// MUST UPDATE visits VARIABLE AS THE NUMBER OF VISITS UNTIL FINDING THE KEY.
		int index = findIndex(key);

			if (index == -1)
				return null;
			else
				return (E) data[index];
	}

	private int hash1(K key)
	{
		/*
			The return value is a valid index of the table's arrays. The index is
			calculated as the remainder when the absolute value of the key's
			hash code is divided by the size of the table's arrays.
		*/
		return Math.abs(key.hashCode()) % data.length;
	}
	
	private int hash2(K key)
	{
		return 1 + Math.abs(key.hashCode()) % (data.length-2); // determine the stride for nextIndex
	}
	
	public E put(K key, E element) {
		// Student implementation
		int index = findIndex(key);
			E answer;

			if (index != -1) { // The key is already in the table.
				answer = (E) data[index];
				data[index] = element;
				return answer;
			} else if (manyItems < data.length) { // The key is not yet in this
													// Table.
				index = hash1(key);
				while (keys[index] != null)
					index = nextIndex(index,key); // key for double hashing
				keys[index] = key;
				data[index] = element;
				hasBeenUsed[index] = true;
				manyItems++;
				return null;
			} else { // The table is full.
				throw new IllegalStateException("Table is full.");
			}
	}
	
	public E remove(K key) {
		// Student implementation
		int index = findIndex(key);
			E answer = null;

			if (index != -1) {
				answer = (E) data[index];
				keys[index] = null;
				data[index] = null;
				manyItems--;
			}

			return answer;
	}
}
