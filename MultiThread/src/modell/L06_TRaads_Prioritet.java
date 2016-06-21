package modell;

public class L06_TRaads_Prioritet  {

	public static void main(String[] args) {
		
		MyRunnable_implements_Runnable r = new MyRunnable_implements_Runnable();	
		Thread t1=new Thread(r); //OBS MÅ sende r som parameter
		t1.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("This line executed by: "+Thread.currentThread().getName());			
		}

	}
	
}

