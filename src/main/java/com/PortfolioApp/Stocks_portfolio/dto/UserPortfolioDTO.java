package com.PortfolioApp.Stocks_portfolio.dto;

import org.apache.catalina.User;

import java.util.List;

public class UserPortfolioDTO {
       private String name;
       private String email;
        private Double totalExpenditure;
        private Double profit;
        private List<StockHoldingDTO> holdingDTOList;

        public String getEmail() {
        return email;
    }
       public UserPortfolioDTO(String name, String email, Double totalExpenditure,Double profit,List<StockHoldingDTO>holdingDTOList){
            this.name = name;
            this.email =email;
            this.totalExpenditure = totalExpenditure;
            this.profit = profit;
            this.holdingDTOList = holdingDTOList;
       }
       public UserPortfolioDTO(){

       }
    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(Double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public List<StockHoldingDTO> getHoldingDTOList() {
        return holdingDTOList;
    }

    public void setHoldingDTOList(List<StockHoldingDTO> holdingDTOList) {
        this.holdingDTOList = holdingDTOList;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
