package org.bank.main;

import java.util.Scanner;

import org.bank.dao.AdminDAO;
import org.bank.service.AdminService;
import org.bank.service.CustomerService;

public class Main {
	public static void main(String[] args) {
		CustomerService  customerService = new CustomerService();
		AdminService adminService = new AdminService();
		System.out.println("***---Welcome to Riyaz Bank---***");
		System.out.println("Enter "
				+ "\n1.For Customer Login "
				+ "\n2.For Customer Registration "
				+ "\n3.For Admin Login");
		Scanner scanner =new Scanner(System.in);
		
		switch(scanner.nextInt())
		{
		case 1:
			System.out.println("Customer Login");
			customerService.customerLogin();
			break;
		case 2:
			System.out.println("Customer Registration");
			customerService.customerRegistration();
			break;
		case 3:
			System.out.println("Admin Login");
			adminService.adminLogin();
			break;
		default:
			System.out.println("Invalid Request");
			break;	
		}
		
		scanner.close();
	}

}
