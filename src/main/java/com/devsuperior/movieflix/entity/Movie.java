package com.devsuperior.movieflix.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_movie")
public class Movie implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String title; 
	
	private String subTitle; 
	
	private Integer year; 
	
	private String imgUrl;
	
	@Size(min = 5, max = 1000)
	private String synopsis;
	
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;
	
	@OneToMany(mappedBy = "movie")
	private List<Review> reviews = new ArrayList<>();
	
	public Movie() {} 
	
	public Movie(Long id, String title, String subtitle, Integer year, String imgUrl, String synopsis, Genre genre) {	
		this.id = id;
		this.title = title;
		this.subTitle = subtitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
		this.genre = genre; 
		
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public Genre getGenre() {
		return genre;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
