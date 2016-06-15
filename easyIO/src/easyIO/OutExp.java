package easyIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * <p>Klasse for enkel skriving til fil og skjerm. Tilbyr metoder for
 * formatering av tegn, tall og tekst. �nsket posisjonering angis
 * evt. med en av konstantene, hhv. <code>LEFT, RIGHT</code> eller
 * <code>CENTER</code>
 *
 * <p>Klassen benytter Format-klassen for formateringen. �nsker man
 * stringkonkatenering sammen med formatering av tekst m� man benytte
 * Format-klassen sammen med <code>out / outln </code> metodene.
 *
 * <p>Eksempler p� bruk:<br>
 * For skriving til fil:
 * <pre>
 * // Lager fil-objektet.
 * OutExp utfil = new OutExp("filnavn");
 *
 * // skriver ut et tall (123)
 * utfil.out(123);
 *
 * // skriver ut linjeskift
 * utfil.outln();
 *
 * // utskrift med linjeskift.
 * utfil.outln("En linje med tekst");
 *
 * // skriver ut desimaltallet 123.456 med to desimaler.
 * utfil.outln(123.456, 2);
 *
 * // som over, h�yrejustert p� 10 plasser.
 * utfil.outln(123.456, 2, 10);
 *
 * // Teksten skrives h�yrejustert p� 10 plasser.
 * utfil.outln("Til h�yre", 10, OutExp.RIGHT);
 *
 * // Lukker filen etter skriving.
 * utfil.close();
 * </pre>
 *
 * @see easyIO.Format
 * @author Forfatterne av "Rett p� Java"
 * @version 5.0. (april 2007)
 */
public class OutExp {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int CENTER = 3;
    protected PrintStream stdout; // settes i default konstrukt�r
    protected PrintWriter fout;   // settes i fil-konstrukt�r
    static final String versjon = "ver.3.2 - 2006-08-28";


    /**
     * Lager en skriver som er satt til � skrive til skjermen (samme
     * som {@link java.lang.System#out}).
     */
    public OutExp() {
        stdout = System.out;
    }


    /**
     * Lager en skriver som er satt til � skrive til en ny fil
     * med det oppgitte filnavnet. Evt. eksisterende fil blir skrevet
     * over.
     *
     * @param filnavn navn p� filen.
     * @see #OutExp(String, boolean)
     * @throws IOException hvis filen ikke kan opprettes.
     */
    public OutExp(String filnavn) throws IOException {
        this(filnavn, false);
    }

    /**
     * Konstrukt�r for oppretting av skriveobjekt til fil.
     * Hvis <code>append</code> er <code>true</code> f�yes utskriften
     * til slutt hvis filen allerede eksisterer (den skrives alts� ikke
     * over).
     *
     * @param filnavn navn p� filen.
     * @param append hvis <code>true</code> legges utskriften til slutt
     * i eksisterende fil.
     * @throws IOException hvis filen ikke kan opprettes.
     */
    public OutExp(String filnavn, boolean append) throws IOException {
        fout = new PrintWriter
        (new BufferedWriter(new FileWriter(filnavn, append)));
    }



    /**
     * Skriver ut et linjeskift
     */
    public void outln() {
        if (stdout != null) stdout.println();
        else fout.println();
    }

    /**
     * Skriver ut teksten <code>s</code>
     * @param s teksten som skal skrives ut.
     */
    public void out(String s) {
        if (stdout != null) stdout.print(s);
        else fout.print(s);
    }

    /**
     * Som {@link #out(String)} etterfulgt av linjeskift.
     * @param s teksten som skal skrives ut.
     */
    public void outln(String s) {
        out(s);
        outln();
    }

    /**
     * Skriver ut et tegn.
     * @param c tegnet som skal skrives ut.
     */
    public void out(char c) {
        if (stdout != null) stdout.print(c);
        else fout.print(c);
    }

    /**
     * Skriver ut et tegn, venstrejustert p�
     * <code>width</code> plasser.
     * @param c tegnet som skal skrives ut.
     * @param width bredden p� feltet.
     */
    public void out(char c, int width) {
        out(Format.alignLeft(c, width));
    }


