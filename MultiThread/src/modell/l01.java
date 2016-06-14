package modell;

public class l01 {

	public static void main(String[] args) throws InterruptedException {

		Mythread t1 = new Mythread();
		t1.start();

//        Thread.sleep(4000);
        
		for (int i = 0; i < 10; i++) {
			System.out.println("main thread*******************************************");
			
		}
		

	}

	
	
	
	
	
	
}

class Mythread extends Thread {
	@Override
	public void run(){
		for (int i = 0; i < 10; i++) {
			System.out.println("child thread----------------------------------");
		}
	}
}