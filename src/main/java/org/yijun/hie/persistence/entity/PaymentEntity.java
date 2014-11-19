package org.yijun.hie.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Payment", schema = "", catalog = "hiedb")
public class PaymentEntity {
    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPayment;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "is_pay")
    private Boolean isPay;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = OrderEntity.class)
    @JoinColumn(name = "id_order")
    public OrderEntity orderEntity;

    @JsonIgnore
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (idPayment != that.idPayment) return false;
        if (isPay != that.isPay) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (payDate != null ? !payDate.equals(that.payDate) : that.payDate != null) return false;

        return true;
    }

}
