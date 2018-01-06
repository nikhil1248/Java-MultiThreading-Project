package Threads;

public class ProductThread extends Thread{
	
	int num;
	
	public ProductThread(int value) {
		num = value;
	}
	
	public void run() {
		num = num * 3;
	}

}
