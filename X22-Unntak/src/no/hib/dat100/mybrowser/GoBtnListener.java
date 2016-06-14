package no.hib.dat100.mybrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GoBtnListener implements ActionListener {

	private JTextArea webpagetextarea;
	private JTextField urltextfield;

	public GoBtnListener(JTextArea webpagetextarea, JTextField urltextfield) {
		this.webpagetextarea = webpagetextarea;
		this.urltextfield = urltextfield;
	}

	public void actionPerformed(ActionEvent e) {

		String urlstr = urltextfield.getText();
		String text = "";

		try {
			URL url = new URL(urlstr);
			
			Scanner in = new Scanner(url.openStream());
			
			// accumulate text received via the URL
			int i = 1;
			while (i <= MyBrowser.MAX_LINES && in.hasNextLine()) {
				
				String linje = in.nextLine();
				text = text + "\n" + linje;
				i++;
				
			}

			// put the text into the web text area
			webpagetextarea.setText(text);
			
			in.close();
			
		} catch (MalformedURLException exception) {
			webpagetextarea.setText("Feil format på URL: " + urlstr);
		} catch (IOException exception) {
			webpagetextarea.setText("Web siden kunne ikke åpnes.");
		}
		finally {
			// always clear the url text field after each "Go"
			urltextfield.setText("");
		}
	}
}
