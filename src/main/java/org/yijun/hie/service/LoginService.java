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

    @Transactional
    public UserAccountEntity userLogin (String userName, String password) {
        return userRepository.getUserByName(userName).get(0);
    }

    @Transactional
    public boolean duplicatedUserChecked(String userName){
        if(userRepository.getUserByName(userName).get(0)==null){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public void createUserAccount(HttpServletRequest request, String string) throws ParseException {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        if(string.equals("Customer")) {
            userAccountEntity.setEmail(request.getParameter("email"));
            userAccountEntity.setPassword(request.getParameter("pass"));
            userAccountEntity.setUserName(request.getParameter("username"));
            userAccountEntity.setStatus(Boolean.valueOf(true));
            userAccountEntity.setFirstName(request.getParameter("fname"));
            userAccountEntity.setLastName(request.getParameter("lname"));
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String str = request.getParameter("mm") + "-" + request.getParameter("dd") + "-" + request.getParameter("yyyy");
            userAccountEntity.setDateOfBirth((Timestamp) sdf.parse(str));
            userAccountEntity.setAge(Integer.getInteger(request.getParameter("age")));
            userAccountEntity.setStreet(request.getParameter("street"));
            userAccountEntity.setCity(request.getParameter("city"));
            userAccountEntity.setState(request.getParameter("state"));
            userAccountEntity.setZip(request.getParameter("zip"));
            userAccountEntity.setPhone(request.getParameter("phone"));
            userAccountEntity.setIsSmallBusiness(Boolean.valueOf(request.getParameter("smallBusiness")));
            userAccountEntity.setIsFamily(Boolean.valueOf(request.getParameter("family")));
            userAccountEntity.setIncomeStatus(request.getParameter("income"));

            userRepository.addUserAccount(userAccountEntity);
        }

    }
}
