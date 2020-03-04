package no.hib.dat103.mb;

import java.util.Vector;

/**
 * An implementation of the Channel interface illustrating message passing.
 *
 * @author Gagne, Galvin, Silberschatz Operating System Concepts with Java - Eighth Edition Copyright John Wiley & Sons
 *         - 2010.
 */
public class MessageQueue<E> implements Channel<E> {
    private Vector<E> queue;

    /**
     * Constructs a new instance of MessageQueue.
     */
    public MessageQueue() {
        queue = new Vector<E>();
    }

    @Override
    public void send(E item) {
        queue.addElement(item);
    }

    @Override
    public E receive() {
        if (queue.size() == 0) {
            return null;
        } else {
            return queue.remove(0);
        }
    }
}
