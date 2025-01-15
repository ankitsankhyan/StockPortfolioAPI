package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Service.CreateUserService;
import com.PortfolioApp.Stocks_portfolio.dto.UserDTO;
import com.PortfolioApp.Stocks_portfolio.dto.UserPortfolioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@Tag(name = "User API")
public class UserController {
    private CreateUserService createUserService;
    UserController(CreateUserService createUserService){
        this.createUserService = createUserService;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        try {
            createUserService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("User Created Successfully");
        } catch (Exception e) {
            // Log the exception (use a logger in production)
            System.err.println("An error occurred while creating the user: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the user");
        }
    }


    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<List<UserEntity>> getUsers(){
        try{
            List<UserEntity>  users = createUserService.getallUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);

        } catch (Exception e) {
            System.err.println("Error in fetching users : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

    }

    @GetMapping(path = "/get/{id}", produces = "application/json")
    public ResponseEntity<UserPortfolioDTO> getUser(@PathVariable("id") Integer id){
        try{
            Optional<UserPortfolioDTO> userOptional = createUserService.getUserById(id);
            if(userOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserPortfolioDTO());
            }else {
                UserPortfolioDTO user = userOptional.get();
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }catch(Exception e){
            System.err.println("Error in fetching users : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserPortfolioDTO());
        }

    }
}
