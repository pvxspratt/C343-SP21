class EmptyQueueE extends Exception {}

abstract class Queue<E> {
    abstract void enqueue (E elem);
    abstract void dequeue () throws EmptyQueueE;
    abstract E getFront () throws EmptyQueueE;
}

// ----------------------------------------------------------------------

class SlowQueue<E> extends Queue<E> {
    // Our queue is represented by an object of our Stack class in Stack.java
    // So, for clarification, whenever the method docs ask to "modify our queue",
    // we are actually modifying this object.
    // Read Stack.java please:)
    private Stack<E> stack;

    /**
     * This initializes our private var "stack" to be a new EmptyS (see -Stack.java-)
     */
    SlowQueue () { stack = new EmptyS<>(); }

    /**
     * Although the method itself does not return anything, enqueue modifies our queue by adding given element to the
     * end. Hint: look in -Stack.java-
     *
     * @param elem a generic element to be added to the queue
     */
    void enqueue(E elem) {
        // TODO
        stack = stack.addLast(elem);
    }

    /**
     * This method should remove the first element of our queue. Hint: look in -Stack.java-.
     *
     * @throws EmptyQueueE in case the queue is empty (throws what and why)
     */
    void dequeue() throws EmptyQueueE {
        // TODO
        try {
            stack = stack.pop();
        } catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }

    /**
     * getFront returns an element of **generic type**.
     * This method returns the first element of our queue by using -Stack.java-'s getFront()
     *
     * @return the removed first element of out queue, E
     * @throws EmptyQueueE????? (throws what and why)
     */
    E getFront() throws EmptyQueueE {
        try { return stack.getTop(); }
        catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }
}

// ----------------------------------------------------------------------

class AmortizedQueue<E> extends Queue<E> {
    private Stack<E> front, back;
    // enqueue in front; dequeue from back

    /**
     * This method initializes our private vars "front" and "back" to be
     * a new EmptyS (see -Stack.java-)
     */
    AmortizedQueue () {
        front = new EmptyS<>();
        back = new EmptyS<>();
    }

    /**
     * Although the method itself does not return anything, enqueue modifies our queue by calling on
     * -Stack.java-'s push() on our front stack.
     *
     * @param elem a generic element to be added to the queue. we do this on front because we are enqueueing
     */
    void enqueue(E elem) {
        front = front.push(elem);
    }

    /**
     * This method should remove the first element of our queue. What we mean by "first" here is based off of the
     * queue's FIFO (first-in-first-out) structure.
     * That is: we cannot simply just remove from our enqueued "front". Why?
     * In order to complete this method, transfer everything from our front to our back whenever back DOES NOT
     * contain any elements. Then, dequeue (remove).
     *
     * Think about what would happen if we moved everything from front to back on every dequeue call. Would this process
     * still be considered amortized? Would this process still be correct?
     *
     * Use methods in -Stack.java- to help you deal with front and back.
     *
     * @throws EmptyQueueE in case the queue or stack is empty????? (throws what and why)
     */
    void dequeue() throws EmptyQueueE {
        // TODO
        if(back.isEmpty()) {
            try {
                back.push(front.getTop());
            } catch (EmptyStackE e) {
                throw new EmptyQueueE();
            }
        }
    }

    /**
     * Returns the first element in our queue. Similar to dequeue with maintaining the
     * 2 stacks; the only thing that changes here is that we are not removing the top, but instead returning the element
     *
     * @return the removed first element of out queue, E
     * @throws EmptyQueueE in case the back is empty????? (throws what and why)
     */
    E getFront() throws EmptyQueueE {
        // TODO
        try {
            return front.getLast();
        } catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
        //return null;
    }
}