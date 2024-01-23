package com.kuso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member{
	@Id
	@Column(name = "account",nullable = false)
    private String account;
	
	@Column(name = "username",nullable = false)
    private String username;
}
