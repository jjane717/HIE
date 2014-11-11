package org.yijun.hie.persistence.entity;

import javax.persistence.*;

/**
 * Created by liuyijun on 14-11-11.
 */
//@Entity
//@Table(name = "User_Role", schema = "", catalog = "hiedb")
//public class UserRoleEntity {
//    private int idUserRole;
//    private int idRole;
//    private int idUserAccount;
//
//    @Id
//    @Column(name = "id_user_role")
//    public int getIdUserRole() {
//        return idUserRole;
//    }
//
//    public void setIdUserRole(int idUserRole) {
//        this.idUserRole = idUserRole;
//    }
//
//    @Basic
//    @Column(name = "id_role")
//    public int getIdRole() {
//        return idRole;
//    }
//
//    public void setIdRole(int idRole) {
//        this.idRole = idRole;
//    }
//
//    @Basic
//    @Column(name = "id_user_account")
//    public int getIdUserAccount() {
//        return idUserAccount;
//    }
//
//    public void setIdUserAccount(int idUserAccount) {
//        this.idUserAccount = idUserAccount;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserRoleEntity that = (UserRoleEntity) o;
//
//        if (idRole != that.idRole) return false;
//        if (idUserAccount != that.idUserAccount) return false;
//        if (idUserRole != that.idUserRole) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idUserRole;
//        result = 31 * result + idRole;
//        result = 31 * result + idUserAccount;
//        return result;
//    }
//}
