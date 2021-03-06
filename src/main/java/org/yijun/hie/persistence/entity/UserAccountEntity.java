package org.yijun.hie.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "User_Account", schema = "", catalog = "hiedb")
public class UserAccountEntity {
    @Id
    @Column(name = "id_user_account")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idUserAccount;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "age")
    private Integer age;

    @Column(name = "is_small_business")
    private Boolean isSmallBusiness;

    @Column(name = "is_family")
    private Boolean isFamily;

    @Column(name = "income_status")
    private String incomeStatus;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = EnterpriseEntity.class)
    @JoinColumn(name = "id_enterprise")
    public EnterpriseEntity enterpriseEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = RoleEntity.class)
    @JoinColumn(name = "id_role")
    private RoleEntity roleEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = OrderEntity.class, mappedBy = "userAccountEntity")
    List<OrderEntity> orderEntityList = new LinkedList<OrderEntity>();

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @JsonIgnore
    public EnterpriseEntity getEnterpriseEntity() {
        return enterpriseEntity;
    }

    public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
        this.enterpriseEntity = enterpriseEntity;
    }

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount) {
        this.idUserAccount = idUserAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsSmallBusiness() {
        return isSmallBusiness;
    }

    public void setIsSmallBusiness(Boolean isSmallBusiness) {
        this.isSmallBusiness = isSmallBusiness;
    }

    public Boolean getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(Boolean isFamily) {
        this.isFamily = isFamily;
    }

    public String getIncomeStatus() {
        return incomeStatus;
    }

    public void setIncomeStatus(String incomeStatus) {
        this.incomeStatus = incomeStatus;
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

        UserAccountEntity that = (UserAccountEntity) o;

        if (age != that.age) return false;
        if (idUserAccount != that.idUserAccount) return false;
        if (isFamily != that.isFamily) return false;
        if (isSmallBusiness != that.isSmallBusiness) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (incomeStatus != null ? !incomeStatus.equals(that.incomeStatus) : that.incomeStatus != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

        return true;
    }

}
