package org.yijun.hie.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idOrder;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = EnterpriseProductEntity.class)
    @JoinColumn(name = "id_enterprise_product")
    public EnterpriseProductEntity enterpriseProductEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserAccountEntity.class)
    @JoinColumn(name = "id_user_account")
    public UserAccountEntity userAccountEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = PaymentEntity.class, mappedBy = "orderEntity")
    List<PaymentEntity> paymentEntityList = new LinkedList<PaymentEntity>();

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<PaymentEntity> getPaymentEntityList() {
        return paymentEntityList;
    }

    public void setPaymentEntityList(List<PaymentEntity> paymentEntityList) {
        this.paymentEntityList = paymentEntityList;
    }

    public EnterpriseProductEntity getEnterpriseProductEntity() {
        return enterpriseProductEntity;
    }

    public void setEnterpriseProductEntity(EnterpriseProductEntity enterpriseProductEntity) {
        this.enterpriseProductEntity = enterpriseProductEntity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    @JsonIgnore
    public UserAccountEntity getUserAccountEntity() {
        return userAccountEntity;
    }

    public void setUserAccountEntity(UserAccountEntity userAccountEntity) {
        this.userAccountEntity = userAccountEntity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

}
