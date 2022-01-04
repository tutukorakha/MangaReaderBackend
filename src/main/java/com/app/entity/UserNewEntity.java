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
public class UserNewEntity extends BaseEntity implements Serializable {

	@Column(name = "email", length = 128, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "nama_lengkap")
	private String nama_lengkap;
	
	@Column(name = "tanggal_lahir")
	private String tanggalLahir;
	
	@Column(name = "gender")
	private String gender;
}
