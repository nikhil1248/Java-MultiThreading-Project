package market.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import market.ConsumerThread;
import market.Market;

public class ConsumerTest {
	
	@Test
	//Test consumer thread state when market is empty
	public void testThreadState() throws InterruptedException {
		Market market = new Market(100);
		market.availableQuantity = 0;
		
		ConsumerThread ct = new ConsumerThread(market, 30, 30, 30, 30, 1);
		ct.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		assertEquals(Thread.State.WAITING, ct.getState());
		
	}

}
