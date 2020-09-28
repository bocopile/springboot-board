package com.example.demo.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@DynamicInsert
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer no;
	
	private String username;
	
	private String password;
	
	private String name;
	
	@Column(updatable = false, nullable= false)
	@CreationTimestamp
	private Timestamp created;
	
	@UpdateTimestamp
	@Column(insertable = false, updatable = true)
	private Timestamp modified;
	
	@ColumnDefault("'N'")
	private String delYn;
	
	public User() {
		
	}
	
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.delYn = "N";
	}
	
	


}
