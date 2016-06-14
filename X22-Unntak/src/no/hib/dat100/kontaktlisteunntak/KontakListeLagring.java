package no.hib.dat100.kontaktlisteunntak;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import no.hib.dat100.kontakter.KontaktInfo;
import no.hib.dat100.kontakter.KontaktListe;

public class KontakListeLagring extends KontaktListe {

	public void skrivTilFil(String filnavn) {
		
		// TODO - START
		
		try {
			
		// �pne filen (PrintWriter og FileWriter)
		PrintWriter f = new PrintWriter (new FileWriter(filnavn));
		
		// skriv ut antall poster f�rst - size metode p� HashMap
		f.println(kontakter.size());
		
		// iterer over hash map via keySet og get metodene p� HashMap og skrive ut til filen
		for (String key : kontakter.keySet()) {
			KontaktInfo ki = kontakter.get(key);
			f.println(key);
			f.println(ki.getType());
			f.println(ki.getNummer());
			
		}
		
		// lukk filen
		f.close();
		}
		
		// h�ndter/fange opp eventuelle expeptions
		catch (IOException exception) {
			JOptionPane.showMessageDialog(null,exception.getMessage());
		}
		
		// TODO - SLUTT
	}
}