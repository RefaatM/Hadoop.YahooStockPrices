package com.GenenticThought.YahooStockPrices.Main.YQLFetchStockHistory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import yahoofinance.histquotes.HistoricalQuote;

public class StockHistoryInfo implements Serializable {
	    
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Calendar date; 
	    private BigDecimal open;
	    private BigDecimal low;
	    private BigDecimal high;
	    private BigDecimal close;
	    private BigDecimal adjClose;
	    private Long volume;
	    
	    public StockHistoryInfo()
	    {
	    	
	    }
	    
	    public StockHistoryInfo(HistoricalQuote hq)
	    {
	    	this.setAdjClose(hq.getAdjClose());
	    	this.setDate(hq.getDate());
	    	this.setOpen(hq.getOpen());
	    	this.setClose(hq.getClose());
	    	this.setHigh(hq.getHigh());
	    	this.setLow(hq.getLow());
	    	this.setVolume(hq.getVolume());
	    			
	    }

		public Calendar getDate() {
			return date;
		}

		public void setDate(Calendar date) {
			this.date = date;
		}

		public Long getVolume() {
			return volume;
		}

		public void setVolume(Long volume) {
			this.volume = volume;
		}

		public BigDecimal getOpen() {
			return open;
		}

		public void setOpen(BigDecimal open) {
			this.open = open;
		}

		public BigDecimal getLow() {
			return low;
		}

		public void setLow(BigDecimal low) {
			this.low = low;
		}

		public BigDecimal getHigh() {
			return high;
		}

		public void setHigh(BigDecimal high) {
			this.high = high;
		}

		public BigDecimal getClose() {
			return close;
		}

		public void setClose(BigDecimal close) {
			this.close = close;
		}

		public BigDecimal getAdjClose() {
			return adjClose;
		}

		public void setAdjClose(BigDecimal adjClose) {
			this.adjClose = adjClose;
		}

}
