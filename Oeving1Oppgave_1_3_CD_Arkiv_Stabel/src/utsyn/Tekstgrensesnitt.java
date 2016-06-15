package utsyn;

import easyIO.In;
import easyIO.Out;
import modell.CD;
import modell.CDarkivADT;
import modell.Sjanger;

public class Tekstgrensesnitt {

	String linje="*****************************************************************************";
	private In tastatur = new In();
	private Out skjerm = new Out();


	/** lese opplysningene om en CD fra tastatur**/
	public CD lesCD(){
		skjerm.outln("Tast inn CD nr");
		int cdNr=tastatur.inInt();

		skjerm.out("Tast inn Navn på artist/gruppe: ");
		String artn=tastatur.inLine();

		skjerm.out("Tast inn CD tittle: ");
		String cdt=tastatur.inLine();

		skjerm.out("Tast inn lanseringsÅr");
		int lAar=tastatur.inInt();

		skjerm.out("Tast inn sjange nr");
		int sjnr=tastatur.inInt();
		sjnr=testSjange(sjnr);
		Sjanger sj=  Sjanger.finnSjanger(sjnr); 

		skjerm.out("Tast inn navn på plateselskap: ");
		String ps= tastatur.inLine();
		
		CD nyCD=new CD(cdNr, artn, cdt, lAar, sj,ps);
		return nyCD;
	}
	public int testSjange(int sjnr){
		while(sjnr<0||sjnr>3){
			skjerm.out("Feil inndata: sjange nr er ett hel tall mellom å 0 og 3 \n"
					+ "Tast inn sjange nr");
			sjnr=tastatur.inInt();
		};
		return sjnr;
	}
	/** vise en CD med alle opplysninger på skjerm*/
	public void visCD(CD cd){
		skjerm.out(cd.getCdNumer(),10,3);
		skjerm.out("|");
		skjerm.out(cd.getArtistNavn(),15,3);
		skjerm.out("|");
		skjerm.out(cd.getCdTittle(),15,3);
		skjerm.out("|");
		skjerm.out(cd.getLansAar(),8,3);
		skjerm.out("|");
		skjerm.out(cd.getSjange()+"",8,3);
		skjerm.out("|");
		skjerm.out(cd.getPlateselskap(),15,3);
		skjerm.out("|");
		skjerm.outln();
		skjerm.outln(linje);

	}

	/** Skrive ut alle CD-er med en spesiell delstreng i tittelen*/
	public void skrivUtCdDelstrengITittel(CDarkivADT cda, String delstreng) {
		CD hjelpeTabell[]= cda.sokTittel(delstreng);
		if (hjelpeTabell!=null) {
			String melding="Det er "+ hjelpeTabell.length+" registrert CD-er med tittel: "+ delstreng;
			skjerm.outln(melding);
			skrivheader();
			
			for (CD cd : hjelpeTabell) {
				visCD(cd);
			}
			
		}else{
			skjerm.outln(linje);
			String melding="Det er ikke registrert noen CD med tittel: "+ delstreng;
			skjerm.outln(melding);
			skjerm.outln(linje);
		}
	}

	/** Skriver ut alle CD-er av en artist / en gruppe*/
	public void skrivUtCdArtist(CDarkivADT cda, String delstreng) {
		CD hjelpeTabell[]= cda.sokArtist(delstreng);
		if (hjelpeTabell.length!=0) {
			String melding="Det er "+ hjelpeTabell.length+" registrert CD-er av artist/gruppe: "+ delstreng;
			skjerm.outln(melding);
			skrivheader();

			for (CD cd : hjelpeTabell) {
				visCD(cd);
			}
		}else{
			skjerm.outln(linje);
			String melding="Det er ikke registrert noen CD av artist/gruppe: "+ delstreng;
			skjerm.outln(melding);
			skjerm.outln(linje);

		}
	}

	/** Skrive ut en enkel statistikk som inneholder antall CD-er totalt
	 og hvor mange det er i hver sjanger*/
	public void skrivUtStatistikk(CDarkivADT cda) {		
		
		String lin="***********************************************************************";
		String lin2="******************************************************";

		skjerm.outln();
		skjerm.out("",15,3);
		skjerm.out("|");
		skjerm.out("Antall CD-er per sjange",54,3);
		skjerm.outln("|");
		
		skjerm.out("Antall CD-er",15,3);
		skjerm.out("|");
		
		skjerm.out(lin2);
		skjerm.outln("|");

		skjerm.out("",15,3);
		skjerm.out("|");
		skjerm.out("ROCK",10,3);
		skjerm.out("|");
		skjerm.out("POP",10,3);
		skjerm.out("|");
		skjerm.out("OPERA",10,3);
		skjerm.out("|");
		skjerm.out("CLASSIC",10,3);
		skjerm.out("|");
		skjerm.out("UKJENT",10,3);
		skjerm.outln("|");
		skjerm.outln(lin);
		
		skjerm.out(cda.hentAntall(),15,3);
		skjerm.out("|");
		skjerm.out(cda.hentAntall(Sjanger.ROCK),10,3);
		skjerm.out("|");
		skjerm.out(cda.hentAntall(Sjanger.POP),10,3);
		skjerm.out("|");
		skjerm.out(cda.hentAntall(Sjanger.OPERA),10,3);
		skjerm.out("|");
		skjerm.out(cda.hentAntall(Sjanger.CLASSIC),10,3);
		skjerm.out("|");
		skjerm.out(cda.hentAntall(Sjanger.UKJENT),10,3);
		skjerm.outln("|");
		skjerm.outln(lin);	
	}
	
	/** Skriver ut alle CD-er */
	public void skrivUtAlleCDer(CDarkivADT cda) {
		CD hjelpeTabell[]= cda.hentCdTabell();
		if (hjelpeTabell.length!=0) {
			String melding="Det er "+ cda.hentAntall()+" registrert CD-er";
			skjerm.outln(melding);
			skrivheader();

			for (CD cd : hjelpeTabell) {
				if (cd!=null) visCD(cd);
			}
		}else{
			skjerm.outln(linje);
			String melding="Det er ikke registerert noen CD-er i arkivet: ";
			skjerm.outln(melding);
			skjerm.outln(linje);

		}
	}

	
	// hjelpe metoder
	public void skrivheader(){		
		skjerm.outln();
		skjerm.out("CD-nr",10,3);
		skjerm.out("|");
		skjerm.out("Artist/Gruppe",15,3);
		skjerm.out("|");
		skjerm.out("Tittel",15,3);
		skjerm.out("|");
		skjerm.out("Lan år",8,3);
		skjerm.out("|");
		skjerm.out("Sjange",8,3);
		skjerm.out("|");
		skjerm.out("Plateselskap",15,3);
		skjerm.out("|");
		skjerm.outln();
		skjerm.outln(linje);
	}

	public String getLinje() {
		return linje;
	}

}//class