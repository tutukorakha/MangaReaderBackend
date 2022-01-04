package com.app.entity.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.entity.UserEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class User extends UserEntity {

}
