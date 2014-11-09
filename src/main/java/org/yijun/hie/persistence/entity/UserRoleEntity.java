package org.yijun.hie.persistence.entity;

import javax.persistence.*;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "User_Role", schema = "", catalog = "hiedb")
public class UserRoleEntity {
    @Id
    @Column(name = "id_user_role")
    private Integer idUserRole;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "id_user_account")
    private Integer idUserAccount;

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount) {
        this.idUserAccount = idUserAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if (idRole != that.idRole) return false;
        if (idUserAccount != that.idUserAccount) return false;
        if (idUserRole != that.idUserRole) return false;

        return true;
    }
}
