package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.PrivilegeEntity;
import org.yijun.hie.persistence.entity.RoleEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;

import java.util.List;


/**
 * Created by liuyijun on 14-11-8.
 */

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private String getUserByNameHql = "from UserAccountEntity where userName = :userName";
    //private String getUserByEnterpriseHql = "from UserAccountEntity where  "
    private String getEnterpriseHql = "from EnterpriseEntity";
    private String getEnterpriseOneHql = "from EnterpriseEntity where idEnterprise = :idEnterprise";
    private String getRoleByNameHql = "from RoleEntity where roleName = :roleName";
    private String getPrivilegeByNameHql = "from PrivilegeEntity where privilegeName = :privilegeName";

    public List<UserAccountEntity> getUserByName (String userName) {
        Session session = sessionFactory.getCurrentSession();
        List<UserAccountEntity> userAccountEntityList;

        userAccountEntityList = session.createQuery(getUserByNameHql).setString("userName", userName).list();
        return userAccountEntityList;
    }

    public void addUserAccount (UserAccountEntity userAccountEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(userAccountEntity);
        session.flush();
    }

    public void updateUserAccount (UserAccountEntity userAccountEntity){
        Session session = sessionFactory.getCurrentSession();
        session.update(userAccountEntity);
        session.flush();
    }

    public List<EnterpriseEntity> getEnterprise () {
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseEntity> enterpriseEntityList;

        enterpriseEntityList = session.createQuery(getEnterpriseHql).list();
        return enterpriseEntityList;
    }

    public List<RoleEntity> getRole (String roleName) {
        Session session = sessionFactory.getCurrentSession();
        List<RoleEntity> roleEntityList;
        roleEntityList = session.createQuery(getRoleByNameHql).setString("roleName", roleName).list();
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
        RoleEntity roleEntity = userAccountEntity.getRoleEntity();
        List<PrivilegeEntity> privilegeEntityList;
        privilegeEntityList = roleEntity.getPrivilegeEntityList();
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

    public List<UserAccountEntity> getUserAccountsByEnterpriseFromUR (EnterpriseEntity enterpriseEntity){
        return enterpriseEntity.getUserAccountEntityList();

    }
}
