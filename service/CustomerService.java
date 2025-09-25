package org.bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	CustomerDetails customerDetails =new CustomerDetails();
	Scanner sc = new Scanner(System.in);
	TransactionService transactionService = new TransactionService(); 
	public void customerRegistration() throws CustomerInvalidDataException
	{
		
		CustomerDAO customerDAO =new CustomerDAO();
		List<CustomerDetails> allcustomerdetails =customerDAO.getAllCustomerDetails();
		//customer Name
		System.out.println("Enter Customer Name : ");
		String cname=sc.next();

		// customer  Email id
		System.out.println("Enter Customer Emailid : ");

		while(true)
		{
			String cemail=sc.next();
			try
			{
				if(cemail.endsWith("@gmail.com"))
				{
					long emailcount =allcustomerdetails.stream().filter((customer)->customer.getCusteomeremailId().equals(cemail)).count();
					try
					{
					if(emailcount>0)
					{
						throw new CustomerInvalidDataException("Already Email Existed");
					
					}
					else
					{
					    customerDetails.setCusteomeremailId(cemail);
					    break;
					}
					}
					catch(CustomerInvalidDataException e)
					{
						System.out.println("Already Email-id Existed");
						System.out.println("Re-Enter Email-id");
					}
				}
				else
				{
					
					throw new CustomerInvalidDataException("Invalid emailid");					
				}
			}
			catch(CustomerInvalidDataException e)
			{
				System.out.println("Invalid Email-id Existed");
				System.out.println("Re-Enter Email-id");
			}
		}
		
		//customer mobile number
		System.out.println("Enter Customer Mobile Number : ");
		while(true)
		{
			long cmobilenumber=sc.nextLong();
			
			try
			{
				if(cmobilenumber>=6000000000l && cmobilenumber<=9999999999l)
				{
					long mobilecount =allcustomerdetails.stream().filter((customers)->customers.getCusteomermobilenumber()==cmobilenumber).count();
					try
					{
						if(mobilecount>0)
						{
							throw new CustomerInvalidDataException("Already Mobile Number existed");
						}
						else
						{
						    customerDetails.setCusteomermobilenumber(cmobilenumber);
						    break;
						}
					}
					catch(CustomerInvalidDataException e)
					{
						System.out.println("Already Mobile Number Existed");
						System.out.println("Re-Enter the Mobile Number :");
					}
				}
				else
				{
					throw new CustomerInvalidDataException("Invalid Mobile Number");
				}
			}
			catch(CustomerInvalidDataException e)
			{
				System.out.println("Invalid Mobile Number");
				System.out.println("Re-Enter the Mobile Number :");
			}
		}
		
		// customer aadher number
		System.out.println("Enter Customer Aadhar Number : ");
		long caadharnumber=sc.nextLong();
		if( caadharnumber>=100000000000l && caadharnumber<=999999999999l)
		{
			long addharcount=allcustomerdetails.stream().filter((customer)->customer.getAadharunumber()==caadharnumber).count();
			if(addharcount>0)
			{
				throw new CustomerInvalidDataException("Already Aadhar Number Existed");
			}
			else
			{
			    customerDetails.setAadharunumber(caadharnumber);
			} 
		}
		else
		{
			throw new CustomerInvalidDataException("Invalid Aadhar Number");
		}
		
		//customer pan number
		System.out.println("Enter Customer Pan Number : ");
		String cpannumber=sc.next();
		if(cpannumber.length()!=10)
		{
			throw new CustomerInvalidDataException("Invalid Pan Number");
		}
		for(int i=0;i<5;i++)
		{
			if(cpannumber.charAt(i)>='A'&& cpannumber.charAt(i)<='Z')
			{
			}
			else
			{
				throw new CustomerInvalidDataException("Invalid Pan Number");
			}
		}
		for(int i=5;i<9;i++)
		{
			if(cpannumber.charAt(i)>='0'&& cpannumber.charAt(i)<='9')
			{
			}
			else
			{
				throw new CustomerInvalidDataException("Invalid Pan Number");
			}
		}
		if(cpannumber.charAt(9)>='A'&& cpannumber.charAt(9)<='Z')
		{
			 customerDetails.setPannumber(cpannumber);
		}
		else
		{
			throw new CustomerInvalidDataException("Invalid Pan Number");
		}
		
		//customer gender
		System.out.println("Enter Customer Gender : ");
		String cgender=sc.next();
		if(cgender.equalsIgnoreCase("male")|| cgender.equalsIgnoreCase("female"))
		{
			 customerDetails.setGender(cgender);
		}
		else
		{
			throw new CustomerInvalidDataException("Invalid Gender");
		}
		
		//customer  Data of birth
		System.out.println("Enter Customer Date of Birth : ");
		String dob=sc.next();
		
		// customer Address
		System.out.println("Enter Customer Address : ");
		String caddress=sc.next();
		
		// Amount
		System.out.println("Enter Amount : ");
		double amount=sc.nextDouble();
		if(amount<0)
		{
			throw new CustomerInvalidDataException("Invalid Amount");
		}
		else
		{
			  customerDetails.setAmount(amount);
		}
		
	        customerDetails.setCusteomername(cname);
	        customerDetails.setDateofbirth(Date.valueOf(dob));
	        customerDetails.setAddress(caddress);	        
	        if(customerDAO.insertCustomerdetails(customerDetails))
	        {
	        	System.out.println("Registration successfull ....");
	        }
	        else
	        {
	        	System.out.println("server error 500");
	        }
	}
	
	public List<CustomerDetails> getAllPendingDetails(){
		return customerDAO.selectCustomerDetailsByUsingStatus();
	}
	
	public void customerLogin()
	{
		System.out.println("Enter Customer Email-Id or Mobile Number : ");
		String emailidormobilenumber = sc.next();
		
		String status = customerDAO.selectByUsingEmailidOrMobileNumber(emailidormobilenumber);
		try
		{
			if(status.equals("ACTIVE"))
			{ 
				System.out.println("Enter the password : " );
				int pin =sc.nextInt();
				if(customerDAO.selectCustomerByUsingPin(emailidormobilenumber, pin))
				{
					System.out.println("Login Successful...");
					customerOperations(emailidormobilenumber,pin);
				}
				else
				{
					System.out.println("Invalid PIN");
				}
			}
			else
			{
				System.out.println("Account is still under processing....");
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("Invalid Credintials");
		}
	}
	
	public boolean updateAccountNumberAndPinAndIFSCCodeAndStatus(CustomerDetails customerDetails)
	{
		return customerDAO.updateAccountNumberAndPinAndIFSCCodeAndStatus(customerDetails);
	}
	
	
	public void customerOperations(String customerEmail , int pin)
	{
		while(true)
		{
			System.out.println("Enter \n 1.For Debit "
					+ "\n 2.For Credit"
					+ "\n 3.For Check Balance"
					+ "\n 4.For Check statement"
					+ "\n 5.For Mobile Transaction"
					+ "\n 6.For Change Pin"
					+ "\n 7.For Closing Account");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("Debit");
				customerDebitOperation(customerEmail,pin);
				break;
			case 2:
				System.out.println("Credit");
				customerCebitOperation(customerEmail, pin);
				break;
			case 3:
				System.out.println("Check Balance");
				checkBalance(pin);
				break;
			case 4:
				System.out.println("Check statement");
				transactionService.checkStatement();
				break;
			case 5:
				System.out.println("Mobile Transaction");
				break;
			case 6:
				System.out.println("Change Pin");
				changePin(pin);
				break;
			case 7:
				System.out.println("Closing Account");
				break;
			}
			System.out.println();
			System.out.println("***-------------------***");
			System.out.println("Do you want to Continue \nEnter \n1.For Yes \n2.For No");
			if(sc.nextInt()==1)
			{
				continue;
			}
			else
			{
				System.out.println("Thank you Visit Again");
				break;
			}
		}
	}
	
	public void customerDebitOperation(String customerEmail ,int pin)
	{
		System.out.println("Enter the Account Number : ");
		long account_number=sc.nextLong();
		CustomerDetails customer_details =customerDAO.selectCustomerDetailsByUsingAccountNumber(account_number);
		int pin_number =customer_details.getPinnumber();
		if(customer_details!=null && pin==pin_number)
		{
			System.out.println("Enter the Ammount to be debited : ");
			double amount =sc.nextDouble();
			if(amount>0)
			{
				double databaseammount =customer_details.getAmount();
				if( amount <= databaseammount)
				{
					double balanceamount = databaseammount-amount;
					System.out.println(balanceamount);
					boolean update= customerDAO.updateBalanceAmountByUsingAccountNumber(account_number, balanceamount);
					if(update)
					{
						System.out.println("Amount Debited Successfully...");
						TransactionDetails transactionDetails =new TransactionDetails();
						transactionDetails.setTransactiontype("DEBIT");
						transactionDetails.setTransactionamount(amount);
						transactionDetails.setTransactiondate(LocalDate.now());
						transactionDetails.setTransactiontime(LocalTime.now());
						transactionDetails.setAccountnumber(account_number);
						transactionDetails.setBalanceamount(balanceamount);
						transactionDetails.setRaccountnumber(account_number);
						transactionService.insertTranstionDetails(transactionDetails);
					}
					else
					{
						System.out.println("Under Process...");
					}
				}
				else
				{
					System.out.println("In-Sufficient amount");
				}
				
			}
			else
			{
				System.out.println("InAmount amount");
			}
		}
		else
		{
			System.out.println("Invalid Account Number");
		}
	}
	public void customerCebitOperation(String customerEmail ,int pin)
	{
		System.out.println("Enter the Account Number : ");
		long account_number=sc.nextLong();
		CustomerDetails customer_details =customerDAO.selectCustomerDetailsByUsingAccountNumber(account_number);
		int pin_number =customer_details.getPinnumber();
		if(customer_details!=null && pin==pin_number)
		{
			System.out.println("Enter the amount to be debited : ");
			double amount = sc.nextDouble();
			if(amount>0)
			{
				double databaseammount =customer_details.getAmount();
				double balanceamount = databaseammount+amount;
				boolean update= customerDAO.updateBalanceAmountByUsingAccountNumber(account_number, balanceamount);
				if(update)
				{
					System.out.println("Amount Debited Successfully...");
					TransactionDetails transactionDetails =new TransactionDetails();
					transactionDetails.setTransactiontype("CREDIT");
					transactionDetails.setTransactionamount(amount);
					transactionDetails.setTransactiondate(LocalDate.now());
					transactionDetails.setTransactiontime(LocalTime.now());
					transactionDetails.setAccountnumber(account_number);
					transactionDetails.setBalanceamount(balanceamount);
					transactionDetails.setRaccountnumber(account_number);
					transactionService.insertTranstionDetails(transactionDetails);
				}
				else
				{
					System.out.println("Under Process...");
				}
			}
			else
			{
				System.out.println("Invalid Amount");
			}
		}
		else
		{
			System.out.println("Invalid Account Number");
		}
	}
	public void checkBalance(int pin)
	{
		System.out.println("Enter the Account Number : ");
		long account_number=sc.nextLong();
		CustomerDetails customer_details =customerDAO.selectCustomerDetailsByUsingAccountNumber(account_number);
		int pin_number =customer_details.getPinnumber();
		if(customer_details!=null && pin==pin_number)
		{
			double databaseammount =customer_details.getAmount();
			System.out.println("Account Balance : "+databaseammount);
		}
		else
		{
			System.out.println("Invalid account number");
		}
	}
	
	public void changePin(int pin)
	{
		System.out.println("Enter the Account Number : ");
		long account_number=sc.nextLong();
		System.out.println("Enter the email or phoneno");
		String emailorphone = sc.next();
		CustomerDetails customer_details =customerDAO.selectCustomerDetailsByUsingAccountNumber(account_number);
		int pin_number =customer_details.getPinnumber();
		if(customer_details!=null && pin==pin_number)
		{
			System.out.println("Enter new Pin number ");
			int newpin = sc.nextInt();
			if(newpin>1000 && newpin<10000)
			{
				System.out.println("Confirm new Pin number ");
				int confPin = sc.nextInt();
				if(confPin==newpin)
				{
					customer_details.setPinnumber(newpin);
					if(customerDAO.changepinNumber(emailorphone, newpin))
					{
						System.out.println("Pin changed succesfully");
						System.out.println("Your Account Number : "+account_number);
						System.out.println("Your new pin is :"+newpin);
					}
					else
					{
						System.out.println("Server 500");
					}
					
				}
				else
				{
					System.out.println("New Pin didn't match");
				}
			}
			else
			{
				System.out.println("Invalid Pin number");
			}
		}
	}
}
