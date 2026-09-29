package dataStructures;

/**
 * Implementation of Singly Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class SinglyLinkedList<E> extends SequenceLinkedList<E> {

    public SinglyLinkedList( ) {
        super();
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addFirst(E element) {
       //TODO: Left as an exercise.
        LinkedNode newNode = new SinglyListNode<>(element);
        super.addFirstNode(newNode);
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addLast(E element) {
       //TODO: Left as an exercise.
        LinkedNode newNode = new SinglyListNode<>(element);
        super.addLastNode(newNode);
    }

    void addMiddle(int position, E element) {
        //TODO: Left as an exercise.
        pairNode pair = getNodes(position);
        LinkedNode newNode = new SinglyListNode<>(element);
        newNode.setNext(pair.node());
        super.addMiddleNode(pair, newNode);
    }
}
