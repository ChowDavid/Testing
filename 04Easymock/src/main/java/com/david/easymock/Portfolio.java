package com.david.easymock;

import java.util.List;

public class Portfolio {
	
	private List<Stock> stocks;
	private StockService service;

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}



	public void setService(StockService service) {
		this.service = service;
	}
	
	public double getMarketValue(){
		double sum=0;
		for (Stock stock:stocks){
			
			service.getPrice(stock);
			sum+=stock.getStock()*service.getPrice(stock);
		}
		return sum;
	}

}
