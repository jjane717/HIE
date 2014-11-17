package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.CustomerRepository;
import org.yijun.hie.persistence.repository.UserRepository;

import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public String getMarket(UserAccountEntity userAccountEntity){
        String market = new String();
        Boolean isFamily = userAccountEntity.getIsFamily();
        Boolean isSmallBusiness = userAccountEntity.getIsSmallBusiness();
        String income = userAccountEntity.getIncomeStatus();
        Integer age = userAccountEntity.getAge();

        if (!isFamily && !isSmallBusiness && !income.equalsIgnoreCase("low") && age<50){
            market = "AdultMarket";
        }else if(!isFamily && !isSmallBusiness && !income.equalsIgnoreCase("low") && age>=50){
            market = "SeniorMarket";
        }else if (!isFamily && !isSmallBusiness && income.equalsIgnoreCase("low")){
            market = "LowIncomeMarket";
        }else if (isFamily && !isSmallBusiness && income.equalsIgnoreCase("low")){
            market = "LowIncomeFamilyMarket";
        }else if (isFamily && isSmallBusiness && income.equalsIgnoreCase("low")){
            market = "LowIncomeSmallBusinessFamilyMarket";
        }else if (isFamily && isSmallBusiness && !income.equalsIgnoreCase("low")){
            market = "SmallBusinessFamilyMarket";
        }else if (!isFamily && isSmallBusiness && !income.equalsIgnoreCase("low")){
            market = "SmallBusinessIndividualMarket";
        }else if (isFamily && !isSmallBusiness && !income.equalsIgnoreCase("low")){
            market = "FamilyMarket";
        }else if (!isFamily && isSmallBusiness && income.equalsIgnoreCase("low")){
            market = "LowIncomeSmallBusinessIndividualMarket";
        }else{
            market = "No appropriate market.";
        }

        return market;
    }

    public List<ProductEntity> getProductEntityListForMarketFromService(String market){
        List<ProductEntity> productEntityList;
        if (market.equalsIgnoreCase("No appropriate market.")){
            productEntityList = null;
        }else {
            productEntityList = customerRepository.getProductEntityListForMarketFormRepository(market);
        }
        return productEntityList;
    }
}
