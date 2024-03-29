package com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.app.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class MangaEntity extends BaseEntity implements Serializable {
	
	@Column(name="name")
	private String name;
	
	@Column(name="synopsis")
	private String synopsis;
	
	@Column(name="path_cover_image")
	private String pathCoverImage;
}
