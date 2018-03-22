package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.manager.StockManager;

@Controller
public class ViewController {
	
	@Autowired
	StockManager stockManager;
	
	@RequestMapping("/view")
	public String view(Model model){
		model.addAttribute("stocks", stockManager.getAllStocks());
		return "view";
	}

}
