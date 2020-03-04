package no.hib.dat103.buffer;

/**
 * A simple program which tests the Buffer implementation.
 */
public class Test {
    
    /**
     * The app.
     * 
     * @param args not in use
     */
    public static void main(String[] args) {
        Buffer<String> buffer = new BufferImpl<String>();

        buffer.insert("Jay");
        buffer.insert("Pat");
        buffer.insert("Tom");

        String element = buffer.remove();
        System.out.println(element);
        System.out.println(buffer.remove());
        System.out.println(buffer.remove());
    }
}