    /**
     * Som {@link #out(char)} etterfulgt av linjeskift.
     * @param c tegnet som skal skrives ut.
     */
    public void outln(char c) {
        out(c);
        outln();
    }

    /**
     * Som {@link #out(char, int)}, etterfulgt av linjeskift.
     * @param c tegnet som skal skrives ut
     * @param width bredden p� feltet
     */
    public void outln(char c, int width) {
        outln(Format.alignLeft(c, width));
    }

    /**
     * Skriver ut et tegn, venstre- eller h�yre-justert eller
     * sentrert p� <code>width</code> plasser. Justering,
     * evt. sentrering angis med en av f�lgende konstanter:
     * <pre>
     * LEFT, RIGHT, CENTER
     * </pre>
     * @param c tegnet som skal skrives ut
     * @param width bredden p� feltet
     * @param ALIGN angir h�yre, venstre eller sentrert justering
     */
    public void out(char c, int width, final int ALIGN) {

        switch(ALIGN) {
        case LEFT: out(Format.alignLeft(c, width));
        break;
        case RIGHT: out(Format.align(c, width));
        break;
        case CENTER: out(Format.center(c, width));
        break;
        default :
            throw new IllegalArgumentException
            ("Ukjent konstant. ALIGN m� v�re enten LEFT, RIGHT" +
            ", eller CENTER");
        //break;
        }
    }


    /**
     * Som {@link #out(char, int, int)} etterfulgt av linjeskift.
     * @param c tegnet som skal skrives ut
     * @param width bredden p� feltet
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     */
    public void outln(char c, int width, final int ALIGN) {
        out(c, width, ALIGN);
        outln();
    }


    /**
     * Skriver ut et heltall.
     * @param i heltallet som skal skrives ut
     */
    public void out(int i) {
        if (stdout != null) stdout.print(i);
        else fout.print(i);
    }

    /**
     * Som {@link #out(int)} etterfulgt av linjeskift.
     * @param i heltallet som skal skrives ut.
     */
    public void outln(int i) {
        out(i);
        outln();
    }

    /**
     * Skriver ut et heltall, h�yrejustert p�
     * <code>width</code> plasser. Hvis oppgitt bredde er for liten
     * skrives det ut opptill tre prikker.
     * @param i heltallet som skal skrives ut.
     * @param width bredden p� feltet.
     */
    public void out(int i, int width) {
        out(Format.align(i, width));
    }

    /**
     * Som for {@link #out(int, int)}, etterfulgt av linjeskift.
     * @param i heltallet som skal skrives ut.
     * @param width bredden p� feltet.
     */
    public void outln(int i, int width) {
        outln(Format.align(i, width));
    }


    /**
     * Som for {@link #out(int, int)}. Siste parameter gir mulighet
     * for � velge enten h�yre-, venstre- eller sentrert justering.
     * @param i heltallet som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     */
    public void out(int i, int width, final int ALIGN) {
        switch(ALIGN) {
        case LEFT: out(Format.alignLeft(i, width));
        break;
        case RIGHT: out(Format.align(i, width));
        break;
        case CENTER: out(Format.center(i, width));
        break;
        default :
            throw new IllegalArgumentException
            ("Ukjent konstant. ALIGN m� v�re enten LEFT, RIGHT" +
            ", eller CENTER");
        //break;
        }
    }

    /**
     * som for {@link #out(int, int, int)} etterfulgt av linjeskift.
     * @param i heltallet som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     */
    public void outln(int i, int width, final int ALIGN) {
        out(i, width, ALIGN);
        outln();
    }


    /**
     * Som {@link #out(int)} men skriver ut et desimaltall.
     * @param d tallet som skal skrives ut.
     */
    public void out(double d) {
        if (stdout != null) stdout.print(d);
        else fout.print(d);
    }

    /**
     * Som {@link #outln(int)} men skriver ut desimaltall.
     * @param d tallet som skal skrives ut.
     */
    public void outln(double d) {
        out(d);
        outln();
    }

    /**
     * Skriver ut et desimaltall med <code>decimals</code>
     * antall desimaler.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @see Format#format
     */
    public void out(double d, int decimals) {
        out(Format.format(d, decimals));
    }

