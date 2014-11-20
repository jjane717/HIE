package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import java.util.List;

/**
 * Created by liuyijun on 14-11-19.
 */
@Controller
public class UserRolePrivilegeController {
    @Autowired
    private UserRolePrivilegeService userRolePrivilegeService;
    @Autowired
    private LoginService loginService;

    @Transactional
    public List<UserAccountEntity> getAllEditableUserRole(){
        List<UserAccountEntity> userAccountEntityList;
        UserAccountEntity userAccountEntity = loginService.userLogin();
        EnterpriseEntity enterpriseEntity = userAccountEntity.getEnterpriseEntity();
        if(enterpriseEntity.getIdEnterprise() == 3){
            userAccountEntityList = userRolePrivilegeService.getAllAdminUserRole();
            userAccountEntityList.remove(userAccountEntity);
        }else{
            userAccountEntityList = enterpriseEntity.getUserAccountEntityList();
            userAccountEntityList.remove(userAccountEntity);
        }
        return userAccountEntityList;
    }
}
