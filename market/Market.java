package market;

import java.util.HashMap;

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

	public synchronized void addFruit(HashMap<Fruits, Integer> quantity) {

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
			} else if (capacity - availableQuantity < quantity.get(Fruits.values()[i])) {
				int num = fruitSlots.get(Fruits.values()[i]) + (capacity - availableQuantity);
				fruitSlots.put(Fruits.values()[i], num);
				int val = quantity.get(Fruits.values()[i]);
				quantity.put(Fruits.values()[i], val - num);
				availableQuantity = capacity;
			}
		}
	}

	public synchronized void sellFruit(HashMap<Fruits, Integer> quantity) {

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
			} else if (fruitSlots.get(Fruits.values()[i]) - quantity.get(Fruits.values()[i]) < 0) {
				int num = quantity.get(Fruits.values()[i]) - fruitSlots.get(Fruits.values()[i]);
				fruitSlots.put(Fruits.values()[i], 0);
				availableQuantity -= num;
				int val = quantity.get(Fruits.values()[i]) - num;
				quantity.put(Fruits.values()[i], val);
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Market market = new Market(100);
		
	}

}
