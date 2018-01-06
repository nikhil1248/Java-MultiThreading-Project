package market;

import java.util.HashMap;

public class ConsumerThread extends Thread{
	
	public Market market;
	
	public HashMap<Fruits, Integer> quantity;
	public int totalQuantity;
	
	public ConsumerThread(Market market, int apples, int oranges, int grapes, int watermelons) {
		this.market = market;
		quantity = new HashMap<>();
		quantity.put(Fruits.APPLE, apples);
		quantity.put(Fruits.ORANGE, oranges);
		quantity.put(Fruits.GRAPE, grapes);
		quantity.put(Fruits.WATERMELON, watermelons);
		totalQuantity = apples + oranges + grapes + watermelons;
	}
	
	public void run() {
		try {
			while(market.availableQuantity == 0)
				wait();
			
			market.RemoveFruit(quantity);
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
