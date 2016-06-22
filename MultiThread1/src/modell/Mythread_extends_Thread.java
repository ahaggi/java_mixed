package modell;
/** extends Thread is not recommended because we lose inheritance benefits*/
public class Mythread_extends_Thread extends Thread { //OBS extends
	@Override
	public void run(){
		Thread.currentThread().setName("child thread");

		for (int i = 0; i < 10; i++) {
			System.out.println("This line executed by: "+Thread.currentThread().getName()+" , priority is "+Thread.currentThread().getPriority());
		}
	}
}