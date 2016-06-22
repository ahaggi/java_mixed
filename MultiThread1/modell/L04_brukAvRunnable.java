package modell;

public class L04_brukAvRunnable  {

	public static void main(String[] args) {
		
		MyRunnable_implements_Runnable r = new MyRunnable_implements_Runnable();	
		String tNvn = "MyNewThread";
		String gNvn = "MyNewThread";
		ThreadGroup g =new ThreadGroup("");
		Long stackSize = (long) 100000000;
		
		Thread t1=new Thread(); 
		Thread t2=new Thread(r); //(Runnable r)
		Thread t3=new Thread(tNvn); //(String s) thread's name
		Thread t4=new Thread(r , tNvn);//(Runnable r , String tNvn) thread's name
		Thread t5=new Thread(g , gNvn);//(ThreadGroup g , String gNvn) Group's name
		Thread t6=new Thread(g , r);//(ThreadGroup g , Runnable r) 
		Thread t7=new Thread(g , r , tNvn);//(ThreadGroup g , Runnable r , String tNvn) thread's name
		Thread t8=new Thread(g , r , tNvn , stackSize);//(ThreadGroup g , Runnable r , String tNvn , Long stackSize) thread's name

		/**Legg merke til at vi kan også sende objekt som extends Thread klasse til annet thread-klasse-objekt*/
		Mythread_extends_Thread k1 = new Mythread_extends_Thread(); // vi kunne ==> k1.start()
		Thread t =new Thread(k1);
		t.start();
		
		

	}
	
}

