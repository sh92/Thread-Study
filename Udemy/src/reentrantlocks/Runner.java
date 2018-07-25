package reentrantlocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	private void incremenet() {
		for (int i = 0; i < 100000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {

		System.out.println("Wating ...");
		lock.lock();
		cond.await();
		System.out.println("Waken up!");
		try {
			incremenet();
		} finally {
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();

		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		cond.signal();
		try {
			incremenet();
		} finally {
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Count is : " + count);
	}
}
