package com.app.entity.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.entity.ContentEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "content", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Content extends ContentEntity {

}
