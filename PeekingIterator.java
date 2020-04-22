//https://github.com/google/guava/blob/master/android/guava/src/com/google/common/collect/PeekingIterator.java
//https://github.com/google/guava/wiki/CollectionHelpersExplained#peekingiterator
//https://stackoverflow.com/questions/46345139/getting-the-current-and-the-next-element-from-a-collection-in-a-loop-using-iter
//https://medium.com/@harycane/peeking-iterator-ef69ce9ef788

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator<E> implements Iterator<E> {

    //a property called next pointing to the next of the iterator obj at a given time
    private E next;
    
    //a property called iter to store the incoming iterator obj
    private Iterator<E> iter;
    
    //a property to keep track of whether there are further elements in the iterator obj
    private boolean noSuchElement;
    
	public PeekingIterator(Iterator<E> iterator) {
	    // initialize incoming iterator with iter member
        iter = iterator;
        //invoke the advanceIterator method to update the next member to point to the next element of the iterator obj
        advanceIterator();
	    
	}
    
    // T O(1)
    // Returns the next element in the iteration without advancing the iterator.
	public E peek() {
        return next;
	}

    // T O(1)
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public E next() {
	    E res = next; //retrieve the cached result and return;
        advanceIterator(); //remember to update the next to point to next of iterator by invoking advanceIterator
        return res;
	}

    //T O(1)
	@Override
	public boolean hasNext() {
	    return !noSuchElement;
	}
    
    //T O(1)
    private void advanceIterator() {
        //condition to check if there is a next element or not in the iter obj
        if (iter.hasNext()) {
            //if present then update next to point to it 
            next = iter.next(); //caching the next of iterator to our next property
        } else {
            noSuchElement = true;
        }
    }
}