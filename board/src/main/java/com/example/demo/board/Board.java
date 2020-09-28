package com.example.demo.board;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.user.User;

import lombok.Data;

@Entity
@Data
@DynamicInsert
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer no;
	
	private String title;
	
	@Column(length=2048)
	private String contents;
	
	@OneToOne
	@JoinColumn(name="creatorNo")
	private User creator;
	
	@OneToOne
	@JoinColumn(name="modifierNo")
	private User modifier;
	
	@CreationTimestamp
	@Column(updatable = false, nullable= false)	
	private Timestamp created;
	
	@UpdateTimestamp
	@Column(insertable = false, updatable = true)	
	private Timestamp modified;
	
	@ColumnDefault("'N'")
	private String delYn; 
	
	public Board() {}
	
	public Board(String title, String contents, User creator) {
		this.title = title;
		this.contents = contents;
		this.creator = creator;
	}
	


}
