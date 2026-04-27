package com.example.JUnitTest;

public class Sushi {
	private String name;
	private int price;
	private int taxPrice;

	public Sushi(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
    public int getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(int taxPrice) {
        this.taxPrice = taxPrice;
    }
}
