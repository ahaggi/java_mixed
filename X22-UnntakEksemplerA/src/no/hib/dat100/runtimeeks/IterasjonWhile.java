package no.hib.dat100.runtimeeks;

public class IterasjonWhile {

	static public void main (String[] args) {
		
		char[] tab = { 'd','a','t','1','0','0'};
		
		// skriv alle tegn i tabellen ut et på hver linje
		int i = 0;
		while (i<tab.length) {
			System.out.println(tab[i] + " ");
		}
	}
}
