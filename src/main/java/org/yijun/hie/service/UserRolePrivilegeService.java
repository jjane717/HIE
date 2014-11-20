package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yijun.hie.persistence.entity.EmployeeRoleEntity;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.PrivilegeEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-11.
 */

@Service
public class UserRolePrivilegeService {

    @Autowired
    private UserRepository userRepository;

    public List<PrivilegeEntity> getParticularPrivileges (UserAccountEntity userAccountEntity) {
        return userRepository.getPrivilegeByRole(userAccountEntity);
    }

    public List<UserAccountEntity> getAllAdminUserRole(){
        List<EnterpriseEntity> enterpriseEntityList = userRepository.getEnterprise();
        List<UserAccountEntity> userAccountEntityList = new LinkedList<UserAccountEntity>();
        for(EnterpriseEntity enterpriseEntity : enterpriseEntityList){
            for(UserAccountEntity userAccountEntity: enterpriseEntity.getUserAccountEntityList()){
                if(userAccountEntity.getRoleEntity().getRoleName().contains("Admin")){
                    userAccountEntityList.add(userAccountEntity);
                }
            }
        }

        return userAccountEntityList;
    }

    public UserAccountEntity getUserAccountByIDFromService(Integer idUserAccount){
        return userRepository.getUserAccountByIDFromUR(idUserAccount);
    }

    public PrivilegeEntity getPrivilegeByIdFromService(Integer idPrivilege){
        return userRepository.getPrivilegeByIdFromRepository(idPrivilege);
    }

    public EmployeeRoleEntity getEmployeeRoleFromService(Integer idRole){
        return userRepository.getEmployeeRoleByIDFromUR(idRole);
    }

    public void changePrivilegeForUserRoleFromService(Integer idRole, String[] strings){
        List<PrivilegeEntity> privilegeEntityList = new LinkedList<PrivilegeEntity>();
        EmployeeRoleEntity employeeRoleEntity = getEmployeeRoleFromService(idRole);
        for(String str : strings){
            Integer idPrivilege = Integer.valueOf(str);
            PrivilegeEntity privilegeEntity = getPrivilegeByIdFromService(idPrivilege);
            privilegeEntityList.add(privilegeEntity);
        }
        employeeRoleEntity.setPrivilegeEntityList(privilegeEntityList);
        userRepository.changePrivilegeForUserRoleFromRepository(employeeRoleEntity);
    }
}
