package org.bank.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
	
	private int transactionid;
	private String transactiontype;
	private double transactionamount;
	private LocalDate transactiondate;
	private LocalTime transactiontime;
	private long accountnumber;
	private double balanceamount;
	private long raccountnumber;
	
	public TransactionDetails()
	{
	}
	
	public TransactionDetails(int transactionid, String transactiontype, double transactionamount,
			LocalDate transactiondate, LocalTime transactiontime, long accountnumber, double balanceamount,
			long raccountnumber) {
		super();
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactionamount = transactionamount;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.accountnumber = accountnumber;
		this.balanceamount = balanceamount;
		this.raccountnumber = raccountnumber;
	}


	public int getTransactionid() {
		return transactionid;
	}


	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}


	public String getTransactiontype() {
		return transactiontype;
	}


	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}


	public double getTransactionamount() {
		return transactionamount;
	}


	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}


	public LocalDate getTransactiondate() {
		return transactiondate;
	}


	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}


	public LocalTime getTransactiontime() {
		return transactiontime;
	}


	public void setTransactiontime(LocalTime transactiontime) {
		this.transactiontime = transactiontime;
	}


	public long getAccountnumber() {
		return accountnumber;
	}


	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}


	public double getBalanceamount() {
		return balanceamount;
	}


	public void setBalanceamount(double balanceamount) {
		this.balanceamount = balanceamount;
	}


	public long getRaccountnumber() {
		return raccountnumber;
	}


	public void setRaccountnumber(long raccountnumber) {
		this.raccountnumber = raccountnumber;
	}

	@Override
	public String toString() {
		return "TransactionDetails [transactionid=" + transactionid + ", transactiontype=" + transactiontype
				+ ", transactionamount=" + transactionamount + ", transactiondate=" + transactiondate
				+ ", transactiontime=" + transactiontime + ", accountnumber=" + accountnumber + ", balanceamount="
				+ balanceamount + ", raccountnumber=" + raccountnumber + "]";
	}
	
	
	
	

}
