package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;

import java.util.List;

/**
 * Created by liuyijun on 14-11-18.
 */
@Repository
public class EnterpriseRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private String geEnterpriseFroTypesHql = "from EnterpriseEntity where enterpriseType = :enterpriseType";

    public List<EnterpriseEntity> getEnterpriseListForTypeFromRepository(String type){
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseEntity> enterpriseEntityList = session.createQuery(geEnterpriseFroTypesHql).setString("enterpriseType",type).list();
        return enterpriseEntityList;
    }

}
