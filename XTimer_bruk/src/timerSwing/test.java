package timerSwing;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class test {
	static int i = 0;

	/************
	 * det virker med jframe OG UNDER DEBUG PROSPECTIVE  men kunne ikke finne ut hva som er feil her
	 **********/
	public static void main(String[] args) {
		System.out.println("1");
		iterate(500 , tull);
		System.out.println("2");
	}

	static Timer timer;

	public static void iterate(int miliseconds , Action name) {
		timer = new Timer(miliseconds, name);
		timer.setInitialDelay(miliseconds);
		timer.setDelay(miliseconds);
		timer.setRepeats(true);
		timer.start();
		System.out.println("3");
	}

	private static Action tull = new AbstractAction() {
		private static final long serialVersionUID = 7892201122490351698L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			i++;
			System.out.println("kjøring nr" + i);

			if (i >= 5) {
				timer.setRepeats(false);
			}
			System.out.println("sl" + i);

		}
	};

}
