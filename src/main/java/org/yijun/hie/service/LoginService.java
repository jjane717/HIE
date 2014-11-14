package org.yijun.hie.service;

import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.CustomerRoleEntity;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.RoleEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public UserAccountEntity createUserAccount(UserAccountEntity userAccountEntity, String ss, HttpServletRequest request){
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
            userAccountEntity.setRoleEntity((CustomerRoleEntity)userRepository.getRole("Customer").get(0));
            return userRepository.addUserAccount(userAccountEntity);
        }else{
            userAccountEntity.setRoleEntity(userRepository.getRoleByIDFromUR(Integer.valueOf(request.getParameter("idRole"))));
            userAccountEntity.setEnterpriseEntity(userRepository.getEnterpriseOneFromUR(Integer.valueOf(request.getParameter("idEnterprise"))));
            return userRepository.addUserAccount(userAccountEntity);
        }
    }

    public void updateUserAccount(UserAccountEntity userAccountEntity){
        userAccountEntity.setRoleEntity(userRepository.getUserByName(userAccountEntity.getUserName()).get(0).getRoleEntity());
        userAccountEntity.setIdEmployee(userRepository.getUserByName(userAccountEntity.getUserName()).get(0).getIdEmployee());
        userAccountEntity.setEnterpriseEntity(userRepository.getUserByName(userAccountEntity.getUserName()).get(0).getEnterpriseEntity());
        userRepository.updateUserAccount(userAccountEntity);
    }

    public List<EnterpriseEntity> getEnterpriseList(){
        return userRepository.getEnterprise();
    }

    public List<EnterpriseEntity> getEnterpriseGroupListFromService(String enterpriseType){
        return userRepository.getEnterpriseGroupFromUR(enterpriseType);
    }

    public EnterpriseEntity getEnterpriseOneFromService(Integer idEnterprise){
        return userRepository.getEnterpriseOneFromUR(idEnterprise);
    }

    public void updateEnterpriseFromService(EnterpriseEntity enterpriseEntity){
        userRepository.updateEnterpriseFromUR(enterpriseEntity);
    }

    public EnterpriseEntity addEnterpriseFromService(EnterpriseEntity enterpriseEntity){
        return userRepository.addEnterpriseFromUR(enterpriseEntity);
    }

    public void deleteEnterpriseFromService(EnterpriseEntity enterpriseEntity){
        userRepository.deleteEnterpriseFromUR(enterpriseEntity);
    }

    public List<UserAccountEntity> getUserAccountsByEnterpriseFromService (EnterpriseEntity enterpriseEntity){
        return userRepository.getUserAccountsByEnterpriseFromUR(enterpriseEntity);
    }

    public void getUserAccountByIDFromService (Integer idUserAccount, Boolean status){
        UserAccountEntity userAccountEntity = userRepository.getUserAccountByIDFromUR(idUserAccount);
        userAccountEntity.setStatus(status);
        updateUserAccount(userAccountEntity);
    }

    public List<RoleEntity> getRoleListFromService (){
        return userRepository.getRoleListFromUR();
    }

    public RoleEntity getRoleByIDFromService(Integer idRole){
        return userRepository.getRoleByIDFromUR(idRole);
    }

}
