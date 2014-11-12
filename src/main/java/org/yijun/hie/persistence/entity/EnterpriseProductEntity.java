package org.yijun.hie.persistence.entity;

import javax.persistence.*;

/**
 * Created by liuyijun on 14-11-10.
 */
@Entity
@Table(name = "Enterprise_Product", schema = "", catalog = "hiedb")
public class EnterpriseProductEntity {
    private int idEnterpriseProduct;
    private int idEnterprise;
    private int idProduct;

    @Id
    @Column(name = "id_enterprise_product")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdEnterpriseProduct() {
        return idEnterpriseProduct;
    }

    public void setIdEnterpriseProduct(int idEnterpriseProduct) {
        this.idEnterpriseProduct = idEnterpriseProduct;
    }

    @Basic
    @Column(name = "id_enterprise")
    public int getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(int idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    @Basic
    @Column(name = "id_product")
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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
