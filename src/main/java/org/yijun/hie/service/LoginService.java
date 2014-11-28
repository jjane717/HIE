package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.EmployeeRoleEntity;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.RoleEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public UserAccountEntity userLogin () {
        //UserAccountEntity userAccountEntity = userRepository.getUserByName("aaa").get(0);
        SecurityContext securityContext=(SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserAccountEntity userAccountEntity = (UserAccountEntity) securityContext.getAuthentication().getPrincipal();
//        session.setAttribute("user",userAccountEntity);
        return userAccountEntity;
    }

    public Boolean isUserExist(String userName){
        if(userRepository.getUserByName(userName) != null){
            return true;
        }else{
            return false;
        }
    }

    public UserAccountEntity createUserAccount(UserAccountEntity userAccountEntity, HttpServletRequest request){
        EnterpriseEntity enterpriseEntity = userRepository.getEnterpriseOneFromUR(Integer.valueOf(request.getParameter("idEnterprise")));
        userAccountEntity.setEnterpriseEntity(enterpriseEntity);
        userAccountEntity.setStatus(true);
        if(userAccountEntity.getIsFamily()==null){
            userAccountEntity.setIsFamily(false);
        }

        if(userAccountEntity.getIsSmallBusiness()==null){
            userAccountEntity.setIsSmallBusiness(false);
        }
        String idRole = request.getParameter("idRole");
        if(idRole.equals("employee")){
            EmployeeRoleEntity employeeRoleEntity = new EmployeeRoleEntity();
            Integer id = userAccountEntity.getEnterpriseEntity().getEnterpriseCode();
            Integer idEmployee = id*10000 + enterpriseEntity.getUserAccountEntityList().size() + 1;
            employeeRoleEntity.setIdEmployee(idEmployee);
            employeeRoleEntity.setRoleName("Employee");
            userAccountEntity.setRoleEntity(employeeRoleEntity);
        }else{
            userAccountEntity.setRoleEntity(userRepository.getRoleByIDFromUR(Integer.valueOf(idRole)));
        }
        userAccountEntity.setEnterpriseEntity(userRepository.getEnterpriseOneFromUR(Integer.valueOf(request.getParameter("idEnterprise"))));
        userAccountEntity.setStatus(true);
        return userRepository.addUserAccount(userAccountEntity);

    }

    public void updateUserAccountForSecurity(UserAccountEntity userAccount, HttpServletRequest request){
        userAccount.setUserName(request.getParameter("userName"));
        userAccount.setPassword(request.getParameter("password"));
        userAccount.setAge(Integer.valueOf(request.getParameter("age")));
        userAccount.setDateOfBirth(request.getParameter("dateOfBirth"));
        userAccount.setEmail(request.getParameter("email"));
        userAccount.setFirstName(request.getParameter("firstName"));
        userAccount.setLastName(request.getParameter("lastName"));
        userAccount.setStreet(request.getParameter("street"));
        userAccount.setCity(request.getParameter("city"));
        userAccount.setState(request.getParameter("state"));
        userAccount.setZip(request.getParameter("zip"));
        userAccount.setPhone(request.getParameter("phone"));
    }

    @Transactional
    public void updateUserAccount(HttpServletRequest request){
        UserAccountEntity userAccount = userRepository.getUserAccountByIDFromUR(Integer.valueOf(request.getParameter("idUserAccount")));
        //from request
        userAccount.setUserName(request.getParameter("userName"));
        userAccount.setPassword(request.getParameter("password"));
        userAccount.setAge(Integer.valueOf(request.getParameter("age")));
        userAccount.setDateOfBirth(request.getParameter("dateOfBirth"));
        userAccount.setEmail(request.getParameter("email"));
        userAccount.setFirstName(request.getParameter("firstName"));
        userAccount.setLastName(request.getParameter("lastName"));
        userAccount.setStreet(request.getParameter("street"));
        userAccount.setCity(request.getParameter("city"));
        userAccount.setState(request.getParameter("state"));
        userAccount.setZip(request.getParameter("zip"));
        userAccount.setPhone(request.getParameter("phone"));

        userRepository.updateUserAccount(userAccount);
    }

    public void updateAllUserAccount(UserAccountEntity userAccountEntity){
        userAccountEntity.setRoleEntity(userRepository.getUserByName(userAccountEntity.getUserName()).getRoleEntity());
        userAccountEntity.setEnterpriseEntity(userRepository.getUserByName(userAccountEntity.getUserName()).getEnterpriseEntity());

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

    public void updateEnterpriseFromService(HttpServletRequest request){
        EnterpriseEntity enterpriseEntity = getEnterpriseOneFromService(Integer.valueOf(request.getParameter("idEnterprise")));
        enterpriseEntity.setEnterpriseName(request.getParameter("enterpriseName"));
        enterpriseEntity.setEnterpriseCode(Integer.valueOf(request.getParameter("enterpriseCode")));
        enterpriseEntity.setStreet(request.getParameter("street"));
        enterpriseEntity.setCity(request.getParameter("city"));
        enterpriseEntity.setState(request.getParameter("state"));
        enterpriseEntity.setZip(request.getParameter("zip"));
        enterpriseEntity.setEnterpriseType(request.getParameter("enterpriseType"));
        enterpriseEntity.setPhone(request.getParameter("phone"));
        enterpriseEntity.setEmail(request.getParameter("email"));
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
        updateAllUserAccount(userAccountEntity);
    }

    public UserAccountEntity getUserAccountByID(Integer idUserAccount){
        return userRepository.getUserAccountByIDFromUR(idUserAccount);
    }

    public List<UserAccountEntity> getAllUserAccount(){
        return userRepository.getAllUserAccount();
    }

    public List<RoleEntity> getRoleListFromService (){
        return userRepository.getRoleListFromUR();
    }

    public RoleEntity getRoleByIDFromService(Integer idRole){
        return userRepository.getRoleByIDFromUR(idRole);
    }

    public Double transferMoneyFromService(HttpServletRequest request){
        EnterpriseEntity hie = getEnterpriseOneFromService(Integer.valueOf(request.getParameter("idEnterprise")));
        EnterpriseEntity insurance = userRepository.getEnterpriseByNameFromUR(request.getParameter("enterpriseName"));
        Double amount = Double.valueOf(request.getParameter("amount"));
        hie.setEnterpriseBalance(hie.getEnterpriseBalance()-amount);
        insurance.setEnterpriseBalance(insurance.getEnterpriseBalance()+amount);
        userRepository.updateEnterpriseFromUR(hie);
        userRepository.updateEnterpriseFromUR(insurance);
        return hie.getEnterpriseBalance();
    }

}
