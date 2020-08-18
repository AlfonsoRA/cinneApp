package com.alfonso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //anotacion que identifica la clase como entidad
@Table(name="detalles") //nombre de la tabla en bd
public class Detalle {
	@Id //Anotacion que indica que el atributo es la clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Auto_increment MYSQL
	private int id;
	private String director;
	private String actores;
	private String sinopsis;
	private String trailer;
	
	public Detalle() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", director=" + director + ", actores=" + actores + ", sinopsis=" + sinopsis
				+ ", trailer=" + trailer + "]";
	}
}
