package com.cg.ba.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cg.ba.beans.Account;
import com.cg.ba.ui.MyBankApplication;

public class RegisterLoginDAOImpl implements RegisterLoginDAO {
	Account account=new Account();
	MyBankApplication myBank=new MyBankApplication();
	//Scanner sc=new Scanner(System.in);

	public int register() {
		// TODO Auto-generated method stub
		int count=0,accNo=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "new", "oracle");
			PreparedStatement preparedStatement= con.prepareStatement("insert into customer_details(first_name,last_name,email_id,password,pancard_no,aadhar_no,address,mobile_no,balance) values(?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,account.getfName());
			preparedStatement.setString(2, account.getlName());
			preparedStatement.setString(3,account.getEmailId());
			preparedStatement.setString(4,account.getPassword());
			preparedStatement.setString(5, account.getPancard());
			preparedStatement.setLong(6, account.getAadhar());
			preparedStatement.setString(7, account.getAddress());
			preparedStatement.setLong(8, account.getMobileNo());
			preparedStatement.setDouble(9, 0.0);
			int i=preparedStatement.executeUpdate();
			PreparedStatement preparedStatement1= con.prepareStatement("select*from customer_details where aadhar_no=?");
			preparedStatement1.setLong(1, account.getAadhar());
			ResultSet resultSet=preparedStatement1.executeQuery();
			while(resultSet.next())
			{
				accNo=resultSet.getInt(1);
				count++;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accNo;
}

	public double login(long accNo,String password) {
		// TODO Auto-generated method stub
		double bal=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "new", "oracle");
			PreparedStatement preparedStatement1= con.prepareStatement("select * from customer_details where account_no=? and password=?");
			preparedStatement1.setLong(1, accNo);
			preparedStatement1.setString(2, password);
			ResultSet resultSet=preparedStatement1.executeQuery();
			while(resultSet.next())
			{
				bal=resultSet.getDouble(10);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	

}
