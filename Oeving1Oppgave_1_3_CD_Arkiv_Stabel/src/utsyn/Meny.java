package utsyn;

import java.io.IOException;

import easyIO.In;
import easyIO.Out;
import modell.CD;
import modell.CDarkivADT;

public class Meny {

	private Tekstgrensesnitt tekstgr;
	private CDarkivADT cda;
	private Fil fil;
	Out skjerm= new Out();
	In tastValg=new In();
	public Meny(CDarkivADT cda){
		tekstgr = new Tekstgrensesnitt();
		fil = new Fil(cda);
		this.cda = cda;
	}
	
	public void start() throws IOException{
		
		//Oppretter CDarkiv først og leser fra fil
		int antallFør=cda.hentAntall();
		CDarkivADT cda=fil.lesFraFil("register.txt");
		int antallEtter=cda.hentAntall();
		int antallNye=antallEtter-antallFør;
		String melding2 ="Det er "+antallNye+" registrerte CD-er i arkivet";
		skjerm.outln(melding2);
		skjerm.outln(tekstgr.getLinje());
		//

		String meny = "\n" + "1 - legge inn nye CD \"les fra tastatur\". \n"
				+ "2 - Søk etter CD-er med en spesiell delstreng i tittelen.\n"
				+ "3 - Søk etter CD-er av en artist / en gruppe.\n"
				+ "4 - Skrive ut oversiktig tabell av registrerte CD-er sortert etter sjanger .\n"//TODO
				+ "5 - Skrive ut opplysninger til alle registerert CD-er .\n"
				+ "6 - lagre opplysninger til alle CD-er i filen.\n"
				+ "7 - slett en CD.\n"
				+ "8 - avslutter programmet.\n";

		String valg = "";

		do {
			
			skjerm.out(meny);
			valg = tastValg.inWord();

			switch (valg) {
			case "1":
				CD cd=tekstgr.lesCD();
				cda.leggTilCd(cd);		
				break;
			case "2":
				skjerm.outln("Tast inn tittelen pÃ¥ CD som du søker etter");
				String delstreng3=tastValg.inLine();
				tekstgr.skrivUtCdDelstrengITittel(cda, delstreng3);
				break;
			case "3":
				skjerm.outln("Tast inn navn pÃ¥ artisten / gruppen som du søker etter");
				String delstreng4=tastValg.inLine();
				tekstgr.skrivUtCdArtist(cda, delstreng4);
				break;
			case "4":
				tekstgr.skrivUtStatistikk(cda);
				break;
			case "5":
				tekstgr.skrivUtAlleCDer(cda);
				break;
			case "6":
				fil.skrivTilFil(cda, "register.txt", false);

				break;
			case "7":
				skjerm.outln("Tast inn nummeret pÃ¥ CD som skal slettes");
				int cdNr=tastValg.inInt();
				boolean b =cda.slettCd(cdNr);
				String melding="";
				if (b) {
					melding ="CD Nr: "+cdNr+", er slettet fra registret";
				}else{
					melding ="CD Nr: "+cdNr+", er ikke registrert i registret";
				}
				skjerm.outln(tekstgr.getLinje());
				skjerm.outln(melding);
				skjerm.outln(tekstgr.getLinje());
				break;

			default:
				break;
			}
				
			} while (!valg.equals("8"));

	}
}//class