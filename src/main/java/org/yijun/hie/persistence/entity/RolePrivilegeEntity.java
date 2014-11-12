package org.yijun.hie.persistence.entity;

import javax.persistence.*;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Role_Privilege", schema = "", catalog = "hiedb")
public class RolePrivilegeEntity {
    @Id
    @Column(name = "id_role_privilege")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRolePrivilege;

    @Column(name = "id_privilege")
    private Integer idPrivilege;

    @Column(name = "id_role")
    private Integer idRole;

    public Integer getIdRolePrivilege() {
        return idRolePrivilege;
    }

    public void setIdRolePrivilege(Integer idRolePrivilege) {
        this.idRolePrivilege = idRolePrivilege;
    }

    public Integer getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePrivilegeEntity that = (RolePrivilegeEntity) o;

        if (idPrivilege != that.idPrivilege) return false;
        if (idRole != that.idRole) return false;
        if (idRolePrivilege != that.idRolePrivilege) return false;

        return true;
    }
}
