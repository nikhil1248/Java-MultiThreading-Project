package Threads;

public class SumThread extends Thread{
	
	int num;
	
	public SumThread(int value) {
		num = value;
		
	}
	
	public void run() {
		num = num + 3;
	}

}
