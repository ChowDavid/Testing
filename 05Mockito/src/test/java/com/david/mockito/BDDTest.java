package com.david.mockito;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.BDDMockito.*;

/**
 * Detail sample for BDDMockito. That is BDD 
 * given
 * when
 * then
 * 
 * @author david
 *
 */
public class BDDTest {

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
		
		
		//BDD Given
		StockService stockServiceMock = mock(StockService.class);
		given(stockServiceMock.getPrice(javaBook)).willReturn(100.0);
		given(stockServiceMock.getPrice(cBook)).willReturn(50.0);
		given(stockServiceMock.getPrice(null)).willThrow(new RuntimeException());
		given(stockServiceMock.getPrice(swiftBook)).will(new Answer<Double>(){

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				System.out.println(invocation.getMethod().getName());
				return new Double(12.3);
			}});
		
		
		
		
		Portfolio portfolio = new Portfolio();
		portfolio.setStocks(stocks);
		portfolio.setService(stockServiceMock);
		//BDD When
		double sum=portfolio.getMarketValue();

		//BDD Then
		verify(stockServiceMock,atMost(2)).getPrice(swiftBook);
		verify(stockServiceMock,times(2)).getPrice(cBook);
		verify(stockServiceMock,times(2)).getPrice(javaBook);
		assertEquals("sum check",14962.3,sum,0.0);
		InOrder inOrder = inOrder(stockServiceMock);
		//inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(cBook);
		inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(javaBook);
		inOrder.verify(stockServiceMock,atLeastOnce()).getPrice(cBook);
		
		
		
		
	}


}
