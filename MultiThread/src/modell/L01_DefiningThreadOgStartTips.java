package modell;

public class L01_DefiningThreadOgStartTips {

	public static void main(String[] args) throws InterruptedException {

		Mythread t1 = new Mythread();
		t1.start();
/** .start():
 * 1-registerer den nye thread på schedule
 * 2-perform all other mandatory activities
 * 3-invoke run method */
		
/**OBS vi kan bruke t1.run() uten start(), men da lager det ikke en ny tråd. 
 * den blir kjørt som vanlig obj	ekt-metode*/

/**ved kjøring av neste kode ,vil det skrive ut main thread/child thread  eller child thread/main thread
 * eller blanding*/

		
/**It is never legal to start a thread more than once.
 *  In particular, a thread may not be restarted once it has completed execution.*/

			for (int i = 0; i < 10; i++) {
				System.out.println("main thread00000000000000000000000000000000000000");			
			}
	}
}
/** extends Thread is not recommended because we lose inheritance benefits*/
class Mythread extends Thread { //OBS extends
	@Override
	public void run(){
		for (int i = 0; i < 10; i++) {
			System.out.println("child thread--------------------------------------");
		}
	}
}