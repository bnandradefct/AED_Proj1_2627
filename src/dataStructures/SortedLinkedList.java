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
     */
    public void add(E element) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);

        // 1. Caso lista vazia
        if (head == null) {
            head = newNode;
            tail = newNode;
            currentSize++;
            return;
        }

        // 2. Inserir antes da cabeca
        if (comparator.compare(element, head.getElement()) < 0) {
            newNode.setNext(head);
            head = newNode;
            currentSize++;
            return;
        }

        // 3. Inserir depois da cauda (ou igual a cauda)
        if (comparator.compare(element, tail.getElement()) >= 0) {
            tail.setNext(newNode);
            tail = newNode;
            currentSize++;
            return;
        }

        // 4. Inserir no meio da lista
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

    void addElem(E element) {
        add(element);
    }

    private boolean invariant() {
        return true;
    }

    void writeData(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    void readData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }
}
