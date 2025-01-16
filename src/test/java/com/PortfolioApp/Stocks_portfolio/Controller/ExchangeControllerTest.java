package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Service.ExchangeService;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExchangeControllerTest {

    @Mock
    private ExchangeService exchangeService;

    @InjectMocks
    private ExchangeController exchangeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void exchangeStock_validRequest_returnsOkResponse() {
        // Arrange
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        String expectedResponse = "Stock exchanged successfully";
        when(exchangeService.exhangeStock(exchangeDTO)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> response = exchangeController.exchangeStock(exchangeDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(exchangeService, times(1)).exhangeStock(exchangeDTO);
    }

    @Test
    void exchangeStock_nullRequest_returnsBadRequest() {
        // Act
        ResponseEntity<String> response = exchangeController.exchangeStock(null);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid request", response.getBody());
        verifyNoInteractions(exchangeService);
    }

    @Test
    void exchangeStock_serviceThrowsException_returnsInternalServerError() {
        // Arrange
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        when(exchangeService.exhangeStock(exchangeDTO)).thenThrow(new RuntimeException("Service error"));

        // Act
        ResponseEntity<String> response = exchangeController.exchangeStock(exchangeDTO);

        // Assert
        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Internal server error"));
        assertTrue(response.getBody().contains("Service error"));
        verify(exchangeService, times(1)).exhangeStock(exchangeDTO);
    }
}
