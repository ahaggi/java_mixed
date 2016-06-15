package utsyn;

import java.io.IOException;

import cd_arkive.CDarkiv;
import cd_arkive.CDarkiv_kjedet_stabel;
import easyIO.*;
import modell.CDarkivADT;

public class CDarkivKlient2 {
	public static void main(String[] args) throws IOException {
		
		CDarkivADT cda2 = new CDarkiv_kjedet_stabel(); 
		Meny meny = new Meny(cda2);
		meny.start();
	}

}
