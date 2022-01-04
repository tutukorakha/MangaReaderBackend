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
@SuppressWarnings("serial")
public class UserEntity extends BaseEntity implements Serializable{
	
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "role", length = 128, nullable = false)
	private String role;
}