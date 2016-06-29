package utsyn;

import java.io.IOException;

import cd_arkive.CDarkiv;
import cd_arkive.CDarkiv_kjedet_stabel;
import easyIO.*;
import modell.CDarkivADT;

public class CDarkivKlient {
	public static void main(String[] args) throws IOException {
		
		CDarkivADT cda = new CDarkiv(10); 
		Meny meny = new Meny(cda);
		meny.start();
	}

}
	