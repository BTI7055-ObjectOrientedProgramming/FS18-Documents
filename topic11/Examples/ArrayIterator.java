package topic11;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<E> implements Iterator<E> {

	private E[] array;
	private int nextIndex;
	
	public ArrayIterator(E[] array) {
		this.array = array;
		this.nextIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return this.nextIndex < this.array.length;
	}

	@Override
	public E next() {
		if (!this.hasNext()){
			throw new NoSuchElementException();
		}
		return this.array[this.nextIndex++];
	}

}
