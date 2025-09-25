package org.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.TransactionDAO;
import org.bank.dto.TransactionDetails;

public class TransactionService {
	Scanner scanner = new Scanner(System.in);
	TransactionDAO transactionDAO = new TransactionDAO();
	
	public void insertTranstionDetails(TransactionDetails transactionDetails)
	{
		if(transactionDAO.insertTransactionDetails(transactionDetails))
		{
			System.out.println("Transaction details Inserted");
		}
		else
		{
			System.out.println("Sever 500");
		}
	}
	public void checkStatement()
	{
		System.out.println("Enter the account number :");
		long accountnumber =scanner.nextLong();
		List <TransactionDetails> list = transactionDAO.checkStatement(accountnumber);
		if(list!=null)
		{
			list.stream().forEach((transaction)->System.out.println(transaction));
		}
	}
}
