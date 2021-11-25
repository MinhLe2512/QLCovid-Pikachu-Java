package com.example.qlcovid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.example.qlcovid.string.UtilsString;

public class UserAccount extends Account {
	private String citizenId;
	private String fullName;
	private LocalDate dateOfBirth;
	private String address;
	private String condition;
	private int treatmentPlaceId;
	private String relatedTo;
	
	public String getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the treatmentPlaceId
	 */
	public int getTreatmentPlaceId() {
		return treatmentPlaceId;
	}
	/**
	 * @param treatmentPlaceId the treatmentPlaceId to set
	 */
	public void setTreatmentPlaceId(int treatmentPlaceId) {
		this.treatmentPlaceId = treatmentPlaceId;
	}
	/**
	 * @return the relatedTo
	 */
	public String getRelatedTo() {
		return relatedTo;
	}
	/**
	 * @param relatedTo the relatedTo to set
	 */
	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}
}
