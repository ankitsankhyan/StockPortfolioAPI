package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Service.GetStocksService;
import com.PortfolioApp.Stocks_portfolio.Service.StockUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class StockControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private StockUpdateService stockUpdateService;

    @MockBean
    private GetStocksService getStocksService;

    @Test
    public void testUploadData_success() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.csv",
                MediaType.TEXT_PLAIN_VALUE,
                "sample data".getBytes()
        );

        Mockito.doNothing().when(stockUpdateService).addtoDB(any(MockMultipartFile.class));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(multipart("/stock/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("File uploaded and processed successfully."));
    }

    @Test
    public void testUploadData_failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.csv",
                MediaType.TEXT_PLAIN_VALUE,
                (byte[]) null
        );

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(multipart("/stock/upload").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("File is empty. Please upload a valid file."));
    }

    @Test
    public void testGetStockById_success() throws Exception {
        StockEntity stock = new StockEntity();
        stock.setId(1);
        stock.setName("Test Stock");

        Mockito.when(getStocksService.getStockById(1)).thenReturn(Optional.of(stock));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(get("/stock/get/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Stock")));
    }

    @Test
    public void testGetStockById_notFound() throws Exception {
        Mockito.when(getStocksService.getStockById(1)).thenReturn(Optional.empty());

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(get("/stock/get/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllStocks() throws Exception {
        StockEntity stock = new StockEntity();
        stock.setId(1);
        stock.setName("Test Stock");

        Mockito.when(getStocksService.getAllStocks()).thenReturn(Collections.singletonList(stock));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(get("/stock/get").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Stock")));
    }
}
