package no.hib.dat101.utsyn;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
public class Klient {

	public static void main(String[] args) {

		/**
		 * Starter spill meny.
		 * 
		 * @param args
		 */

		Meny meny = new Meny();

		meny.start();
		meny.close();
	}
}
