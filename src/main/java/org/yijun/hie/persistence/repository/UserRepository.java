package org.yijun.hie.persistence.repository;

import org.eclipse.jetty.util.ajax.JSON;
import org.hibernate.HibernateException;
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
    private String getEnterprise = "from EnterpriseEntity";
    private String getRoleByName = "from RoleEntity where roleName = :roleName";
    private String getPrivilegeByName = "from PrivilegeEntity where privilegeName = :privilegeName";

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

        enterpriseEntityList = session.createQuery(getEnterprise).list();
        return enterpriseEntityList;
    }

    public List<RoleEntity> getRole (String roleName) {
        Session session = sessionFactory.getCurrentSession();
        List<RoleEntity> roleEntityList;
        roleEntityList = session.createQuery(getRoleByName).setString("roleName", roleName).list();
        return roleEntityList;
    }

    public List<PrivilegeEntity> getPrivilege (String privilegeName) {
        Session session = sessionFactory.getCurrentSession();
        List<PrivilegeEntity> privilegeEntityList;
        privilegeEntityList = session.createQuery(getPrivilegeByName).setString("privilegeName", privilegeName).list();
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


}
