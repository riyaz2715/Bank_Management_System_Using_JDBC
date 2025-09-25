package org.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.bank.dao.AdminDAO;
import org.bank.dto.CustomerDetails;

public class AdminService {
	Scanner scanner = new Scanner(System.in);
	CustomerService customerService =new CustomerService();
//	CustomerDetails customerDetails = new CustomerDetails();
	public void adminLogin()
	{
		System.out.println("Enter Admin Email-Id :");
		String aemail=scanner.next();
		System.out.println("Enter Admin Password :");
		String apassword=scanner.next();
		if(AdminDAO.selectAdminDetailsByUsingEmailAndPassword(aemail,apassword))
		{
			System.out.println("Login Successful...");
			System.out.println("Enter \n 1.To Get All Customers Details"
					               + "\n 2.To Accept Pending Details"
					               + "\n 3.To Get All Account Closing Requests");
			switch(scanner.nextInt())
			{
			case 1:
				System.out.println("1.To Get All Customers Details");
				
				break;
			case 2:
				System.out.println("2.To Accept Pending Details");
				acceptPendingDetails();
				break;
			case 3:
				System.out.println("3.To Get All Account Closing Requests");
				
				break;
			default:
				System.out.println("Invalid Request");
				break;	
			}
		}
		else
		{
			System.out.println("Invalid Emailid and Password");
		}
	}
	
	public List<CustomerDetails> allPendingDetails()
	{
		List<CustomerDetails> allPendingDetails=customerService.getAllPendingDetails();
		for(CustomerDetails customerDetails:allPendingDetails)
			{
				System.out.println("Customer Name: "+customerDetails.getCusteomername());
				System.out.println("Customer Email-Id : "+customerDetails.getCusteomeremailId());
				String st =""+customerDetails.getCusteomermobilenumber();
				st=st.substring(0,3)+"****"+st.substring(7);
				System.out.println("Customer Mobile Number : "+st);
				System.out.println("Customer Gender : "+customerDetails.getGender());
				System.out.println("Customer Address : "+customerDetails.getAddress());
				System.out.println("Customer Amount : "+customerDetails.getAmount());
				System.out.println("----------------------------------------------");
				
			}
		System.out.println("Enter EmailId of The Customers");
		String emaildi=scanner.next();
		//List<CustomerDetails>list
		return allPendingDetails.stream()
				.filter((customer)->customer.getCusteomeremailId()
						.equals(emaildi)).collect(Collectors.toList());
		//System.out.println(list);
		//acceptPendingDetails();
	}
	
	public void acceptPendingDetails()
	{
		//List<CustomerDetails>list =allPendingDetails();
		CustomerDetails customerDetails =allPendingDetails().get(0);
		Random random =new Random();
		int accountnumber =random.nextInt(10000000);
		if(accountnumber<10000000)
			accountnumber+=10000000;
		
		int pin =random.nextInt(1000);
		if(pin<1000)
			pin+=1000;
		
		customerDetails.setAccountnumber(accountnumber);
		customerDetails.setPinnumber(pin);
		customerDetails.setIfsccode("RIYAZ2707");
		customerDetails.setStatus("ACTIVE");
		
		if(customerService.updateAccountNumberAndPinAndIFSCCodeAndStatus(customerDetails))
		{
			System.out.println(customerDetails.getCusteomername()+" Details Updated Succesfully");
			System.out.println(customerDetails.getAccountnumber()+ " Customer Accoount Number");
			System.out.println(customerDetails.getPinnumber()+ " Customer Pin Number");
		}
	}
	
}

