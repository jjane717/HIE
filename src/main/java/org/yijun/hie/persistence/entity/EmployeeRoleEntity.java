package org.yijun.hie.persistence.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by liuyijun on 14-11-19.
 */
@Entity
@DiscriminatorValue(value = "employee_role_entity")
public class EmployeeRoleEntity extends RoleEntity{
    @Column(name = "id_employee")
    private Integer idEmployee;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }
}
