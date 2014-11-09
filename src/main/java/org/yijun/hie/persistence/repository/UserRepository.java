package org.yijun.hie.persistence.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
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

    public List<UserAccountEntity> getUserByName (String userName) {
        Session session = sessionFactory.openSession();
        List<UserAccountEntity> userAccountEntityList;

        userAccountEntityList = session.createQuery(getUserByNameHql).setString("userName", userName).list();

//        session.flush();
        session.close();
        return userAccountEntityList;
    }

    public List<EnterpriseEntity> getEnterprise () {
        Session session = sessionFactory.openSession();
        List<EnterpriseEntity> enterpriseEntityList;

        enterpriseEntityList = session.createQuery(getEnterprise).list();

//        session.flush();
        session.close();
        return enterpriseEntityList;
    }

}
