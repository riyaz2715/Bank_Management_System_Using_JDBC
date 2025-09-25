/**
 * 
 */
package org.bank.dto;

import java.sql.Date;

public class CustomerDetails {
	
	private int customerif;
	private String custeomername;
	private String custeomeremailId;
	private long custeomermobilenumber;
	private long  aadharunumber;
	private String pannumber;
	private long accountnumber;
	private int pinnumber;
	private String gender;
	private String address;
	private Date dateofbirth;
	private String ifsccode;
	private double amount;
	private String status;
	public CustomerDetails() {
		
	}
	public CustomerDetails(int customerif, String custeomername, String custeomeremailId, long custeomermobilenumber,
			long aadharunumber, String pannumber, long accountnumber, int pinnumber, String gender, String address,
			Date dateofbirth, String ifsccode, double amount, String status) {
		super();
		this.customerif = customerif;
		this.custeomername = custeomername;
		this.custeomeremailId = custeomeremailId;
		this.custeomermobilenumber = custeomermobilenumber;
		this.aadharunumber = aadharunumber;
		this.pannumber = pannumber;
		this.accountnumber = accountnumber;
		this.pinnumber = pinnumber;
		this.gender = gender;
		this.address = address;
		this.dateofbirth = dateofbirth;
		this.ifsccode = ifsccode;
		this.amount = amount;
		this.status = status;
	}
	public int getCustomerif() {
		return customerif;
	}
	public void setCustomerif(int customerif) {
		this.customerif = customerif;
	}
	public String getCusteomername() {
		return custeomername;
	}
	public void setCusteomername(String custeomername) {
		this.custeomername = custeomername;
	}
	public String getCusteomeremailId() {
		return custeomeremailId;
	}
	public void setCusteomeremailId(String custeomeremailId) {
		this.custeomeremailId = custeomeremailId;
	}
	public long getCusteomermobilenumber() {
		return custeomermobilenumber;
	}
	public void setCusteomermobilenumber(long custeomermobilenumber) {
		this.custeomermobilenumber = custeomermobilenumber;
	}
	public long getAadharunumber() {
		return aadharunumber;
	}
	public void setAadharunumber(long aadharunumber) {
		this.aadharunumber = aadharunumber;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public int getPinnumber() {
		return pinnumber;
	}
	public void setPinnumber(int pinnumber) {
		this.pinnumber = pinnumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
