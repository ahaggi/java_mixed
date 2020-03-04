package magicSquere;


 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class FiveMagicBox extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8676592650951494887L;
	private JPanel contentPane;
	private JLabel [][] lbl;
	private int box [][]= new int[][] { {27 ,5 ,18 ,1 ,14},
										{3 ,11 ,29 ,7 ,15},
										{9 ,17 ,0 ,13 ,26},
										{10 ,28 ,6 ,19 ,2},
										{16 ,4 ,12 ,25 ,8}};
	private JTextField textField;
	JLabel lblNewLabel;

/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FiveMagicBox frame = new FiveMagicBox();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public FiveMagicBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(enter);
		btnNewButton.setBounds(292, 128, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.addActionListener(enter);
		textField.setBounds(295, 73, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Totalt: 65");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(49, 218, 121, 20);
		contentPane.add(lblNewLabel);
		
		
		lbl = new JLabel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				lbl[i][j] = new JLabel(""+box[i][j]);        
				lbl[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				lbl[i][j].setBounds(10+(j*40), 11+(i*40), 40, 40);
				lbl[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				lbl[i][j].setOpaque(true);
				if (i%2==0) {
					if (j%2!=0) {
						lbl[i][j].setBackground(Color.WHITE);
					}
				}else{
					if (j%2==0) {
						lbl[i][j].setBackground(Color.WHITE);
					}
				}
				contentPane.add(lbl[i][j]);
			}
		}// end for
		
	
		
	}
	
	Action enter = new AbstractAction() {
				
		/**
		 * 
		 */
		private static final long serialVersionUID = -1659365131717518851L;

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int nyTot=Integer.valueOf(textField.getText());
				calc(nyTot);
				textField.setText("");
				lblNewLabel.setText("Totalt: "+ nyTot);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Ulovlig input: " + e2.getMessage());
				textField.setText("");
			}

		}};

	
	
	public void calc(int nyTot){
		int x=nyTot - 65;//23
		int tilLegg= x/5;
		int spesialTilLegg=x%5; 
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				lbl[i][j].setText(box[i][j]+tilLegg+"");        
			}
		}// end for
		
		lbl[0][0].setText(box[0][0]+tilLegg+spesialTilLegg+"");        
		lbl[1][2].setText(box[1][2]+tilLegg+spesialTilLegg+"");        
		lbl[2][4].setText(box[2][4]+tilLegg+spesialTilLegg+"");        
		lbl[3][1].setText(box[3][1]+tilLegg+spesialTilLegg+"");        
		lbl[4][3].setText(box[4][3]+tilLegg+spesialTilLegg+"");        

	}
}
