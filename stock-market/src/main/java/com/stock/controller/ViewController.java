package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.service.StockService;

@Controller
public class ViewController {
	
	@Autowired
	StockService stockService;
	
	@RequestMapping("/view")
	public String view(Model model){
		model.addAttribute("stocks", stockService.getAllStocks());
		return "view";
	}

}
