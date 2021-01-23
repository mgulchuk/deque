package edu.greenriver.dev;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Deque is a double ended queue.
 * @param <E> The type of the items in the queue
 */
public class Deque<E> implements Iterable<E> {

    // add your fields here and remove this comment

    // you will also likely need some helper classes
    //      a helper Node class (for a doubly linked list)
    //      a custom Iterator class (so that you can return it)
    // (Some people put their helper classes up top, some people like them
    //  at the bottom of the class.)
    private int numAddRemoves = 0;
    private Node pre;
    private Node post;
    private int size;

    private class Node{
        E item;
        Node next;
        Node prev;
    }

    /**
     * Construct an empty deque
     */
    public Deque() {
        // create two dummy nodes, one at pre, one at post, and link them together
        // remember that the dummy nodes will help us avoid a whole bunch of null checks in our implementation
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
        size = 0;
    }

    /**
     * Is the deque empty?
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return pre == null;
    }

    /**
     * Number of items on the deque
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Add item to the front
     * @param item the item to add
     */
    public void addFirst(E item) {
        numAddRemoves++;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = pre.next;
        newNode.prev = pre;
        pre.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    /**
     * Add item to the back
     * @param item the item to add
     */
    public void addLast(E item) {
        Node newNode = new Node();
        newNode.next = post;
        newNode.prev = post.prev;
        newNode.prev.next = newNode;
        post.prev = newNode;
        post.prev.item = item;
        size++;
    }

    /**
     * Remove and return the item from the front
     * @return the first item in the deque
     */
    public E removeFirst() {
        numAddRemoves++;
         E item = pre.next.item;
         pre.next = pre.next.next;
         pre.next.prev = pre;
         size--;
        return item;
    }

    /**
     * Remove and return the item from the back
     * @return the last item in the deque
     */
    public E removeLast() {
        E item = post.prev.item;
        post.prev = post.prev.prev;
        post.prev.next = post;
        size--;
        return item;
    }

    /**
     * Return an iterator over items in order from front to back
     * @return a front-to-back iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator{

        Node current = pre.next;
        int snapshotCount;


        public ListIterator(){
            snapshotCount = numAddRemoves;
        }

        @Override
        public boolean hasNext() {
            if(snapshotCount != numAddRemoves)
                throw new ConcurrentModificationException();
            return current.next != null;
        }

        @Override
        public Object next() {
            if(snapshotCount != numAddRemoves)
                throw new ConcurrentModificationException();
            E item = current.item;
            current = current.next;
            return item;
        }
    }
}
