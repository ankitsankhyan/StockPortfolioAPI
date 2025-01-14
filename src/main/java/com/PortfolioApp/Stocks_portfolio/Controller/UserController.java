package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Service.CreateUserService;
import com.PortfolioApp.Stocks_portfolio.dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private CreateUserService createUserService;
    UserController(CreateUserService createUserService){
        this.createUserService = createUserService;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){
         ;
           createUserService.createUser(user);
           return ResponseEntity.status(HttpStatus.OK).body("User Created Successfully");
    }

    @GetMapping(path = "/get", produces = "application/json")
    public List<UserEntity> getUsers(){
        List<UserEntity>  users = createUserService.getallUsers();

        return users;
    }
}
