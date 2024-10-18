package com.galaxe.applicationForm;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;

	private static int counter=987654;
	private int paymentId;
	private float amount;
	private LocalDateTime ldt = LocalDateTime.now();
	private String paymentMode, paymentFor;
	
	
	public Payment(float amount, String paymentMode, String paymentFor) {
		this.paymentId = counter;
		counter+=1;
		
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentFor = paymentFor;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDateTime getLdt() {
		return ldt;
	}
	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentFor() {
		return paymentFor;
	}
	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}
	
}