    /**
     * Som {@link #out(double, int)}, etterfulgt av linjeskift.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @see Format#format
     */
    public void outln(double d, int decimals) {
        outln(Format.format(d, decimals));
    }


    /**
     * Skriver ut et desimaltall h�yrejustert p�
     * <code>width</code> plasser med <code>decimals</code> siffer
     * etter komma. Hvis oppgit bredde er for liten, skrives det ut
     * opptill tre prikker.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @param width bredden p� feltet.
     * @see Format#format
     */
    public void out(double d, int decimals, int width) {
        out(Format.align(d, width, decimals));
    }


    /**
     * Som {@link #out(double, int, int)} etterfulgt av linjeskift.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @param width bredden p� feltet.
     * @see Format#format
     */
    public void outln(double d, int decimals, int width) {
        outln(Format.align(d, width, decimals));
    }


    /**
     * Som {@link #outln(double, int, int)}. Siste parameter gir mulighet
     * for � velge enten h�yre-, venstre- eller sentrert justering.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     * @see Format#format
     */
    public void out(double d, int decimals, int width, final int ALIGN) {
        switch(ALIGN) {
        case LEFT: out(Format.alignLeft(d, width, decimals));
        break;
        case RIGHT: out(Format.align(d, width, decimals));
        break;
        case CENTER: out(Format.center(d, width, decimals));
        break;
        default :
            throw new IllegalArgumentException
            ("Ukjent konstant. ALIGN m� v�re enten LEFT, RIGHT" +
            ", eller CENTER");
        //break;
        }
    }


    /**
     * Som {@link #out(double, int, int, int)} etterfulgt av linjeskift.
     * @param d tallet som skal skrives ut
     * @param decimals antall desimaler som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     * @see Format#format
     */
    public void outln(double d, int decimals, int width, final int ALIGN) {
        out(d, decimals, width, ALIGN);
        outln();
    }


    /**
     * Skriver ut teksten <code>s</code> h�yrejustert p�
     * <code>width</code> plasser. Hvis oppgitt bredde er for liten
     * skrives det ut optill tre prikker.
     * @param s teksten som skal skrives ut
     * @param width bredden p� feltet.
     */
    public void out(String s, int width) {
        out(Format.alignLeft(s, width));
    }



    /**
     * Som {@link #out(String, int)}, etterfulgt av linjeskift.
     * @param s teksten som skal skrives ut.
     * @param width bredden p� feltet.
     */
    public void outln(String s, int width) {
        outln(Format.alignLeft(s, width));
    }

    /**
     * Skriver ut en tekst med oppgitt Siste parameter gir mulighet
     * for � velge enten h�yre-, venstre- eller sentrert justering.
     * @param s teksten som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     */
    public void out(String s, int width , final int ALIGN) {
        switch(ALIGN) {
        case LEFT: out(Format.alignLeft(s, width));
        break;
        case RIGHT: out(Format.align(s, width));
        break;
        case CENTER: out(Format.center(s, width));
        break;
        default :
            throw new IllegalArgumentException
            ("Ukjent konstant. ALIGN m� v�re enten LEFT, RIGHT" +
            ", eller CENTER");
        //break;
        }
    }


    /**
     *  Som {@link #out(String, int, int)}, etterfulgt av linjeskift.
     * @param s teksten som skal skrives ut.
     * @param width bredden p� feltet.
     * @param ALIGN angir enten h�yre, venstre eller sentrert justering.
     */
    public void outln(String s, int width, final int ALIGN) {
        out(s, width, ALIGN);
        outln();
    }

    /**
     * Skriver ut <code>Object o</code> ved � kalle objektets
     * <code>toString()</code> metode.
     * @param o objektet som skal skrives ut.
     */
    public void out(Object o) {
        if (stdout != null) stdout.print(o);
        else fout.print(o);
    }

    /**
     * Som {@link #out(Object)} etterfulgt av linjeskift.
     * @param o objektet som skal skrives ut.
     */
    public void outln(Object o) {
        out(o);
        outln();
    }

    /**
     * Lukker fil etter skriving.
     */
    public void close() {
        if (stdout == null) fout.close();
    }


}




