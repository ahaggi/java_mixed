package no.hib.dat103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Viser hvordan en prosess brukes i Java.
 */
public class OSProcess {

    /**
     * Hovedprogram.
     * 
     * @param args komandu som skal startes
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java OSProcess <command>");
            System.exit(1);
        }

        try {
            // args[0] is the command
            ProcessBuilder pb = new ProcessBuilder(args[0]);
            Process process = pb.start();

            // obtain the input and output streams
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        } catch (IOException e) {
            System.err.println("" + e);
            System.exit(2);
        }

    }
}
