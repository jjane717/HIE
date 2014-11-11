package org.yijun.hie.persistence.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Role", schema = "", catalog = "hiedb")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "role_type",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "role_entity")
public class RoleEntity {
    @Id
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = UserAccountEntity.class, mappedBy = "roleEntity")
    private List<UserAccountEntity> userAccountEntities = new LinkedList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Role_Privilege", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_privilege"))
    private List<PrivilegeEntity> privilegeEntityList  = new LinkedList<>();

    public List<UserAccountEntity> getUserAccountEntities() {
        return userAccountEntities;
    }

    public void setUserAccountEntities(List<UserAccountEntity> userAccountEntities) {
        this.userAccountEntities = userAccountEntities;
    }

    public List<PrivilegeEntity> getPrivilegeEntityList() {
        return privilegeEntityList;
    }

    public void setPrivilegeEntityList(List<PrivilegeEntity> privilegeEntityList) {
        this.privilegeEntityList = privilegeEntityList;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (idRole != that.idRole) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }
}
