package no.hib.dat100;

import easyIO.*;

public class Student {
	private final int STD = 4;
	private String navn;
	private int antKurs;
	private Kurs[] kurs; //her kan vi letter etter hvor mange kurs hver enkelt student tar

	Student() {
		kurs = new Kurs[STD];//oppretter en tabell med 4 cells
		navn = "";
		antKurs = 0;
	}

	Student(String navn) {
		kurs = new Kurs[STD];
		this.navn = navn;

	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getAntKurs() {
		return antKurs;
	}


	public boolean maksKurs() {
		return antKurs == kurs.length;
	}

	public void meldPåKurs(Kurs k) {
		kurs[antKurs] = k;
		antKurs++;
	}

	public void skrivTimeplan() {
		Out skjerm = new Out();
		skjerm.outln("Timeplan for " + navn);
		skjerm.outln();
		skjerm.out("tid/dag\t");
		for (Ukedag dag : Ukedag.values()) {
			skjerm.out(dag + "\t");//   \t = tab
		}
		skjerm.out("\n----------------------------------------------------------");
		skjerm.outln("----------------------------------------------------------");

		for (int tid = 8; tid < 22; tid++) {
			skjerm.out(tid, 6);
			skjerm.out("|\t");
			for (int i = 0; i < antKurs; i++) {
				if (kurs[i].getTid() == tid) {
					for (int j = 0; j < kurs[i].getDag().ordinal(); j++) {
						skjerm.out("\t");
					}
					skjerm.out(kurs[i]);
				}

			}
			skjerm.outln();
		}

	}//

}// class

