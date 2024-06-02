package com.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class RegularUser  extends User{
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
