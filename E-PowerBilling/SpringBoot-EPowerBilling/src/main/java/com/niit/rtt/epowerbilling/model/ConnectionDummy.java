package com.niit.rtt.epowerbilling.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class ConnectionDummy {
	private int connectionId;
	
	private int userId;
	
	

	private String connectionName;

	private String connectionType;

	private Date connectionStartDate;

	private List<BillDetails> bill;
	
	private int billId;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	private int billAmount;
	

	private Date dueDate;

	//private Date submitDate;

	private Date generationDate;

	private String status;

	private String consumedUnits;


	public ConnectionDummy() {
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public Date getConnectionStartDate() {
		return connectionStartDate;
	}

	public void setConnectionStartDate(Date connectionStartDate) {
		this.connectionStartDate = connectionStartDate;
	}

	public List<BillDetails> getBill() {
		return bill;
	}

	public void setBill(List<BillDetails> bill) {
		this.bill = bill;
	}
	
	public int getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConsumedUnits() {
		return consumedUnits;
	}

	public void setConsumedUnits(String consumedUnits) {
		this.consumedUnits = consumedUnits;
	}

}
