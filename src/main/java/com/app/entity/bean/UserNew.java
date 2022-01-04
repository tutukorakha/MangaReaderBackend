package com.app.entity.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.entity.UserNewEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "userNew", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class UserNew extends UserNewEntity {

}
