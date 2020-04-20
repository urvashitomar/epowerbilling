package com.niit.rtt.epowerbilling.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
//@Table(name="billDetails")
public class BillDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="billId")
	private int billId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="connId")
	private ConnectionDetails connectiondetails;
	
	@OneToOne(mappedBy="billDetails", cascade= {CascadeType.ALL})
	private Payment payment;
	

	public ConnectionDetails getConnectiondetails() {
		return connectiondetails;
	}

	public void setConnectiondetails(ConnectionDetails connectiondetails) {
		this.connectiondetails = connectiondetails;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="billAmount")
	private int billAmount;
	
	@Column(name="dueDate")
	private Date dueDate;
	
	@Column(name="submitDate")
	private Date submitDate;
	
	@Column(name="genertionDate")
	private Date generationDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="consumedUnits")
	private String consumedUnits;

	

	public BillDetails() {
		super();
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public ConnectionDetails getConnection() {
		return connectiondetails;
	}

	public void setConnection(ConnectionDetails connection) {
		this.connectiondetails = connection;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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
