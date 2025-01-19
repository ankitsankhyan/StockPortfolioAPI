package com.PortfolioApp.Stocks_portfolio.Exception;

public class RetryFailException extends Exception{
    public RetryFailException(String message, Throwable cause){
        super(message,cause);
    }
    public RetryFailException(String message){
        super(message);
    }
}
