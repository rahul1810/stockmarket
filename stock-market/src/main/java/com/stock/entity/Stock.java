package com.stock.entity;

import java.sql.Timestamp;

public class Stock {
	private int id;
	private String name;
	private double currentPrice;
	private Timestamp lastUpdate;
	
	public Stock(int id, String name, double currentPrice, Timestamp lastUpdate){
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}
	
	public Stock(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Stock [ id = ")
		.append(this.id)
		.append(", name = ")
		.append(this.name)
		.append(", currentPrice =")
		.append(", lastUpdate = ")
		.append(lastUpdate)
		.append(" ]");
		return sb.toString();
	}
	
}
