package com.stockmanagement;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockManagement {
	public static Scanner sc = new Scanner(System.in);
	public static JSONArray stockList = new JSONArray();

	public static void main(String[] args) {
		System.out.println("Stock Management");
		getInputFromUser();

	}
/*
 * Purpose : Method to get input from the user
 */
	private static void getInputFromUser() {
		System.out.println("Which Operation you want to perform 1.Add Stock 2. Print stock 3. Exit");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			addStock();
			break;
		case 2:
			printstock();
			break;
		case 3:
			System.exit(1);
			break;
		default:
			System.out.println("Invalid Choice");
		}
	}
/*
 * Purpose : Method to print the stock
 */
	private static void printstock() {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) jsonParser
					.parse(new FileReader("C:\\Users\\HP\\java\\json\\Stock Management\\Stock.json"));
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String name = (String) obj.get("name");
				long shares = (long) obj.get("no_of_shares");
				double price = (double) obj.get("price");
				System.out.println("Stock Name:" + name);
				System.out.println("Number of shares:" + shares);
				System.out.println("Stock Price:" + price);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		getInputFromUser();

	}
/*
 * Purpose: Method to add stock
 */
	@SuppressWarnings("unchecked")
	private static void addStock() {
		System.out.println("Add Stock");
		System.out.println("Enter Stock Name");
		String stockName = sc.next();
		System.out.println("Enter number of share");
		int stockShare = sc.nextInt();
		System.out.println("Enter share price");
		double sharePrice = sc.nextDouble();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", stockName); // Assigning values to JSON object
		jsonObject.put("no_of_shares", stockShare);
		jsonObject.put("price", sharePrice);
		stockList.add(jsonObject); // adding JSON object into JSON array
		try {
			FileWriter file = new FileWriter("C:\\Users\\HP\\java\\json\\Stock Management\\Stock.json");
			file.write(stockList.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("added" + jsonObject);
		getInputFromUser();
	}

}