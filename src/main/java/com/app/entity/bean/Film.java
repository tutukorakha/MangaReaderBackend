package com.app.entity.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.entity.FilmEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "film", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Film extends FilmEntity {

}
