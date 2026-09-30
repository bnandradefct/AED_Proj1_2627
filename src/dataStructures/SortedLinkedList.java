package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Sorted linked list Implementation
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class SortedLinkedList<E> extends LinkedList<E> implements SortedList<E> {
    /**
     * Comparator of elements.
     */
    private final Comparator<E> comparator;
    /**
     * Constructor of an empty sorted singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SortedLinkedList(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Returns the first element of the list.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMin( ) throws NoSuchElementException{
        //TODO: Left as an exercise.
        if(currentSize == 0){
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMax( ) throws NoSuchElementException{
        //TODO: Left as an exercise.
        if(currentSize == 0){
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }
    /**
     * Returns the first occurrence of the element equals to the given element in the list.
     * @return element in the list or null
     */
    @Override
    public E get(E element) {
        //TODO: Left as an exercise.
        Iterator<E> iterator = iterator();
        while(iterator.hasNext()){
            E elem = iterator.next();
            int comp = comparator.compare(elem, element);
            if(comp == 0){
                return elem;
            } else if(comp > 0){
                return null;
            }
        }
        return null;
    }
    /**
     * Returns true iff the element exists in the list.
     *
     * @param element to be found
     * @return true iff the element exists in the list.
     */
    public boolean contains(E element) {
        //TODO: Left as an exercise.
        Iterator<E> iterator = iterator();
        while(iterator.hasNext()){
            E elem = iterator.next();
            int comp = comparator.compare(elem, element);
            if(comp == 0){
                return true;
            } else if(comp > 0){
                return false;
            }
        }
        return false;
    }

    /**
     * Inserts the specified element at the list, according to the comparator order.
     * If there is an equal element, the new element is inserted after it.
     * @param element to be inserted
     */
    public void add(E element) {
        //TODO: Left as an exercise.
        if(isEmpty() || comparator.compare(element, head.getElement()) < 0){
            addFirst(element);
            return;
        }

        if(comparator.compare(element, tail.getElement()) >= 0){
            addLast(element);
            return;
        }

        LinkedNode<E> current = head;
        while(current.getNext() != null && comparator.compare(element, current.getNext().getElement()) >= 0){
            current = current.getNext();
        }

        addBeforeNode(element, current);

    }
    /**
     * Inserts the element before node after.
     * Precondition: after is not the head of the list.
     * @param element - Element to be inserted
     * @param before - Node to be previous to the new node
     */
    void addBeforeNode(E element, LinkedNode<E> before){
        //TODO: Left as an exercise.
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        newNode.setNext(before.getNext());
        before.setNext(newNode);
        currentSize++;
    }
    /**
     * Inserts the element at the first position in the list.
     * @param element - Element to be inserted
     */
    void addFirst( E element ) {
        //TODO: Left as an exercise.
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        if(isEmpty()){
            tail = newNode;
        } else{
            newNode.setNext(head);
        }
        head = newNode;

        currentSize++;
    }

    /**
     * Inserts the element at the last position in the list.
     * @param element - Element to be inserted
     */
    void addLast( E element ) {
        //TODO: Left as an exercise.
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        if(isEmpty()){
            head = newNode;
        } else{
            tail.setNext(newNode);
        }
        tail = newNode;
        currentSize++;
    }

    /**
     * Removes and returns the first occurrence of the element equals to the given element in the list.
     * @return element removed from the list or null if !belongs
     */
    public E remove(E element) {
        //TODO: Left as an exercise.
        if(isEmpty()){
            return null;
        }

        if (comparator.compare(element, head.getElement()) == 0) {
            E removed = head.getElement();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            currentSize--;
            return removed;
        }

        LinkedNode<E> current = head;
        while (current.getNext() != null && comparator.compare(element, current.getNext().getElement()) >= 0) {
            if (comparator.compare(element, current.getNext().getElement()) == 0) {
                LinkedNode<E> target = current.getNext();
                current.setNext(target.getNext());
                if (target == tail) {
                    tail = current;
                }
                currentSize--;
                return target.getElement();
            }
            current = current.getNext();
        }

        return null;
    }

    

    void addElem(E element){
        //TODO: Left as an exercise.
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        LinkedNode<E> current = head;
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        } else{
            while(current.getNext()!=null && comparator.compare(element, current.getElement()) <= 0){
                if(comparator.compare(element, current.getNext().getElement()) == 0){
                    LinkedNode<E> next = current.getNext();
                    current.setNext(newNode);
                    newNode.setNext(next);
                } else{
                    current = current.getNext();
                }
            }
        }
        currentSize++;
    }


    private boolean invariant() {
        //TODO: Left as an exercise.
        return true;
    }

    void writeData(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // write the normal attributes
    }
    void readData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // read the normal attributes
    }
}
