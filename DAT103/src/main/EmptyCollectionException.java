package main;


//********************************************************************
//Representerer situasjonen når samlingen er tom.
//********************************************************************
public class EmptyCollectionException extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1631038779640627062L;

/******************************************************************
Unntak med passende melding
******************************************************************/
public EmptyCollectionException (String samling) {
super ("Samlingen " + samling + " er tom.");
}
}
