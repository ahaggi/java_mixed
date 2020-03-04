package main;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class KlientOppgave2 {

	public static void main(String[] args) {
		final int ANTALL = 10;
		Buffer<Integer> buffer = new Buffer<Integer>(ANTALL);

		Semaphore mutexSem = new Semaphore(1);

		//produsent har antall tillatelse lik "ANTALL", etterpå må den få ekstra tillatelse f.eks når konsument kalle produsentSem.release()
		Semaphore produsentSem = new Semaphore(ANTALL); 

		//konsument har antall tillatelse lik "0", den må få tillatelse f.eks når produsent kalle konsumentSem.release()
		Semaphore konsumentSem = new Semaphore(0);

		Produsent p = new Produsent(buffer, mutexSem, produsentSem, konsumentSem);
		Konsument k = new Konsument(buffer, mutexSem, produsentSem, konsumentSem);

		Thread t1 = new Thread(p);
		Thread t2 = new Thread(k);

		t1.start();
		t2.start();

	}

}

//class Semafor {
//	int s;
//
//	public Semafor(int antall) {
//		this.s = antall;
//	}
//
//	public void waitt() {
//		while (s <= 0){}
//			;
//		s--;
//	}
//
//	public void signal() {
//		s++;
//	}
//}

class Produsent implements Runnable {

	Buffer<Integer> buffer;
	Semaphore mutexSem;
	Semaphore produsentSem;
	Semaphore konsumentSem;

	public Produsent(Buffer<Integer> b, Semaphore m, Semaphore t, Semaphore f) {
		super();
		buffer = b;
		mutexSem = m;
		produsentSem = t;
		konsumentSem = f;
	}
	

	public void run() {
		while (true) {

			try {
				produsentSem.acquire();
				mutexSem.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Integer el = (new Random()).nextInt(200) + 1;
			buffer.innKoe(el);
			System.out.println("elementet " + el + ", er blitt produsert");

			mutexSem.release();
			konsumentSem.release();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

class Konsument implements Runnable {

	Buffer<Integer> buffer;
	Semaphore mutexSem;
	Semaphore produsentSem;
	Semaphore konsumentSem;

	public Konsument(Buffer<Integer> b, Semaphore m, Semaphore t, Semaphore f) {
		super();
		buffer = b;
		mutexSem = m;
		produsentSem = t;
		konsumentSem = f;
	}

	public void run() {
		while (true) {

			try {
				konsumentSem.acquire();
				mutexSem.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			Integer el = buffer.utKoe();
			System.out.println("elementet " + el + ", er blitt konsumert");
			System.out.println("Antall =" + buffer.antall());

			mutexSem.release();
			produsentSem.release();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		
	}
}

