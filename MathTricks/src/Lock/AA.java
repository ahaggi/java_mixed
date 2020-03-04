package Lock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1964660975459341124L;
	private JPanel contentPane;
	private JButton hoyre;
	private JButton mid;
	private JButton venstre;
	private JButton reset;
	private  JTextArea antallTrykk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AA frame = new AA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mid = new JButton("3");
		mid.setBounds(168, 90, 89, 23);
		contentPane.add(mid);

		
		venstre = new JButton("3");
		venstre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValue(venstre, mid);
			}
		});
		venstre.setBounds(81, 90, 89, 23);
		contentPane.add(venstre);
		
		
		hoyre = new JButton("3");
		hoyre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValue(hoyre, mid);
			}
		});
		hoyre.setBounds(252, 90, 89, 23);
		contentPane.add(hoyre);

		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hoyre.setText("3");
				mid.setText("3");
				venstre.setText("3");
				antallTrykk.setText("0");
			}
		});
		reset.setBounds(81, 136, 89, 23);
		contentPane.add(reset);

		
		antallTrykk = new JTextArea("0");
		antallTrykk.setBounds(239, 21, 89, 23);
		contentPane.add(antallTrykk);
	}
	
	public void addValue(JButton a, JButton b){
		int x=Integer.valueOf(a.getText());
		int y =Integer.valueOf(b.getText());
		x=sjekk(x);
		y=sjekk(y);
		
		a.setText(""+x);
		b.setText(""+y);
		
		int antall=Integer.valueOf(antallTrykk.getText());
		antall++;
		antallTrykk.setText(""+antall);

	}
	
	public int sjekk(int tall){
		if (tall==3) {
			tall=1;
		}else{
			tall++;
		}
	return tall;

	}
}
