package com.david.easymock;

import static org.junit.Assert.*;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
    public void test(){
		List<Stock> stocks=new ArrayList<Stock>();
		Stock javaBook=new Stock("1","Java Book",100);
		Stock cBook=new Stock("2","C# Book",99);
		stocks.add(javaBook);
		stocks.add(cBook);
		
		//StockService stockServiceMock = new StockDaoService();
		StockService stockServiceMock = createStrictMock(StockService.class);
		expect(stockServiceMock.getPrice(javaBook)).andReturn(100.0).anyTimes();
		//expect(stockServiceMock.getPrice(javaBook)).andReturn(100.0);
		expect(stockServiceMock.getPrice(cBook)).andReturn(50.0).anyTimes();
		//expect(stockServiceMock.getPrice(cBook)).andReturn(50.0);
		
		
		replay(stockServiceMock);
		
		Portfolio portfolio = new Portfolio();
		portfolio.setStocks(stocks);
		portfolio.setService(stockServiceMock);
		double sum=portfolio.getMarketValue();
		//expectLastCall().atLeastOnce();
		verify(stockServiceMock);
		
		
		
		//System.out.println("Sum="+sum);
		assertEquals("sum check",14950.0,sum,0.0);
		
		
		
		
	}


}
