package com.app.entity.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.entity.MangaEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "manga", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Manga extends MangaEntity {

}
