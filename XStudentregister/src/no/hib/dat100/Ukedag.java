package no.hib.dat100;

public enum Ukedag {
	mandag, tirsdag, onsdag, torsdag, fredag, lørdag, søndag;

	public static Ukedag finnDag(int nr) {//ble lest fra fil register.txt
		for (Ukedag dag : Ukedag.values()) {
			if (dag.ordinal() == nr) {
				return dag;
			}
		}
		return null; // feil dagnummer
	}

}// class
