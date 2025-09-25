package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.TransactionDetails;
import org.bank.util.JDBCConnections;

public class TransactionDAO {
	
	private static final String insert_transaction_details ="insert into transaction_details "
			+ "(Transaction_Type, Transaction_Amount, Transaction_Date, Transaction_Time, "
			+ "Account_Number, Balance_Amount, R_Account_Number) values (?,?,?,?,?,?,?)";
	
	private static final String get_transcation_details ="select * from transaction_details"
			+ " where Account_Number =?";
	public boolean insertTransactionDetails(TransactionDetails transactionDetails)
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(insert_transaction_details);
			preparedStatement.setString(1, transactionDetails.getTransactiontype());
			preparedStatement.setDouble(2, transactionDetails.getTransactionamount());
			preparedStatement.setDate(3, Date.valueOf(transactionDetails.getTransactiondate()));
			preparedStatement.setTime(4,Time.valueOf( transactionDetails.getTransactiontime()));
			preparedStatement.setLong(5, transactionDetails.getAccountnumber());
			preparedStatement.setDouble(6, transactionDetails.getBalanceamount());
			preparedStatement.setLong(7, transactionDetails.getRaccountnumber());
			int result =preparedStatement.executeUpdate();
			return result==1;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<TransactionDetails> checkStatement(long accountnumber)
	{
		
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(get_transcation_details);
			preparedStatement.setLong(1, accountnumber);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				List <TransactionDetails> list = new ArrayList<>();
				while(resultSet.next())
				{
					TransactionDetails transactionDetails = new TransactionDetails();
					transactionDetails.setTransactionid(resultSet.getInt("Transaction_Id"));
					transactionDetails.setTransactiontype(resultSet.getString("Transaction_Type"));
					transactionDetails.setTransactionamount(resultSet.getDouble("Transaction_Amount"));
					transactionDetails.setTransactiondate(resultSet.getDate("Transaction_Date").toLocalDate());
					transactionDetails.setTransactiontime(resultSet.getTime("Transaction_Time").toLocalTime());
					transactionDetails.setAccountnumber(resultSet.getLong("Account_Number"));
					transactionDetails.setBalanceamount(resultSet.getDouble("Balance_Amount"));
					transactionDetails.setRaccountnumber(resultSet.getLong("R_Account_Number"));
					list.add(transactionDetails);
				}
				return list;
			}
			else
			   System.out.println("No transactions");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		
		}
		return null;
	}
}
