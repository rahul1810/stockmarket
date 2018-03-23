package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.util.BaseResponse;
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
	public ResponseEntity<BaseResponse> updateOneStock(@RequestBody Stock stock) throws StockException {
		int responseCode = stockManager.updateStock(stock.getId(), stock.getCurrentPrice());
		return new ResponseEntity<>(new BaseResponse(responseCode),HttpStatus.ACCEPTED);
		
	}

	@RequestMapping(value = "/stocks/1", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> addStock(@RequestBody Stock stock) throws StockException {
		int responseCode = stockManager.addStock(stock);
		return new ResponseEntity<>(new BaseResponse(responseCode),HttpStatus.CREATED);
	}

}
