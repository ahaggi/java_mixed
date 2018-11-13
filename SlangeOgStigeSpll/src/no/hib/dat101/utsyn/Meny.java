package no.hib.dat101.utsyn;

import java.util.Scanner;

import no.hib.dat101.utsyn.Tekstgrensesnitt;

/**
 * @author Peter Boer, Daniel Moberg og Abdella Haji
 *
 */
public class Meny {

	Tekstgrensesnitt tg = new Tekstgrensesnitt();
	Scanner tastatur = new Scanner(System.in);
	int valg = 0;

	/**
	 * Starter menyen til ansatte.
	 */
	public void start() {
		do {
			System.out.println("*************************************");
			System.out.println("Meny");
			System.out.println(
					"1. Lag og spill et nytt Spill OBS! hva skjer hvis vi sletter spillet som har id 1 fra DB!!");
			System.out.println("2. Spill fra et eksisterende brett");

			System.out.println("3. Exit");
			System.out.println("*************************************");

			valg = tastatur.nextInt();
			tastatur.nextLine();

			switch (valg) {
			case 1:
				tg.nySpill();
				break;
			case 2:
				tg.hentBrett();
				break;
			default:
				System.out.println("Lukker meny");
				break;
			}
		} while (valg != 3);
	}

	/**
	 * Lukker kobling til database.
	 */
	public void close() {
//		tg.close();
	}
}
