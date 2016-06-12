package com.david.mockito;

import static org.junit.Assert.*;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * Detail sample for Mockito.
 * 
 * @author david
 *
 */
public class MockitoTest {

	@Test
    public void test(){
		//Normal Data prepare phase
		List<Stock> stocks=new ArrayList<Stock>();
		Stock javaBook=new Stock("1","Java Book",100);
		Stock cBook=new Stock("2","C# Book",99);
		Stock swiftBook=new Stock("3","Swift Book",1);
		stocks.add(javaBook);
		stocks.add(cBook);
		stocks.add(swiftBook);
		
		
		//Mock prepare phase
		StockService stockServiceMock = mock(StockService.class);
		when(stockServiceMock.getPrice(javaBook)).thenReturn(100.0);
		when(stockServiceMock.getPrice(cBook)).thenReturn(50.0);
		when(stockServiceMock.getPrice(null)).thenThrow(new RuntimeException());
		when(stockServiceMock.getPrice(swiftBook)).then(new Answer<Double>(){

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				System.out.println(invocation.getMethod().getName());
				return new Double(12.3);
			}});
		
		
		
		
		Portfolio portfolio = new Portfolio();
		portfolio.setStocks(stocks);
		portfolio.setService(stockServiceMock);
		double sum=portfolio.getMarketValue();

		//verify phase
		verify(stockServiceMock,atMost(2)).getPrice(swiftBook);
		verify(stockServiceMock,times(2)).getPrice(cBook);
		verify(stockServiceMock,times(2)).getPrice(javaBook);
		
		
		
	
		
		
		
		//System.out.println("Sum="+sum);
		assertEquals("sum check",14962.3,sum,0.0);
		InOrder inOrder = inOrder(stockServiceMock);
		//inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(cBook);
		inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(javaBook);
		inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(cBook);
		
		
		
		
	}


}
