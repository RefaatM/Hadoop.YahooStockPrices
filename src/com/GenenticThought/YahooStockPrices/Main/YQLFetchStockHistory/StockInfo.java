package com.GenenticThought.YahooStockPrices.Main.YQLFetchStockHistory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import com.GenenticThought.YahooStockPrices.UnitTests.StockTest;
import com.google.gson.*;

public class StockInfo implements Serializable{
	 public static final Logger LOG = Logger.getLogger(StockInfo.class.getName());
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String symbol;
	    private String name;
	    private String currency;
	    private String stockExchange;
	    private List<StockHistoryInfo> history;
	    
	    public StockInfo()
	    {
	    	
	    }
	    public void Initialize(String sym, int years) throws IOException
	    {
	    	this.setSymbol(sym);
	    	Stock stk = YahooFinance.get(sym);
	    	Calendar from = Calendar.getInstance();
	    	Calendar to = Calendar.getInstance();
	    	from.add(Calendar.YEAR, -1 * years); // from X year ago
	    	List<HistoricalQuote> histQuotes = stk.getHistory(from, to, Interval.DAILY);
	    	this.setCurrency(stk.getCurrency());
            this.setName(stk.getName());
            this.setStockExchange(stk.getStockExchange());
            history = new ArrayList<StockHistoryInfo>(histQuotes.size());
            for(HistoricalQuote hq : histQuotes)
            {
            	history.add( new StockHistoryInfo(hq));
            }
	    }
	    
	    public void writeJsonToStream(OutputStream os){
	    	
	    }
	 
	    @Override 
	    public String toString(){
	    	return toJSON();
	    }
	    
	    public String toJSON()
	    {
	    	Gson gson = getGson();
	    	return gson.toJson(this);
	    }
		/**
		 * @return
		 */
		static private Gson getGson() {
			GsonBuilder builder = new GsonBuilder();
	    	builder.setPrettyPrinting().serializeNulls();
	        Gson gson = builder.create();
			return gson;
		}
		
	    static public StockInfo fromJSON(String jsn)
	    {
	    	StockInfo result = null;
	    	Gson gson = getGson();
	    	try{
	    		result = gson.fromJson(jsn, StockInfo.class);		
	    	}
	    	catch(Exception ex){
	    		LOG.logp(Level.SEVERE, StockTest.class.getName(), "fromJson",ex.toString());
	    	}
	    	
	    	return result;
	    }
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getStockExchange() {
			return stockExchange;
		}
		public void setStockExchange(String stockExchange) {
			this.stockExchange = stockExchange;
		}
	
}
