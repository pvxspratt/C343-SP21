import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

class NoSuchElementE extends Exception {}

public abstract class DequeueArray<E> {
    protected Optional<E>[] elements;
    protected int capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //

    @SuppressWarnings("unchecked")
    DequeueArray(int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, 1);
        Arrays.fill(elements, Optional.empty());
        capacity = 1;
        front = 0;
        back = 0;
        size = 0;
    }

    public int size () { return size; }

    // --- real work begins --- //

    /**
     * Adds the given elem to the front of the dequeue
     * If there is no room, grow the dequeue first
     */
    public void addFirst(E elem) {
        // TODO
        if(size == capacity) {
            this.grow();
        }
        elements[front] = Optional.ofNullable(elem);
        front = Math.floorMod(front - 1, capacity);
        size++;
            // you need ot grow the size by 1 -sophia
    }

    /**
     * Adds the given elem to the back of the dequeue
     * If there is no room, grow the dequeue first
     */
    public void addLast(E elem) {
        // TODO
        if(size == capacity) { // this should be size == capacity -sophia
            this.grow();
        }
        elements[back] = Optional.ofNullable(elem);
        back = Math.floorMod(back + 1, capacity);
        size++;
            // you need to grow the size by 1 here too -sophia
    }

    public E getFirst() throws NoSuchElementE {
        return elements[Math.floorMod(front+1,capacity)].orElseThrow(NoSuchElementE::new);
    }

    public E getLast() throws NoSuchElementE {
        //return null; // TODO
        return elements[Math.floorMod(back - 1, capacity)].orElseThrow(NoSuchElementE::new);
    }

    /**
     * Removes (and returns) the first element in the dequeue
     * If the dequeue is empty, throw an exception instead
     */
    public E removeFirst() throws NoSuchElementE {
        //return null; // TODO
        front = Math.floorMod(front + 1, capacity);
        E elem = elements[front].orElseThrow(NoSuchElementE::new);
        elements[front] = Optional.empty();
        // don't forget to update the size :D -sophia
        size--;
        return elem;
    }

    /**
     * Removes (and returns) the last element in the dequeue
     * If the dequeue is empty, throw an exception instead
     */
    public E removeLast() throws NoSuchElementE {
        //return null; // TODO
        back = Math.floorMod(back - 1, capacity);
        E elem = elements[back].orElseThrow(NoSuchElementE::new);
        elements[back] = Optional.empty();
        size--;
        // don't forget to update the size here too -sophia
        return elem;
    }

    // the following methods are for debugging and testing

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

    abstract void grow ();
}

// ---------------------------------------------------------

class DequeueArrayDouble<E> extends DequeueArray<E> {

    DequeueArrayDouble (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Grow the dequeue by doubling its size.
     * A new array is created, all the elements in the old array
     * are copied in the first half of the new array
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        // the logic for grow is the same for all of them the only thing that changes is the capacity
        // also be wary of the order that you update your class variables in
        // you'll need a new array instance of the new capacity to hold the original values in the order (front -> back)
        // love, sophia
        Optional<E>[] deuce = (Optional<E>[]) Array.newInstance(Optional.class, this.capacity * 2);
        Arrays.fill(deuce, Optional.empty());

        // iterate through the old elements/array and bring those elements into the new one created in the correct order
        // front to back
        for(int i = 0; i < capacity; i++) {
            front = Math.floorMod(front + 1, capacity);
            deuce[i] = elements[front];
        }
        // update the values in your Dequeue
        elements = deuce;
        back = capacity;
        capacity = this.getCapacity() * 2;
        front = capacity - 1;
    }
}

// ---------------------------------------------------------

class DequeueArrayOneAndHalf<E> extends DequeueArray<E> {

    DequeueArrayOneAndHalf (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Grow the dequeue by multiplying its size by 1.5 and rounding
     * A new array is created, all the elements in the old array
     * are copied in the first half of the new array
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        // hint use Math.round(1.5f * capacity); the float is very important so you don't get mismatched data types
        Optional<E>[] oP5 = (Optional<E>[]) Array.newInstance(Optional.class, Math.round(1.5f * capacity));
        Arrays.fill(oP5, Optional.empty());

        // iterate through the old elements/array and bring those elements into the new one created in the correct order
        // front to back
        for(int i = 0; i < capacity; i++) {
            front = Math.floorMod(front + 1, capacity);
            oP5[i] = elements[front];
        }
        // update the values in your Dequeue
        elements = oP5;
        back = capacity;
        capacity = Math.round(1.5f * capacity);
        front = capacity - 1;
    }
}

// ---------------------------------------------------------

class DequeueArrayPlusOne<E> extends DequeueArray<E> {

    DequeueArrayPlusOne (int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Grow the dequeue by one
     * A new array is created, all the elements in the old array
     * are copied in the first part of the new array
     */
    @SuppressWarnings("unchecked")
    void grow() {
        // TODO
        Optional<E>[] invitedGuest = (Optional<E>[]) Array.newInstance(Optional.class, Math.round(capacity + 1));
        Arrays.fill(invitedGuest, Optional.empty());

        // iterate through the old elements/array and bring those elements into the new one created in the correct order
        // front to back
        for(int i = 0; i < capacity; i++) {
            front = Math.floorMod(front + 1, capacity);
            invitedGuest[i] = elements[front];
        }
        // update the values in your Dequeue
        elements = invitedGuest;
        back = capacity;
        capacity = Math.round(capacity + 1);
        front = capacity - 1;
    }
}
