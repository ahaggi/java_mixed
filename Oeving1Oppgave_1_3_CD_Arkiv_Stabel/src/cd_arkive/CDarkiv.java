package cd_arkive;

import easyIO.In;
import easyIO.Out;
import modell.CD;
import modell.CDarkivADT;
import modell.Sjanger;

public class  CDarkiv  implements CDarkivADT{

/*
* Data:
• En samling CD-er
Operasjoner:
• Legge inn en ny CD. Det er frivillig om du vil teste på om den ligger der fra før.
• Slette en CD
• Søker og henter CD-er med en gitt delstreng i tittelen
• Søker og henter artister med en gitt delstreng i tittelen
• Henter antall CD-er
*/

	private CD [] cd;
	private int maksAntallCDer;
	private int antall=0;
	In tastatur = new In();
	Out skjerm = new Out();

	public CDarkiv(){
		maksAntallCDer= 10;
		cd=new CD [maksAntallCDer];
	}
	public CDarkiv(int n){
		maksAntallCDer= n;
		cd=new CD [maksAntallCDer];
	}

	public CD[] hentCdTabell() {
		return cd;
	}

	@Override
	public void leggTilCd(CD nyCD){

		if (erLedigPlass()){

			if(finnCD_nr(nyCD.getCdNumer())==-1) {
				cd[antall]=nyCD;
				antall++;
			}else{
				skjerm.outln("Feil melding: CD Nr("+nyCD.getCdNumer()+"), er registrert fra før");
			}
		}else{
			//			System.out.println("Kan ikke legge til en ny CD fordi registeret er fult");
			utvidKapasitet();
			leggTilCd(nyCD);
		}

	}

	@Override
	public boolean slettCd(int cdNr){
		boolean b;

		int indx=finnCD_nr(cdNr);
		if (indx!=-1) {
			cd[indx]=cd[antall-1];
			cd[antall-1]=null;
			antall--;
			b=true;
		}else{
			b=false;
		}
		return b;
	}

	@Override
	public CD [] sokTittel(String cdTl){//TODO må teste null tabell

		CD hjelpeCD[]=null;
		int resAntall=0;

		for (int i = 0; i < antall; i++) {
			if (cd[i].getCdTittle().contains(cdTl.trim())) {resAntall++;} 
		}

		if (resAntall==0) {
			return hjelpeCD;//retunerer tom tabell
		}else{
			hjelpeCD=new CD[resAntall];
			int resIndx=0;
			for (int i = 0; i < antall; i++) {
				if (cd[i].getCdTittle().contains(cdTl.trim())) {
					hjelpeCD[resIndx]=cd[i];
					resIndx++;
				} 
			}
			return hjelpeCD;//retunerer full tabell
		}

	}

	@Override
	public CD [] sokArtist(String artNv){//TODO teste null tabell

		CD hjelpeCD[]=null;
		int resAntall=0;

		for (int i = 0; i < antall; i++) {
			if (cd[i].getArtistNavn().contains(artNv.trim())) {resAntall++;} 
		}
		
		if (resAntall==0) {
			return hjelpeCD;//retunerer tom tabell
		}else{
			hjelpeCD=new CD[resAntall];
			int resIndx=0;
			for (int i = 0; i < antall; i++) {
				if (cd[i].getArtistNavn().contains(artNv.trim())) {
					hjelpeCD[resIndx]=cd[i];
					resIndx++;
				}  
			}
			return hjelpeCD;//retunerer full tabell
		}

	}

	@Override
	public int hentAntall(){return antall;}//husk antall begynner fra 0


	@Override
	public int hentAntall(Sjanger sjanger){
		int resAntall=0;
		for (int i = 0; i < antall; i++) {
			if (cd[i].getSjange().equals(sjanger)) {resAntall++;} 
		}

		return resAntall;
	}

	@Override
	public int hentAntall(String sjanger){
		int resAntall=0;
		for (int i = 0; i < antall; i++) {
			if (cd[i].getSjange().equals(sjanger.trim())) {resAntall++;} 
		}

		return resAntall;
	}



	//3 hjelpe metoder

	private void utvidKapasitet(){
		CD[] hjelpetabell = new CD [(int)(cd.length*1.1)] ;
		for (int i = 0; i < cd.length; i++){
			hjelpetabell[i] = cd[i];
		}
		cd = hjelpetabell;
	}

	private int finnCD_nr(int cdNr){
		int cdPosisjon=-1;
		for (int i = 0; i < antall&& cdPosisjon==-1; i++) {
			if (cd[i].getCdNumer()==cdNr ) {cdPosisjon=i;}
		}
		return cdPosisjon;		
	}

	private boolean erLedigPlass(){
		return (antall<cd.length);
	}

	private String CDToString() {
		String s="";
		for (int i = 0; i < antall; i++) {
			s=s+cd[i].getCdTittle()+"\n";
		}
		return s;
	}






}
