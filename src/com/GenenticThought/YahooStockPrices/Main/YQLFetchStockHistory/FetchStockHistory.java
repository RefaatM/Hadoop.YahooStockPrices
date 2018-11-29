package com.GenenticThought.YahooStockPrices.Main.YQLFetchStockHistory;
import java.util.Date;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;



public class FetchStockHistory {

    public static List<HistoricalQuote> FetchStockHistory(String stockSymbole, Calendar from,Calendar to ) throws IOException{
    	
    	Stock stock = YahooFinance.get(stockSymbole);
    	return stock.getHistory(from, to, Interval.DAILY);
    }
}
