package market.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import market.FarmerThread;
import market.Market;

public class FarmerTest {
	
	@Test
	//test thread state when market is full
	public void testThreadState() throws InterruptedException {
		Market market = new Market(100);
		market.availableQuantity = 100;
		
		FarmerThread ft = new FarmerThread(market, 30, 30, 30, 30, 1);
		ft.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		assertEquals(Thread.State.WAITING, ft.getState());
	}

}
