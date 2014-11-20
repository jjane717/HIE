package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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
        List<UserAccountEntity> userAccountEntityList = new LinkedList<UserAccountEntity>();
        UserAccountEntity userAccountEntity = loginService.userLogin();
        EnterpriseEntity enterpriseEntity = userAccountEntity.getEnterpriseEntity();

        List<UserAccountEntity> users = enterpriseEntity.getUserAccountEntityList();
        users.remove(userAccountEntity);
        for(UserAccountEntity u:users) {
            if (u.getRoleEntity().getRoleName().equals("Employee")) {
                userAccountEntityList.add(u);
            }
        }
        return userAccountEntityList;
    }

    @RequestMapping(value = "/choosePrivilege", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public List<PrivilegeEntity> getPrivilegeForUserRole(HttpServletRequest request){
        EmployeeRoleEntity employeeRoleEntity = userRolePrivilegeService.getEmployeeRoleFromService(Integer.valueOf(request.getParameter("id")));
        List<PrivilegeEntity> privilegeEntityList = employeeRoleEntity.getPrivilegeEntityList();
        return privilegeEntityList;
    }

    @RequestMapping(value = "/changePrivilege", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String changePrivilegeForUserRole(HttpServletRequest request){
        String[] strings = request.getParameterValues("selectedPrivilege");
        Integer idRole = Integer.valueOf(request.getParameter("idRole"));
        userRolePrivilegeService.changePrivilegeForUserRoleFromService(idRole,strings);
        return "You have already updated.";
    }
}
