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
public class FilmEntity extends BaseEntity implements Serializable {

	@Column(name = "nama_film", length = 128, nullable = false)
	private String namaFilm;
	
	@Column(name = "sinopsis", nullable = false)
	private String sinopsis;
	
	@Column(name = "genre", nullable = false)
	private String genre;
	
	@Column(name = "harga_film", nullable = false)
	private Integer hargaFilm;
	
	@Column(name = "url_trailer", nullable = false)
	private String urlTrailer;
	
	@Column(name = "url_poster", nullable = false)
	private String urlPoster;
	
	@Column(name = "is_buy", nullable = false)
	private Boolean isBuy;
}
