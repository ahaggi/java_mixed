/**
 * 
 */
package utsyn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
//import no.hib.dat102.adt.*;

import modell.CD;
import modell.CDarkivADT;
import modell.Sjanger;

/**
 * @author Ole Olsen
 * 
 */
public class Fil {

	private static String SKILLE = " ";
	private CDarkivADT cda;

	
	public Fil(CDarkivADT cda){
		this.cda = cda;

	}

	/**
	 * @param filnavn
	 * @return Referansen til CD-arkivet
	 * @throws java.io.IOException
	 */
	public CDarkivADT lesFraFil(String filnavn) throws java.io.IOException {
		

		try {
			// // 1 - FileReader
			FileReader ansFil = new FileReader(filnavn);

			// 2 - BufferedReader
			BufferedReader innfil = new BufferedReader(ansFil);

			// 4 - Les postene, en hel post om gangen
			String post = innfil.readLine();
			while (post!=null) {

			//	if (testInnput(post)) { //fÃ¸rst tester inndata som stÃ¥r pÃ¥ filen, etterpÃ¥ opprette CD objekt
					String[] felt = post.split(SKILLE);
					int nr = Integer.parseInt(felt[0]);
					String artist = felt[1];
					String tittel = felt[2];
					int år = Integer.parseInt(felt[3]);
					Sjanger sjanger = Sjanger.finnSjanger(Integer.valueOf(felt[4])); //Eventuelt lagret som heltall...MÙ‡ sÙ‡ konvertere til enum
					String plselskap = felt[5];

					CD cd = new CD(nr, artist, tittel, år, sjanger, plselskap);
					cda.leggTilCd(cd);    
			//	}
				post = innfil.readLine();

			}

			// 4 - Lukk filen
			innfil.close();

		} 
		catch (FileNotFoundException unntak) {//arver fra IOException må stå fÙ‘rst
			// hvis ikke vil unntaket for IOException skygge
			System.out.println("Finner ikke filen " + filnavn);
			System.exit(-1);
		} 
		catch (IOException e) {
			System.out.println("Feil ved lesing av fil: " + e);
			System.exit(1);
		}

		return cda;
	}

	
//	public boolean testInnput(String test){//skjekker om data som ble hentet fra filen er riktige for hvert linje
//
//		boolean riktig= false;
//		try {
//			String[] testFelt = test.split(SKILLE);
//			int nr = Integer.parseInt(testFelt[0]);
//			String artist = testFelt[1];
//			String tittel = testFelt[2];
//			int år = Integer.parseInt(testFelt[3]);
//			Sjanger sjanger = Sjanger.finnSjanger(Integer.valueOf(testFelt[4])); 
//			String plselskap = testFelt[5];
//			riktig= true;
//			
//		} catch (Exception e) {
//			System.out.println("Feil melding: fÙ‘lgende CD ble ikke registerert\n"+ test);
//			riktig = false;
//		}finally {
//			return riktig;
//		}
//
//		
//		
//	}
	public void skrivTilFil(CDarkivADT cdarkiv, String filnavn, boolean utvid) throws java.io.IOException {

		CD hjelpeTabell[]= cdarkiv.hentCdTabell();
		try {
			// // 1 - FileWriter
			FileWriter ansFil = new FileWriter(filnavn,false);

			// 2 - PrintWriter
			PrintWriter utfil = new PrintWriter(ansFil);

			if (hjelpeTabell.length!=0) {
				int cdNr,lansAar, sj;
				String artistNv,cdTittel,plSels;

				for (int i = 0; i < cdarkiv.hentAntall(); i++) {
					cdNr=hjelpeTabell[i].getCdNumer();
					artistNv=hjelpeTabell[i].getArtistNavn();
					cdTittel=hjelpeTabell[i].getCdTittle();
					lansAar=hjelpeTabell[i].getLansAar();
					sj=hjelpeTabell[i].getSjange().getNr();
					plSels=hjelpeTabell[i].getPlateselskap();
					utfil.println(cdNr+SKILLE+artistNv+SKILLE+cdTittel+SKILLE+lansAar+SKILLE+ sj+SKILLE+plSels); 
				}//for
			}//if

			// 4 - Lukk filen
			utfil.close();

		} 
		catch (FileNotFoundException unntak) {//arver fra IOException må stå fÙ‘rst
			// hvis ikke vil unntaket for IOException skygge
			System.out.println("Finner ikke filen " + filnavn);
			System.exit(-1);
		} 
		catch (IOException e) {
			System.out.println("Feil ved skriving på fil: " + e);
			System.exit(1);
		}

	}

}// class
