package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bank.dto.CustomerDetails;
import org.bank.util.JDBCConnections;

public class AdminDAO {
	Scanner scanner= new Scanner(System.in);
	private static final String admin_login="select *from admin_details where admin_email_id=?"
			+ " and admin_password=?";
	
	public static boolean selectAdminDetailsByUsingEmailAndPassword(String email,String password) {
		
		try {
			Connection connection =JDBCConnections.forMySqlConnection();
			PreparedStatement preparedStatement =connection.prepareStatement(admin_login);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet =preparedStatement.executeQuery();
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

}
