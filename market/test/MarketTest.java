package market.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import market.Fruits;
import market.Market;

public class MarketTest {

	@Test
	public void marketAvaialableQty() {
	}

	// function that calculates the value which decides the consumer or farmer
	@Test
	public void testRandomiser() {

		int acceptableCount = 0;
		int numTrials = 10;

		for (int j = 0; j < numTrials; j++) {
			int farmerCount = 0, consumerCount = 0;

			for (int i = 0; i < 1000; i++) {
				int val = Market.getRandomNum();
				if (val % 3 == 0)
					farmerCount++;
				else
					consumerCount++;
			}

			if (farmerCount < consumerCount)
				acceptableCount++;
		}

		System.out.println(acceptableCount);
		assertTrue(acceptableCount >= numTrials / 2);
	}
	
	@Test
	public void testingMarketAddFunc() {
		HashMap<Fruits, Integer> qty = new HashMap<>();
		for(int i = 0; i < 4; i++) 
			qty.put(Fruits.values()[i], 30);
		
		Market market = new Market(100);
		market.addFruit(qty, 1);
		
		assertEquals(100, market.availableQuantity);
		assertEquals(0, (int)qty.get(Fruits.values()[0]));
		assertEquals(0, (int)qty.get(Fruits.values()[1]));
		assertEquals(0, (int)qty.get(Fruits.values()[2]));
		assertEquals(20, (int)qty.get(Fruits.values()[3]));
		
	}
	
	@Test
	public void testingMarketSellFunc() {
		HashMap<Fruits, Integer> qty = new HashMap<>();
		for(int i = 0; i < 4; i++) 
			qty.put(Fruits.values()[i], 30);
		
		Market market = new Market(100);
		market.addFruit(qty, 1);
		for(int i = 0; i < 4; i++) 
			qty.put(Fruits.values()[i], 30);
		market.sellFruit(qty, 1);
		
		//checking the available qty after the customer has bought the fruits
		assertEquals(0, market.availableQuantity);
		
		//checking the remaining amount of each fruit in the consumer thread qty hash map
		assertEquals(0, (int)qty.get(Fruits.values()[0]));
		assertEquals(0, (int)qty.get(Fruits.values()[1]));
		assertEquals(0, (int)qty.get(Fruits.values()[2]));
		assertEquals(20, (int)qty.get(Fruits.values()[3]));
		
	}

}
