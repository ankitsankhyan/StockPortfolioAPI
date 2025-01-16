package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Service.CreateUserService;
import com.PortfolioApp.Stocks_portfolio.dto.UserDTO;
import com.PortfolioApp.Stocks_portfolio.dto.UserPortfolioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService createUserService;

    private UserDTO userDTO;
    private UserEntity userEntity;
    private UserPortfolioDTO userPortfolioDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO("John", "john.doe@example.com");
        userEntity = new UserEntity(1, "John", "john.doe@example.com", Collections.emptyList());
        userPortfolioDTO = new UserPortfolioDTO("John", "john.doe@example.com", 0d, 0d, Collections.emptyList());
    }





    @Test
    void testGetUsers_Success() throws Exception {
        when(createUserService.getallUsers()).thenReturn(Arrays.asList(userEntity));

        mockMvc.perform(get("/user/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

        verify(createUserService, times(1)).getallUsers();
        verifyNoMoreInteractions(createUserService);
    }

    @Test
    void testGetUsers_EmptyList() throws Exception {
        when(createUserService.getallUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        verify(createUserService, times(1)).getallUsers();
        verifyNoMoreInteractions(createUserService);
    }

    @Test
    void testGetUserById_Success() throws Exception {
        when(createUserService.getUserById(1)).thenReturn(Optional.of(userPortfolioDTO));

        mockMvc.perform(get("/user/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(createUserService, times(1)).getUserById(1);
        verifyNoMoreInteractions(createUserService);
    }


}
