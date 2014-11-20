package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        UserAccountEntity userAccountEntity = userRepository.getUserByName("admin").get(0);
        session.setAttribute("user",userAccountEntity);
        return userAccountEntity;
    }

    public Boolean isUserExist(String userName){
        if(userRepository.getUserByName(userName).size() > 0){
            return true;
        }else{
            return false;
        }
    }

    public UserAccountEntity createUserAccount(UserAccountEntity userAccountEntity, HttpServletRequest request){
        EnterpriseEntity enterpriseEntity = userRepository.getEnterpriseOneFromUR(Integer.valueOf(request.getParameter("idEnterprise")));
        userAccountEntity.setEnterpriseEntity(enterpriseEntity);
        userAccountEntity.setStatus(true);
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

    public void updateUserAccount(UserAccountEntity userAccountEntity, HttpServletRequest request){
        userAccountEntity.setUserName(request.getParameter("userName"));
        userAccountEntity.setPassword(request.getParameter("password"));
        userAccountEntity.setAge(Integer.valueOf(request.getParameter("age")));
        userAccountEntity.setDateOfBirth(request.getParameter("dateOfBirth"));
        userAccountEntity.setEmail(request.getParameter("email"));
        userAccountEntity.setFirstName(request.getParameter("firstName"));
        userAccountEntity.setLastName(request.getParameter("lastName"));
        userAccountEntity.setStreet(request.getParameter("street"));
        userAccountEntity.setCity(request.getParameter("city"));
        userAccountEntity.setState(request.getParameter("state"));
        userAccountEntity.setZip(request.getParameter("zip"));
        userAccountEntity.setPhone(request.getParameter("phone"));

        userRepository.updateUserAccount(userAccountEntity);
    }

    public void updateAllUserAccount(UserAccountEntity userAccountEntity){
        userAccountEntity.setRoleEntity(userRepository.getUserByName(userAccountEntity.getUserName()).get(0).getRoleEntity());
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
        updateAllUserAccount(userAccountEntity);
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

}
