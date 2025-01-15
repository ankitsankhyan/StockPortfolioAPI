package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;
import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Repository.UserRepository;
import com.PortfolioApp.Stocks_portfolio.dto.StockHoldingDTO;
import com.PortfolioApp.Stocks_portfolio.dto.UserDTO;
import com.PortfolioApp.Stocks_portfolio.dto.UserPortfolioDTO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<UserPortfolioDTO> getUserById(Integer id){
        Optional<UserEntity> userOptional = userRepository.findById((id));
        UserEntity user = userOptional.get();
        List<StockHolding> stocks =  user.getHoldingList();
        List<StockHoldingDTO> stockHoldingDTOS = new ArrayList<>();
        Double totalExpenditure = 0d;
        Double totalProfit = 0d;

        for(int i = 0; i < stocks.size(); i++){
            StockHolding stockHolding = stocks.get(i);
            StockHoldingDTO stockHoldingDTO = new StockHoldingDTO();
            stockHoldingDTO.setStockId(stockHolding.getStock().getId());
            stockHoldingDTO.setCurrentPrice(stockHolding.getStock().getClosePrice());
            stockHoldingDTO.setNetPrice(stockHolding.getPrice());
            stockHoldingDTO.setNetQuantity(stockHolding.getQuantity());
            Double currentPrice = stockHoldingDTO.getCurrentPrice();
            Double netQuantity = stockHoldingDTO.getNetQuantity();
            Double netPrice = stockHoldingDTO.getNetPrice();
            Double profit = netPrice - currentPrice*netQuantity;
            totalExpenditure += netPrice;
            totalProfit += profit;
            stockHoldingDTO.setProfit(profit);
            stockHoldingDTOS.add(stockHoldingDTO);
;        }

        UserPortfolioDTO userPortfolioDTO = new UserPortfolioDTO();
        userPortfolioDTO.setName(user.getName());
        userPortfolioDTO.setEmail(user.getEmail());
        userPortfolioDTO.setProfit(totalProfit);
        userPortfolioDTO.setTotalExpenditure(totalExpenditure);
        userPortfolioDTO.setHoldingDTOList(stockHoldingDTOS);

        return Optional.of(userPortfolioDTO);
    }
}
