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
	public double withdraw(double withdrawAmount,long accNo,Account account1) {
		// TODO Auto-generated method stub
		count=0;
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
				PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where account_no=?");
				preparedStatement.setDouble(1, (account1.getBalance()-withdrawAmount));
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
				return account1.getBalance()-withdrawAmount;
			} else {
				return account1.getBalance();
			}
			
		
	}

	public double deposit(double depositAmount,long accNo,Account account1) {
		// TODO Auto-generated method stub
		
		count=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle123");
			PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where account_no=?");
			preparedStatement.setDouble(1, (account1.getBalance()+depositAmount));
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
			return account1.getBalance()+depositAmount;
		} else {
			return account1.getBalance();
		}
		
	}

	public double showBalance(long accNo) {
		// TODO Auto-generated method stub
		//long accNo1=accNo;
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
		double bal=0,toBal=0;
		int cnt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "new", "oracle123");
			PreparedStatement ps = con.prepareStatement("select * from customer_details where account_no=? ");
			ps.setLong(1, accNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				accNo = rs.getLong(1);
				bal = rs.getDouble(10);
			}
			PreparedStatement ps1 = con.prepareStatement("select * from customer_details where account_no=? ");
			ps1.setLong(1, toAccNo);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				toAccNo = rs1.getLong(1);
				toBal = rs1.getDouble(10);
			}
			if (amountTransfer < bal) {
				bal = bal - amountTransfer;
				toBal = toBal + amountTransfer;
				PreparedStatement ps2 = con
						.prepareStatement("insert into transaction_details values(transaction_id_seq.nextval,?,?,?)");
				ps2.setLong(1, accNo);
				ps2.setLong(2, toAccNo);
				ps2.setDouble(3, amountTransfer);
				int i = ps2.executeUpdate();
				if (i == 1) {
					PreparedStatement ps3 = con
							.prepareStatement("update customer_details set balance= ? where account_no=?");
					ps3.setDouble(1, bal);
					ps3.setLong(2, accNo);
					ps3.executeUpdate();

					PreparedStatement ps4 = con
							.prepareStatement("update customer_details set balance= ? where account_no=?");
					ps4.setDouble(1, toBal);
					ps4.setLong(2, toAccNo);
					ps4.executeUpdate();

					// System.out.println("done");
				}
				cnt++;
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cnt!=0) {
			return bal;
		} else {
			return 0;
		}
	}

}
