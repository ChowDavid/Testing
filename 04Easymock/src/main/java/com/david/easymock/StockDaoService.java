package com.david.easymock;

public class StockDaoService implements StockService {
	
	@Override
	public double getPrice(Stock name){
		//Dummy return value from DAO
		return 10.0;
	}

}
