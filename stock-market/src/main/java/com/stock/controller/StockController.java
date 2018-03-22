package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.util.ResponseCodes;
import com.stock.util.ResponseMessages;
import com.stock.entity.Stock;
import com.stock.exception.StockException;
import com.stock.manager.StockManager;

@RestController
@RequestMapping("/api")
public class StockController {

	@Autowired
	StockManager stockManager;
	
	@RequestMapping(value = "/stocks/", method = RequestMethod.GET)
	public List<Stock> listAllStocks() {
		return stockManager.getAllStocks();
	}

	@RequestMapping(value = "/stocks/1", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOneStock(@RequestBody Stock stock) {
		try {
			stockManager.updateStock(stock.getId(), stock.getCurrentPrice());
		} catch (StockException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ResponseMessages.messages.get(ResponseCodes.SUCCESS),HttpStatus.ACCEPTED);
		
	}

	@RequestMapping(value = "/stocks/1", method = RequestMethod.POST)
	public ResponseEntity<String> addStock(@RequestBody Stock stock) {
		try {
			stockManager.addStock(stock);
		} catch (StockException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ResponseMessages.messages.get(ResponseCodes.SUCCESS),HttpStatus.CREATED);
	}

}
