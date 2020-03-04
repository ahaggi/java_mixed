//package main;
//
//import java.util.concurrent.Semaphore;
//
//public class KlientOppgave4 {
//
//	public static void main(String[] args) {
//
//		DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
//		Semaphore mutex = new Semaphore(1); // (initially = 1)
//		Semaphore next = new Semaphore(0); // (initially = 0)
//		Semaphore x_sem = new Semaphore(0); // (initially = 0)
//
//		Condition cd1 = new Condition(diningPhilosophers, mutex, next, x_sem);
//		Condition cd2 = new Condition(diningPhilosophers, mutex, next, x_sem);
//		Condition cd3 = new Condition(diningPhilosophers, mutex, next, x_sem);
//		Condition cd4 = new Condition(diningPhilosophers, mutex, next, x_sem);
//		Condition cd5 = new Condition(diningPhilosophers, mutex, next, x_sem);
//		diningPhilosophers.setSelf(cd1,cd2,cd3,cd4,cd5);
//		cd1.start();
//		cd2.start();
//		cd3.start();
//		cd4.start();
//		cd5.start();
//		
//		// Semaphore r_mutex = new Semaphore(1);
//		// Semaphore rw_mutex = new Semaphore(1);
//
//		// Skriveren sk = new Skriveren(liste, r_mutex, rw_mutex);
//		// Leseren ls=new Leseren(liste, r_mutex, rw_mutex);
//		// Thread t1 = new Thread();
//
//		// t1.start();
//
//	}
//
//}
//
//enum States {
//	THINKING, HUNGRY, EATING
//};
//
//class DiningPhilosophers {
//
//	States[] states = new States[5];
//
//	Condition self[] = new Condition[5];
//
//	public DiningPhilosophers() {
//		super();
//
//		for (int i = 0; i < 5; i++) {
//			states[i] = States.THINKING;
//		}
//	}
//
//	void pickup(int i) {
//		states[i] = States.HUNGRY;
//		test(i);
//		if (states[i] != States.EATING) {
//				self[i].waitt();
//		}
//	}
//
//	void putdown(int i) {
//		states[i] = States.THINKING;
//		// test left and right neighbors
//		test((i + 4) % 5);
//		test((i + 1) % 5);
//	}
//
//	void test(int i) {
//		if ((states[(i + 4) % 5] != States.EATING) && (states[i] == States.HUNGRY)
//				&& (states[(i + 1) % 5] != States.EATING)) {
//			states[i] = States.EATING;
//			self[i].signal();
//		}
//	}
//
//	public Condition[] getSelf() {
//		return self;
//	}
//
//	public void setSelf(Condition i0,Condition i1,Condition i2,Condition i3,Condition i4) {
//		this.self[0] = i0;
//		this.self[1] = i1;
//		this.self[2] = i2;
//		this.self[3] = i3;
//		this.self[4] = i4;
//	}
//
//
//}
//
//class Condition extends Thread {
//	DiningPhilosophers diningPhilosophers;
//	Semaphore mutex; // (initially = 1)
//	Semaphore next; // (initially = 0)
//	Semaphore x_sem; // (initially = 0)
//	int x_count;
//	int next_count;
//
//	
//
//	public Condition(DiningPhilosophers diningPhilosophers, Semaphore mutex, Semaphore next, Semaphore x_sem) {
//		super();
//		this.diningPhilosophers = diningPhilosophers;
//		this.mutex = mutex;
//		this.next = next;
//		this.x_sem = x_sem;
//		this.x_count = 0;
//		this.next_count = 0;
//	}
//
//
//	public void waitt() {
//		x_count++;
//		if (next_count > 0) {
//			next.release();
//		} else {
//			mutex.release();
//		}
//		try {
//			x_sem.acquire();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		x_count--;
//	}
//	
//	
//	public void signal() {
//		if (x_count > 0) {
//			next_count++;
//			x_sem.release();
//			try {
//				next.acquire();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			next_count--;
//			}
//	}
//	
//	
//
//
//	@Override
//	public void run() {
//
//
//
//		
//	}
//
//
//}
