package org.bank.dao;
import org.bank.util.*;

import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bank.dto.CustomerDetails;

/**
 * 
 */
public class CustomerDAO {
	
	private static final String insert_customer_details =
	"insert into customer_details(Customer_Name, Customer_Email_id,"
	+ "Customer_Mobile_Number, Customer_Pan_Number, Customer_Aadhar_Number,"
	+ " Customer_Gender, Customer_Date_Of_Birth, Customer_Address, "
	+ "Customer_Status, Customer_Amount) values (?,?,?,?,?,?,?,?,?,?)";
	
	// Select all records of customer
	private static final String select_all_customers="select * from customer_details";
	
	private static final String select_by_using_status="select * from customer_details where Customer_Status='Pending'";
	
	
	//update customer details
	
	private static final String update_customer_details ="update customer_details set "
			+ "Customer_Account_Number=? , Customer_Pin=?,Customer_IFSC_Code=?,Customer_Status=? where Customer_Email_id=?"; 
	// customer login
	private static final String customer_login ="select * from customer_details "
			+ "where Customer_Email_id=? or Customer_Mobile_Number=?";
	
	private static final String customer_logon_with_pin ="select * from customer_details where ("
			+ "Customer_Email_id=? or Customer_Mobile_Number=?) and Customer_Pin=?";
	
	
	// Select customer details by using account number
	
	private static final String select_customer_details_by_using_account_number ="select * from customer_details"
			+ " where Customer_Account_Number=?";
	// Customer Account Balance by using account number 
	private static final String update_balance_amount = "update customer_details set "
			+ "Customer_Amount =?  where Customer_Account_Number=?";
	
	//update the pin number 
	
	//private static final String upadate_pin_number = "update customer_details set Customer_Pin=? where Customer_Account_Number=?";

	private static final String upadate_pin_number = "update customer_details set Customer_Pin=? where (Customer_Email_id=? or Customer_Mobile_Number=?)";
	public boolean insertCustomerdetails(CustomerDetails customerDetails )
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1, customerDetails.getCusteomername());
			preparedStatement.setString(2, customerDetails.getCusteomeremailId());
			preparedStatement.setLong(3, customerDetails.getCusteomermobilenumber());
			preparedStatement.setString(4, customerDetails.getPannumber());
			preparedStatement.setLong(5, customerDetails.getAadharunumber());
			preparedStatement.setString(6, customerDetails.getGender());
			preparedStatement.setDate(7, customerDetails.getDateofbirth()); // java.sql.Date
			preparedStatement.setString(8, customerDetails.getAddress());
			preparedStatement.setString(9, "Pending");
			preparedStatement.setDouble(10, customerDetails.getAmount());
			int result = preparedStatement.executeUpdate();
			if(result!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CustomerDetails> getAllCustomerDetails()
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(select_all_customers);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<CustomerDetails> listofcustomers =new ArrayList<>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					CustomerDetails customerDetails =new CustomerDetails();
					customerDetails.setAadharunumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCusteomeremailId(resultSet.getString("Customer_Email_id"));
					customerDetails.setCusteomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					listofcustomers.add(customerDetails);
				}
				return listofcustomers;
			}
			else
			{
				return listofcustomers;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CustomerDetails> selectCustomerDetailsByUsingStatus()
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(select_by_using_status);
			ResultSet resultSet =preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomerDetails =new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					CustomerDetails customerDetails =new CustomerDetails();
					customerDetails.setCusteomername(resultSet.getString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setDateofbirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setAadharunumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCusteomeremailId(resultSet.getString("Customer_Email_id"));
					customerDetails.setCusteomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					listOfCustomerDetails.add(customerDetails);
				}
				return listOfCustomerDetails;
			}
			else
			{
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<CustomerDetails> getAllCustomersRecords()
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(select_all_customers);
			ResultSet resultSet =preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomerDetails =new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					CustomerDetails customerDetails =new CustomerDetails();
					customerDetails.setCusteomername(resultSet.getString("Customer_Name"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setDateofbirth(resultSet.getDate("Customer_Date_Of_Birth"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
					customerDetails.setAadharunumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setCusteomeremailId(resultSet.getString("Customer_Email_id"));
					customerDetails.setCusteomermobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					listOfCustomerDetails.add(customerDetails);
				}
				return listOfCustomerDetails;
			}
			else
			{
				return null;
			}
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String selectByUsingEmailidOrMobileNumber(String emailid)
	{
		Connection connection;
		try {
			connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(customer_login);
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, emailid);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				String status=resultSet.getString("Customer_Status");
				return status;
			}
			else
			{
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean selectCustomerByUsingPin(String emailId ,int customer_pin)
	{
		try {
			Connection connection =JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(customer_logon_with_pin);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, emailId);
			preparedStatement.setInt(3, customer_pin);
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	public boolean updateAccountNumberAndPinAndIFSCCodeAndStatus(CustomerDetails customerDetails)
	{
		try {
			Connection connection =JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(update_customer_details);
			preparedStatement.setLong(1, customerDetails.getAccountnumber());
			preparedStatement.setInt(2, customerDetails.getPinnumber());
			preparedStatement.setString(3, customerDetails.getIfsccode());
			preparedStatement.setString(4, customerDetails.getStatus());
			preparedStatement.setString(5, customerDetails.getCusteomeremailId());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public CustomerDetails selectCustomerDetailsByUsingAccountNumber(long accountNumber)
	{
		try {
			Connection connection =JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(select_customer_details_by_using_account_number);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setAmount(resultSet.getDouble("Customer_Amount"));
				customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
				return customerDetails;
			}
			else
			{
				return null;
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public boolean updateBalanceAmountByUsingAccountNumber(long accountnumber, double amount)
	{
		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(update_balance_amount);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setLong(2, accountnumber);
		    int result =preparedStatement.executeUpdate();
		    return result!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean changepinNumber(/*long accountnumber*/ String emailidorphone ,int pin)
	{

		try {
			Connection connection = JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(upadate_pin_number);
			preparedStatement.setInt(1, pin);
			preparedStatement.setString(2, emailidorphone);
			preparedStatement.setString(3, emailidorphone);
		    int result =preparedStatement.executeUpdate();
		    return result!=0;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
