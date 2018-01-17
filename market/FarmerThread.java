package market;

import java.util.HashMap;

public class FarmerThread extends Thread {

	public Market market;

	public int count;

	public HashMap<Fruits, Integer> quantity;

	public int totalQuantity;

	public FarmerThread(Market market, int apples, int oranges, int grapes, int watermelons, int count) {
		this.count = count;
		this.market = market;
		quantity = new HashMap<>();
		quantity.put(Fruits.APPLE, apples);
		quantity.put(Fruits.ORANGE, oranges);
		quantity.put(Fruits.GRAPE, grapes);
		quantity.put(Fruits.WATERMELON, watermelons);
		totalQuantity = apples + oranges + grapes + watermelons;
	}

	public void run() {
		System.out.println("Farmer " + count + " entered the market");
		try {
			while (totalQuantity != 0) {
				synchronized (market) {
					if (market.availableQuantity == market.capacity) {
						System.out.println("Farmer " + count + " waiting for selling the fruits....");
						market.wait();
					} else {
						market.addFruit(quantity, count);
						market.notifyAll();
					}
					updateQuantity();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Farmer " + count + " exited the market");
	}

	public void updateQuantity() {
		totalQuantity = 0;
		for (int i = 0; i < Fruits.values().length; i++) {
			totalQuantity += quantity.get(Fruits.values()[i]);
		}
	}
	
	

}
