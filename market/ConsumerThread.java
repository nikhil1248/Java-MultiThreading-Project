package market;

import java.util.HashMap;

public class ConsumerThread extends Thread {

	public Market market;

	public int count;

	public HashMap<Fruits, Integer> quantity;
	public int totalQuantity;

	public ConsumerThread(Market market, int apples, int oranges, int grapes, int watermelons, int count) {
		this.market = market;
		this.count = count;
		quantity = new HashMap<>();
		quantity.put(Fruits.APPLE, apples);
		quantity.put(Fruits.ORANGE, oranges);
		quantity.put(Fruits.GRAPE, grapes);
		quantity.put(Fruits.WATERMELON, watermelons);
		totalQuantity = apples + oranges + grapes + watermelons;
	}

	public void run() {
		System.out.println("\nConsumer " + count + " entered the market");
		try {
			while (totalQuantity != 0) {
				synchronized (market) {
					if (market.availableQuantity == 0) {
						System.out.println("Consumer " + count + " waiting for buying fruits");
						market.wait();
					} else {
						market.sellFruit(quantity, count);
						market.notifyAll();
					}
					updateQuantity();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Consumer " + count + " exited the market\n");
	}

	public void updateQuantity() {
		totalQuantity = 0;
		for (int i = 0; i < Fruits.values().length; i++) {
			totalQuantity += quantity.get(Fruits.values()[i]);
		}
	}

}
