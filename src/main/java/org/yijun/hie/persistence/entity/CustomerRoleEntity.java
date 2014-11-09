package org.yijun.hie.persistence.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liuyijun on 14-11-8.
 */
@Entity
@Table(name = "role")
@DiscriminatorValue(value = "customer_role_entity")
public class CustomerRoleEntity extends RoleEntity {
}
