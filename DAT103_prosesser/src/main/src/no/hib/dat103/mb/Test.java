package no.hib.dat103.mb;

import java.util.Date;

/**
 * Viser bruk av meldinger.
 *
 */
public class Test {
    
    /**
     * Appen.
     * 
     * @param args ikke i bruk
     */
    public static void main(String[] args) {
        Channel<Date> mailBox = new MessageQueue<Date>();
        mailBox.send(new Date());

        Date rightNow = mailBox.receive();
        System.out.println(rightNow);
    }
}
