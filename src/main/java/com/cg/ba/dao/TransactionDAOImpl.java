package com.cg.ba.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.ba.beans.Account;

public class TransactionDAOImpl implements TransactionDAO {
static double bal=0;
static int count;
Account account=new Account();
	public double withdraw(double withdrawAmount,long accNo) {
		// TODO Auto-generated method stub
		count=0;
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
				PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where accNo=?");
				preparedStatement.setDouble(1, (account.getBalance()-withdrawAmount));
				preparedStatement.setLong(2, accNo);
				int i=preparedStatement.executeUpdate();
				if (i==1) {
					System.out.println("success");
					count++;
				} else {
					System.out.println("failed");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count==1) {
				return account.getBalance()-withdrawAmount;
			} else {
				return account.getBalance();
			}
			
		
	}

	public double deposit(double depositAmount,long accNo) {
		// TODO Auto-generated method stub
		
		count=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
			PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where accNo=?");
			preparedStatement.setDouble(1, (account.getBalance()+depositAmount));
			preparedStatement.setLong(2, accNo);
			int i=preparedStatement.executeUpdate();
			if (i==1) {
				System.out.println("success");
				count++;
			} else {
				System.out.println("failed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count==1) {
			return account.getBalance()+depositAmount;
		} else {
			return account.getBalance();
		}
		
	}

	public double showBalance(long accNo) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
			PreparedStatement preparedStatement= con.prepareStatement("select balance from customer_details where account_no=?");
			preparedStatement.setLong(1, accNo);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				if (accNo == resultSet.getLong(1))
				{
					
					account.setBalance(resultSet.getDouble(10));
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account.getBalance();
	}

	public double fundTransfer(long accNo,long toAccNo,double amountTransfer) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
			PreparedStatement preparedStatement=con.prepareStatement("insert into transaction_details values(transaction_id_seq.nextval,?,?,?)");
			preparedStatement.setLong(1,accNo);
			preparedStatement.setLong(2, toAccNo);
			preparedStatement.setDouble(3, amountTransfer);
			int i=preparedStatement.executeUpdate();
			if(i==1)
			{
				System.out.println("amount transfered");
			}
			else
			{
				System.out.println("failed");
			}
			PreparedStatement ps=con.prepareStatement("Update customer_details set balance=? where account_no=?");
			ResultSet resultSet=ps.executeQuery();
			/*while(resultSet.next())
			{
				bal=resultSet.getDouble(10);
				
			}*/
			bal=(resultSet.getDouble(10)-amountTransfer);
			ps.setDouble(1,bal);
			ps.setLong(2,accNo);
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("Update customer_details set balance=? where account_no=?");
			ResultSet resultSet1=ps1.executeQuery();
			/*while(resultSet.next())
			{
				bal=resultSet.getDouble(10);
				
			}*/
			ps1.setDouble(1,(resultSet.getDouble(10)+amountTransfer));
			ps1.setLong(2,toAccNo);
			ps1.executeUpdate();
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
