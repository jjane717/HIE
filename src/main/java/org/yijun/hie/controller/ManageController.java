package org.yijun.hie.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.UserRepository;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liuyijun on 14-11-12.
 */

@Controller
public class ManageController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public UserAccountEntity viewAccount (){
        UserAccountEntity userAccountEntity = loginService.userLogin();
        return userAccountEntity;
    }

    @RequestMapping(value="/enterpriseGroup", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<EnterpriseEntity> getEnterpriseGroupList (String enterpriseType) {
        return loginService.getEnterpriseGroupListFromService(enterpriseType);
    }

    @RequestMapping(value="/enterprise", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<EnterpriseEntity> getEnterpriseList () {
        return loginService.getEnterpriseList();
    }

    @RequestMapping(value="/role", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<RoleEntity> getRoleList () {
        return loginService.getRoleListFromService();
    }

    @RequestMapping(value="/idRole", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public RoleEntity getRoleByID (String id) {
        if(id.equals("employee")){
            EmployeeRoleEntity employeeRoleEntity = new EmployeeRoleEntity();
            employeeRoleEntity.setRoleName("Employee");
            return employeeRoleEntity;
        }else{
            Integer idRole = Integer.valueOf(id);
            return loginService.getRoleByIDFromService(idRole);
        }
    }

    @RequestMapping(value="/enterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public EnterpriseEntity getEnterpriseById (HttpServletRequest request) {
        return loginService.getEnterpriseOneFromService(Integer.valueOf(request.getParameter("id")));
    }

    @RequestMapping(value="/updateEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String updateEnterprise (HttpServletRequest request) {
        loginService.updateEnterpriseFromService(request);
        return "OK";
    }

    @RequestMapping(value="/addEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public EnterpriseEntity addEnterprise (EnterpriseEntity enterpriseEntity) {
        return loginService.addEnterpriseFromService(enterpriseEntity);
    }

    @RequestMapping(value="/deleteEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String deleteEnterprise (String id) {
        Integer idEnterprise = Integer.valueOf(id);
        EnterpriseEntity enterpriseEntity = loginService.getEnterpriseOneFromService(idEnterprise);
        loginService.deleteEnterpriseFromService(enterpriseEntity);
        return "You have already Delete this Enterprise.";
    }

    @RequestMapping(value="/userAccountForEnterprise", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<UserAccountEntity> getUserAccountsByEnterprise(){
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<UserAccountEntity> userAccountEntityList;
        if(userAccountEntity.getEnterpriseEntity().getIdEnterprise()==3){
            userAccountEntityList = loginService.getAllUserAccount();
            userAccountEntityList.remove(userAccountEntity);
        }else{
            userAccountEntityList = loginService.getUserAccountsByEnterpriseFromService(userAccountEntity.getEnterpriseEntity());
            userAccountEntityList.remove(userAccountEntity);
        }
        return userAccountEntityList;
    }

    @RequestMapping(value="/updateUserAccountStatus", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void updateUserAccountStatus(HttpServletRequest request){
        Integer idUserAccount = Integer.valueOf(request.getParameter("id"));
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        loginService.getUserAccountByIDFromService(idUserAccount, status);
    }

    @RequestMapping(value="/createUserAccount", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public UserAccountEntity createUserAccount(UserAccountEntity userAccountEntity, HttpServletRequest request){
        String userName = userAccountEntity.getUserName();
        if(!loginService.isUserExist(userName)){
            UserAccountEntity user2 = loginService.createUserAccount(userAccountEntity,request);
            return user2;
        }else{
            return null;
        }
    }

    @RequestMapping(value="/transferMoney", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Double transferMoney(HttpServletRequest request){
        Double balance = loginService.transferMoneyFromService(request);
        return balance;
    }
}
