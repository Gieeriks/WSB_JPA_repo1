package com.capgemini.wsb.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postalCode;

	// Relacja dwustronna z Doctor
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DoctorEntity> doctors;

	// Relacja dwustronna z Patient
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PatientEntity> patients;

	// Gettery i settery

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Set<DoctorEntity> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<DoctorEntity> doctors) {
		this.doctors = doctors;
	}

	public Set<PatientEntity> getPatients() {
		return patients;
	}

	public void setPatients(Set<PatientEntity> patients) {
		this.patients = patients;
	}
}