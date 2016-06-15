package modell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import easyIO.*;

public class AnsatteRegisteret {

	public static void main(String[] args) {

		HashMap<Integer, Ansatte> register = new HashMap<Integer, Ansatte>();

		Out skjerm = new Out();
		Random r = new Random();

		for (int i = 0; i < 10; i++) {
			String n = ((char) (97 + i)) + "";
			double tl = ((r.nextDouble() * 100) + 180);
			Ansatte a = (i % 2 == 0) ? new HT_ansatte(n, tl) : new DT_ansatte(n, tl);
			register.put(i, a);
		}

		skjerm.out("Navn", 5, 3);
		skjerm.out("|");
		skjerm.out("Type", 10, 3);
		skjerm.out("|");
		skjerm.out("Lønn", 10, 3);
		skjerm.out("|");
		skjerm.out("KredittVerdig", 12, 3);
		skjerm.out("|");
		skjerm.outln();

		Iterator it = register.values().iterator();

		while (it.hasNext()) {
			Ansatte a = (Ansatte) it.next();
			skjerm.out(a.navn, 5, 3);
			skjerm.out("|");

			boolean erHT = (a instanceof KredittVerdig);//OBS her!! mhm

			String type = (erHT) ? "HelTid" : "DelTid";
			skjerm.out(type, 10, 3);
			skjerm.out("|");

			skjerm.out(a.beregnLoenn(), 2, 10, 3);
			skjerm.out("|");
			
			double kv = 0 ;
			if (erHT) {
				KredittVerdig k = (KredittVerdig) (a); //OBS 
				kv = k.beregnKreditt() ;
			}

			skjerm.out(kv, 2 ,12, 3);
			skjerm.outln("|");
		}

	}

}
