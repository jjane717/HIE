package org.yijun.hie.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Order", schema = "", catalog = "hiedb")
public class OrderEntity {
    @Id
    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_user_account")
    private Integer idUserAccount;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
    @JoinColumn(name = "id_product")
    public ProductEntity productEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = PaymentEntity.class, mappedBy = "orderEntity")
    List<PaymentEntity> paymentEntityList = new LinkedList<PaymentEntity>();

    public List<PaymentEntity> getPaymentEntityList() {
        return paymentEntityList;
    }

    public void setPaymentEntityList(List<PaymentEntity> paymentEntityList) {
        this.paymentEntityList = paymentEntityList;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount) {
        this.idUserAccount = idUserAccount;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != that.idOrder) return false;
        if (idUserAccount != that.idUserAccount) return false;
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

}
