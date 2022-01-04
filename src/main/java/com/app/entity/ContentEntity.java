package com.app.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;

import com.app.base.BaseEntity;
import com.app.entity.bean.Manga;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@SuppressWarnings("serial")
public class ContentEntity extends BaseEntity implements Serializable {
	
	@JsonIgnore
	@ManyToOne
	@Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
	@JoinColumn(name = "", referencedColumnName = "id", nullable = false)
	private Manga manga;
	
	@Column(name="page_number")
	private Integer numberPage;
	
	@Column(name="path_page_image")
	private String pathPageImage;
}
