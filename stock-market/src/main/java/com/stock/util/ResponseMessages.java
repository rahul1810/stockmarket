package com.stock.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessages {
    public static final Map<Integer, String> messages = new HashMap<Integer, String>();
    static{
    	messages.put(ResponseCodes.STOCK_NOT_FOUND, "Stock not found");
    	messages.put(ResponseCodes.STOCK_ALREADY_EXISTS, "Stock Already Exists");
    	messages.put(ResponseCodes.STOCK_SUCCESSFULLY_ADDED, "Stock has been added successfully");
    	messages.put(ResponseCodes.STOCK_PRICE_SUCCESSFULLY_UPDATED, "Stock price has been updated successfully");

    }

}
