package no.hib.dat100.runtimeeks;

import javax.swing.JOptionPane;

public class HastighetException {

	public static void main(String[] args) {
		System.out.println("Starter main().");

		try {
			String kmstr = JOptionPane.showInputDialog("Avstand i kilometer");
			int km = Integer.parseInt(kmstr); // konverter streng til heltall

			String tmstr = JOptionPane.showInputDialog("Tid i timer");
			int tm = Integer.parseInt(tmstr); // konverter streng til heltall

			skrivHastighet(km, tm);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ulovlig input: " + e.getMessage());
		}

		System.out.println("Returnerer fra main().");
	}

	private static void skrivHastighet(int kilometer, int timer) {

		System.out.println("Starter skrivHastighet().");

		try {
			int hastighet = beregnHastighet(kilometer, timer);

			JOptionPane.showMessageDialog(null, "Hastighet: " + kilometer + "/" + timer + " = " + hastighet);
		} catch (ArithmeticException e) {
			JOptionPane.showMessageDialog(null, "0 ikke lovlig :" + e.getMessage());
		}

		System.out.println("Returnerer fra skrivHastighet().");
	}

	private static int beregnHastighet(int avstand, int tid) {

		System.out.println("Start beregnhastighet().");

		int hastighet = avstand / tid;

		System.out.println("Returnerer beregnHastighet().");

		return hastighet;
	}
}
