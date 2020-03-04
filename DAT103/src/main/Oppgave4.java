package main;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class DiningPhilosophers {
	private Tilstand[] state;
	Condition[] self;
	public DiningPhilosophers(AtomicInteger next_count, Semaphore mutex,
			Semaphore next) {
		state = new Tilstand[5];
		self = new Condition[5];
		for (int i = 0; i < 5; i++) {
			state[ i] = Tilstand. THINKING;
			self[ i] = new Condition(next_count, mutex, next);
		}
	}
	public void pickup(int i) {
		state[ i] = Tilstand. HUNGRY;
		test(i);
		if (state[ i] != Tilstand. EATING)
			try {
				self[ i].vent();
			} catch (InterruptedException e) {}
	}
	public void putdown(int i) {
		state[ i] = Tilstand. THINKING;
		test((i + 4) % 5);
		test((i + 1) % 5);
	}
	public void test(int i) {
		if ((state[(i + 4) % 5] != Tilstand. EATING) && (state[ i] ==
				Tilstand. HUNGRY) && (state[(i + 1) % 5] !=
				Tilstand. EATING)) {
			state[ i] = Tilstand. EATING;
			try {
				self[ i].signal();
			} catch (InterruptedException e) {	}
		}
	}
}


class Philosopher extends Thread {
	private final DiningPhilosophers dp;
	private final AtomicInteger next_count;
	private final Semaphore next;
	private final Semaphore mutex;
	private final int nummer;
	public Philosopher(DiningPhilosophers dp, int nummer, AtomicInteger
			next_count, Semaphore mutex, Semaphore next) {
		this. dp = dp;
		this. nummer = nummer;
		this. next_count = next_count;
		this. next = next;
		this. mutex = mutex;
	}
	@Override
	public void run() {
		int j = 100;
		while (j > 0) {
			try {
				mutex.acquire();
			} catch (InterruptedException e) {}
			dp.pickup(nummer - 1);
			System. out.println(nummer + "spiser");
			dp.putdown(nummer - 1);
			j --;
			if (next_count.intValue() > 0)
				next.release();
			else
				try {
					mutex.acquire();
				} catch (InterruptedException e) {}
		}
	}
}


class Condition {
	private final AtomicInteger next_count;
	private final Semaphore x_sem;
	private final Semaphore mutex;
	private final Semaphore next;
	private Integer x_count;
	public Condition(AtomicInteger next_count, Semaphore mutex, Semaphore
			next) {
		this. next_count = next_count;
		x_sem = new Semaphore(0);
		this. mutex = mutex;
		this. next = next;
		x_count = 0;
	}
	public void vent() throws InterruptedException {
		x_count++;
		if (next_count.intValue() > 0)
			next.acquire();
		else
			mutex.release();
		x_sem.acquire();
		x_count--;
	}
	public void signal() throws InterruptedException {
		if (x_count > 0) {
			next_count.incrementAndGet();
			x_sem.release();
			next.acquire();
			next_count.decrementAndGet();
		}
	}
}

enum Tilstand {
	THINKING, HUNGRY, EATING
}

public class Oppgave4 {
	public static void main(String[] args) {
		AtomicInteger next_count = new AtomicInteger(0);
		Semaphore mutex = new Semaphore(1);
		Semaphore next = new Semaphore(0);
		DiningPhilosophers dp = new DiningPhilosophers(next_count,
				mutex, next);
		Philosopher p1 = new Philosopher(dp, 1, next_count, mutex,
				next);
		Philosopher p2 = new Philosopher(dp, 2, next_count, mutex,
				next);
		Philosopher p3 = new Philosopher(dp, 3, next_count, mutex,
				next);
		Philosopher p4 = new Philosopher(dp, 4, next_count, mutex,
				next);
		Philosopher p5 = new Philosopher(dp, 5, next_count, mutex,
				next);
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
	}
}