package com.mywallet.person.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mywallet.core.domain.utilitary.ValidatorUtils;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@Column(name = "per_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "per_name", nullable = false, length = 150)
	private String name;

	@Column(name = "per_surname", nullable = false, length = 250)
	private String surname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = ValidatorUtils.isNullOrEmpty(name) ? null : name.trim();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = ValidatorUtils.isNullOrEmpty(surname) ? null : surname.trim();
	}


}
