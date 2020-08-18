package com.alfonso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	@NotEmpty(message="Introduzca su Usuario")
	private String cuenta;
	private String pwd;
	private Integer activo;	
	@Email(message="Formato del email incorrecto")
	@NotEmpty(message="Introduzca su Email")
	private String email;
	private String telefono;
	
	
	public Usuarios() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", cuenta=" + cuenta + ", pwd=" + pwd + ", activo=" + activo + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}
	
}
