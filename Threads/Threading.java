package Threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Threading {

	/*
	 * int num; Thread sumThread, productThread;
	 * 
	 * public Threading(int value) { num = value; sumThread = new Thread(new
	 * SumThread(num)); /*sumThread = new Thread(new Runnable() {
	 * 
	 * @Override public void run() { num = (int) (num + Math.random() * 10); } },
	 * "Thread 1");*
	 * 
	 * productThread = new Thread(new Runnable() {
	 * 
	 * @Override public void run() { num = (int) (num * Math.random() * 10); } },
	 * "Thread 2"); }
	 */

	public static void main(String[] args) throws InterruptedException {

		/*
		 * Threading thread = new Threading(10);
		 * 
		 * thread.sumThread.start(); thread.productThread.start();
		 * 
		 * try { thread.sumThread.join(); thread.productThread.join(); } catch
		 * (InterruptedException e) { e.printStackTrace(); }
		 * System.out.println(thread.num);
		 */

		
		AtomicInteger atint = new AtomicInteger(100);
		
		SumThread t1 = new SumThread(10);
		t1.start();

		ProductThread t2 = new ProductThread(t1.num);
		t2.start();
		t2.join();

		System.out.println(t2.num);

	}

}
