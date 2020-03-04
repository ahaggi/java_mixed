package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class KlientOppgave3 {

	public static void main(String[] args) {

		ArrayList<Integer> liste = new ArrayList<Integer>();
		liste.add(0);
		liste.add(1);
		liste.add(2);
		liste.add(3);

		//Leseren har antall tillatelse lik "1", etterpå må den få ekstra tillatelse f.eks når Skriveren kalle r_mutex.release()
		Semaphore r_mutex = new Semaphore(1);

		//Skriveren har antall tillatelse lik "1", etterpå må den få ekstra tillatelse f.eks når Skriveren kalle rw_mutex.release()
		Semaphore rw_mutex = new Semaphore(1);


		Skriveren sk = new Skriveren(liste, rw_mutex);
		Leseren ls = new Leseren(liste, r_mutex, rw_mutex);

		Thread t1 = new Thread(sk);
		Thread t2 = new Thread(ls);

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

class Skriveren implements Runnable {

	ArrayList<Integer> liste;
	Semaphore rw_mutex;

	public 	Skriveren(ArrayList<Integer> l, Semaphore rw) {
		super();
		liste = l;
		rw_mutex = rw;
	}


	public void run() {
		do {
			try {
				rw_mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//			...
			/* writing is performed */
			int ind = (new Random()).nextInt(liste.size());
			ind = (ind<0) ? 0: ind;
			int temp = liste.get(ind);
			temp++;
			liste.set(ind, temp);
			System.out.println("Skriveren skriver på indeks: "+ ind+" , verdi:"+liste.get(ind));
			//			...
			rw_mutex.release();
		} while (true);	}
}

class Leseren implements Runnable {

	ArrayList<Integer> liste;
	Semaphore rw_mutex;
	Semaphore r_mutex;
	int read_count;


	public 	Leseren(ArrayList<Integer> l, Semaphore r, Semaphore rw) {
		super();
		liste = l;
		rw_mutex = rw;
		r_mutex = r;
		read_count = 0;
	}

	public void run() {
		do {
			try {
				r_mutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			read_count++;
			if (read_count == 1) {
				try {
					rw_mutex.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			r_mutex.release();
			//			...
			int ind = (new Random()).nextInt(liste.size());
			ind = (ind<0) ? 0: ind;
			System.out.println("Leseren leser fra indeks: "+ ind+" , verdi:"+liste.get(ind));

			/* reading is performed */
			
			//			...
			try {
				r_mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			read_count--;
			if (read_count == 0)
				rw_mutex.release();
			r_mutex.release();
		} while (true);
	}
}
