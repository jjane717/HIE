package org.yijun.hie.persistence.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Enterprise", schema = "", catalog = "hiedb")
public class EnterpriseEntity {
    @Id
    @Column(name = "id_enterprise")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEnterprise;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "enterprise_type")
    private String enterpriseType;

    @Column(name = "enterprise_balance")
    private double enterpriseBalance;

    @Column(name = "enterprise_code")
    private Integer enterpriseCode;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = UserAccountEntity.class, mappedBy = "enterpriseEntity")
    List<UserAccountEntity> userAccountEntityList = new LinkedList<UserAccountEntity>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Enterprise_Product", joinColumns = @JoinColumn(name = "id_enterprise"), inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<ProductEntity> productEntityList  = new LinkedList<ProductEntity>();

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public List<UserAccountEntity> getUserAccountEntityList() {
        return userAccountEntityList;
    }

    public void setUserAccountEntityList(List<UserAccountEntity> userAccountEntityList) {
        this.userAccountEntityList = userAccountEntityList;
    }

    public Integer getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(Integer idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public double getEnterpriseBalance() {
        return enterpriseBalance;
    }

    public void setEnterpriseBalance(double enterpriseBalance) {
        this.enterpriseBalance = enterpriseBalance;
    }

    public Integer getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(Integer enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterpriseEntity that = (EnterpriseEntity) o;

        if (Double.compare(that.enterpriseBalance, enterpriseBalance) != 0) return false;
        if (idEnterprise != that.idEnterprise) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (enterpriseName != null ? !enterpriseName.equals(that.enterpriseName) : that.enterpriseName != null)
            return false;
        if (enterpriseType != null ? !enterpriseType.equals(that.enterpriseType) : that.enterpriseType != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

        return true;
    }

}
