package org.yijun.hie.persistence.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "Privilege", schema = "", catalog = "hiedb")
public class PrivilegeEntity {
    @Id
    @Column(name = "id_privilege")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPrivilege;

    @Column(name = "privilege_name")
    private String privilegeName;

    @Column(name = "privilege_file")
    private String privilegeFile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Role_Privilege", joinColumns = @JoinColumn(name = "id_privilege"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<RoleEntity> roleEntityList  = new LinkedList<RoleEntity>();

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public String getPrivilegeFile() {
        return privilegeFile;
    }

    public void setPrivilegeFile(String privilegeFile) {
        this.privilegeFile = privilegeFile;
    }

    public void setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
    }

    public Integer getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivilegeEntity that = (PrivilegeEntity) o;

        if (idPrivilege != that.idPrivilege) return false;
        if (privilegeName != null ? !privilegeName.equals(that.privilegeName) : that.privilegeName != null)
            return false;

        return true;
    }
}
