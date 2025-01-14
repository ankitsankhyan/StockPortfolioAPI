package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;
import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Repository.UserRepository;
import com.PortfolioApp.Stocks_portfolio.dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateUserService {
    private UserRepository userRepository;
    CreateUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public void createUser(UserDTO newUser){
        UserEntity user = new UserEntity();
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setHoldingList(new ArrayList<StockHolding>());
        System.out.println(user);
        userRepository.save(user);
    }

    public List<UserEntity> getallUsers(){
        return userRepository.findAll();
    }
}
