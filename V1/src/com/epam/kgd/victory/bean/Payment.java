package com.epam.kgd.victory.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long paymentId;
	private int buyerId;
	private int lotId;
	private String dateOfPayment;
	private BigDecimal value;
	
	public Payment(){
		
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getLotId() {
		return lotId;
	}

	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	public String getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buyerId;
		result = prime * result + ((dateOfPayment == null) ? 0 : dateOfPayment.hashCode());
		result = prime * result + lotId;
		result = prime * result + (int) (paymentId ^ (paymentId >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (buyerId != other.buyerId)
			return false;
		if (dateOfPayment == null) {
			if (other.dateOfPayment != null)
				return false;
		} else if (!dateOfPayment.equals(other.dateOfPayment))
			return false;
		if (lotId != other.lotId)
			return false;
		if (paymentId != other.paymentId)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", buyerId=" + buyerId + ", lotId=" + lotId + ", dateOfPayment="
				+ dateOfPayment + ", value=" + value + "]";
	}
	
	
	
	
	

}
