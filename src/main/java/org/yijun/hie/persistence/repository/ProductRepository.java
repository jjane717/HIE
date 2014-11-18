package org.yijun.hie.persistence.repository;

import com.sun.tools.javac.comp.Enter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private String getProductEntityForEnterpriseEntityHql ="from EnterpriseProductEntity where idEnterprise = :idEnterprise";
    private String getProductByIDHql = "from ProductEntity where idProduct = :idProduct";
    private String getEnterpriseProductByIdProductForProductHql = "from EnterpriseProductEntity where idProduct = :idProduct";
    private String getEnterpriseProductByIDHql = "from EnterpriseProductEntity where idEnterpriseProduct = :idEnterpriseProduct";

    public ProductEntity getProductByIDFromRepository(Integer idProduct){
        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity = (ProductEntity)session.createQuery(getProductByIDHql).setInteger("idProduct",idProduct).list().get(0);
        return productEntity;
    }

    public void updateEnterpriseProductStatusFromRepository(EnterpriseProductEntity enterpriseProductEntity){
        Session session = sessionFactory.getCurrentSession();
        session.update(enterpriseProductEntity);
        session.flush();
    }

    public EnterpriseEntity findInsuranceForProductFromRepository(ProductEntity productEntity){
        Session session = sessionFactory.getCurrentSession();
        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        List<EnterpriseProductEntity> enterpriseProductEntityList = session.createQuery(getEnterpriseProductByIdProductForProductHql).setInteger("idProduct", productEntity.getIdProduct()).list();
        for(EnterpriseProductEntity enterpriseProductEntity: enterpriseProductEntityList){
            if ((enterpriseProductEntity.getEnterpriseEntity().getEnterpriseType()).equals("Insurance")){
                enterpriseEntity = enterpriseProductEntity.getEnterpriseEntity();
                break;
            }
        }
        return enterpriseEntity;
    }

    public List<EnterpriseEntity> getHIEEnterpriseListByIdProductFromRepository(List<EnterpriseEntity> hieEnterpriseList, Integer idProduct, Boolean isHIE){
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseProductEntity> enterpriseProductEntityList = session.createQuery(getEnterpriseProductByIdProductForProductHql).setInteger("idProduct", idProduct).list();
        List<EnterpriseEntity> newHIEList = new LinkedList<EnterpriseEntity>();
        for(EnterpriseEntity enterpriseEntity:hieEnterpriseList){
            Boolean flag = false;
            for(EnterpriseProductEntity enterpriseProductEntity:enterpriseProductEntityList){
                if(enterpriseProductEntity.getIdEnterprise() == enterpriseEntity.getIdEnterprise()){
                    flag = true;
                }
            }
            if(flag == isHIE){
                newHIEList.add(enterpriseEntity);
            }
        }

        return newHIEList;
    }


    public List<EnterpriseProductEntity> getProductEntityForEnterpriseFromRepository(Integer idEnterprise){
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseProductEntity> enterpriseProductEntityList= session.createQuery(getProductEntityForEnterpriseEntityHql).setInteger("idEnterprise", idEnterprise).list();
        return enterpriseProductEntityList;
    }

    public EnterpriseProductEntity getEnterpriseProductEntityByID(Integer idEnterpriseProduct){
        Session session = sessionFactory.getCurrentSession();
        return  (EnterpriseProductEntity) session.createQuery(getEnterpriseProductByIDHql).setInteger("idEnterpriseProduct", idEnterpriseProduct).list().get(0);
    }

    public ProductEntity createProductEntityFromRepository(ProductEntity productEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(productEntity);
        session.flush();
        EnterpriseProductEntity enterpriseProductEntity = (EnterpriseProductEntity)session.createQuery(getEnterpriseProductByIdProductForProductHql).setInteger("idProduct", productEntity.getIdProduct()).list().get(0);
        enterpriseProductEntity.setStatus(true);
        session.update(enterpriseProductEntity);
        session.flush();
        return productEntity;
    }

    public void placeProductFromRepository(EnterpriseProductEntity enterpriseProductEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(enterpriseProductEntity);
        session.flush();
    }

//    @SuppressWarnings("unchecked")
//    public List<EnterpriseProductEntity> getProductsByEnterpriseId(int enterpriseId) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery(getProductEntityForEnterpriseEntityHql).setInteger("idEnterprise", enterpriseId).list();
//    }

}
