package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.ProductEntity;

import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String getProductEntityForEnterpriseEntityHql ="select ProductEntity from EnterpriseProductEntity where idEnterprise = :idEnterprise";

    public List<ProductEntity> getProductEntityForEnterpriseFromRepository(Integer idEnterprise){
        Session session = sessionFactory.getCurrentSession();
        List<ProductEntity> productEntityList = session.createQuery(getProductEntityForEnterpriseEntityHql).setInteger("idEnterprise", idEnterprise).list();
        return productEntityList;
    }

}
