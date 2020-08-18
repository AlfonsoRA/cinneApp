package com.alfonso.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity // anotacion que identifica la clase como entidad
@Table(name="horarios") // nombre de la tabla en bd
public class Horario {

	@Id //Anotacion que indica que el atributo es la clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Auto_increment MYSQL
	private int id;
	private Date fecha;
	private String hora;
	private String sala;
	private double precio;
	//@Transient
	@ManyToOne //relacion muchos a uno
	@JoinColumn(name="idPelicula") //une una columna de tabla pelicula a tabla horario 
	private Pelicula pelicula;

	public Horario() {
		super();
	}
	
	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", sala=" + sala + ", precio=" + precio
				+ ", pelicula=" + pelicula + "]";
	}
}
