package com.users.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user" ,uniqueConstraints =@UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String  firstname;
	
	@Column(name="last_name")
	private String  lastname;
	private String  email;
	private String password;
	
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",
	joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(
			name="role_id",referencedColumnName="id"))
	
	private Collection<Roles> Roles;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLasttname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Roles> getRoles() {
		return Roles;
	}
	public void setRoles(Collection<Roles> roles) {
		Roles = roles;
	}
	public User(String firstname, String lastname, String email, String password,
			Collection<com.users.model.Roles> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		Roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User() {
		
	}
	
	

}
