package com.PortfolioApp.Stocks_portfolio.util;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.utils.CSVparser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVparserTest {

    private CSVparser csvParser;

    @BeforeEach
    void setUp() {
        csvParser = new CSVparser();
    }

    @Test
    void testParseCSVData_Success() throws IOException {
        String csvContent = "MARKET,SERIES,SYMBOL,SECURITY,PREV_CL_PR,OPEN_PRICE,HIGH_PRICE,LOW_PRICE,CLOSE_PRICE,NET_TRDVAL,NET_TRDQTY,CORP_IND,HI_52_WK,LO_52_WK\n" +
                "NSE,EQ,INFY,Infosys,1500,1510,1520,1490,1515,1000000,10000,N,1530,1480";

        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        List<StockEntity> stocks = csvParser.parseCSVData(file);

        assertEquals(1, stocks.size());
        StockEntity stock = stocks.get(0);
        assertEquals("Infosys", stock.getName());
        assertEquals(1510.0, stock.getOpenPrice());
        assertEquals(1515.0, stock.getClosePrice());
        assertEquals(1520.0, stock.getHighPrice());
        assertEquals(1490.0, stock.getLowPrice());
        assertEquals(1515.0, stock.getSettlementPrice());
    }

    @Test
    void testParseCSVData_EmptyFile() throws IOException {
        String csvContent = "MARKET,SERIES,SYMBOL,SECURITY,PREV_CL_PR,OPEN_PRICE,HIGH_PRICE,LOW_PRICE,CLOSE_PRICE,NET_TRDVAL,NET_TRDQTY,CORP_IND,HI_52_WK,LO_52_WK\n";

        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        List<StockEntity> stocks = csvParser.parseCSVData(file);

        assertTrue(stocks.isEmpty());
    }


}
