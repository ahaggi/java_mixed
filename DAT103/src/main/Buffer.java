package main;

import java.util.Random;


/********************************************************************
	//
	// Representerer en tabell implementasjon av samling kّ.
	// front til kّen er alltid i posisjon 0.
	//********************************************************************/
public class Buffer<T>  {
	private static Random tilf= new Random();
	private final int STDK = 10;
	private int front, bak, antall;
	private T[] koe;

	/******************************************************************
Oppretter en tom kّ med standard stّrrelse.
 ******************************************************************/
public Buffer() {
	front= bak= antall = 0;
	koe = (T[]) new Object[STDK];
}

/******************************************************************
Oppretter en tom kّ med spesifisert kapasitet..
 ******************************************************************/
public Buffer (int startKapasitet){
	front=bak= antall = 0;
	koe = (T[])(new Object[startKapasitet]);
}

/******************************************************************
Legger til et spesifisert element bak i kّen, utvider hvis nّdvendig
 ******************************************************************/
public void innKoe(T element) {
//	if (antall() == koe.length){
//		utvid();
//	}
	koe[bak] = element; // bak er posisjonen til neste ledige
	bak=(bak+1) % koe.length;
	antall++;
}

/******************************************************************
Fjerner elementet foran i kّen.
 ******************************************************************/
public T utKoe() {
//	if (erTom())
//		throw new EmptyCollectionException("koe");

	T resultat = null;
	resultat = koe[front];
	koe[front]=null;
	front=(front+1)% koe.length;
	antall--;
	
	// trnger ikke å flytte elementene en plass frem 

	return resultat;
}

///******************************************************************
//utvider tabellen med ekstra plass (tabell.length*1.1)+1)
//legg merke til at hvis tabellen har 9 plasser,vil ikke skjer noe i tilfelle (koe.length*1.1)
//******************************************************************/
//
//private void utvid() {
//	T[] hjelpeTabell = (T[])(new Object[(int) ((koe.length*1.1)+1)]);//10% +1 i tilfelle tabellen er mindre enn 10
//	for (int indeks =0; indeks < antall; indeks++){
//		hjelpeTabell[indeks] = koe[(indeks+ front) % koe.length];
//	}
//	front = 0;
//	bak = antall;
//	koe = hjelpeTabell;
//	
//	}


/******************************************************************
retunerer den foerste elemen i koen
******************************************************************/
public T foerste() {
	if (erTom())
		throw new EmptyCollectionException("koe");

	
	return koe[front];
}

/******************************************************************
sjekker om tabellen er tom
******************************************************************/
public boolean erTom() {
	return (antall==0);
}
/******************************************************************
sjekker om tabellen er full
******************************************************************/
public boolean erFull() {
	return (antall() == koe.length);
}

/******************************************************************
retunerer antall elementer
******************************************************************/
public int antall() {
	return antall;
}


 public T fjernTilfeldig() {
	
	T svar = null;
	if (erTom())
		throw new EmptyCollectionException("koe");

		int indeks = tilf.nextInt(antall);
		System.out.println("\n tf "+indeks);
		int riktigNdx =(indeks+front) % koe.length;
		svar = koe[riktigNdx];
	
	return fjern(svar);
//	T svar = null;
//	if (antall>0){
//		int indeks = tilf.nextInt(antall);
//		int riktigNdx =(indeks+front) % koe.length;
//		svar = koe[riktigNdx];
//
//		//flytter alle elementer
//		for (int i = indeks; i < antall; i++)
//			koe[(i+front) % koe.length] = koe[(i+front+1) % koe.length ];
//		
//		koe[bak] = null; //kan ha med, men ikke nødvendig
//		
//		bak=(bak+koe.length -1)%koe.length;
//		antall--;
//	}
//	return svar;
}

 public T fjern(T element) {

	int pos = -1;
	T svar= null;
	
	if (erTom())
		throw new EmptyCollectionException("koe");
	
	for(int i = 0; (i<antall); i++){
		int hNdx = (front+i) % koe.length;
		if(koe[hNdx].equals(element) && pos == -1){
			pos = hNdx;
			svar=koe[pos];
		}
		if(pos != -1){
			koe[hNdx]=koe[(hNdx+1) % koe.length];
		}

	}
	if(pos != -1){
		bak=(bak + koe.length -1) % koe.length;
		antall--;
	}

	return(svar);



	//	int pos = -1;
	//	T svar= null;
	//	if (!erTom()) {
	//		for(int i = 0; (i<antall)&&(pos==-1); i++){
	//			if(koe[(i+front)%koe.length].equals(element))
	//				pos = (i+front)%koe.length;
	//		}
	//
	//		if (pos!=-1) { // funnet, skal så slette
	//			svar=koe[pos];
	//
	//			//flytter alle elementer
	//			for (int i = (pos+koe.length-front)%koe.length; i < antall-1; i++)
	//				koe[(i+front) % koe.length] = koe[(i+front+1) % koe.length ];
	//			
	//			bak=(bak+koe.length -1)%koe.length;
	//			antall--;
	//
	//
	//		}
	//
	//	}
	//	return(svar);
}

 public boolean inneholder(T element) {
	int pos = -1;
	for(int i = 0; (i<antall)&&(pos==-1); i++){
		if(koe[(i+front)%koe.length].equals(element))
			pos = (i+front)%koe.length;
	}
	return(pos!=-1);
}

 }