package modell;

public class L02_brukAvRunnable  {

	public static void main(String[] args) {
		
		Thread.currentThread().setPriority(8);
		
		MyRunnable_implements_Runnable r = new MyRunnable_implements_Runnable();	
		Thread t1=new Thread(r); //OBS MÅ sende r som parameter

		//Thread.currentThread().setPriority(8); må bli definert før "new" linje, ellers den nye tråden vil få det gamle prioritet
		
		t1.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("This line executed by: "+Thread.currentThread().getName());			
		}

	}
	
}

