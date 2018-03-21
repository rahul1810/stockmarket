package com.stock.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.stock.entity.Stock;
import com.stock.exception.StockException;
import com.stock.util.Constants;
import com.stock.util.ResponseCodes;

@Component
public class StockService{
	
 
	private static Logger logger = LoggerFactory.getLogger(StockService.class);
	
	/*
	 * This static block creates random stocks as per the constraints specified in Contstants.java 
	 */
	public static Map<Integer, Stock> stockMap = new HashMap<>();
	static {
		logger.info("Creating " +Constants.stockCount +" stocks");
		for(int i = 0; i< Constants.stockCount; i++){
			String name = randomAlphabeticString(Constants.stockNameLength);
			float currentPrice = (float) (Math.random() * Constants.maxStockPrice);
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			stockMap.put(i, new Stock(i, name, currentPrice, currentTime));
		}
		logger.info("Stock Map has been updated "+stockMap);
	}
	
	static String randomAlphabeticString(int count) {
		
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*Constants.ALPHA_NUMERIC_STRING.length());
		builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	/*
	 * Returns list of all stocks present in memory
	 */
	public List<Stock> getAllStocks(){
		logger.info("Getting all stocks");
		List<Stock> stockList = new ArrayList<>();
		for(Entry<Integer, Stock> entry: stockMap.entrySet())
			stockList.add(entry.getValue());
		return stockList;
	}
	
	/*
	 * Updates stock if it exists. If it does not, this returns an exception
	 */
	public void updateStock(int id, double price) throws StockException{
		logger.info("Updating stock with id: " + id);
		Stock stock = stockMap.get(id);
		if(stock == null)
			throw new StockException(ResponseCodes.STOCK_NOT_FOUND);
		stock.setCurrentPrice(price);
		stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
	}
	
	
	/*
	 * Creates stock if it does not exists. If it does, this returns an exception
	 */
	public void addStock(Stock stock) throws StockException{
		logger.info("Creating new stock");
		if(stockMap.containsKey(stock.getId()))
			throw new StockException(ResponseCodes.STOCK_ALREADY_EXISTS);
		stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		stockMap.put(stock.getId(), stock);
	}
	
}
