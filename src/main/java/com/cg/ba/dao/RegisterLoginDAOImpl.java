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
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "new", "oracle123");
			PreparedStatement preparedStatement= con.prepareStatement("insert into customer_details values(account_no_seq.nextval,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,account.getfName());
			preparedStatement.setString(2, account.getlName());
			preparedStatement.setString(3,account.getEmailId());
			preparedStatement.setString(4,account.getPassword());
			preparedStatement.setString(5, account.getPancard());
			preparedStatement.setLong(6, account.getAadhar());
			preparedStatement.setString(7, account.getAddress());
			preparedStatement.setLong(8, account.getMobileNo());
			preparedStatement.setDouble(9, account.getBalance());
			int i=preparedStatement.executeUpdate();
			if(i==1)
			{
			PreparedStatement preparedStatement1= con.prepareStatement("select*from customer_details where aadhar_no=?");
			preparedStatement1.setLong(1, account.getAadhar());
			ResultSet resultSet=preparedStatement1.executeQuery();
			while(resultSet.next())
			{
				accNo=resultSet.getInt(1);
				count++;
			}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==1) 
			return accNo;
		else			
			return accNo;
}

	public double login(long accNo,String password) {
		// TODO Auto-generated method stub
		double bal=0;
		int count=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "new", "oracle123");
			PreparedStatement preparedStatement1= con.prepareStatement("select * from customer_details where account_no=? and password=?");
			preparedStatement1.setLong(1, accNo);
			preparedStatement1.setString(2, password);
			ResultSet resultSet=preparedStatement1.executeQuery();
			while(resultSet.next())
			{
				if (accNo == resultSet.getLong(1))
				{
					if (password.equals(resultSet.getString(5)))
					{
						account.setfName(resultSet.getString(2));
						account.setlName(resultSet.getString(3));
						account.setEmailId(resultSet.getString(4));
						account.setPassword(resultSet.getString(5));
						account.setPancard(resultSet.getString(6));
						account.setAadhar(resultSet.getLong(7));
						account.setAddress(resultSet.getString(8));
						account.setMobileNo(resultSet.getLong(9));
						//account.setBalance(resultSet.getLong(10));
						account.setBalance(resultSet.getDouble(10));
						count++;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count==1) {
			return bal;
		} else {
			return 0;
		}
	}

	

}
