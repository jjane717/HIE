package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by liuyijun on 14-11-8.
 */

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private String getUserByNameHql = "from UserAccountEntity where userName = :userName";
    private String getUserAccountHql = "from UserAccountEntity";
    private String getUserAccountByIDHql = "from UserAccountEntity where idUserAccount = :idUserAccount";
    private String getEnterpriseHql = "from EnterpriseEntity";
    private String getRoleHql = "from RoleEntity ";
    private String getRoleByIDHql = "from RoleEntity where idRole = :idRole";
    private String getEnterpriseOneHql = "from EnterpriseEntity where idEnterprise = :idEnterprise";
    private String getEnterpriseGroupHql = "from EnterpriseEntity where enterpriseType = :enterpriseType";
    private String getRoleByNameHql = "from RoleEntity where roleName = :roleName";
    private String getPrivilegeByNameHql = "from PrivilegeEntity where privilegeName = :privilegeName";
    private String getPrivilegeByIdHql = "from PrivilegeEntity where idPrivilege = :idPrivilege";

    public UserAccountEntity getUserByName (String userName) {
        Session session = sessionFactory.getCurrentSession();
        UserAccountEntity userAccountEntity = null;
        if(session.createQuery(getUserByNameHql).setString("userName", userName).list().size()>0) {
            userAccountEntity = (UserAccountEntity) session.createQuery(getUserByNameHql).setString("userName", userName).list().get(0);
        }
        return userAccountEntity;
    }

    public List<UserAccountEntity> getAllUserAccount(){
        Session session = sessionFactory.getCurrentSession();
        List<UserAccountEntity> userAccountEntityList;

        userAccountEntityList = session.createQuery(getUserAccountHql).list();
        return userAccountEntityList;
    }

    public UserAccountEntity addUserAccount (UserAccountEntity userAccountEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(userAccountEntity);
        session.flush();
        return userAccountEntity;
    }

    public void updateUserAccount (UserAccountEntity userAccountEntity){
        Session session = sessionFactory.getCurrentSession();
        session.update(userAccountEntity);
        session.flush();
    }


    public List<UserAccountEntity> getUserAccountsByEnterpriseFromUR (EnterpriseEntity enterpriseEntity){
        return enterpriseEntity.getUserAccountEntityList();
    }

    public UserAccountEntity getUserAccountByIDFromUR(Integer idUserAccount){
        Session session = sessionFactory.getCurrentSession();
        UserAccountEntity userAccountEntity = (UserAccountEntity)session.createQuery(getUserAccountByIDHql).setInteger("idUserAccount", idUserAccount).list().get(0);
        return userAccountEntity;
    }

    public List<EnterpriseEntity> getEnterprise () {
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseEntity> enterpriseEntityList;
        enterpriseEntityList = session.createQuery(getEnterpriseHql).list();
        return enterpriseEntityList;
    }

    public List<EnterpriseEntity> getEnterpriseGroupFromUR (String enterpriseType) {
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseEntity> enterpriseEntityList;
        if(enterpriseType.equals("Employee")){
            enterpriseEntityList = session.createQuery(getEnterpriseHql).list();
        }else{
            enterpriseEntityList = session.createQuery(getEnterpriseGroupHql).setString("enterpriseType", enterpriseType).list();
        }
        return enterpriseEntityList;
    }

    public List<RoleEntity> getRole (String roleName) {
        Session session = sessionFactory.getCurrentSession();
        List<RoleEntity> roleEntityList;
        roleEntityList = session.createQuery(getRoleByNameHql).setString("roleName", roleName).list();
        return roleEntityList;
    }

    public RoleEntity getRoleByIDFromUR (Integer idRole) {
        Session session = sessionFactory.getCurrentSession();
        RoleEntity roleEntity;
        roleEntity = (RoleEntity)session.createQuery(getRoleByIDHql).setInteger("idRole",idRole).list().get(0);
        return roleEntity;
    }

    public EmployeeRoleEntity getEmployeeRoleByIDFromUR (Integer idRole) {
        Session session = sessionFactory.getCurrentSession();
        EmployeeRoleEntity employeeRoleEntity;
        employeeRoleEntity = (EmployeeRoleEntity)session.createQuery(getRoleByIDHql).setInteger("idRole",idRole).list().get(0);
        return employeeRoleEntity;
    }

    public List<RoleEntity> getRoleListFromUR () {
        Session session = sessionFactory.getCurrentSession();
        List<RoleEntity> roleEntityList;
        roleEntityList = session.createQuery(getRoleHql).list();
        return roleEntityList;
    }

    public List<PrivilegeEntity> getPrivilege (String privilegeName) {
        Session session = sessionFactory.getCurrentSession();
        List<PrivilegeEntity> privilegeEntityList;
        privilegeEntityList = session.createQuery(getPrivilegeByNameHql).setString("privilegeName", privilegeName).list();
        return privilegeEntityList;
    }

    public void addPrivilege (RoleEntity role, PrivilegeEntity privilege) {
        role.getPrivilegeEntityList().add(privilege);
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
        session.flush();
    }

    public List<PrivilegeEntity>  getPrivilegeByRole (UserAccountEntity userAccountEntity){
        Session session = sessionFactory.getCurrentSession();
        List<PrivilegeEntity> privilegeEntityList;
        privilegeEntityList = userAccountEntity.getRoleEntity().getPrivilegeEntityList();
        return privilegeEntityList;
    }

    public EnterpriseEntity getEnterpriseOneFromUR (Integer idEnterprise){
        Session session = sessionFactory.getCurrentSession();
        EnterpriseEntity enterpriseEntity;
        enterpriseEntity = (EnterpriseEntity)session.createQuery(getEnterpriseOneHql).setInteger("idEnterprise",idEnterprise).list().get(0);
        return enterpriseEntity;
    }

    public void updateEnterpriseFromUR (EnterpriseEntity enterpriseEntity){
        Session session = sessionFactory.getCurrentSession();
        session.update(enterpriseEntity);
        session.flush();
    }

    public EnterpriseEntity addEnterpriseFromUR (EnterpriseEntity enterpriseEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(enterpriseEntity);
        session.flush();
        return enterpriseEntity;
    }

    public void deleteEnterpriseFromUR (EnterpriseEntity enterpriseEntity){
        Session session = sessionFactory.getCurrentSession();
        session.delete(enterpriseEntity);
        session.flush();
    }

    public PrivilegeEntity getPrivilegeByIdFromRepository(Integer idPrivilege){
        Session session = sessionFactory.getCurrentSession();
        PrivilegeEntity privilegeEntity = (PrivilegeEntity) session.createQuery(getPrivilegeByIdHql).setInteger("idPrivilege", idPrivilege).list().get(0);
        return privilegeEntity;
    }

    public void changePrivilegeForUserRoleFromRepository(EmployeeRoleEntity employeeRoleEntity){
        Session session = sessionFactory.getCurrentSession();
        session.update(employeeRoleEntity);
        session.flush();
    }
}
