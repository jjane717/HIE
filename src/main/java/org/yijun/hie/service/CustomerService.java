package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.CustomerRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class CustomerService {
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

    public List<EnterpriseProductEntity> getProductEntityListForMarketFromService(String market){
        List<EnterpriseProductEntity> enterpriseProductEntityList;
        if (market.equalsIgnoreCase("No appropriate market.")){
            enterpriseProductEntityList = null;
        }else {
            enterpriseProductEntityList = customerRepository.getProductEntityListForMarketFormRepository(market);
        }
        return enterpriseProductEntityList;
    }

    public ProductEntity getProductEntityByIDFromService(Integer idProduct){
        return customerRepository.getProductEntityByIDFromRepository(idProduct);
    }

    public OrderEntity getOrderEntityByIDFromService(Integer idOrder){
        return customerRepository.getOrderEntityByIDFromRepository(idOrder);
    }

    public void createOrderEntityByIDFromService(OrderEntity orderEntity){
        Date createDate = Date.from(Instant.now());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(createDate);
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderEntity.setCreateDate(createDate);
        List<PaymentEntity> paymentEntityList = new LinkedList<PaymentEntity>();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        Double price = orderEntity.getEnterpriseProductEntity().getTotalPrice();
        int duration = orderEntity.getDuration();
        String paymentType = orderEntity.getPaymentType();
        int type;
        if (paymentType.equals("Annually")) {
            type=1;
        }else if (paymentType.equals("Monthly")){
            type=2;
        }else {
            type=3;
        }

        switch (type){
            case 1:
                for(int i = duration, j=0 ; i > 0 ; i-=12, j++){

                    PaymentEntity paymentEntity = new PaymentEntity();

                    int dueMonth;
                    int dueYear;
                    int dueDay = createDate.getDay();
                    if(month == 12){
                        dueMonth = 1;
                        dueYear = year + 1 +j;
                    }else{
                        dueMonth = month + 1;
                        dueYear = year + j;
                    }
                    if(dueMonth == 2 && day>=29){
                        dueDay = 28;
                    }

                    Date dueDate;
                    String sDate = dueYear+"-"+ dueMonth +"-"+ dueDay;
                    SimpleDateFormat  sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dueDate = sdfDate.parse(sDate);
                    } catch (ParseException ex) {
                        System.out.println( "Due day is not correct." );
                        return;
                    }

                    paymentEntity.setDueDate(dueDate);

                    if(i>=12){
                        paymentEntity.setAmount(price*12);
                    }else{
                        paymentEntity.setAmount(price*i);
                    }
                    paymentEntity.setIsPay(false);
                    paymentEntity.setOrderEntity(orderEntity);
                    paymentEntityList.add(paymentEntity);
                }
                break;

            case 2:
                int dueMonth;
                int dueYear = year;
                int dueDay;
                if(month == 12){
                    dueMonth = 1;
                }else{
                    dueMonth = month;
                }
                if(dueMonth == 2 && day>=29){
                    dueDay = 28;
                }else{
                    dueDay = day;
                }

                for(int i = duration, j = 0; i>0; i--,j++){
                    PaymentEntity paymentEntity = new PaymentEntity();
                    dueMonth++;
                    if(dueMonth >12){
                        dueMonth = 1;
                        dueYear++;
                    }

                    Date dueDate;
                    String sDate = dueYear+"-"+ dueMonth +"-"+ dueDay;
                    SimpleDateFormat  sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dueDate = sdfDate.parse(sDate);
                    } catch (ParseException ex) {
                        System.out.println("Due day is not correct.");
                        return;
                    }

                    paymentEntity.setDueDate(dueDate);
                    paymentEntity.setAmount(price);
                    paymentEntity.setIsPay(false);
                    paymentEntity.setOrderEntity(orderEntity);
                    paymentEntityList.add(paymentEntity);
                }
                break;

            case 3:
                dueYear = year;
                if(month == 12){
                    dueMonth = 1;
                }else{
                    dueMonth = month + 1;
                }
                if(dueMonth == 2 && day>=29){
                    dueDay = 28;
                }else{
                    dueDay = day;
                }

                for(int i = duration,j=0; i>0; i-=3, j++){
                    PaymentEntity paymentEntity = new PaymentEntity();
                    if(j>0){
                        dueMonth += 3;
                    }
                    if(dueMonth >12){
                        dueMonth -= 12;
                        dueYear++;
                    }

                    Date dueDate;
                    String sDate = dueYear+"-"+ dueMonth +"-"+ dueDay;
                    SimpleDateFormat  sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dueDate = sdfDate.parse(sDate);
                    } catch (ParseException ex) {
                        System.out.println("Due day is not correct.");
                        return;
                    }

                    paymentEntity.setDueDate(dueDate);

                    if(i>=3){
                        paymentEntity.setAmount(price*3);
                    }else{
                        paymentEntity.setAmount(price*i);
                    }
                    paymentEntity.setIsPay(false);
                    paymentEntity.setOrderEntity(orderEntity);
                    paymentEntityList.add(paymentEntity);
                }
                break;
        }
        orderEntity.setPaymentEntityList(paymentEntityList);
        customerRepository.createOrderEntityByIDFromRepository(orderEntity);
    }

}
