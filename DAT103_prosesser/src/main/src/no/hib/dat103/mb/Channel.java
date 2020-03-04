package no.hib.dat103.mb;

/**
 * Channel interface.
 *
 * @author Gagne, Galvin, Silberschatz Operating System Concepts with Java - Eighth Edition Copyright John Wiley & Sons
 *         - 2010.
 */
public interface Channel<E> {
    /**
     * Sender et produsert element.
     *
     * @param item element
     */
    public void send(E item);

    /**
     * Mottar et produsert element.
     *
     * @return element
     */
    public E receive();
}
