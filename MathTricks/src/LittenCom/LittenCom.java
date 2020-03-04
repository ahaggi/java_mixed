package LittenCom;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import magicSquere.FiveMagicBox;

public class LittenCom extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel[][] lbl;
	private JButton start;
	private JButton fram;
	private JButton bak;
	private JButton forkort;
 	private boolean[][] liste = { 
			{ true, false, false, false, true }, 
			{ false, true, true, true, false },
			{ false, true, true, true, false }, 
			{ true, false, false, false, true }, };
	private Koe koe= new Koe();

	private boolean[][] orig = { 
			{ true, false, false, false, true }, 
			{ false, true, true, true, false },
			{ false, true, true, true, false }, 
			{ true, false, false, false, true }, };

	public LittenCom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		start = new JButton("start på nytt");
		start.addActionListener(enter);
		start.setBounds(292, 128, 89, 23);
		contentPane.add(start);

		fram = new JButton("==>");
		fram.addActionListener(framListner);
		fram.setBounds(292, 228, 89, 23);
		contentPane.add(fram);

		bak = new JButton("<==");
		bak.addActionListener(bakListner);
		bak.setBounds(192, 228, 89, 23);
		contentPane.add(bak);

		forkort = new JButton("forkort..");
		forkort.addActionListener(forkortListner);
		forkort.setBounds(236, 260, 89, 23);
		contentPane.add(forkort);

 
		lbl = new JLabel[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				lbl[i][j] = new JLabel();
				lbl[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				lbl[i][j].setBounds(10 + (j * 40), 11 + (i * 40), 40, 40);
				lbl[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				lbl[i][j].setOpaque(true);
				if (liste[i][j])
					lbl[i][j].setBackground(Color.WHITE);

				contentPane.add(lbl[i][j]);
			}
		} // end for
		
	}

	void utfoerSteg(Steg steg) {

		int i = steg.getX() - 1;
		int j = steg.getY();
		if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
			liste[i][j] = liste[i][j] ^ true;
			lbl[i][j].setBackground(liste[i][j] ? Color.WHITE : Color.LIGHT_GRAY);

		}

		i = steg.getX();
		j = steg.getY() - 1;
		if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
			liste[i][j] = liste[i][j] ^ true;
			lbl[i][j].setBackground(liste[i][j] ? Color.WHITE : Color.LIGHT_GRAY);
		}

		i = steg.getX();
		j = steg.getY();
		if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
			liste[i][j] = liste[i][j] ^ true;
			lbl[i][j].setBackground(liste[i][j] ? Color.WHITE : Color.LIGHT_GRAY);
		}

		i = steg.getX();
		j = steg.getY() + 1;
		if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
			liste[i][j] = liste[i][j] ^ true;
			lbl[i][j].setBackground(liste[i][j] ? Color.WHITE : Color.LIGHT_GRAY);
		}

		i = steg.getX() + 1;
		j = steg.getY();
		if (i >= 0 && i < liste.length && j >= 0 && j < liste[i].length) {
			liste[i][j] = liste[i][j] ^ true;
			lbl[i][j].setBackground(liste[i][j] ? Color.WHITE : Color.LIGHT_GRAY);
		}

	}

	boolean erFerdig() {

		boolean b = true;

		boolean[][] listeRes = { 
				{ true, false, false, false, false }, 
				{ false, false, false, false, false },
				{ true, false, false, true, false }, 
				{ true, false, false, true, false }, 
				};

		for (int i = 0; i < liste.length && b; i++)
			for (int j = 0; j < liste[i].length && b; j++) //Arrays.deepEquals
				b = liste[i][j] == listeRes[i][j];

		return b;
	}

	Action enter=new AbstractAction(){@Override public void actionPerformed(ActionEvent e){
		start();
	}};
	Action framListner=new AbstractAction(){@Override public void actionPerformed(ActionEvent e){
		if (koe.getDenne()!=null && koe.getDenne().getNeste()!=null) {
 			koe.setDenne(koe.getDenne().getNeste());
 			utfoerSteg(koe.getDenne());
 		}
	}};
	Action bakListner=new AbstractAction(){@Override public void actionPerformed(ActionEvent e){
		if (koe.getDenne()!=null && koe.getDenne().getForrige()!=null) {
 			utfoerSteg(koe.getDenne());
			koe.setDenne(koe.getDenne().getForrige());
 		}
	}};
	Action forkortListner=new AbstractAction(){@Override public void actionPerformed(ActionEvent e){

		utfoerForkortelse();
		gjennomfoerForkortelse();
	}};


	void start() {
		for (int i = 0; i < liste.length; i++) {
			liste[i] = orig[i].clone();
			}
		
		Random r = new Random();
		boolean ferdig = false;
		for (int i = 0; i < 1000000 && !ferdig; i++) {
			
			int x = r.nextInt(4);
			int y = r.nextInt(5);

			Steg steg = new Steg(x,y);
			utfoerSteg(steg);
			
			boolean [][] naaVarendeListe = new boolean[4][5];
			for (int ii = 0; ii < liste.length; ii++) {
				naaVarendeListe[ii] = liste[ii].clone();
			}

			steg.setDaVaerndeliste(naaVarendeListe);
			koe.leggTil(steg);

			ferdig = erFerdig();
			System.out.println( i + " " + steg.getX() + ", " + steg.getY() + " , " + erFerdig());

		}
		
 		
	}

	public void utfoerForkortelse() {
		Steg steg = koe.getDenne().getForrige();
		Steg forgjenger = koe.getDenne().getForrige().getForrige();
		while (forgjenger != null) {
			if (Arrays.deepEquals(steg.getDaVaerndeliste(), forgjenger.getDaVaerndeliste())) {
 				forgjenger.foranderNeste(steg.getNeste());
				steg.getNeste().setForrige(forgjenger);
				steg = forgjenger;
			}
			forgjenger = forgjenger.getForrige();
		}
		System.out.println("ferdig!!!!");
	}
	
	public void gjennomfoerForkortelse() {
		for (int i = 0; i < liste.length; i++) {
			liste[i]=orig[i].clone();
			}
		
  		Steg denne = koe.getTopp();
 		int i = 0;
		while (denne != null) {
 			utfoerSteg(denne);			
 			System.out.println( i++ + " " + denne.getX() + ", " + denne.getY() + " , " + erFerdig());			
			denne = denne.getNeste();
		}		
	}

	public static void main(String[] args) {
		try {
			LittenCom littenCom = new LittenCom();
			littenCom.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

