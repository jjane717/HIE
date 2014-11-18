package org.yijun.hie.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by liuyijun on 14-11-10.
 */
@Entity
@Table(name = "Enterprise_Product", schema = "", catalog = "hiedb")
public class EnterpriseProductEntity {
    @Id
    @Column(name = "id_enterprise_product")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEnterpriseProduct;

    @Column(name = "id_enterprise", insertable = false, updatable = false)
    private int idEnterprise;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = EnterpriseEntity.class)
    @JoinColumn(name = "id_enterprise")
    private EnterpriseEntity enterpriseEntity;

    @Column(name = "id_product", insertable = false, updatable = false)
    private int idProduct;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;

    public int getIdEnterpriseProduct() {
        return idEnterpriseProduct;
    }

    public void setIdEnterpriseProduct(int idEnterpriseProduct) {
        this.idEnterpriseProduct = idEnterpriseProduct;
    }

    public int getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(int idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public EnterpriseEntity getEnterpriseEntity() {
        return enterpriseEntity;
    }

    public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
        this.enterpriseEntity = enterpriseEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterpriseProductEntity that = (EnterpriseProductEntity) o;

        if (idEnterprise != that.idEnterprise) return false;
        if (idEnterpriseProduct != that.idEnterpriseProduct) return false;
        if (idProduct != that.idProduct) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEnterpriseProduct;
        result = 31 * result + idEnterprise;
        result = 31 * result + idProduct;
        return result;
    }
}
