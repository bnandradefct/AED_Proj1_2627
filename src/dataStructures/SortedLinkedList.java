package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Sorted linked list Implementation
 * @author AED Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 * Space complexity: O(n), where n is the number of elements in the list.
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
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    public SortedLinkedList(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Returns the first element of the list.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    public E getMin() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    public E getMax() throws NoSuchElementException {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    /**
     * Returns the first occurrence of the element equals to the given element in the list.
     * @return element in the list or null
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty, the element is at the head, or the head element is greater than the searched element.
     * - Worst case: O(n) — when the element is at the tail or greater than all elements in the list.
     */
    @Override
    public E get(E element) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E elem = iterator.next();
            int comp = comparator.compare(elem, element);
            if (comp == 0) {
                return elem;
            } else if (comp > 0) {
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
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty, the element is at the head, or the head element is greater than the searched element.
     * - Worst case: O(n) — when the element is at the tail or greater than all elements in the list.
     */
    public boolean contains(E element) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E elem = iterator.next();
            int comp = comparator.compare(elem, element);
            if (comp == 0) {
                return true;
            } else if (comp > 0) {
                return false;
            }
        }
        return false;
    }

    /**
     * Inserts the specified element at the list, according to the comparator order.
     * If there is an equal element, the new element is inserted after it.
     * @param element to be inserted
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty, or the element is inserted before the head or after the tail.
     * - Worst case: O(n) — when the element is inserted in the middle, right before the tail.
     */
    public void add(E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
            currentSize++;
            return;
        }

        if (comparator.compare(element, head.getElement()) < 0) {
            newNode.setNext(head);
            head = newNode;
            currentSize++;
            return;
        }

        if (comparator.compare(element, tail.getElement()) >= 0) {
            tail.setNext(newNode);
            tail = newNode;
            currentSize++;
            return;
        }

        LinkedNode<E> current = head;
        while (current.getNext() != null && comparator.compare(element, current.getNext().getElement()) >= 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        currentSize++;
    }

    /**
     * Inserts the element before node after.
     * Precondition: after is not the head of the list.
     * @param element - Element to be inserted
     * @param before - Node to be previous to the new node
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    void addBeforeNode(E element, LinkedNode<E> before) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        newNode.setNext(before.getNext());
        before.setNext(newNode);
        if (before == tail) {
            tail = newNode;
        }
        currentSize++;
    }

    /**
     * Inserts the element at the first position in the list.
     * @param element - Element to be inserted
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    void addFirst(E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        currentSize++;
    }

    /**
     * Inserts the element at the last position in the list.
     * @param element - Element to be inserted
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    void addLast(E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        currentSize++;
    }

    /**
     * Removes and returns the first occurrence of the element equals to the given element in the list.
     * @return element removed from the list or null if !belongs
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty, the element is at the head, or the element is smaller than the head.
     * - Worst case: O(n) — when the element is at the tail or greater than all elements in the list.
     */
    public E remove(E element) {
        if (head == null) {
            return null;
        }

        // Remover da cabeca
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

    /**
     * Inserts the element at the list.
     * @param element - Element to be inserted
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(n)
     */
    void addElem(E element) {
        add(element);
    }

    /**
     * Checks the class invariant.
     * @return true if the invariant holds
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    private boolean invariant() {
        return true;
    }

    /**
     * Time complexity:
     * - Best case: O(1) — when the list is empty.
     * - Worst case: O(n) — where n is the number of elements in the list.
     */
    @java.io.Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeData(oos);
    }

    /**
     * Time complexity:
     * - Best case: O(1) — when the list is empty.
     * - Worst case: O(n) — where n is the number of elements deserialized (since elements are already sorted, each add at the tail takes O(1)).
     */
    @java.io.Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readData(ois);
    }

    /**
     * Serializes the list data into the output stream.
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty.
     * - Worst case: O(n) — where n is the number of elements in the list.
     */
    void writeData(ObjectOutputStream oos) throws IOException {

        oos.defaultWriteObject();

        oos.writeInt(currentSize);

        LinkedNode<E> current = head;
        while (current != null) {
            oos.writeObject(current.getElement());
            current = current.getNext();
        }
    }

    /**
     * Deserializes the list data from the input stream.
     * 
     * Time complexity:
     * - Best case: O(1) — when the list is empty.
     * - Worst case: O(n) — where n is the number of elements deserialized (each add at the tail executes in O(1)).
     */
    @SuppressWarnings("unchecked")
    void readData(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        ois.defaultReadObject();

        int size = ois.readInt();

        head = null;
        tail = null;
        currentSize = 0;

        for (int i = 0; i < size; i++) {
            E element = (E) ois.readObject();
            add(element);
        }
    }

}
