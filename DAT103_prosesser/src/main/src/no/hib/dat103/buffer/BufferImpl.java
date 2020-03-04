package no.hib.dat103.buffer;

/**
 * En implementasjon av Buffer som viser bruk av delt hukommelse mellom produsent og konsument.
 *
 * @author Gagne, Galvin, Silberschatz Operating System Concepts with Java - Eighth Edition Copyright John Wiley & Sons
 *         - 2010.
 */
public class BufferImpl<E> implements Buffer<E> {
    private static final Integer BUFFER_SIZE = 5;
    private E[] elements;
    private Integer in;
    private Integer out;
    private Integer count;

    /**
     * Lager en ny buffer.
     */
    public BufferImpl() {
        count = 0;
        in = 0;
        out = 0;

        elements = (E[]) new Object[BUFFER_SIZE];
    }

    @Override
    public void insert(E item) {
        while (count == BUFFER_SIZE) {
            ; // do nothing -- no free space
        }

        // add an element to the buffer
        elements[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;
    }

    @Override
    public E remove() {
        E item;

        while (count == 0) {
            ; // do nothing - nothing to consume
        }

        // remove an item from the buffer
        item = elements[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;

        return item;
    }
}
