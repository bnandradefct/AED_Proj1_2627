package dataStructures;

import dataStructures.exceptions.InvalidPositionException;
import dataStructures.exceptions.NoSuchElementException;

public class ArrayClass<E> implements TwoWayList<E> {
    private static final int SIZE = 50;

    /**
     * Array of elements E.
     */
    private E[] elems;

    /**
     * Number of elements on an array.
     */
    private int counter;

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayClass() {
        elems = (E[]) new Object[SIZE];
        counter = 0;
    }

    /**
     *
     * @param dimension
     */
    @SuppressWarnings("unchecked")
    public ArrayClass(int dimension) {
        elems = (E[]) new Object[dimension];
        counter = 0;
    }

    @Override
    public TwoWayIterator<E> twoWayiterator() {
        return new ArrayIterator<>(elems, counter);
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(elems, counter);
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return elems[0];
    }

    @Override
    public E getLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return elems[counter - 1];
    }

    @Override
    public E get(int position) throws InvalidPositionException {
        if (position < 0 || position >= counter)
            throw new InvalidPositionException();
        return elems[position];
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < counter; i++) {
            if ((element == null && elems[i] == null) ||
                    (element != null && element.equals(elems[i]))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    @Override
    public void addLast(E element) {
        add(counter, element);
    }

    @Override
    public void add(int position, E element) throws InvalidPositionException {
        if(position < 0 || position > counter) throw new InvalidPositionException();

        if(counter == elems.length){
            resize();
        }

        for(int i = counter; i > position; i--){
            elems[i] = elems[i - 1];
        }

        elems[position] = element;
        counter++;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return remove(0);
    }

    @Override
    public E removeLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return remove(counter - 1);
    }

    @Override
    public E remove(int position) throws InvalidPositionException {
        if (position < 0 || position >= counter)
            throw new InvalidPositionException();

        E element = elems[position];

        for (int i = position; i < counter - 1; i++) {
            elems[i] = elems[i + 1];
        }
        counter--;
        elems[counter] = null;
        return element;
    }
    //TODO: Left as an exercise.
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newElems = (E[]) new Object[elems.length * 2];
        for (int i = 0; i < counter; i++) {
            newElems[i] = elems[i];
        }
        elems = newElems;
    }
}
