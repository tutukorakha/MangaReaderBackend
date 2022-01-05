package com.app.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ENTITY_FLAG_NOT_DELETED = 0;
	public static final int ENTITY_FLAG_IS_DELETED = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "code", length = 64, nullable = true)
	private String code;
	
	@Column(name = "created_at", updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdAt;
	
	@Column(name = "created_by", updatable = false)
	@JsonIgnore
	private Integer createdBy;
	
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date updatedAt;
	
	@Column(name = "updated_by", updatable = false)
	@JsonIgnore
	private Integer updatedBy;
	
	@Column(name = "is_deleted", columnDefinition = "bigint default 0")
	@JsonIgnore
	private Integer isDeleted;
	
	@PrePersist
	protected void onCreate() {
		if (createdAt == null) {
			createdAt = new Date();
		}
	}
}
