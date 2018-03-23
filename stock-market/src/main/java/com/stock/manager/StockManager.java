package com.stock.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.stock.entity.Stock;
import com.stock.exception.StockException;
import com.stock.util.ResponseCodes;

@Component
public class StockManager{
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(StockManager.class);
	
	private Map<Integer, Stock> stockMap;
	
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
	public int updateStock(int id, double price) throws StockException{
		logger.info("Updating stock with id: " + id);
		Stock stock = stockMap.get(id);
		if(stock == null)
			throw new StockException(ResponseCodes.STOCK_NOT_FOUND);
		stock.setCurrentPrice(price);
		stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		return ResponseCodes.STOCK_PRICE_SUCCESSFULLY_UPDATED;
	}
	
	
	/*
	 * Creates stock if it does not exists. If it does, this returns an exception
	 */
	public int addStock(Stock stock) throws StockException{
		logger.info("Creating new stock");
		if(stockMap.containsKey(stock.getId()))
			throw new StockException(ResponseCodes.STOCK_ALREADY_EXISTS);
		stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		stockMap.put(stock.getId(), stock);
		return ResponseCodes.STOCK_SUCCESSFULLY_ADDED;

	}
	
	@PostConstruct
	private void initailise(){
		int stockCount = Integer.parseInt(env.getProperty("config.stockCount"));
		int stockNameLength = Integer.parseInt(env.getProperty("config.stockNameLength"));
		int maxStockPrice =  Integer.parseInt(env.getProperty("config.maxStockPrice"));
		logger.info("Creating " + stockCount +" stocks");
		stockMap = new HashMap<>();
		for(int i = 1; i<= stockCount; i++){
			String name = randomAlphabeticString(stockNameLength);
			double currentPrice = (double) (Math.random() * maxStockPrice);
			currentPrice = Math.round(currentPrice*100)/100.0;
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			stockMap.put(i, new Stock(i, name, currentPrice, currentTime));
		}
		logger.info("Stock Map has been updated "+stockMap);
	}
	
	private String randomAlphabeticString(int count) {
		
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			String ALLOWED_CHARS = env.getProperty("config.allowedcharsinstockname");
		int character = (int)(Math.random()*ALLOWED_CHARS.length());
		builder.append(ALLOWED_CHARS.charAt(character));
		}
		return builder.toString();
	}
	
}
