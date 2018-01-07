package market;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Market {

	public int capacity;
	public HashMap<Fruits, Integer> fruitSlots;
	public int availableQuantity;

	public Market(int capacity) {
		this.capacity = capacity;
		fruitSlots = new HashMap<>();
		fruitSlots.put(Fruits.APPLE, 0);
		fruitSlots.put(Fruits.ORANGE, 0);
		fruitSlots.put(Fruits.GRAPE, 0);
		fruitSlots.put(Fruits.WATERMELON, 0);
		availableQuantity = 0;
	}

	public synchronized void addFruit(HashMap<Fruits, Integer> quantity, int count) {

		for (int i = 0; i < Fruits.values().length; i++) {
			if (availableQuantity == capacity)
				break;
			if (quantity.get(Fruits.values()[i]) == 0)
				continue;
			else if (quantity.get(Fruits.values()[i]) + availableQuantity <= capacity) {
				int num = fruitSlots.get(Fruits.values()[i]) + quantity.get(Fruits.values()[i]);

				fruitSlots.put(Fruits.values()[i], num);
				availableQuantity += quantity.get(Fruits.values()[i]);
				quantity.put(Fruits.values()[i], 0);
				
				System.out.println("Farmer " + count + " sold some " + Fruits.values()[i]);
				
			} else if (capacity - availableQuantity < quantity.get(Fruits.values()[i])) {

				int num = fruitSlots.get(Fruits.values()[i]) + (capacity - availableQuantity);
				fruitSlots.put(Fruits.values()[i], num);

				int val = quantity.get(Fruits.values()[i]);
				quantity.put(Fruits.values()[i], val - num);
				availableQuantity = capacity;
				
				System.out.println("Farmer " + count + " sold some " + Fruits.values()[i]);
			}
		}
	}

	public synchronized void sellFruit(HashMap<Fruits, Integer> quantity, int count) {

		for (int i = 0; i < Fruits.values().length; i++) {
			if (availableQuantity == 0)
				break;
			if (quantity.get(Fruits.values()[i]) == 0)
				continue;
			else if (fruitSlots.get(Fruits.values()[i]) - quantity.get(Fruits.values()[i]) >= 0) {
				int num = fruitSlots.get(Fruits.values()[i]) - quantity.get(Fruits.values()[i]);
				fruitSlots.put(Fruits.values()[i], num);
				availableQuantity -= quantity.get(Fruits.values()[i]);
				quantity.put(Fruits.values()[i], 0);
				
				System.out.println("Consumer " + count + " bought some " + Fruits.values()[i]);
			} else if (fruitSlots.get(Fruits.values()[i]) - quantity.get(Fruits.values()[i]) < 0) {
				int num = quantity.get(Fruits.values()[i]) - fruitSlots.get(Fruits.values()[i]);
				fruitSlots.put(Fruits.values()[i], 0);
				availableQuantity -= num;
				
				int val = quantity.get(Fruits.values()[i]) - num;
				quantity.put(Fruits.values()[i], val);
				System.out.println("Consumer " + count + " bought some " + Fruits.values()[i]);
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Market market = new Market(100);
		int nThreads = 10;
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);

		int fCount = 0, cCount = 0;

		System.out.println("Market opens \n");
		
		long time = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		
		
		while ((now - time) <= 1000) {
			Random numGen = new Random();
			int val = (int) (Math.random() * 100);

			if (val % 2 == 0) {
				fCount++;
				pool.execute(new FarmerThread(market, numGen.nextInt(20), numGen.nextInt(20), numGen.nextInt(20),
						numGen.nextInt(20), fCount));
			} else {
				cCount++;
				pool.execute(new ConsumerThread(market, numGen.nextInt(20), numGen.nextInt(20), numGen.nextInt(20),
						numGen.nextInt(20), cCount));
			}

			TimeUnit.MILLISECONDS.sleep(5);
			now = System.currentTimeMillis();
		}

		pool.shutdown();

	}

}
