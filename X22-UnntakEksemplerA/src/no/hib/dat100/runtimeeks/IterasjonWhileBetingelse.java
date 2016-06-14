package no.hib.dat100.runtimeeks;

public class IterasjonWhileBetingelse {

	static public void main (String[] args) {
		
		boolean[] tab = { false,false,true,false,false,false};
		
		// sjekk om alle elementer i tabellen er true
		boolean alletrue = true;
		
		int i = 0;
		while (alletrue && i<tab.length) {
			alletrue = alletrue || tab[i];
			i++;
		}
		
	    System.out.println(alletrue);
	}

}
