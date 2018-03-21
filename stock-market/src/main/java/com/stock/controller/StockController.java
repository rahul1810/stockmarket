package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.service.StockService;
import com.stock.util.ResponseCodes;
import com.stock.util.ResponseMessages;
import com.stock.entity.Stock;
import com.stock.exception.StockException;

@RestController
@RequestMapping("/api")
public class StockController {

	@Autowired
	StockService stockService;
	
	@RequestMapping(value = "/stocks/", method = RequestMethod.GET)
	public List<Stock> listAllStocks() {
		return stockService.getAllStocks();
	}

	@RequestMapping(value = "/stocks/1", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOneStock(@RequestBody Stock stock) {
		try {
			stockService.updateStock(stock.getId(), stock.getCurrentPrice());
		} catch (StockException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ResponseMessages.messages.get(ResponseCodes.SUCCESS),HttpStatus.OK);
		
	}

	@RequestMapping(value = "/stocks/1", method = RequestMethod.POST)
	public ResponseEntity<String> addStock(@RequestBody Stock stock) {
		try {
			stockService.addStock(stock);
		} catch (StockException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ResponseMessages.messages.get(ResponseCodes.SUCCESS),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stocks/view", method = RequestMethod.GET)
	public String view(ModelMap model) {
        model.put("stocks", stockService.getAllStocks());
        return "view";
    }

}
