package org.yijun.hie.service;

import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by liuyijun on 14-11-8.
 */

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public UserAccountEntity userLogin (String userName, String password) {
        return userRepository.getUserByName(userName).get(0);
    }

    @Transactional
    public boolean isUserExist(String userName){
        if(userRepository.getUserByName(userName).size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void createUserAccount(UserAccountEntity userAccountEntity, String ss) throws ParseException {
//        UserAccountEntity userAccountEntity = new UserAccountEntity();
        if(ss.equals("Customer")) {
//            userAccountEntity.setEmail(request.getParameter("email"));
//            userAccountEntity.setPassword(request.getParameter("password"));
//            userAccountEntity.setUserName(request.getParameter("userName"));
//            userAccountEntity.setStatus(Boolean.valueOf(true));
//            userAccountEntity.setFirstName(request.getParameter("firstName"));
//            userAccountEntity.setLastName(request.getParameter("lastName"));
//            userAccountEntity.setDateOfBirth(request.getParameter("dateOfBirth"));
//            userAccountEntity.setAge(Integer.getInteger(request.getParameter("age")));
//            userAccountEntity.setStreet(request.getParameter("street"));
//            userAccountEntity.setCity(request.getParameter("city"));
//            userAccountEntity.setState(request.getParameter("state"));
//            userAccountEntity.setZip(request.getParameter("zip"));
//            userAccountEntity.setPhone(request.getParameter("phone"));
//            userAccountEntity.setIsSmallBusiness(Boolean.valueOf(request.getParameter("isSmallBusiness")));
//            userAccountEntity.setIsFamily(Boolean.valueOf(request.getParameter("isFamily")));
//            userAccountEntity.setIncomeStatus(request.getParameter("incomeStatus"));
            userAccountEntity.setRoleEntity(userRepository.getRole("Customer").get(0));
            userRepository.addUserAccount(userAccountEntity);
        }
    }
}
