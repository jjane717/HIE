package org.yijun.hie.persistence.entity;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Product", schema = "", catalog = "hiedb")
public class ProductEntity {
    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "co_Pay")
    private double coPay;

    @Column(name = "deductible")
    private double deductible;

    @Column(name = "co_Insurance")
    private double coInsurance;

    @Column(name = "offer_price")
    private double offerPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "targetMarket")
    private String targetMarket;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Enterprise_Product", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_enterprise"))
    private List<EnterpriseEntity> enterpriseEntities  = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = OrderEntity.class, mappedBy = "productEntity")
    List<OrderEntity> orderEntityList = new LinkedList<>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTargetMarket() {
        return targetMarket;
    }

    public void setTargetMarket(String targetMarket) {
        this.targetMarket = targetMarket;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public List<EnterpriseEntity> getEnterpriseEntities() {
        return enterpriseEntities;
    }

    public void setEnterpriseEntities(List<EnterpriseEntity> enterpriseEntities) {
        this.enterpriseEntities = enterpriseEntities;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public double getCoPay() {
        return coPay;
    }

    public void setCoPay(double coPay) {
        this.coPay = coPay;
    }

    public double getDeductible() {
        return deductible;
    }

    public void setDeductible(double deductible) {
        this.deductible = deductible;
    }

    public double getCoInsurance() {
        return coInsurance;
    }

    public void setCoInsurance(double coInsurance) {
        this.coInsurance = coInsurance;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (Double.compare(that.coInsurance, coInsurance) != 0) return false;
        if (Double.compare(that.coPay, coPay) != 0) return false;
        if (Double.compare(that.deductible, deductible) != 0) return false;
        if (idProduct != that.idProduct) return false;
        if (offerName != that.offerName) return false;
        if (Double.compare(that.offerPrice, offerPrice) != 0) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

}
