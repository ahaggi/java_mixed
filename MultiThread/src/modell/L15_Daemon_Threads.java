package modell;
/**
 * The Threads which are executing in the background are called daemon Threads.
 * The main objective of daemon Threads is to provide support for non-daemon Threads like main Thread.
 * Example:
 * Garbage collector
 * 
 * When ever the program runs with low memory the JVM will execute Garbage Collector to provide free memory. So that the main Thread can continue it's execution. 
 * 
 */
class DaemonDemo implements Runnable {

	public void run(){

		for(int i=0;i<10;i++){
			System.out.println("lazy thread");
			try
			{
				Thread.sleep(2000);
			}
			catch (InterruptedException e)
			{
				System.out.println("i got interrupted");
			}
		}
	}

} 

/*If we comment line 1 then both main & child Threads are non-Daemon , and hence both threads will 
 * be executed untill there completion.
 * 
 * If we are not comment line 1 then main thread is non-Daemon and child thread is Daemon. 
 * Hence when ever main Thread terminates automatically child thread will be terminated.
*/
public class L15_Daemon_Threads{

	public static void main(String[] args) throws InterruptedException{
		DaemonDemo d = new DaemonDemo();
		Thread t = new Thread(d);
		
		t.setDaemon(true); //--> 1
							
		t.start();
		
		System.out.println("end of main Thread");
	}
}