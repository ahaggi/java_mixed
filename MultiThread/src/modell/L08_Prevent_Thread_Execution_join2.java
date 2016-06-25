package modell;


public class L08_Prevent_Thread_Execution_join2{

	public static void main(String[] args) throws Exception{

		Thread t = new Thread(new JoinDemo2(Thread.currentThread()));
		t.start();

		for(int i=0;i<5;i++){
			Thread.sleep(2000);
			System.out.println("Main Thread");
		}
	}
}


class JoinDemo2 implements Runnable {
	Thread main_Thread;
	public JoinDemo2(Thread m) {
		main_Thread=m;
	}
	public void run() {

		try	{
			main_Thread.join(); 
		}catch (InterruptedException e){}		


		for(int i=0;i<5;i++)
			System.out.println("Child Thread");

	}


} 
// If main thread calls join() on child thread object and child thread called
// join() on main thread object then both threads will wait for each other
// forever and the program will be hanged(like deadlock if a Thread class join()
// method on the same thread itself then the program will be hanged ).	