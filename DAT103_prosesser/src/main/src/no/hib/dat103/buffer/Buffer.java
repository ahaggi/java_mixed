package no.hib.dat103.buffer;

/**
 * Grensensitt for begrenset delt buffer mellom produsent og konsument.
 *
 * @author Gagne, Galvin, Silberschatz Operating System Concepts with Java - Eighth Edition Copyright John Wiley & Sons
 *         - 2010.
 */
public interface Buffer<E> {
    /**
     * Produsenter legger inn nye elementer med denne.
     *
     * @param item element
     */
    public void insert(E item);

    /**
     * Konsumenter henter elementer med denne.'
     *
     * @return element
     */
    public E remove();
}
