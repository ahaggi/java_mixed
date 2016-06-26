package modell;


/**Even though we declared wish() method as synchronized but we will get irregular output in this case, 
 * because both Threads are operating on different objects.

 * Conclusion: If multiple threads are operating on multiple objects then there is no impact of Synchronization.
 * If multiple threads are operating on same java objects then synchronized concept is required(applicable). 
*/


public class L11_Prevent_Thread_Execution_Interrupting2{

	public static void main(String[] args) throws Exception{
		
		Display d1=new Display();// 2 ulike  object
		Display d2=new Display();
		Thread t1 = new Thread(new ThreadDemo(d1,"dhoni"));
		Thread t2 = new Thread(new ThreadDemo(d2,"yuvaraj"));// 2 ulike  object
		t1.start();
		t2.start();
	}
}

	