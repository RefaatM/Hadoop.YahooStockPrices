package com.GenenticThought.YahooStockPrices.UnitTests;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
//import com.GenenticThought.YahooStockPrices.Main.YahooStocks.*;

import com.GenenticThought.YahooStockPrices.Main.YQLFetchStockHistory.FetchStockHistory;
import com.GenenticThought.YahooStockPrices.Main.YQLFetchStockHistory.StockInfo;
import com.GenenticThought.YahooStockPrices.Main.HDFSFacade.HDFSFacade;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;



public class StockTest { 
   public static final Logger LOG = Logger.getLogger(StockTest.class.getName());
	
	@Test
	public void testYQLFetchSTockHistoy() { 
		try{
			Stock stock = YahooFinance.get("GOOG");
			stock.print();
			Calendar from = Calendar.getInstance();
	    	Calendar to = Calendar.getInstance();
	    	from.add(Calendar.YEAR, -1); // from 1 year ago
		List<HistoricalQuote> googleHistQuotes = FetchStockHistory.FetchStockHistory("GOOG",from,to);
		 LOG.info("GOOG History Stock Prices");
    	for(HistoricalQuote quote: googleHistQuotes)
    	{
    	 LOG.info(quote.toString());
    	}
		}
		catch(IOException exp){
			LOG.logp(Level.SEVERE, StockTest.class.getName(), "testStockHistory",exp.toString());
		}
	}
	
	@Test
	public void testStockInfo() { 
			try{
				StockInfo stock = new StockInfo();
				stock.Initialize("GOOG",1);
			 LOG.info("GOOG History Stock Prices");
			 String json = stock.toJSON();
	    	 LOG.info(json);
	    	 StockInfo stock2 = StockInfo.fromJSON(json);
	    	 LOG.info(stock2.toString());
			}
			catch(IOException exp){
				LOG.logp(Level.SEVERE, StockTest.class.getName(), "testStockHistory",exp.toString());
			}
		
	}
	@Test
	public void testCreateHDFSDir(){
		try{
	     HDFSFacade hdfs = new HDFSFacade("moustafarefaat", "hdfs://localhost:7500");
	     hdfs.deleteDir("/Stocks");
	        hdfs.createDir("/Stocks");
		}
		catch(IOException exp){
			LOG.logp(Level.SEVERE, StockTest.class.getName(), "testCreateHDFSDir",exp.toString());
		}
	}
	
	
	@Test
	public void testHDFSFacadeWriteStringToFile() { 
			try{
				StockInfo stock = new StockInfo();
				stock.Initialize("GOOG",5);
			 LOG.info("GOOG History Stock Prices");
			 String json = stock.toJSON();
	         HDFSFacade hdfs = new HDFSFacade("moustafarefaat", "hdfs://localhost:7500");
	        hdfs.createDir("/user/Stocks");
	        hdfs.writeStringToFile(json, "/user/Stooks/GOOG.jsn"); 	
         
			}
			catch(IOException exp){
				LOG.logp(Level.SEVERE, StockTest.class.getName(), "testStockHistory",exp.toString());
			}
		
	}
}
